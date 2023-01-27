import React, { useEffect, useRef, useState } from 'react';

import * as data from '@/constants/SoundBarData';
import { useWave } from '@/hooks/useAnimation';

import styles from '@/styles/SoundBar.module.scss';
import { BAR_NUM } from '@/constants/SoundBarData';

function SoundBar() {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const noteTableRef = useRef<number[]>([]);
  const [noteTable, setNoteTable] = useState<number[]>([]);
  const DOWN_FLAG = new Array(BAR_NUM).fill(false);

  const draw = () => {
    if (!canvasRef.current) return;
    const ctx = canvasRef.current.getContext('2d');
    if (!ctx) return;

    const width = data.WIDTH;
    const height = data.HEIGHT;
    ctx.clearRect(0, 0, width, height);
    const barWidth = width / data.BAR_NUM;
    const barHeight = height / data.NOTE_HEIGHT;
    let x = 0;
    let y = height;

    for (let i = 0; i < data.BAR_NUM; i++) {
      for (let j = 0; j <= noteTable[i] + 1; j++) {
        const gradient = ctx.createLinearGradient(
          x,
          y,
          x + barWidth - 10,
          y + barHeight / 2,
        );
        const startColor = data.COLOR.map(
          (color, index) => color + data.PLUS[index] * j,
        );
        gradient.addColorStop(
          0,
          `rgba(${startColor[0]}, ${startColor[1]}, ${startColor[2]})`,
        );
        const endColor = data.COLOR.map(
          (color, index) => color + data.PLUS[index] * (j + 1),
        );
        gradient.addColorStop(
          1,
          `rgba(${endColor[0]}, ${endColor[1]}, ${endColor[2]})`,
        );
        ctx.fillStyle = gradient;
        ctx.beginPath();
        ctx.roundRect(x, y, barWidth - 10, barHeight / 2, [5, 5, 5, 5]);
        ctx.fill();
        y -= barHeight;
      }
      x += barWidth;
      y = height;
      // noteTable[i] = DOWN_FLAG[i]
      //   ? noteTable[i] + Math.ceil(Math.random() * 3)
      //   : noteTable[i] - Math.ceil(Math.random() * 3);
      noteTable[i] = DOWN_FLAG[i]
        ? noteTable[i] + ((i % 3) + 1)
        : noteTable[i] - ((i % 3) + 1);
      if (noteTable[i] < 0) {
        noteTable[i] = 1;
        DOWN_FLAG[i] = true;
      } else if (noteTable[i] > data.NOTE_HEIGHT) {
        noteTable[i] = data.NOTE_HEIGHT - 1;
        DOWN_FLAG[i] = false;
      }
      // noteTable[i] =
      //   (noteTable[i] + Math.floor(Math.random() * 7) - 3) % data.NOTE_HEIGHT;
      // if (noteTable[i] < 0) noteTable[i] = 1;
    }
  };
  useWave(() => {
    draw();
  }, [noteTable]);

  useEffect(() => {
    for (let i = 0; i < data.NOTE_NUM.length; i++) {
      noteTableRef.current[i] = Math.ceil(Math.random() * data.NOTE_HEIGHT);
      if (i !== 0 && noteTableRef.current[i] === noteTableRef.current[i - 1]) {
        noteTableRef.current[i] =
          (noteTableRef.current[i] + data.NOTE_HEIGHT / 2) % data.NOTE_HEIGHT;
      }
    }
    noteTableRef.current = data.NOTE_NUM.map(() =>
      Math.ceil(Math.random() * data.NOTE_HEIGHT),
    );
    setNoteTable(noteTableRef.current);
  }, []);

  return (
    <div className={styles.soundBar}>
      <canvas
        width={data.WIDTH}
        height={data.HEIGHT}
        ref={canvasRef}
        className={styles.canvas}
      />
    </div>
  );
}

export default SoundBar;
