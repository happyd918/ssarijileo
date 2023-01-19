import React, { createContext } from 'react';
import type { AppProps } from 'next/app';
import { Global } from '@emotion/react';
import { lightTheme, darkTheme, ColorTheme } from '@/styles/theme';

// export default function App({ Component, pageProps }: AppProps) {
//   return <Component {...pageProps} />;
// }

// createContext 타입지정
interface ContextProps {
  colorTheme: ColorTheme;
  toggleColorTheme: () => void;
}

// Context 생성
export const ThemeContext = createContext<ContextProps>({
  colorTheme: lightTheme, // 초기 값으로 lightTheme를 넣어줍니다.
  toggleColorTheme: () => {
    // light || dark mode를 토글합니다.
    return null;
  },
});

function MyApp({ Component, pageProps }: AppProps) {
  // ❗️useDarkMode hook을 통해 theme과 toggleTheme return;
  const { theme, toggleTheme } = useDarkMode();

  return (
    // Provider은 context의 변화를 알리는 역할을 합니다.
    // toggleTheme를 통해 theme이 변경되면 하위 컴포넌트들은 모두 리렌더링됩니다.
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      <Component {...pageProps} />
    </ThemeContext.Provider>
  );
}
