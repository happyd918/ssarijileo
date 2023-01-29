import { useEffect, useState } from 'react';
import Image from 'next/image';
import { useSelector } from 'react-redux';

import Top from '@/components/common/Top';

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
      <div className={styles.top}>
        <Top />
        {/*<Image*/}
        {/*  src="img/common/common_microphone_image.svg"*/}
        {/*  width={350}*/}
        {/*  height={350}*/}
        {/*  alt="mic"*/}
        {/*  className={styles.mic}*/}
        {/*/>*/}
        {/*<Image*/}
        {/*  src="img/common/common_music_note_image.svg"*/}
        {/*  width={130}*/}
        {/*  height={130}*/}
        {/*  alt="noteA"*/}
        {/*  className={styles.noteA}*/}
        {/*/>*/}
        {/*<Image*/}
        {/*  src="img/common/common_music_note2_image.svg"*/}
        {/*  width={160}*/}
        {/*  height={160}*/}
        {/*  alt="noteB"*/}
        {/*  className={styles.noteB}*/}
        {/*/>*/}
        {/*<Image*/}
        {/*  src="img/common/common_music_note3_image.svg"*/}
        {/*  width={120}*/}
        {/*  height={120}*/}
        {/*  alt="noteC"*/}
        {/*  className={styles.noteC}*/}
        {/*/>*/}
      </div>
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
