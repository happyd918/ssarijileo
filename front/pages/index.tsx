import React from 'react';
import Head from 'next/head';
import { Inter } from '@next/font/google';

// 컴포넌트
import Footer from '@/components/common/Footer';
import Header from '@/components/common/Header';
// import Main from '@/components/main/Main';
import Sing from '@/components/sing/sing';

import styles from '@/styles/Home.module.scss';

const inter = Inter({ subsets: ['latin'] });

function Home() {
  return (
    <>
      <Head>
        <title>My App</title>
      </Head>
      <main className={`${styles.main} ${inter.className}`}>
        <Header />
        <div className={styles.container}>
          {/* <Main /> */}
          <Sing />
        </div>
        <Footer />
      </main>
    </>
  );
}

export default Home;
