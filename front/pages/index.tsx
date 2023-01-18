import Head from 'next/head';
import Image from 'next/image';
import { Inter } from '@next/font/google';
import styles from '@/styles/Home.module.scss';
import Footer from '@/components/Footer';

function Home() {
  return (
    <>
      <Head>
        <title>My App</title>
      </Head>
      <main className={styles.main}>
        <Footer />
      </main>
    </>
  );
}

export default Home;
