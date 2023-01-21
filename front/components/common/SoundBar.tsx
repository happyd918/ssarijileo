import Image from 'next/image';
import styles from '@/styles/SoundBar.module.scss';

function SoundBar() {
  return (
    <div className={styles.soundBar}>
      <Image
        src="img/common/common_sound_bar_image.svg"
        alt="soundBar"
        width={1000}
        height={60}
        className={styles.img}
      />
      <Image
        src="img/common/common_sound_bar_image.svg"
        alt="soundBar"
        width={1000}
        height={60}
        className={styles.img}
      />
      <Image
        src="img/common/common_sound_bar_image.svg"
        alt="soundBar"
        width={1000}
        height={60}
        className={styles.img}
      />
    </div>
  );
}

export default SoundBar;
