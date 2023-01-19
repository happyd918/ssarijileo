import Image from 'next/image';
import Link from 'next/link';
import { useState, ChangeEvent } from 'react';

// import { Inter } from '@next/font/google'
import styles from '@/styles/Header.module.scss';

// const inter = Inter({ subsets: ['latin'] });

function Header() {
  // 다크모드, 라이트모드 변경
  const [switchState, setSwitchState] = useState(false);
  // eslint-disable-next-line no-undef
  function handleOnChange(e: ChangeEvent<HTMLInputElement>) {
    console.log('---', e.target.checked);
    setSwitchState(!switchState);
  }

  // header에 들어갈 menu 리스트
  const headerMenu = [
    {
      name: '노래방',
      link: '/',
    },
    {
      name: '인기차트',
      link: '/',
    },
    {
      name: '노래자랑',
      link: '/',
    },
    {
      name: '마이뮤직',
      link: '/',
    },
  ];

  // menu 리스트 요소에 대한 태그 생성
  const headerMenus = headerMenu.map(item => (
    <Link className={styles.pages} href={item.link}>
      {item.name}
    </Link>
  ));

  return (
    <header className={styles.header}>
      <div className={styles.logo}>
        <Link href="/">
          <Image
            src="icon/header/light/light_logo.svg"
            alt="logoFail"
            width={70}
            height={70}
          />
        </Link>
      </div>
      <div className={styles.menu}>{headerMenus}</div>
      <div className={styles.icons}>
        <div className={styles.icon}>
          <input
            type="checkbox"
            id="toggle"
            checked={switchState}
            onChange={handleOnChange}
            hidden
          />
          <label htmlFor="toggle" className={styles.toggleSwitch}>
            <input
              type="checkbox"
              className={styles.toggleButton}
              checked={switchState}
              onChange={handleOnChange}
            />
          </label>
          <Image
            src="icon/header/light/light_mode_icon.svg"
            alt="mode"
            width={20}
            height={20}
          />
        </div>
        <div className={styles.icon}>
          <Image
            src="icon/header/light/light_alarm_icon.svg"
            alt="alarm"
            width={20}
            height={20}
          />
          <div className={styles.profile}>
            <Image
              src="icon/header/light/light_profile_icon.svg"
              alt="prifile"
              // className={styles.profileIcon}
              width={25}
              height={25}
            />
          </div>
        </div>
      </div>
    </header>
  );
}

export default Header;
