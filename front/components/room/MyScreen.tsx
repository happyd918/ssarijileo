import React, { useEffect } from 'react';

import styles from '@/styles/room/Screen.module.scss';

function MyScreen(props: any) {
  const videoRef = React.createRef();
  const screen = props.streamManager;

  useEffect(() => {
    if (props && !!videoRef) {
      screen.addVideoElement(videoRef.current);
    }
  }, [screen]);

  return (
    <div className={styles.myScreen}>
      내화면
      <video className={styles.video} autoPlay ref={videoRef} />
    </div>
  );
}

export default MyScreen;
