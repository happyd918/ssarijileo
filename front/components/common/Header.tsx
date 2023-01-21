import { useState, useEffect } from 'react';

import Image from 'next/image';
import Link from 'next/link';
import React from 'react';
import styles from '@/styles/Header.module.scss';

function Header() {
  const [themeMode, setThemeMode] = useState(false);
  const changeMode = (e: any) => {
    e.preventDefault();
    setThemeMode(!themeMode);
  };

  useEffect(() => {
    document.body.dataset.theme = themeMode ? 'dark' : 'light';
  }, [themeMode]);

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

  const LightIcons = {
    logo: 'icon/header/light/light_logo.svg',
    mode: 'icon/header/light/light_mode_icon.svg',
    alarm: 'icon/header/light/light_alarm_icon.svg',
    profile: 'icon/header/light/light_profile_icon.svg',
  };

  const DarkIcons = {
    logo: 'icon/header/dark/dark_logo.svg',
    mode: 'icon/header/dark/dark_mode_icon.svg',
    alarm: 'icon/header/dark/dark_alarm_icon.svg',
    profile: 'icon/header/dark/dark_profile_icon.svg',
  };

  const icons = themeMode ? DarkIcons : LightIcons;

  // menu 리스트 요소에 대한 태그 생성
  const headerMenus = headerMenu.map(item => (
    <Link key={item.name} className={styles.pages} href={item.link}>
      {item.name}
    </Link>
  ));

  return (
    <header className={styles.header}>
      <div className={styles.logo}>
        <Link href="/">
          <Image src={icons.logo} alt="logoFail" width={70} height={70} />
        </Link>
      </div>
      <div className={styles.menu}>{headerMenus}</div>
      <div className={styles.icons}>
        <div className={styles.icon}>
          <input
            type="checkbox"
            id="toggle"
            checked={themeMode}
            onChange={changeMode}
            hidden
          />
          <label htmlFor="toggle" className={styles.toggleSwitch}>
            <input
              type="checkbox"
              className={styles.toggleButton}
              checked={themeMode}
              onChange={changeMode}
            />
          </label>
          <Image src={icons.mode} alt="mode" width={20} height={20} />
        </div>
        <div className={styles.icon}>
          <Image src={icons.alarm} alt="alarm" width={20} height={20} />
          <div className={styles.profile}>
            <Image
              src={icons.profile}
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
