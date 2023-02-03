import React from 'react';
import Link from 'next/link';

import styles from '@/styles/common/Title.module.scss';

function Title(props: { main: string; sub: string }) {
  const { main, sub } = props;
  return (
    <div className={styles.title}>
      <div className={styles.top}>
        <div className={styles.point}>singing & enjoy </div>
        <hr className={styles.line} />
      </div>
      <div className={styles.main}>{main}</div>
      <div className={styles.sub}>{sub}</div>
      <div className={styles.btn}>
        <Link href="sing/" key="sing" className={styles.pages}>
          <button className={styles.singBtn} type="button">
            노래 부르러 GO
          </button>
        </Link>
        <Link href="chart/" key="chart" className={styles.pages}>
          <button className={styles.chartBtn} type="button">
            실시간 인기곡
          </button>
        </Link>
      </div>
    </div>
  );
}

export default Title;
