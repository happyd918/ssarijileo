import Image from 'next/image';
import styles from '@/styles/sing/Sing.module.scss';

import Top from '@/components/common/Top';
import SoundBar from '@/components/common/SoundBar';
import RoomList from '@/components/sing/RoomList';

function Index() {
  return (
    <div className={styles.container}>
      <div className={styles.top}>
        <Top />
        <Image
          src="img/common/common_play_image.svg"
          width={600}
          height={358}
          alt="img"
          className={styles.topImg}
        />
        <Image
          src="img/common/common_music_note1_image.svg"
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
      <SoundBar />
      <div className={styles.title}>노래방 입장하기</div>
      <RoomList />
      <SoundBar />
    </div>
  );
}

export default Index;
