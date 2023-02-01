import styles from '@/styles/room/VolumeController.module.scss';

import MicControlBar from '../common/MicControlBar';
import EchoControlBar from '../common/EchoControlBar';

function VolumeController({ setVolumeModal }: any) {
  return (
    <div className={styles.layout}>
      <input
        type="button"
        className={styles.back}
        onClick={() => {
          setVolumeModal(false);
        }}
      />
      <div className={styles.container}>
        <div className={styles.context}>
          <div className={styles.title}>마이크 볼륨</div>
          <div className={styles.bar}>
            <MicControlBar />
          </div>
          <div className={styles.beat}>
            <div className={styles.text}>0%</div>
            <div className={styles.text}>100%</div>
          </div>
          <div className={styles.title}>에코</div>
          <div className={styles.bar}>
            <EchoControlBar />
          </div>
          <div className={styles.beat}>
            <div className={styles.text}>0%</div>
            <div className={styles.text}>100%</div>
          </div>
        </div>
        <button
          className={styles.btn}
          type="button"
          onClick={() => {
            setVolumeModal(false);
          }}
        >
          저장
        </button>
      </div>
    </div>
  );
}

export default VolumeController;
