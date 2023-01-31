import Header from '@/components/common/Header';
import Footer from '@/components/common/Footer';

import styles from '@/styles/Layout.module.scss';

function Layout(props: any) {
  return (
    <div className={styles.main}>
      <Header />
      {props.children}
      <Footer />
    </div>
  );
}

export default Layout;
