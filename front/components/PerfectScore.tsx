import React, { useRef, useState, useEffect } from 'react';

import styles from '../styles/PerfectScore.module.scss';

function PerfectScore() {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const [canvas, setCanvas] = useState<HTMLCanvasElement | null>(null);
  useEffect(() => {
    setCanvas(canvasRef.current);
  }, []);

  const onUser = () => {
    const audioContext = new AudioContext();
    navigator.mediaDevices.getUserMedia({ audio: true }).then(stream => {
      const source = audioContext.createMediaStreamSource(stream);
      const analyser = audioContext.createAnalyser();
      const gainNode = audioContext.createGain();

      source.connect(analyser);
      analyser.connect(gainNode);
      gainNode.connect(audioContext.destination);

      analyser.fftSize = 2048;
      const bufferLength = analyser.frequencyBinCount;
      const dataArray = new Uint8Array(bufferLength);

      if (canvas) {
        const canvasCtx = canvas.getContext('2d');
        if (canvasCtx) {
          const draw = () => {
            const drawVisual = requestAnimationFrame(draw);

            analyser.getByteFrequencyData(dataArray);

            canvasCtx.fillStyle = 'black';
            canvasCtx.fillRect(0, 0, canvas.width, canvas.height);

            const barWidth = (canvas.width / bufferLength) * 2.5;
            let barHeight;
            let x = 0;

            for (let i = 0; i < bufferLength; i++) {
              barHeight = dataArray[i];
              canvasCtx.fillStyle = `rgb(${barHeight + 100},${
                barHeight + 100
              },${barHeight + 100})`;
              canvasCtx.fillRect(
                x,
                canvas.height - barHeight / 2,
                barWidth,
                barHeight / 2,
              );
              x += barWidth + 1;
            }
          };
          draw();
        }
      }
    });
  };

  const onSong = (event: React.MouseEvent<HTMLElement>) => {
    event.preventDefault();

    const audioContext = new AudioContext();

    fetch('sounds/test.mp3')
      .then(response => response.arrayBuffer())
      .then(downloadedBuffer => audioContext.decodeAudioData(downloadedBuffer))
      .then((decodedBuffer: AudioBuffer) => {
        const sourceNode = new AudioBufferSourceNode(audioContext, {
          buffer: decodedBuffer,
        });

        const analyserNode = new AnalyserNode(audioContext);
        const javascriptNode = audioContext.createScriptProcessor(1024, 1, 1);

        sourceNode.connect(audioContext.destination);
        sourceNode.connect(analyserNode);
        analyserNode.connect(javascriptNode);
        javascriptNode.connect(audioContext.destination);

        sourceNode.start(0); // Play the sound now

        // Set up the event handler that is triggered every time enough samples have been collected
        // then trigger the audio analysis and draw the results
        javascriptNode.onaudioprocess = () => {
          // Read the frequency values
          const amplitudeArray = new Uint8Array(analyserNode.frequencyBinCount);

          // Get the time domain data for this sample
          analyserNode.getByteTimeDomainData(amplitudeArray);
          if (audioContext.state === 'running' && canvas) {
            // Draw the time domain in the canvas
            requestAnimationFrame(() => {
              // Get the canvas 2d context
              const canvasCTX = canvas.getContext('2d');
              // Clear the canvas
              if (canvasCTX) {
                canvasCTX.clearRect(0, 0, canvas.width, canvas.height);

                // Draw the amplitude inside the canvas
                for (let i = 0; i < amplitudeArray.length; i++) {
                  const value = amplitudeArray[i] / 256;
                  const y = canvas.height - canvas.height * value;
                  canvasCTX.fillStyle = 'white';
                  canvasCTX.fillRect(i, y, 1, 1);
                }
              }
            });
          }
        };
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
