import Image from 'next/image';
import styles from '@/styles/Main.module.scss';
import Top from '@/components/common/Top';

import SoundBar from '@/components/common/SoundBar';

function Main() {
  const chartItemA = [
    {
      title: 'Ditto',
      singer: 'NewJeans',
    },
    {
      title: '사건의 지평선',
      singer: '윤하',
    },
    {
      title: 'Hype boy',
      singer: 'NewJeans',
    },
    {
      title: 'OMG',
      singer: 'NewJeans',
    },
    {
      title: 'After LIKE',
      singer: 'IVE(아이브)',
    },
  ];
  const chartItemB = [
    {
      title: 'ANTIFRAGILE',
      singer: 'LE SSERAFIM (르세라핌)',
    },
    {
      title: 'Attention',
      singer: 'NewJeans',
    },
    {
      title: 'LOVE DIVE',
      singer: 'IVE(아이브)',
    },
    {
      title: 'Nxde',
      singer: '여자(아이들)',
    },
    {
      title: 'NOT SORRY (Feat. pH-1) (Prod. by Slom)',
      singer: '이영지',
    },
  ];
  const chartListA = chartItemA.map((item, idx) => (
    <div className={styles.song}>
      <div className={styles.img}>커버</div>
      <div className={styles.rank}>{idx + 1}</div>
      <div className={styles.songInfo}>
        <div className={styles.songTitle}>{item.title}</div>
        <div className={styles.singer}>{item.singer}</div>
      </div>
    </div>
  ));
  const chartListB = chartItemB.map((item, idx) => (
    <div className={styles.song}>
      <div className={styles.img}>커버</div>
      <div className={styles.rank}>{idx + 6}</div>
      <div className={styles.songInfo}>
        <div className={styles.songTitle}>{item.title}</div>
        <div className={styles.singer}>{item.singer}</div>
      </div>
    </div>
  ));
  return (
    <div className={styles.container}>
      <div className={styles.top}>
        <Top />
        <Image
          src="img/common/common_microphone_image.svg"
          width={350}
          height={350}
          alt="mic"
          className={styles.mic}
        />
        <Image
          src="img/common/common_music_note_image.svg"
          width={130}
          height={130}
          alt="noteA"
          className={styles.noteA}
        />
        <Image
          src="img/common/common_music_note2_image.svg"
          width={160}
          height={160}
          alt="noteB"
          className={styles.noteB}
        />
        <Image
          src="img/common/common_music_note3_image.svg"
          width={120}
          height={120}
          alt="noteC"
          className={styles.noteC}
        />
      </div>
      <div>
        <SoundBar />
      </div>
      <div className={styles.chart}>
        <div className={styles.chartTitle}>
          <Image
            src="img/main/main_headphone_image.svg"
            width={40}
            height={40}
            alt="chart"
            className={styles.chartIcon}
          />
          <div className={styles.title}>싸리질러 인기차트</div>
          <Image
            src="img/main/main_headphone_image.svg"
            width={40}
            height={40}
            alt="chart"
            className={styles.chartIcon}
          />
        </div>
        <div className={styles.moreView}>
          <div className={styles.context}>실시간 TOP 100</div>
          <Image
            src="img/common/common_more_view_image.svg"
            width={30}
            height={30}
            alt="moreView"
          />
        </div>
        <div className={styles.chartList}>
          <div className={styles.List}>{chartListA}</div>
          <div className={styles.List}>{chartListB}</div>
        </div>
      </div>
    </div>
  );
}

export default Main;