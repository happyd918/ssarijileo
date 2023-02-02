import React, { useEffect, useState } from 'react';
import NextImage from 'next/image';
import { useSelector } from 'react-redux';
import { useComponentSize } from 'react-use-size';
import { useCanvas } from '@/hooks/useCanvas';
import { useAnimation } from '@/hooks/useAnimation';

import Title from '@/components/chart/Title';
import TopImg from '@/components/common/TopImg';

import styles from '@/styles/chart/ChartTop.module.scss';

function ChartTop() {
  const [themeMode, setThemeMode] = useState('light');

  const storeTheme = useSelector<any>(state => state.theme);
  useEffect(() => {
    setThemeMode(localStorage.getItem('theme') || 'light');
  }, [themeMode, storeTheme]);

  const { height, width, ref } = useComponentSize();
  const canvasWidth = width;
  const canvasHeight = height;

  const canvasRef = useCanvas(canvasWidth, canvasHeight);

  const noteImages = (num: number) =>
    `img/chart/${themeMode}/${themeMode}_chart_heart${num}_image.svg`;
  const noteWindow: {
    speed: {
      x: number;
      y: number;
    };
    start: {
      x: number;
      y: number;
    };
    specific: number;
    size: number;
    life: number;
  }[] = [];

  const animate = () => {
    if (!canvasRef.current) return;
    const ctx = canvasRef.current?.getContext('2d');
    if (!ctx) return;
    ctx.clearRect(0, 0, canvasWidth, canvasHeight);

    for (let i = 0; i < noteWindow.length; i++) {
      noteWindow[i].start.x += noteWindow[i].speed.x * 0.7;
      noteWindow[i].start.y += noteWindow[i].speed.y * 0.7;
      noteWindow[i].life -= 1;
      noteWindow[i].size += 0.1;
      if (noteWindow[i].life < 0) {
        noteWindow.splice(i, 1);
      }
    }

    for (let i = 0; i < noteWindow.length; i++) {
      const img = new Image();
      img.src = noteImages(noteWindow[i].specific);
      ctx.drawImage(
        img,
        noteWindow[i].start.x,
        noteWindow[i].start.y,
        noteWindow[i].size,
        noteWindow[i].size,
      );
    }
  };

  const onClickParticle = (e: React.MouseEvent<HTMLCanvasElement>) => {
    const rect = canvasRef.current?.getBoundingClientRect();
    if (!rect) return;
    for (let i = 0; i < 3; i++) {
      const note = {
        speed: {
          x: Math.random() - 0.5,
          y: -1,
        },
        start: {
          x: e.clientX - rect.left - 10,
          y: e.clientY - rect.top - 10,
        },
        specific: Math.floor(Math.random() * 3) + 1,
        life: Math.random() * 50 + 50,
        size: 0,
      };
      note.size = Math.random() * 10 + 15;
      noteWindow.push(note);
    }
  };

  const heartA = `img/chart/${themeMode}/${themeMode}_chart_heart1_image.svg`;
  const heartB = `img/chart/${themeMode}/${themeMode}_chart_heart2_image.svg`;
  const heartC = `img/chart/${themeMode}/${themeMode}_chart_heart3_image.svg`;

  const heartD = `img/chart/${themeMode}/${themeMode}_chart_heart4_image.svg`;

  useAnimation(animate, 0);
  // 월간, 주간, 일간 1등 노래 정보 받기

  const best = [
    {
      // img: 'https://w.namu.la/s/6aa55fa851df9b94d4efd286dd2379c0c16daaccf1342b9ce41f48a2c5a3178463b0b7e997fb4ef3002ed45b6cb50b12f9150f14d3cc7e0b9c8f6a9655f10f0e670ba2aa8526575b19fdd962e6d3e47297811d8a89cb37ed90435896e91b4b31',
      title: 'OMG',
      singer: 'NewJeans',
    },
    {
      // img: 'https://images.genius.com/a336c0b9afbfd6d1bf645ac584bf56b9.1000x1000x1.png',
      title: 'After LIKE',
      singer: 'IVE',
    },
    {
      // img: 'https://cdnimg.melon.co.kr/cm2/album/images/110/78/852/11078852_20221017102947_500.jpg?3afd315fe8957d40a511b9c42aeaf516/melon/optimize/90',
      title: 'Nxde',
      singer: '(여자)아이들',
    },
  ];

  const bestData = best.map(item => {
    return (
      <div className={styles.item}>
        <div>앨범커버</div>
        <div>
          {item.title}-{item.singer}
        </div>
      </div>
    );
  });

  return (
    <div className={styles.container} ref={ref}>
      <canvas
        className={styles.canvas}
        ref={canvasRef}
        onClick={onClickParticle}
      />
      <TopImg />
      <div className={styles.topList}>{bestData}</div>
      <NextImage
        src={heartA}
        width={50}
        height={50}
        alt="noteA"
        className={styles.heartA}
      />
      <NextImage
        src={heartB}
        width={85}
        height={85}
        alt="noteB"
        className={styles.heartB}
      />
      <NextImage
        src={heartC}
        width={70}
        height={70}
        alt="noteC"
        className={styles.heartC}
      />
      <NextImage
        src={heartD}
        width={60}
        height={60}
        alt="noteC"
        className={styles.heartD}
      />
      <Title />
    </div>
  );
}

export default ChartTop;
