// Path: front/pages/room.tsx
import styles from '@/styles/Room.module.scss';
// import PerfectScore from '@/components/room/PerfectScore';
import RoomHeader from '@/components/room/RoomHeader';
import RoomFooter from '@/components/room/RoomFooter';

function Room() {
  return (
    <div className={styles.container}>
      <RoomHeader />
      {/* <PerfectScore /> */}
      <RoomFooter />
    </div>
  );
}

export default Room;
