import React, { useState, useEffect } from 'react';
import Image from 'next/image';
import Link from 'next/link';

import styles from '@/styles/Header.module.scss';

function Header() {
  const [themeMode, setThemeMode] = useState('light');
  const [checked, setChecked] = useState(false);

  const changeMode = () => {
    if (themeMode === 'light') {
      setThemeMode('dark');
      localStorage.setItem('theme', 'dark');
    } else {
      setThemeMode('light');
      localStorage.setItem('theme', 'light');
    }
    setChecked(!checked);
  };

  useEffect(() => {
    document.body.dataset.theme = themeMode;
  }, [themeMode]);

  useEffect(() => {
    const theme = localStorage.getItem('theme') || 'light';
    setThemeMode(theme);
    theme === 'dark' ? setChecked(true) : setChecked(false);
  }, []);

  // header 에 들어갈 menu 리스트
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

  const icons = themeMode === 'dark' ? DarkIcons : LightIcons;

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
          <label className={styles.switch}>
            <input
              type="checkbox"
              onChange={changeMode}
              className={styles.checkbox}
              checked={checked}
              id="toggleSwitch"
            />
          </label>
          <Image src={icons.mode} alt="mode" width={20} height={20} />
        </div>
        <div className={styles.icon}>
          <Image src={icons.alarm} alt="alarm" width={20} height={20} />
          <div className={styles.profile}>
            <Image src={icons.profile} alt="profile" width={25} height={25} />
          </div>
        </div>
      </div>
    </header>
  );
}

export default Header;
