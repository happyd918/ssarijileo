import { useEffect, useState } from 'react';
import Image from 'next/image';
import { useSelector } from 'react-redux';

import IconTop from '@/components/common/IconTop';
import SoundBar from '@/components/common/SoundBar';
import TodayChart from '@/components/main/TodayChart';
import TodayContest from '@/components/main/TodayContest';

import styles from '@/styles/main/Main.module.scss';

function Main() {
  const [themeMode, setThemeMode] = useState('light');

  const storeTheme = useSelector<any>(state => state.theme);
  useEffect(() => {
    setThemeMode(localStorage.getItem('theme') || 'light');
  }, [themeMode, storeTheme]);

  const img = {
    team: `img/common/${themeMode || 'light'}/${
      themeMode || 'light'
    }_team_info_image.svg`,
  };

  return (
    <div className={styles.container}>
      <IconTop />
      <SoundBar />
      <TodayChart />
      <SoundBar />
      <TodayContest />
      <SoundBar />
      <div className={styles.team}>
        <Image src={img.team} width={1300} height={800} alt="team" />
      </div>
    </div>
  );
}

export default Main;
