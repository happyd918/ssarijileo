import React, { useRef, useEffect } from 'react';

export const useCanvas = (
  canvasWidth: number,
  canvasHeight: number,
  callback: (ctx: CanvasRenderingContext2D) => void,
  delay: number,
  deps?: React.DependencyList | undefined,
) => {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const requestRef = useRef<number>();
  const previousTimeRef = useRef<number>(0);
  const depsArray = deps || [];

  useEffect(() => {
    const canvas = canvasRef.current;
    const ctx = canvas?.getContext('2d');

    const setCanvas = () => {
      const devicePixelRatio = window.devicePixelRatio || 1;

      if (canvas && ctx) {
        canvas.style.width = `${canvasWidth}px`;
        canvas.style.height = `${canvasHeight}px`;

        canvas.width = canvasWidth * devicePixelRatio;
        canvas.height = canvasHeight * devicePixelRatio;

        ctx.scale(devicePixelRatio, devicePixelRatio);
      }
    };
    if (canvasWidth !== -1 && canvasHeight !== -1) {
      setCanvas();
    }

    const animate = (timestamp: number) => {
      if (previousTimeRef.current !== undefined) {
        const progress = timestamp - previousTimeRef.current;
        if (ctx && progress > delay) {
          callback(ctx);
          previousTimeRef.current = timestamp;
        }
      }
      requestRef.current = requestAnimationFrame(animate);
    };
    animate(0);

    return () => {
      if (requestRef.current !== undefined) {
        cancelAnimationFrame(requestRef.current);
      }
    };
  }, [canvasWidth, canvasHeight, callback, ...depsArray]);

  return canvasRef;
};
