import Image from 'next/image';
import styles from '@/styles/SoundBar.module.scss';


function SoundBar() {
  return (
    <div className={styles.soundBar}>
      <canvas></canvas>
    </div>
  );
}

export default SoundBar;
