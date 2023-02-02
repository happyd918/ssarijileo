import React from 'react';
import Head from 'next/head';
import { Inter } from '@next/font/google';

// 컴포넌트 추가
import Footer from '@/components/common/Footer';
import Header from '@/components/common/Header';
import Index from '@/pages/sing/index';

import styles from '@/styles/Home.module.scss';

const inter = Inter({ subsets: ['latin'] });

function singsample() {
  return (
    <>
      <Head>
        <title>My App</title>
      </Head>
      <main className={`${styles.main} ${inter.className}`}>
        <Header />
        <div className={styles.container}>
          <Index />
        </div>
        <Footer />
      </main>
    </>
  );
}

export default singsample;
