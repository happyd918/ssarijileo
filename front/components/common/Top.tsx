import { useEffect, useRef } from 'react';

import Title from '@/components/main/Title';
import TopImg from './TopImg';
import { useWave } from '@/hooks/useAnimation';

import styles from '@/styles/common/Top.module.scss';
import Image from 'next/image';

function Top() {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const draw = () => {
    const canvas = canvasRef.current;
    if (!canvas) return;
    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    const width = canvas.width;
    const height = canvas.height;
  };

  useWave(() => {
    draw();
  }, []);

  return (
    <div className={styles.container}>
      <Title />
      <TopImg />
      <Image
        src="img/common/common_microphone_image.svg"
        width={350}
        height={350}
        alt="mic"
        className={styles.mic}
      />
      <canvas
        className={styles.canvas}
        ref={canvasRef}
        width={window.innerWidth}
        height={window.innerHeight}
      />
    </div>
  );
}

export default Top;
