import React from 'react';
import Head from 'next/head';
import { Inter } from '@next/font/google';
// 컴포넌트
import Footer from '@/components/common/Footer';
import Header from '@/components/common/Header';
// import PerfectScore from '@/components/room/PerfectScore';
// import SoundBar from '@/components/common/SoundBar';
// import ErrorComp from '@/components/error/ErrorComp';
// import Main from '@/components/main/main';
// import RoomList from '@/components/sing/RoomList';

import Sing from '@/components/sing/sing';
// import Main from '@/components/main/main';

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
          {/* <PerfectScore /> */}
          {/* <SoundBar /> */}
          {/* <ErrorComp /> */}
          {/* <RoomList /> */}
          <Sing />
          {/* <Main /> */}
        </div>
        <Footer />
      </main>
    </>
  );
}

export default Home;
