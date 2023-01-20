import { useEffect, useState } from 'react';
import { lightTheme, darkTheme, ColorTheme } from '@/styles/theme';

function useDarkMode() {
  // 초기 colorTheme는 light
  const [colorTheme, setColorTheme] = useState<ColorTheme>(lightTheme);

  const setMode = (mode: ColorTheme) => {
    if (mode === lightTheme) {
      localStorage.setItem('theme', 'light');
    } else {
      localStorage.setItem('theme', 'dark');
    }
    setColorTheme(mode);
  };

  // 사용자가 toggleColorTheme 시 setMode로 모드 변경
  const toggleColorTheme = () => {
    if (colorTheme === lightTheme) {
      setMode(darkTheme);
    } else {
      setMode(lightTheme);
    }
  };
  // 마운트 시 localStorage에 theme가 있는지 확인
  // 새로고침 시 다크모드 / 라이트모드 바로 적용
  useEffect(() => {
    const localTheme = localStorage.getItem('theme');
    if (localTheme !== null) {
      setColorTheme(darkTheme);
    } else {
      setColorTheme(lightTheme);
    }
  }, []);

  return { colorTheme, toggleColorTheme };
}

export default useDarkMode;
