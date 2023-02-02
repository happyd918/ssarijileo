import React from 'react';
import Image from 'next/image';

import MicControlBar from '@/components/common/MicControlBar';
import EchoControlBar from '@/components/common/EchoControlBar';

import styles from '@/styles/mypage/Mypage.module.scss';

function ContentForm(props: { theme: string; DUMMY_DATA: any }) {
  const { theme, DUMMY_DATA } = props;

  const imgs = {
    name: `img/mypage/${theme}/${theme}_name_image.svg`,
    nickname: `img/mypage/${theme}/${theme}_nickname_image.svg`,
    email: `img/mypage/${theme}/${theme}_email_image.svg`,
  };

  const saveProfile = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log('저장');
  };

  const nameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    DUMMY_DATA.name = e.target.value;
  };

  const nickNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    DUMMY_DATA.nickname = e.target.value;
  };

  const emailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    DUMMY_DATA.email = e.target.value;
  };

  return (
    <>
      <form onSubmit={saveProfile} className={styles.contentForm}>
        <label htmlFor="name">
          <p>
            <span>*</span>&nbsp;&nbsp;이름
          </p>
          <Image
            src={imgs.name}
            alt=""
            width={16}
            height={16}
            className={styles.img}
          />
          <input
            id="name"
            type="text"
            className={styles.inputTag}
            value={DUMMY_DATA.name}
            onChange={nameChange}
          />
        </label>
        <label htmlFor="nickname">
          <p>
            <span>*</span>&nbsp;&nbsp;닉네임
          </p>
          <Image
            src={imgs.nickname}
            alt=""
            width={16}
            height={16}
            className={styles.img}
          />
          <input
            id="nickname"
            type="text"
            className={styles.nickname}
            value={DUMMY_DATA.nickname}
            onChange={nickNameChange}
          />
          <button type="button" className={styles.checkBtn}>
            중복검사
          </button>
        </label>
        <label htmlFor="email">
          <p>
            <span>*</span>&nbsp;&nbsp;이메일
          </p>
          <Image
            src={imgs.email}
            alt=""
            width={16}
            height={16}
            className={styles.img}
          />
          <input
            id="email"
            type="text"
            className={styles.inputTag}
            value={DUMMY_DATA.email}
            onChange={emailChange}
          />
        </label>
        <div className={styles.scrollBox}>
          <label htmlFor="micVolume">
            <p>
              <span>*</span>&nbsp;&nbsp;마이크 볼륨
            </p>
            <div className={styles.ctlBox}>
              <MicControlBar />
            </div>
          </label>
          <label htmlFor="echo">
            <p>
              <span>*</span>&nbsp;&nbsp;에코
            </p>
            <div className={styles.ctlBox}>
              <EchoControlBar />
            </div>
          </label>
        </div>
        <button type="submit" className={styles.submitBtn}>
          저장
        </button>
      </form>
      <div className={styles.contentFooter}>
        <button type="button" className={styles.signOutBtn}>
          회원 탈퇴
        </button>
      </div>
    </>
  );
}
