import React, { useState } from 'react';

import styles from '@/styles/common/ControlBar.module.scss';

function EchoControlBar() {
  const [volume, setVolume] = useState(0.5);
  const changeVolume = (e: React.ChangeEvent<HTMLInputElement>) => {
    setVolume(Number(e.target.value));
    e.target.style.background = `linear-gradient(to right, #00AADF 0%, #00AADF ${Math.round(
      volume * 100,
    )}%, rgb(236, 236, 236) ${Math.round(
      volume * 100,
    )}%, rgb(236, 236, 236) 100%)`;
  };
  return (
    <div id="controls" className={styles.controls}>
      <input
        type="range"
        min={0}
        max={1}
        step={0.01}
        value={volume}
        onChange={changeVolume}
        className={styles.echoBar}
      />
      <div className={styles.number}>{Math.round(volume * 100)}%</div>
    </div>
  );
}

export default EchoControlBar;
