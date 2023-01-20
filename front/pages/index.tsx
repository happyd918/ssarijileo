import Head from 'next/head';
import { Inter } from '@next/font/google';
import { useEffect } from 'react';
import styles from '@/styles/Home.module.scss';
import Header from '@/components/common/Header';

// import Loading from '@/components/Loading';
// import Title from '@/components/common/Title';

import Footer from '@/components/common/Footer';

const inter = Inter({ subsets: ['latin'] });

function Home() {
  useEffect(() => {
    document.documentElement.setAttribute('data-theme', 'light');
  }, []);
  return (
    <>
      <Head>
        <title>My App</title>
      </Head>
      <main className={`${styles.main} ${inter.className}`}>
        <Header />
        {/* <Loading /> */}
        {/* <Title /> */}
        <Footer />
      </main>
    </>
  );
}

export default Home;
