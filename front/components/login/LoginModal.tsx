import Image from 'next/image';
import styles from '@/styles/LoginModal.module.scss';

function LoginModal({ setModalOpen }: any) {
  const closeModal = () => {
    setModalOpen(false);
  };
  return (
    <div className={styles.back}>
      <div className={styles.container}>
        <div className={styles.top}>
          <button type="button" className={styles.close} onClick={closeModal}>
            <Image
              src="img/common/common_close_image.svg"
              alt="close"
              width={25}
              height={25}
            />
          </button>
        </div>
        <div className={styles.main}>
          <div className={styles.title}>Log in</div>
          <div className={styles.icon}>
            {/* 해당 div 클릭 시 onClick 이벤트로 소셜 로그인 연동 */}
            <Image
              src="img/login/login_kakao_image.svg"
              alt="kakao"
              width={80}
              height={80}
              className={styles.kakao}
            />
            <div className={styles.iconName}>Kakako</div>
          </div>
          <div className={styles.terms}>
            회원가입 없이 소셜 계정을 통해 바로 이용 가능하며 첫 로그인시 <br />
            <b>이용약관</b>및 <b>개인정보처리방침</b> 동의로 간주됩니다.
          </div>
        </div>
        <div className={styles.bottom}>
          <button
            type="button"
            className={styles.closeBtn}
            onClick={closeModal}
          >
            닫기
          </button>
        </div>
      </div>
    </div>
  );
}

export default LoginModal;
