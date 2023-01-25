import React, { useEffect } from 'react';

import '@/styles/theme.scss';
import type { AppProps } from 'next/app';

declare global {
  interface Window {
    Kakao: any;
  }
}

function MyApp({ Component, pageProps }: AppProps) {
  useEffect(() => {
    // env에 KEY 넣어서 사용 (javascript KEY)
    if (!window.Kakao.isInitialized)
      window.Kakao.init(process.env.NEXT_APP_KAKAO_JS_SDK_KEY);
    console.log(window.Kakao.isInitialized());
  }, []);
  return <Component {...pageProps} />;
}

export default MyApp;
