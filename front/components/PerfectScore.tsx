import React, { useRef, useEffect, useState } from 'react';

import { PitchDetector } from 'pitchy';
import useAnimationFrame from '@/hooks/useAnimationFrame';
import * as data from '@/constants/PerfectScoreData';

import styles from '../styles/PerfectScore.module.scss';

function PerfectScore() {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const dataArrayRef = useRef<Float32Array>(new Float32Array(data.BUFFER_SIZE));
  const pitchDetectorRef = useRef<PitchDetector<Float32Array>>(
    PitchDetector.forFloat32Array(data.BUFFER_SIZE),
  );
  const analyserRef = useRef<AnalyserNode>();
  const noteWindowRef = useRef<number[]>(
    new Array(data.NOTE_WINDOW_SIZE / 2).fill(-1),
  );
  const particleWindowRef = useRef<object[]>([]);
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
      ctx.fillStyle = '#000000';
      ctx.fillRect(0, 0, canvasRef.current.width, canvasRef.current.height);

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
      const note = freqToNote(pitch);
      console.log(note);
      const noteWindow = noteWindowRef.current;
      noteWindow.push(note);
      if (noteWindow.length > data.NOTE_WINDOW_SIZE / 2) {
        noteWindow.shift();
      }

      const speed = {
        x: -5 + Math.random() * 10,
        y: -5 + Math.random() * 10,
      };
      const radius = 5 + Math.random() * 5;
      const life = 30 + Math.random() * 10;
      const color = Number.isNaN(note) ? 'white' : data.PARTICLE_COLOR;
      const particle = {
        speed,
        radius,
        life,
        color,
      };
      const particleWindow = particleWindowRef.current;
      particleWindow.push(particle);
      if (particleWindow.length > data.PARTICLE_WINDOW_SIZE) {
        particleWindow.shift();
      }
      // const noteCharactorTable = data.NOTE_CHARTER_TABLE;

      // 음정 출력
      let x = 0;
      const barWidth = canvasRef.current.width / data.NOTE_WINDOW_SIZE;
      for (let i = 0; i < noteWindow.length; i++) {
        const barHeight = noteWindow[i] * 5;
        const y = canvasRef.current.height - barHeight;
        ctx.fillStyle = `blue`;
        ctx.fillRect(x, y, barWidth, 10);
        x += barWidth;

        ctx.arc(
          canvasRef.current.width / 2,
          y,
          particle.radius,
          0,
          Math.PI * 2,
        );
        ctx.fillStyle = particle.color;
        ctx.fill();
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
        width="800"
        height="600"
        ref={canvasRef}
      />
      <div id="controls">
        <input type="button" id="start_button" value="Start" onClick={start} />
        &nbsp; &nbsp;
        <input type="button" id="stop_button" value="Stop" onClick={stop} />
        <br />
        <br />
        <output id="msg" />
      </div>
    </>
  );
}

export default PerfectScore;
