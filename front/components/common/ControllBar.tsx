import React, { useRef, useEffect, useState } from 'react';

import styles from '@/styles/common/ControllBar.module.scss';

function ControllBar() {
  const [volume, setVolume] = useState(0.5);
  const gainRef = useRef<GainNode>();
  const sourceRef = useRef<AudioBufferSourceNode>();
  const [isReady, setIsReady] = useState(false);
  const [isStarted, setIsStarted] = useState(false);

  const start = () => {
    sourceRef.current?.start();
    setIsStarted(true);
  };

  const stop = () => {
    sourceRef.current?.stop();
    setIsStarted(false);
  };

  const changeVolume = (e: React.ChangeEvent<HTMLInputElement>) => {
    setVolume(Number(e.target.value));
    gainRef.current?.gain.setValueAtTime(Number(e.target.value), 0);
  };

  useEffect(() => {
    const audioCtx = new AudioContext();
    const gainNode = audioCtx.createGain();
    gainRef.current = gainNode;

    fetch('sounds/test.mp3')
      .then(response => response.arrayBuffer())
      .then(arrayBuffer => audioCtx.decodeAudioData(arrayBuffer))
      .then(audioBuffer => {
        const source = audioCtx.createBufferSource();
        source.buffer = audioBuffer;
        source.connect(gainNode);
        gainNode.connect(audioCtx.destination);
        sourceRef.current = source;
        setIsReady(sourceRef.current !== undefined);
      });
  }, []);

  return (
    <div id="controls" className={styles.controls}>
      <input
        type="button"
        id="start_button"
        className={styles.button}
        value="Start"
        onClick={start}
        disabled={isStarted || !isReady}
      />
      <input
        type="button"
        id="stop_button"
        className={styles.button}
        value="Stop"
        onClick={stop}
        disabled={!isStarted}
      />
      <input
        type="range"
        min={0}
        max={1}
        step={0.01}
        value={volume}
        onChange={changeVolume}
        className={styles.bar}
      />
    </div>
  );
}

export default ControllBar;
