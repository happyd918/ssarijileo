import classNames from 'classnames';

import styles from '@/styles/TodayChart.module.scss';

function TodayChartItem(props: {
  item: { rank: number; title: string; singer: string };
}) {
  const { item } = props;
  const titleClassName = classNames({
    [styles.songTitle]: true,
    [styles.isLong]: item.title.length > 18,
  });
  return (
    <div className={styles.song}>
      <div className={styles.img}>커버</div>
      <div className={styles.rank}>{item.rank}</div>
      <div className={styles.songInfo}>
        <div className={titleClassName}>{item.title}</div>
        <div className={styles.singer}>{item.singer}</div>
      </div>
    </div>
  );
}

export default TodayChartItem;
