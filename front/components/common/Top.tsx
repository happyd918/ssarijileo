// import Image from 'next/image';
import Title from '@/components/common/Title';
import styles from '@/styles/Top.module.scss';
import TopImg from './TopImg';

function Top() {
  return (
    <div className={styles.container}>
      <Title />
      <TopImg />
    </div>
  );
}

export default Top;
