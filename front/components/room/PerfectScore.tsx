import React, { useRef, useEffect, useState } from 'react';

import { PitchDetector } from 'pitchy';
import useAnimationFrame from '@/hooks/useAnimationFrame';
import * as data from '@/constants/PerfectScoreData';

import styles from '@/styles/PerfectScore.module.scss';

function PerfectScore() {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const dataArrayRef = useRef<Float32Array>(new Float32Array(data.BUFFER_SIZE));
  const pitchDetectorRef = useRef<PitchDetector<Float32Array>>(
    PitchDetector.forFloat32Array(data.BUFFER_SIZE),
  );
  const analyserRef = useRef<AnalyserNode>();
  const noteWindowRef = useRef<number[]>(
    new Array(data.NOTE_WINDOW_SIZE * data.DISPLAY_PERCENTAGE).fill(-1),
  );
  const particles: {
    speed: {
      x: number;
      y: number;
    };
    startX: number;
    startY: number;
    radius: number;
    color: string;
    life: number;
  }[] = [];
  const [isStarted, setIsStarted] = useState(false);

  const start = () => {
    setIsStarted(true);
  };

  const stop = () => {
    setIsStarted(false);
  };

  const isSilentBuffer = (buffer: Float32Array) => {
    let ret = 0;
    for (let i = 0; i < buffer.length; i++) {
      ret += buffer[i] * buffer[i];
    }
    return Math.sqrt(ret / buffer.length) < data.SILENCE_THRESHOLD;
  };

  // 메인 로직
  useAnimationFrame(
    (deltaTime: number) => {
      if (
        !canvasRef.current ||
        !dataArrayRef.current ||
        !pitchDetectorRef.current ||
        !analyserRef.current ||
        !isStarted ||
        deltaTime === undefined
      )
        return;
      // 캔버스
      const ctx = canvasRef.current.getContext('2d');
      if (!ctx) return;
      // ctx.fillStyle = '#000000';
      // ctx.fillRect(0, 0, canvasRef.current.width, canvasRef.current.height);
      // ctx.beginPath();
      // ctx.moveTo(canvasRef.current.width / 2, 0);
      // ctx.lineTo(canvasRef.current.width / 2, canvasRef.current.height);
      // ctx.strokeStyle = '#ffffff';
      // ctx.stroke();

      const backgroudImage = new Image();
      backgroudImage.src = 'img/perfectscore/backgound.jpeg';
      ctx.drawImage(
        backgroudImage,
        0,
        0,
        canvasRef.current.width,
        canvasRef.current.height,
      );

      // 음정 분석
      const dataArray = dataArrayRef.current;
      const pitchDetector = pitchDetectorRef.current;
      const analyser = analyserRef.current;

      analyser.getFloatTimeDomainData(dataArray);
      const [pitch] = isSilentBuffer(dataArray)
        ? [-1, -1]
        : pitchDetector.findPitch(dataArray, analyser.context.sampleRate);

      const freqToNote = (freq: number) => {
        return Math.round(12 * (Math.log(freq / 440.0) / Math.log(2))) + 69;
      };
      // 음정 분석 결과를 노트윈도우에 저장
      let note = freqToNote(pitch);
      if (note < 40 || note > 90) note = -1;
      const noteWindow = noteWindowRef.current;
      const flag = note === noteWindow[noteWindow.length - 1];
      noteWindow.push(note);
      if (noteWindow.length > data.NOTE_WINDOW_SIZE * data.DISPLAY_PERCENTAGE) {
        noteWindow.shift();
      }

      // const noteCharactorTable = data.NOTE_CHARTER_TABLE;
      // const noteCharactor = noteCharactorTable[note % 12];
      // const octave = Math.floor(note / 12) - 1;
      // console.log(noteCharactor, octave);

      // 파티클 생성
      const makeParticle = (particleNum: number) => {
        if (!canvasRef.current) return;
        const particleY =
          canvasRef.current.height - noteWindow[noteWindow.length - 1] * 5;
        for (let i = 0; i < particleNum; i++) {
          const speed = {
            x: Math.random() * 2,
            y: Math.random() * 2 - 1,
          };
          const radius = Math.random();
          const color = data.PARTICLE_COLOR;
          const startX =
            Math.random() * 2 +
            canvasRef.current.width * data.DISPLAY_PERCENTAGE +
            2;
          const startY = particleY + Math.random() * 10;
          const life = Math.random() * 5 + 5;
          particles.push({
            speed,
            startX,
            startY,
            radius,
            color,
            life,
          });
        }
      };

      // 파티클 유지 여부
      if (!flag) {
        particles.splice(0, particles.length);
        makeParticle(data.PARTICLE_COUNT);
      } else {
        for (let i = 0; i < particles.length; i++) {
          const particle = particles[i];
          particle.startX += particle.speed.x;
          particle.startY += particle.speed.y;
          particle.life -= 1;
          if (particle.life < 0) {
            particles.splice(i, 1);
          }
        }
        if (particles.length < data.PARTICLE_COUNT) {
          makeParticle(data.PARTICLE_COUNT - particles.length);
        }
      }

      // 파티클 그리기
      for (let i = 0; i < particles.length; i++) {
        ctx.beginPath();
        ctx.fillStyle = particles[i].color;
        ctx.arc(
          particles[i].startX,
          particles[i].startY,
          particles[i].radius,
          0,
          Math.PI * 2,
        );
        ctx.fill();
      }

      // 음정 출력
      let x = 0;
      const barWidth = canvasRef.current.width / data.NOTE_WINDOW_SIZE;
      for (let i = 0; i < noteWindow.length; i++) {
        const barHeight = 10;
        const y = canvasRef.current.height - noteWindow[i] * 5;
        if (!Number.isNaN(y)) {
          const gradient = ctx.createLinearGradient(
            x,
            y,
            x + barWidth + 1,
            y + barHeight,
          );
          gradient.addColorStop(0, data.NOTE_COLOR.skyblue);
          gradient.addColorStop(1, '#fff5f5');
          ctx.fillStyle = gradient;
        }

        ctx.beginPath();
        if (
          i !== 0 &&
          i !== noteWindow.length - 1 &&
          noteWindow[i] !== noteWindow[i - 1] &&
          noteWindow[i] !== noteWindow[i + 1]
        ) {
          ctx.roundRect(x, y, barWidth + 1, barHeight, [5, 5, 5, 5]);
        } else if (i !== 0 && noteWindow[i] !== noteWindow[i - 1]) {
          ctx.roundRect(x, y, barWidth + 1, barHeight, [5, 0, 0, 5]);
        } else if (
          i !== noteWindow.length - 1 &&
          noteWindow[i] !== noteWindow[i + 1]
        ) {
          ctx.roundRect(x, y, barWidth + 1, barHeight, [0, 5, 5, 0]);
        } else {
          ctx.rect(x, y, barWidth + 1, barHeight);
        }
        ctx.fill();
        x += barWidth;
      }

      // const noteCharactor = noteCharactorTable[noteWindow[i] % 12];
      // const octave = Math.floor(noteWindow[i] / 12) - 1;
      // ctx.font = '8px serif';
      // ctx.fillStyle = `white`;
      // ctx.fillText(`${noteCharactor}${octave}`, x, 10);
    },
    [canvasRef, dataArrayRef, pitchDetectorRef, analyserRef, isStarted],
  );

  // analyser 세팅
  useEffect(() => {
    const audioCtx = new AudioContext();
    const analyser = audioCtx.createAnalyser();
    analyserRef.current = analyser;

    analyser.minDecibels = data.MIN_DB;
    analyser.smoothingTimeConstant = data.SMOOTHING_TIME_CONSTANT;
    analyser.fftSize = data.FFT_SIZE;

    navigator.mediaDevices.getUserMedia({ audio: true }).then(stream => {
      const source = audioCtx.createMediaStreamSource(stream);

      source.connect(analyser);
    });
  }, [analyserRef, isStarted]);

  return (
    <>
      <canvas
        className={styles.canvas}
        width={data.CANVAS_WIDTH}
        height={data.CANVAS_HEIGHT}
        ref={canvasRef}
      />
      <div id="controls">
        <input
          type="button"
          id="start_button"
          className={styles.button}
          value="Start"
          onClick={start}
        />
        &nbsp; &nbsp;
        <input
          type="button"
          id="stop_button"
          className={styles.button}
          value="Stop"
          onClick={stop}
        />
        <br />
        <br />
        <output id="msg" />
      </div>
    </>
  );
}

export default PerfectScore;
