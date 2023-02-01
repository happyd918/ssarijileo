// Path: front/pages/room.tsx
import React from 'react';
import styles from '@/styles/Room.module.scss';
// import PerfectScore from '@/components/room/PerfectScore';
import RoomHeader from '@/components/room/RoomHeader';
import MainScreen from '@/components/room/MainScreen';
import MyScreen from '@/components/room/MyScreen';
import OtherScreen from '@/components/room/OtherScreen';
import RoomFooter from '@/components/room/RoomFooter';

// import RoomController from '@/components/room/RoomController';

function Room() {
  // 참가자 수만큼 생성
  // const member = 5;
  return (
    <div className={styles.container}>
      {/* <RoomController /> */}
      <RoomHeader />
      {/* <PerfectScore /> */}
      <div className={styles.screen}>
        <div className={styles.mainScreen}>
          <MainScreen />
          <div className={styles.singScreen}>노래방화면!!!</div>
        </div>
        <div className={styles.otherScreen}>
          <MyScreen />
          {/* {() => {
            for (let i = 0; i < member; i++) {
              return <OtherScreen />;
            }
          }} */}
          <OtherScreen />
          <OtherScreen />
          <OtherScreen />
          <OtherScreen />
          <OtherScreen />
        </div>
      </div>
      <RoomFooter />
    </div>
  );
}

export default Room;
