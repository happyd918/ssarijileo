import React, { useEffect } from 'react';
import type { AppProps } from 'next/app';
import { Provider } from 'react-redux';
import store from '@/redux/store';

import '@/styles/global.scss';

function MyApp({ Component, pageProps }: AppProps) {
  useEffect(() => {
    document.body.dataset.theme = localStorage.getItem('theme') || 'light';
  }, []);

  return (
    <Provider store={store}>
      <Component {...pageProps} />
    </Provider>
  );
}

export default MyApp;
