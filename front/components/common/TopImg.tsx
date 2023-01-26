import Image from 'next/image';
import styles from '@/styles/TopImg.module.scss';

function TopImg() {
  return (
    <Image
      src="img/common/light/light_background_img.svg"
      width={798}
      height={701}
      alt="background"
      className={styles.background}
    />
  );
}

export default TopImg;
