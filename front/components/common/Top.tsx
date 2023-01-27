// import Image from 'next/image';
import Title from '@/components/main/Title';
import styles from '@/styles/common/Top.module.scss';
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
