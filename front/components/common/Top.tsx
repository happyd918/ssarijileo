import Title from '@/components/main/Title';
import TopImg from './TopImg';

import styles from '@/styles/common/Top.module.scss';

function Top() {
  return (
    <div className={styles.container}>
      <Title />
      <TopImg />
    </div>
  );
}

export default Top;
