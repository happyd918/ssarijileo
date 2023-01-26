import Image from 'next/image';
import styles from '@/styles/Main.module.scss';
import Top from '@/components/common/Top';

function Main() {
  return (
    <div className={styles.container}>
      <Top />
      <Image
        src="img/common/common_microphone_image.svg"
        width={350}
        height={350}
        alt="mic"
        className={styles.mic}
      />
      <Image
        src="img/common/common_music_note_image.svg"
        width={130}
        height={130}
        alt="noteA"
        className={styles.noteA}
      />
      <Image
        src="img/common/common_music_note2_image.svg"
        width={160}
        height={160}
        alt="noteB"
        className={styles.noteB}
      />
      <Image
        src="img/common/common_music_note3_image.svg"
        width={120}
        height={120}
        alt="noteC"
        className={styles.noteC}
      />
    </div>
  );
}

export default Main;
