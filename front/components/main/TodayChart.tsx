import Image from 'next/image';
import classNames from 'classnames';
import styles from '@/styles/TodayChart.module.scss';

function TodayChart() {
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
  const chartListA = chartItemA.map((item, idx) => {
    const titleClassName = classNames({
      [styles.songTitle]: true,
      [styles.isLong]: item.title.length > 18,
    });
    return (
      <div className={styles.song}>
        <div className={styles.img}>커버</div>
        <div className={styles.rank}>{idx + 1}</div>
        <div className={styles.songInfo}>
          <div className={titleClassName}>{item.title}</div>
          <div className={styles.singer}>{item.singer}</div>
        </div>
      </div>
    );
  });
  const chartListB = chartItemB.map((item, idx) => {
    const titleClassName = classNames({
      [styles.songTitle]: true,
      [styles.isLong]: item.title.length > 18,
    });
    return (
      <div className={styles.song}>
        <div className={styles.img}>커버</div>
        <div className={styles.rank}>{idx + 6}</div>
        <div className={styles.songInfo}>
          <div className={titleClassName}>{item.title}</div>
          <div className={styles.singer}>{item.singer}</div>
        </div>
      </div>
    );
  });
  return (
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
        <div className={styles.list}>{chartListA}</div>
        <div className={styles.list}>{chartListB}</div>
      </div>
    </div>
  );
}

export default TodayChart;
