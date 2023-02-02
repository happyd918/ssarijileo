import router from 'next/router';
import styles from '@/styles/main/Title.module.scss';

function Title() {
  return (
    <div className={styles.title}>
      <div className={styles.top}>
        <div className={styles.point}>singing & enjoy </div>
        <hr className={styles.line} />
      </div>
      <div className={styles.main}>
        현재 가장 인기 있는 <br />
        곡들이에요
      </div>
      <div className={styles.sub}>
        싸리질러 사용자들의 노래 재생 시간, 조회수, 사용 정보 등을 <br />
        이용해 자체적으로 매긴 순위입니다.
      </div>
      <div className={styles.btn}>
        <button
          className={styles.singBtn}
          type="button"
          onClick={() => router.push('/')}
        >
          노래 부르러 GO
        </button>
        <button
          className={styles.chartBtn}
          type="button"
          onClick={() => router.push('/')}
        >
          실시간 인기곡
        </button>
      </div>
    </div>
  );
}

export default Title;
