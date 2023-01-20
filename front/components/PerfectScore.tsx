import React, { useRef, useState, useEffect } from 'react';

import styles from '../styles/PerfectScore.module.scss';

function PerfectScore() {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const [canvas, setCanvas] = useState<HTMLCanvasElement | null>(null);
  useEffect(() => {
    setCanvas(canvasRef.current);
  }, []);

  const onUser = () => {
    // 오디오 처리, 주파수 분석 인스턴스
    const audioContext = new AudioContext();
    const analyser = audioContext.createAnalyser();

    // 음향 정보 가져오기
    navigator.mediaDevices
      .getUserMedia({ audio: true })
      .then((stream: MediaStream) => {
        // 오디오 소스 연결
        const source = audioContext.createMediaStreamSource(stream);
        source.connect(analyser);
        //사운드 출력
        analyser.connect(audioContext.destination);
        // 주파수가 담긴 데이터 배열 생성
        // analyser.fftSize = 2048;
        // const bufferLength = analyser.frequencyBinCount;
        // const dataArray = new Uint8Array(bufferLength);
        const dataArray = new Float32Array(2048);
        analyser.getFloatTimeDomainData(dataArray);

        // FFT를 이용해 주파수를 음정으로 변환
        const isSilentBuffer = (buffer: Float32Array) => {
          let N = buffer.length,
            ret = 0;
          for (let i = 0; i < N; i++) {
            ret += buffer[i] * buffer[i];
          }
          return (ret = Math.sqrt(ret / N)) < 0.01;
        };

        const trimBuffer = (buffer: Float32Array, minFreq: number) => {
          let N = buffer.length,
            startIdx = 0,
            endIdx = N - 1;
          for (let i = 0; i < N / 2; i++) {
            if (Math.abs(buffer[i]) < minFreq) {
              startIdx = i;
              break;
            }
          }
          for (let i = endIdx; i > N / 2; i--) {
            if (Math.abs(buffer[i]) < minFreq) {
              endIdx = i;
              break;
            }
          }
          return buffer.slice(startIdx, endIdx);
        };

        const getPitch = (data: Float32Array, rate: number) => {
          if (isSilentBuffer(data)) return 0;
          let sound = trimBuffer(data, 0.2),
            N = sound.length,
            arr = new Array(N).fill(0);
          for (let i = 0; i < N; i++) {
            for (let j = 0; j < N; j++) {
              arr[i] = arr[i] + sound[j] * Math.cos((2 * Math.PI * i * j) / N);
            }
          }
          let downCnt = 0;
          for (; arr[downCnt] > arr[downCnt + 1]; downCnt++);
          let maxV = -1,
            maxIdx = -1;
          for (let i = downCnt; i < N; i++) {
            arr[i] > maxV && ((maxV = arr[i]), (maxIdx = i));
          }
          let ret = (arr[maxIdx - 1] + arr[maxIdx + 1] - 2 * arr[maxIdx]) / 2,
            avg = (arr[maxIdx - 1] + arr[maxIdx + 1]) / 2;
          return (
            (ret = ret && (maxIdx = maxIdx - avg / (2 * ret))), rate / maxIdx
          );
        };

        let pitch = getPitch(dataArray, audioContext.sampleRate);
      });
  };

  const onStop = (event: React.MouseEvent<HTMLElement>) => {
    event.preventDefault();
  };

  return (
    <>
      <h1>PerfectScore</h1>
      <canvas
        className={styles.canvas}
        width="512"
        height="256"
        ref={canvasRef}
      ></canvas>
      <div id="controls">
        <input type="button" id="start_button" value="Start" onClick={onUser} />
        &nbsp; &nbsp;
        <input type="button" id="stop_button" value="Stop" onClick={onStop} />
        <br />
        <br />
        <output id="msg"></output>
      </div>
    </>
  );
}

export default PerfectScore;
