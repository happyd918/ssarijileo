import React, { useEffect } from 'react';

import '@/styles/theme.scss';
import type { AppProps } from 'next/app';

// 카카오 전역 객체 선언
declare global {
  interface Window {
    Kakao: any;
  }
}

function MyApp({ Component, pageProps }: AppProps) {
  useEffect(() => {
    // 카카오 SDK 초기화
    if (!window.Kakao.isInitialized()) {
      window.Kakao.init(process.env.NEXT_PUBLIC_KAKAO_JS_SDK_KEY);
      console.log(window.Kakao.isInitialized());
    }
  }, []);
  return <Component {...pageProps} />;
}

export default MyApp;
