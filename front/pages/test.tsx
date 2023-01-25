import React, { useRef, useEffect, useState } from 'react';

import { PitchDetector } from 'pitchy';
import { useAnimationFrame } from '@/hooks/useAnimationFrame';
import * as data from '@/constants/PerfectScoreData';

import styles from '../styles/PerfectScore.module.scss';

function PerfectScore() {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const dataArrayRef = useRef<Float32Array>(new Float32Array(data.BUFFER_SIZE));
  const pitchDetectorRef = useRef<PitchDetector<Float32Array>>(
    PitchDetector.forFloat32Array(data.BUFFER_SIZE),
  );
  const analyserRef = useRef<AnalyserNode>();
  const sourceRef = useRef<AudioBufferSourceNode>();
  const noteWindowRef = useRef<number[]>([]);
  const [isReady, setIsReady] = useState(false);
  const [isStarted, setIsStarted] = useState(false);

  const start = () => {
    sourceRef.current?.start();
    setIsStarted(true);
  };

  const stop = () => {
    sourceRef.current?.stop();
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

    fetch('sounds/test.mp3')
      .then(response => response.arrayBuffer())
      .then(arrayBuffer => audioCtx.decodeAudioData(arrayBuffer))
      .then(audioBuffer => {
        const source = audioCtx.createBufferSource();
        source.buffer = audioBuffer;
        source.connect(analyser);
        analyser.connect(audioCtx.destination);
        sourceRef.current = source;
        setIsReady(sourceRef.current !== undefined);
      });
  }, []);

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
          disabled={isStarted || !isReady}
        />
        &nbsp; &nbsp;
        <input
          type="button"
          id="stop_button"
          className={styles.button}
          value="Stop"
          onClick={stop}
          disabled={!isStarted}
        />
        <br />
        <br />
        <output id="msg" />
      </div>
    </>
  );
}

export default PerfectScore;
