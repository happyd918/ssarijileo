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
      noteWindow[i].start.x += noteWindow[i].speed.x;
      noteWindow[i].start.y += noteWindow[i].speed.y;
      noteWindow[i].size -= 1;
      noteWindow[i].life -= 1;
      if (noteWindow[i].life < 0) {
        noteWindow.splice(i, 1);
      }
    }

    if (noteWindow.length < 0) {
      const note = {
        speed: {
          x: Math.random() * -5,
          y: Math.random() * -5 - 5,
        },
        start: {
          x: Math.random() * canvasWidth,
          y: 300,
        },
        specific: Math.floor(Math.random() * 3) + 1,
        life: Math.random() * 10 + 10,
        size: 0,
      };
      note.size = note.life + 30;
      noteWindow.push(note);
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

  const heartA = `img/chart/${themeMode}/${themeMode}_chart_heart1_image.svg`;
  const heartB = `img/chart/${themeMode}/${themeMode}_chart_heart2_image.svg`;
  const heartC = `img/chart/${themeMode}/${themeMode}_chart_heart3_image.svg`;
  const heartD = `img/chart/${themeMode}/${themeMode}_chart_heart4_image.svg`;

  useAnimation(animate, 0);

  // 월간, 주간, 일간 1등 노래 정보 받기
  const best = [
    {
      img: 'https://i1.sndcdn.com/artworks-Ah2Fl2dwIlA2xWeb-kWN55A-t240x240.jpg',
      title: 'OMG',
      singer: 'NewJeans',
    },
    {
      img: 'https://image.bugsm.co.kr/album/images/500/40789/4078936.jpg',
      title: 'After LIKE',
      singer: 'IVE',
    },
    {
      img: 'http://image.genie.co.kr/Y/IMAGE/IMG_ALBUM/083/072/254/83072254_1665976983718_1_600x600.JPG',
      title: 'Nxde',
      singer: '(여자)아이들',
    },
  ];

  const bestData = best.map(item => {
    return (
      <div className={styles.item}>
        <NextImage
          src={item.img}
          width={180}
          height={180}
          alt="album"
          className={styles.albumCover}
        />
        <div>
          {item.title}-{item.singer}
        </div>
      </div>
    );
  });

  const onClickParticle = (e: React.MouseEvent<HTMLCanvasElement>) => {
    const rect = canvasRef.current?.getBoundingClientRect();
    if (!rect) return;
    for (let i = 0; i < 3; i++) {
      const note = {
        speed: {
          x: Math.random() * 4 - 2,
          y: Math.random() * 4 - 2,
        },
        start: {
          x: e.clientX - rect.left,
          y: e.clientY - rect.top,
        },
        specific: Math.floor(Math.random() * 3) + 1,
        life: Math.random() * 10 + 10,
        size: 0,
      };
      note.size = note.life + 10;
      noteWindow.push(note);
    }
  };

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
