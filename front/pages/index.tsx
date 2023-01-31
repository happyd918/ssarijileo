import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import Head from 'next/head';
import { Inter } from '@next/font/google';

import IconTop from '@/components/main/IconTop';
import SoundBar from '@/components/common/SoundBar';
import TodayChart from '@/components/main/TodayChart';
import TodayContest from '@/components/main/TodayContest';
import Team from '@/components/main/Team';

import styles from '@/styles/Home.module.scss';

const inter = Inter({ subsets: ['latin'] });

function Home() {
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
    <>
      <Head>
        <title>My App</title>
      </Head>
      <main className={`${styles.main} ${inter.className}`}>
        <IconTop />
        <div className={styles.container}>
          <SoundBar />
          <TodayChart />
          <SoundBar />
          <TodayContest />
          <SoundBar />
          <Team img={img} />
        </div>
      </main>
    </>
  );
}

export default Home;
