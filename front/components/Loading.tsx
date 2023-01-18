import Image from 'next/image';
import styles from '@/styles/Loading.module.scss';

function Loading() {
  return (
    <div className={styles.loading}>
      <div className={styles.loadImg}>
        <Image
          src="img/ssari/light/light_ssari_loading_image.svg"
          alt="load img"
          width={200}
          height={200}
        />
      </div>
      <div className={styles.loadContext}>
        방을 만드는 중입니다. 잠시만 기다려 주세요.
      </div>
    </div>
  );
}

export default Loading;
