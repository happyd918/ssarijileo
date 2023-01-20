import Head from 'next/head';
import { Inter } from '@next/font/google';
import Footer from '@/components/common/Footer';
import Header from '@/components/common/Header';

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
        <Footer />
      </main>
    </>
  );
}

export default Home;
