import Image from 'next/image';
import TodayChartItem from '@/components/main/TodayChartItem';

import styles from '@/styles/main/TodayChart.module.scss';

function TodayChart() {
  const chartItemA = [
    {
      rank: 1,
      title: 'Ditto',
      singer: 'NewJeans',
    },
    {
      rank: 2,
      title: '사건의 지평선',
      singer: '윤하',
    },
    {
      rank: 3,
      title: 'Hype boy',
      singer: 'NewJeans',
    },
    {
      rank: 4,
      title: 'OMG',
      singer: 'NewJeans',
    },
    {
      rank: 5,
      title: 'After LIKE',
      singer: 'IVE(아이브)',
    },
  ];
  const chartItemB = [
    {
      rank: 6,
      title: 'ANTIFRAGILE',
      singer: 'LE SSERAFIM (르세라핌)',
    },
    {
      rank: 7,
      title: 'Attention',
      singer: 'NewJeans',
    },
    {
      rank: 8,
      title: 'LOVE DIVE',
      singer: 'IVE(아이브)',
    },
    {
      rank: 9,
      title: 'Nxde',
      singer: '여자(아이들)',
    },
    {
      rank: 10,
      title: 'NOT SORRY (Feat. pH-1) (Prod. by Slom)',
      singer: '이영지',
    },
  ];
  const chartListA = chartItemA.map(item => {
    return <TodayChartItem key={item.rank} item={item} />;
  });
  const chartListB = chartItemB.map(item => {
    return <TodayChartItem key={item.rank} item={item} />;
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
