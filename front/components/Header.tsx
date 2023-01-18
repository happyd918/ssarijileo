import Image from 'next/image';
import Link from 'next/link';
// import { Inter } from '@next/font/google'
import styles from '@/styles/Header.module.scss';

// const inter = Inter({ subsets: ['latin'] });

function Header() {
  return (
    <header className={styles.header}>
      <div>
        <Link href="/">
          <Image
            src="./icon/light_logo.svg"
            alt="logoFail"
            width={100}
            height={100}
          />
        </Link>
      </div>
      <div className={styles.pagesdiv}>
        <Link className={styles.pages} href="/singingroom">
          노래방
        </Link>
        <Link className={styles.pages} href="/chart">
          인기차트
        </Link>
        <Link className={styles.pages} href="/contest">
          노래자랑
        </Link>
        <Link className={styles.pages} href="/mysong">
          마이뮤직
        </Link>
      </div>
      <div>
        <Image
          src="./icon/light_mode_icon.svg"
          alt="logoFail"
          width={25}
          height={25}
          className={styles.icon}
        />
        <Image
          src="./icon/light_alarm_icon.svg"
          alt="logoFail"
          width={25}
          height={25}
          className={styles.icon}
        />
        <Image
          src="./icon/light_settings_icon.svg"
          alt="logoFail"
          width={25}
          height={25}
          className={styles.icon}
        />
        <Image
          src="./icon/light_google_icon.svg"
          alt="profileImage"
          width={25}
          height={25}
          className={styles.icon}
        />
      </div>
    </header>
  );
}

export default Header;
