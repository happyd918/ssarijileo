import React, { useState, useEffect, useCallback } from 'react';
import Image from 'next/image';
import Link from 'next/link';

import { useDispatch } from 'react-redux';
import { setTheme } from '@/redux/store/themeSlice';

import styles from '@/styles/Header.module.scss';
import LoginModal from '@/components/login/LoginModal';

function Header() {
  const [modalOpen, setModalOpen] = useState(false);
  const [themeMode, setThemeMode] = useState('');
  const [checked, setChecked] = useState(false);
  const dispatch = useDispatch();

  const changeMode = useCallback(() => {
    setChecked(!checked);
    setThemeMode(themeMode === 'light' ? 'dark' : 'light');
    localStorage.setItem('theme', themeMode === 'light' ? 'dark' : 'light');
    dispatch(setTheme(themeMode));
  }, [checked, themeMode]);

  // if (themeMode === 'light') {
  //   setThemeMode('dark');
  //   dispatch(setTheme('dark'));
  //   localStorage.setItem('theme', 'dark');
  // } else if (themeMode === 'dark') {
  //   setThemeMode('light');
  //   dispatch(setTheme('light'));
  //   localStorage.setItem('theme', 'light');
  // }
  // setChecked(!checked);

  useEffect(() => {
    document.body.dataset.theme = themeMode;
  }, [themeMode]);

  useEffect(() => {
    const theme = localStorage.getItem('theme') || 'light';
    setThemeMode(theme);
    dispatch(setTheme(themeMode));
    setChecked(theme === 'dark');
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

  const icons = {
    logo: `icon/header/${themeMode || 'light'}/${
      themeMode || 'light'
    }_logo.svg`,
    mode: `icon/header/${themeMode || 'light'}/${
      themeMode || 'light'
    }_mode_icon.svg`,
    alarm: `icon/header/${themeMode || 'light'}/${
      themeMode || 'light'
    }_alarm_icon.svg`,
    profile: `icon/header/${themeMode || 'light'}/${
      themeMode || 'light'
    }_profile_icon.svg`,
  };

  // menu 리스트 요소에 대한 태그 생성
  const headerMenus = headerMenu.map(menu => (
    <Link href={menu.link} key={menu.name} className={styles.pages}>
      {menu.name}
    </Link>
  ));

  // 로그인 버튼 클릭 시 모달창 Open
  const showModal = () => {
    setModalOpen(true);
  };

  return (
    <header className={styles.header}>
      {modalOpen && <LoginModal setModalOpen={setModalOpen} />}
      <div className={styles.logo}>
        <Link href="/">
          <Image src={icons.logo} alt="logoFail" width={70} height={70} />
        </Link>
      </div>
      <div className={styles.menu}>{headerMenus}</div>
      <div className={styles.icons}>
        <div className={styles.icon}>
          <label className={styles.switch} htmlFor="toggleSwitch">
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
        {/* 로그아웃 상태 */}
        <div className={styles.login}>
          <button type="button" className={styles.loginBtn} onClick={showModal}>
            Login
          </button>
        </div>
        {/* 로그인 상태 */}
        {/* <div className={styles.icon}>
          <Image src={icons.alarm} alt="alarm" width={20} height={20} />
          <div className={styles.profile}>
            <Image src={icons.profile} alt="profile" width={25} height={25} />
          </div>
        </div> */}
      </div>
    </header>
  );
}

export default Header;