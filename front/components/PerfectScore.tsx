import React, { useRef, useEffect, useState } from 'react';

import { PitchDetector } from 'pitchy';
import { useAnimationFrame } from 'hooks';
import {
  FFT_SIZE,
  BUFFER_SIZE,
  SMOOTHING_TIME_CONSTANT,
  MIN_DB,
  NOTE_WINDOW_SIZE,
} from 'constants/AudioSettings';

import styles from '../styles/PerfectScore.module.scss';

function PerfectScore() {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const dataArrayRef = useRef<Float32Array>(new Float32Array(BUFFER_SIZE));
  const pitchDetectorRef = useRef<PitchDetector<Float32Array>>(
    PitchDetector.forFloat32Array(BUFFER_SIZE),
  );
  const analyserRef = useRef<AnalyserNode>();
  const noteWindowRef = useRef<number[]>([]);
  const [isStarted, setIsStarted] = useState(false);

  const start = () => {
    setIsStarted(true);
  };

  const stop = () => {
    setIsStarted(false);
  };

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
      const ctx = canvasRef.current.getContext('2d');
      if (!ctx) return;
      ctx.fillStyle = '#000000';
      ctx.fillRect(0, 0, canvasRef.current.width, canvasRef.current.height);

      const dataArray = dataArrayRef.current;
      const pitchDetector = pitchDetectorRef.current;
      const analyser = analyserRef.current;

      analyser.getFloatTimeDomainData(dataArray);
      const [pitch] = pitchDetector.findPitch(
        dataArray,
        analyser.context.sampleRate,
      );

      const freqToNote = (freq: number) => {
        return Math.round(12 * (Math.log(freq / 440.0) / Math.log(2))) + 69;
      };

      const note = freqToNote(pitch);
      const noteWindow = noteWindowRef.current;
      noteWindow.push(note);
      if (noteWindow.length > NOTE_WINDOW_SIZE) {
        noteWindow.shift();
      }

      const noteCharactorTable = [
        'C',
        'C#',
        'D',
        'D#',
        'E',
        'F',
        'F#',
        'G',
        'G#',
        'A',
        'A#',
        'B',
      ];
      let x = 0;
      const barWidth = canvasRef.current.width / NOTE_WINDOW_SIZE;
      for (let i = 0; i < noteWindow.length; i++) {
        const noteCharactor = noteCharactorTable[noteWindow[i] % 12];

        const octave = Math.floor(noteWindow[i] / 12) - 1;

        const barHeight = noteWindow[i] * 2;
        const y = canvasRef.current.height - barHeight;
        ctx.fillStyle = `red`;
        ctx.fillRect(x, y, barWidth, barHeight);
        x += barWidth;

        ctx.font = '8px serif';
        ctx.fillStyle = `white`;
        ctx.fillText(`${noteCharactor}${octave}`, x, 10);
      }
    },
    [canvasRef, dataArrayRef, pitchDetectorRef, analyserRef, isStarted],
  );
  useEffect(() => {
    const audioCtx = new AudioContext();
    const analyser = audioCtx.createAnalyser();
    analyserRef.current = analyser;

    analyser.minDecibels = MIN_DB;
    analyser.smoothingTimeConstant = SMOOTHING_TIME_CONSTANT;
    analyser.fftSize = FFT_SIZE;

    navigator.mediaDevices.getUserMedia({ audio: true }).then(stream => {
      const source = audioCtx.createMediaStreamSource(stream);

      source.connect(analyser);
      // analyser.connect(audioCtx.destination);
    });
  }, [analyserRef, isStarted]);

  return (
    <>
      <h1>PerfectScore</h1>
      <canvas
        className={styles.canvas}
        width="512"
        height="256"
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
