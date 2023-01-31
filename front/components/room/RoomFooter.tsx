import Image from 'next/image';
import styles from '@/styles/room/RoomFooter.module.scss';

function RoomFooter() {
  return (
    <div className={styles.container}>
      <div className={styles.section}>
        <div className={styles.btn}>
          {/* 노래 중에는 버튼 바꾸기 !!! */}
          <button type="button" className={styles.friend}>
            친구초대
          </button>
          <button type="button" className={styles.reserv}>
            예약하기
          </button>
        </div>
        <div className={styles.sound}>
          <Image
            src="img/ssari/ssari_clap_image.svg"
            width={40}
            height={39}
            alt="clap"
            className={styles.clap}
          />
          <Image
            src="img/ssari/ssari_tambourine_image.svg"
            width={40}
            height={37}
            alt="tambourine"
            className={styles.tambourine}
          />
        </div>
        <Image
          src="img/ssari/ssari_controller_image.svg"
          width={45}
          height={40}
          alt="controller"
          className={styles.controller}
        />
      </div>
      <Image
        src="img/ssari/ssari_chat_image.svg"
        width={42}
        height={39}
        alt="chat"
        className={styles.chat}
      />
    </div>
  );
}

export default RoomFooter;
