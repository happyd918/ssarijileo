import Image from 'next/image';
import styles from '@/styles/room/RoomController.module.scss';

function RoomController({ setModalOpen }: any) {
  return (
    <div className={styles.layout}>
      <input
        type="button"
        className={styles.back}
        onClick={() => {
          setModalOpen(false);
        }}
      />
      <div className={styles.container}>
        <div className={styles.pitch}>
          <Image
            src="img/ssari/ssari_pitch_image.svg"
            width={72}
            height={72}
            alt="pitch"
            className={styles.icon}
          />
          <div className={styles.context}>음정</div>
        </div>
        <div className={styles.beat}>
          <Image
            src="img/ssari/ssari_beat_image.svg"
            width={72}
            height={72}
            alt="beat"
            className={styles.icon}
          />
          <div className={styles.context}>박자</div>
        </div>
        <div className={styles.volume}>
          <Image
            src="img/ssari/ssari_volume_image.svg"
            width={72}
            height={72}
            alt="volume"
            className={styles.icon}
          />
          <div className={styles.context}>볼륨</div>
        </div>
        <div className={styles.musicNote}>
          <Image
            src="img/ssari/ssari_music_note_image.svg"
            width={72}
            height={72}
            alt="musicNote"
            className={styles.icon}
          />
          <div className={styles.context}>악보 표출</div>
        </div>
        <div className={styles.jump}>
          <Image
            src="img/ssari/ssari_jump_image.svg"
            width={72}
            height={72}
            alt="jump"
            className={styles.icon}
          />
          <div className={styles.context}>간주점프</div>
        </div>
        <Image
          src="img/ssari/ssari_setting_image.svg"
          width={52}
          height={52}
          alt="pitch"
          className={styles.setting}
        />
      </div>
    </div>
  );
}

export default RoomController;
