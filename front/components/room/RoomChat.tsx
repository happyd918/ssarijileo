import React, { useState } from 'react';
import Image from 'next/image';
import classNames from 'classnames';
import styles from '@/styles/room/RoomChat.module.scss';

function RoomChat({ setModalOpen }: any) {
  // 현재 입력하는 채팅정보
  const [chatContext, setChat] = useState('');
  const changeChat = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    const eventTarget = e.target as HTMLTextAreaElement;
    setChat(eventTarget.value);
  };

  // 오픈비두에서 제공되는 채팅정보 (data)
  const chat = [
    {
      message: 'ㅋㅋㅋㅋㅋzzzzzㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',
      name: '서예지',
    },
    {
      message:
        'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',
      name: '서예지',
    },
    {
      message: 'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',
      name: '이수민',
    },
    {
      message: 'ㅋㅋㅋㅋㅋㅋㅋㅋ',
      name: '서예지',
    },
    {
      message: 'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',
      name: '이수민',
    },
    {
      message:
        'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',
      name: '이수민',
    },
    {
      message: 'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',
      name: '서예지',
    },
    {
      message: 'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',
      name: '서예지',
    },
  ];
  // 채팅목록 더미데이터 상태관리
  const [chatData, setChatData] = useState(chat);
  // 내 닉네임
  const myName = '이수민';
  const chatList = chatData.map(item => {
    //   item.name을 주고 프로필이미지 받아오기
    //   내가 보낸 것과 다른 사람이 보낸 거 class로 차이 나타내기
    const chatClass = classNames({
      [styles.chat]: true,
      [styles.myChat]: myName === item.name,
      [styles.otherChat]: myName !== item.name,
    });
    return (
      <div className={chatClass}>
        <div className={styles.profileInfo}>
          <div className={styles.profile}>
            <Image
              src="icon/header/dark/dark_profile_icon.svg"
              width={20}
              height={20}
              alt="profile"
              className={styles.profileIcon}
            />
          </div>
          <div className={styles.name}>{item.name}</div>
        </div>
        <div className={styles.context}>{item.message}</div>
      </div>
    );
  });
  return (
    <div className={styles.layout}>
      <input
        type="button"
        className={styles.back}
        onClick={() => {
          setModalOpen(false);
        }}
      />
      <div className={styles.container}>
        <div className={styles.top}>
          <Image
            src="img/common/common_close_image.svg"
            width={25}
            height={25}
            alt="close"
            onClick={() => {
              setModalOpen(false);
            }}
            className={styles.close}
          />
        </div>
        <div className={styles.main}>{chatList}</div>
        <textarea
          id="send"
          className={styles.input}
          onChange={changeChat}
          value={chatContext}
        />
        <button
          type="button"
          className={styles.btn}
          onClick={() => {
            chatData.push({
              message: chatContext,
              name: myName,
            });
            setChatData(chatData);
          }}
        >
          전송
        </button>
      </div>
    </div>
  );
}

export default RoomChat;
