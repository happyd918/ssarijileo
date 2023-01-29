import React, { useRef, useEffect, useCallback } from 'react';
import { timestamp } from 'rxjs';

export const useAnimationFrame = (
  callback: (deltaTime: number) => void,
  deps?: React.DependencyList | undefined,
) => {
  const requestRef = useRef<number>();
  const previousTimeRef = useRef<number>();

  const animate = useCallback(
    (time: number) => {
      if (previousTimeRef.current !== undefined) {
        const deltaTime = time - previousTimeRef.current;
        callback(deltaTime);
      }
      previousTimeRef.current = time;
      requestRef.current = requestAnimationFrame(animate);
    },
    [callback],
  );

  const depsArray = deps || [];
  useEffect(() => {
    requestRef.current = requestAnimationFrame(animate);
    return () => {
      if (requestRef.current !== undefined) {
        cancelAnimationFrame(requestRef.current);
      }
    };
  }, [requestRef, animate, ...depsArray]);
};

export const useWave = (
  callback: () => void,
  deps?: React.DependencyList | undefined,
) => {
  const requestRef = useRef<number>();
  let start = 0;

  const animate = useCallback(
    (timestamp: number) => {
      if (!start) start = timestamp;
      const progress = timestamp - start;
      if (progress > 50) {
        callback();
        start = timestamp;
      }
      requestRef.current = requestAnimationFrame(animate);
    },
    [callback],
  );

  const depsArray = deps || [];
  useEffect(() => {
    requestRef.current = requestAnimationFrame(animate);
    return () => {
      if (requestRef.current !== undefined) {
        cancelAnimationFrame(requestRef.current);
      }
    };
  }, [requestRef, animate, ...depsArray]);
};
