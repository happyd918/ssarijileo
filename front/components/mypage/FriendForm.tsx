import React, { useState } from 'react';
import Image from 'next/image';

import styles from '@/styles/mypage/FriendForm.module.scss';

function FriendForm() {
  const friend = [
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '김태학',
      play: '22회',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '길상욱',
      play: '22회',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '김명준',
      play: '22회',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '김소윤',
      play: '22회',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '서예지',
      play: '22회',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '이수민',
      play: '22회',
    },
  ];
  const [friendList, setState] = useState(friend);

  const searchFriend = (e: React.ChangeEvent<HTMLInputElement>) => {
    const eventTarget = e.target as HTMLInputElement;
    const arr: any[] = [];
    friend.forEach((item, idx) => {
      if (item.name.includes(eventTarget.value)) {
        arr.push(friend[idx]);
      }
    });

    setState(arr);
  };
  const listItems = friendList.map(item => {
    return (
      <tr className={styles.item} key={item.name}>
        <td className={styles.profile}>
          <div className={styles.content}>
            <Image src={item.profile} width={30} height={30} alt="profile" />
          </div>
        </td>
        <td className={styles.name}>{item.name}</td>
        <td className={styles.play}>{item.play}</td>
      </tr>
    );
  });
  return (
    <>
      <div className={styles.contentForm}>
        <div className={styles.top}>
          <button className={styles.addBtn}>
            <Image
              src="img/common/common_add_image.svg"
              width={15}
              height={18}
              alt="add"
              className={styles.img}
            />
            친구 추가
          </button>
          <div className={styles.search}>
            <input
              className={styles.input}
              type="text"
              placeholder="닉네임을 입력하세요..."
              onChange={searchFriend}
            />
            <Image
              src="img/common/light/light_common_find_image.svg"
              width={27}
              height={27}
              alt="find"
              className={styles.find}
            />
          </div>
        </div>
        <div>
          <table className={styles.table}>
            <thead>
              <tr>
                <th role="row" />
                <th>닉네임</th>
                <th>PLAY횟수</th>
              </tr>
            </thead>
            <tbody>
              {/* 상단 고정 (친구요청) */}
              <tr className={styles.item}>
                <td className={styles.profile}>
                  <div className={styles.content}>
                    <Image
                      src="icon/header/dark/dark_profile_icon.svg"
                      width={30}
                      height={30}
                      alt="profile"
                    />
                  </div>
                </td>
                <td className={styles.name}>누구세요</td>
                <td className={styles.play}>
                  <button type="button" className={styles.okBtn}>
                    친구수락
                  </button>
                </td>
              </tr>
            </tbody>
            <tbody className={styles.friendList}>{listItems}</tbody>
          </table>
        </div>
      </div>
    </>
  );
}

export default FriendForm;
