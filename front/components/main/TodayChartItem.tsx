import classNames from 'classnames';

import styles from '@/styles/TodayChart.module.scss';

function TodayChartItem(props: {
  item: { rank: number; title: string; singer: string };
}) {
  const titleClassName = classNames({
    [styles.songTitle]: true,
    [styles.isLong]: props.item.title.length > 18,
  });
  return (
    <div className={styles.song}>
      <div className={styles.img}>커버</div>
      <div className={styles.rank}>{props.item.rank}</div>
      <div className={styles.songInfo}>
        <div className={titleClassName}>{props.item.title}</div>
        <div className={styles.singer}>{props.item.singer}</div>
      </div>
    </div>
  );
}

export default TodayChartItem;
