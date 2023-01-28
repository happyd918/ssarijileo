import styles from '@/styles/common/Spinner.module.scss';

function Spinner() {
  return (
    <div className={styles.containner}>
      <div className={styles.line1}></div>
      <div className={styles.line2}></div>
      <div className={styles.line3}></div>
      {/* <div className={styles.spinner}></div> */}
      <div className={styles.context}>loading</div>
    </div>
  );
}

export default Spinner;
