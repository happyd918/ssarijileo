import React, { useEffect, useRef } from 'react';
import styles from '@/styles/SoundBar.module.scss';

import * as data from '@/constants/SoundBarData';
import { NOTE_HEIGHT } from '@/constants/SoundBarData';

function SoundBar() {
  const canvasRef = useRef<HTMLCanvasElement>(null);

  // let a = 0;
  // const noteTable = data.NOTE_NUM.map((note, idx) => {
  //   note = note + a;
  //   a = (a + 1) % 3;
  //   return note;
  // });
  const noteTable = data.NOTE_NUM.map(() =>
    Math.ceil(Math.random() * NOTE_HEIGHT),
  );

  const draw = () => {
    if (!canvasRef.current) return;
    const ctx = canvasRef.current.getContext('2d');
    if (!ctx) return;

    const width = data.WIDTH;
    const height = data.HEIGHT;
    ctx.clearRect(0, 0, width, height);
    const barWidth = width / data.BAR_NUM;
    const barHeight = height / NOTE_HEIGHT;
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
      noteTable[i] =
        (noteTable[i] + Math.ceil(Math.random() * 8) - 4) % NOTE_HEIGHT;
      if (noteTable[i] < 0) noteTable[i] = 0;
    }
  };
  setInterval(() => {
    requestAnimationFrame(draw);
  }, 100);

  return (
    <div className={styles.soundBar}>
      <canvas width={data.WIDTH} height={data.HEIGHT} ref={canvasRef} />
    </div>
  );
}

export default SoundBar;
