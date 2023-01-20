// import React, { createContext } from 'react';
// // import { Global } from '@emotion/react';
// import { lightTheme, darkTheme, ColorTheme } from '@/styles/theme';
// import useDarkMode from '@/hooks/common/useDarkMode';

// import { useEffect } from 'react';
import '@/styles/global.scss';
import type { AppProps } from 'next/app';

// // createContext 타입지정
// interface ContextProps {
//   colorTheme: ColorTheme;
//   toggleColorTheme: () => void;
// }

// // context 생성
// export const ThemeContext = createContext<ContextProps>({
//   // default 테마 설정
//   colorTheme: lightTheme,
//   // light||dark 토글
//   toggleColorTheme: () => {
//     return null;
//   },
// });

function MyApp({ Component, pageProps }: AppProps) {
  // const { theme, toggleTheme } = useDarkMode();
  // useEffect(() => {
  //   document.documentElement.setAttribute('data-theme', 'light');
  // }, []);
  return (
    // 하위 컴포넌트에 전달,,, value는 필수 값
    <Component {...pageProps} />
  );
}

export default MyApp;
