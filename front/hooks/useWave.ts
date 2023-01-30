import React, { useCallback, useEffect, useRef } from 'react';

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
