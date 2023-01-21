import React, { useRef, useEffect, useCallback } from 'react';

export const useAnimationFrame = (
  callback: (_: number) => void,
  deps?: React.DependencyList | undefined,
) => {
  // Use useRef for mutable variables that we want to persist
  // without triggering a re-render on their change
  // Make sure the effect runs only once
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

export const useEventListener = () => {};
