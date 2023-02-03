import React, { useState } from 'react';
import Image from 'next/image';

import styles from '@/styles/profile/FriendForm.module.scss';

import FriendModal from './FriendModal';

function FriendForm() {
  const [modalOpen, setModalOpen] = useState(false);
  const friend = [
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '김태학',
      // play: '22회',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '길상욱',
      // play: '22회',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '김명준',
      // play: '22회',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '김소윤',
      // play: '22회',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '서예지',
      // play: '22회',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '이수민',
      // play: '22회',
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
      <div className={styles.item} key={item.name}>
        <div className={styles.profile}>
          <div className={styles.content}>
            <Image
              className={styles.img}
              src={item.profile}
              width={30}
              height={30}
              alt="profile"
            />
          </div>
        </div>
        <div className={styles.name}>{item.name}</div>
        <button type="button" className={styles.okBtn}>
          친구삭제
        </button>
      </div>
    );
  });
  return (
    <div className={styles.contentForm}>
      {modalOpen && <FriendModal setModalOpen={setModalOpen} friend={friend} />}
      <div className={styles.top}>
        <button
          type="button"
          className={styles.addBtn}
          onClick={() => {
            setModalOpen(!modalOpen);
          }}
        >
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
      <div className={styles.content}>
        <div className={styles.thead}>
          <div className={styles.tr}>
            <div className={styles.profile} />
            <div className={styles.name}>닉네임</div>
            <div className={styles.play}>상태</div>
          </div>
        </div>
        <div className={styles.table}>
          <div className={styles.fix}>
            {/* 상단 고정 (친구요청) */}
            <div className={styles.item}>
              <div className={styles.profile}>
                <div className={styles.content}>
                  <Image
                    className={styles.img}
                    src="icon/header/dark/dark_profile_icon.svg"
                    width={30}
                    height={30}
                    alt="profile"
                  />
                </div>
              </div>
              <div className={styles.name}>누구세요</div>
              <div className={styles.play}>
                <button type="button" className={styles.okBtn}>
                  친구수락
                </button>
              </div>
            </div>
            <div className={styles.item}>
              <div className={styles.profile}>
                <div className={styles.content}>
                  <Image
                    src="icon/header/dark/dark_profile_icon.svg"
                    width={30}
                    height={30}
                    alt="profile"
                    className={styles.img}
                  />
                </div>
              </div>
              <div className={styles.name}>누구세요</div>
              <div className={styles.play}>
                <button type="button" className={styles.okBtn}>
                  친구수락
                </button>
              </div>
            </div>
          </div>
          <div className={styles.friendList}>{listItems}</div>
        </div>
      </div>
    </div>
  );
}

export default FriendForm;
