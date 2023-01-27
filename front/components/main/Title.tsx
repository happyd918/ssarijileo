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
        집에서 즐기는 <br />
        랜선 노래방 싸리질러
      </div>
      <div className={styles.sub}>
        집에서 친구들과 화상채팅으로 노래를 부르고 <br />
        다양한 게임까지 즐길 수 있습니다.
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
