import Head from 'next/head';
import { Inter } from '@next/font/google';
import PerfectScore from '@/components/PerfectScore';

import styles from '@/styles/Home.module.scss';

const inter = Inter({ subsets: ['latin'] });

function Home() {
  return (
    <>
      <Head>
        <title>My App</title>
      </Head>
      <main className={`${styles.main} ${inter.className}`}>
        <PerfectScore />
      </main>
    </>
  );
}

export default Home;
