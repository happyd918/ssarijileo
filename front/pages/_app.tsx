import React, { useEffect } from 'react';
import type { AppProps } from 'next/app';
import { Provider } from 'react-redux';

import { persistStore } from 'redux-persist';
import { PersistGate } from 'redux-persist/integration/react';
import store from '@/redux/store';

import '@/styles/global.scss';

export const persistor = persistStore(store);

function MyApp({ Component, pageProps }: AppProps) {
  useEffect(() => {
    document.body.dataset.theme = localStorage.getItem('theme') || 'light';
  }, []);

  return (
    <Provider store={store}>
      <PersistGate persistor={persistor}>
        <Component {...pageProps} />
      </PersistGate>
    </Provider>
  );
}

export default MyApp;
