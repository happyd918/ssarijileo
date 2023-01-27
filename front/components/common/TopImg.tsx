import { useEffect, useState } from 'react';
import Image from 'next/image';
import { useSelector } from 'react-redux';
import styles from '@/styles/TopImg.module.scss';

function TopImg() {
  const [themeMode, setThemeMode] = useState('light');

  const storeTheme = useSelector<any>(state => state.theme);
  useEffect(() => {
    setThemeMode(localStorage.getItem('theme') || 'light');
  }, [themeMode, storeTheme]);

  const img = {
    background: `img/common/${themeMode || 'light'}/${
      themeMode || 'light'
    }_background_img.svg`,
  };
  return (
    <Image
      src={img.background}
      width={798}
      height={701}
      alt="background"
      className={styles.background}
      priority={true}
    />
  );
}

export default TopImg;
