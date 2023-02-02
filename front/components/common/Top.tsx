import Title from '@/components/main/Title';
import TopImg from './TopImg';

import styles from '@/styles/common/Top.module.scss';

function Top() {
  return (
    <div className={styles.container}>
      <TopImg />
      <Title />
    </div>
  );
}

export default Top;
