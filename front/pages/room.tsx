// Path: front/pages/room.tsx
import styles from '@/styles/Room.module.scss';
// import PerfectScore from '@/components/room/PerfectScore';
import RoomHeader from '@/components/room/RoomHeader';

function Room() {
  return (
    <div className={styles.container}>
      <RoomHeader />
      {/* <PerfectScore /> */}
    </div>
  );
}

export default Room;
