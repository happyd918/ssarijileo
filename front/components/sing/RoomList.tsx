import React, { useState } from 'react';
import Image from 'next/image';
import styles from '@/styles/sing/RoomList.module.scss';
import Search from '@/components/common/Search';
import RoomListItem from '@/components/sing/RoomListItem';
import RoomModal from './RoomModal';

export interface RoomInfo {
  title: string;
  type: string;
  lock: boolean;
  member: number;
}

function RoomList() {
  // 방만들기 모달창
  const [modalOpen, setModalOpen] = useState(false);
  const showModal = () => {
    setModalOpen(true);
  };

  const sortType = [
    { mode: 'Default' },
    { mode: '일반모드' },
    { mode: '퍼펙트스코어' },
    { mode: '이어부르기' },
    { mode: '가사 맞추기' },
  ];
  const roomInfo = [
    {
      title: '아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '아무나 들어와~',
      type: '퍼펙트싱어',
      lock: true,
      member: 4,
    },
    {
      title: '아무나 들어와~',
      type: '이어부르기',
      lock: true,
      member: 4,
    },
    {
      title: '아무나 들어와~',
      type: '이어부르기',
      lock: true,
      member: 4,
    },
    {
      title: '아무나 들어와~',
      type: '가사맞추기',
      lock: true,
      member: 4,
    },
    {
      title: '아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
  ];
  return (
    <div className={styles.container}>
      {modalOpen && <RoomModal setModalOpen={setModalOpen} />}
      <div className={styles.search}>
        <Search optionItem={sortType} />
      </div>
      <div className={styles.addBtn}>
        <button type="button" className={styles.btn} onClick={showModal}>
          <Image
            src="img/common/common_add_image.svg"
            width={20}
            height={26}
            alt="add"
            className={styles.img}
          />
          방만들기
        </button>
      </div>
      <div className={styles.room}>
        {roomInfo.map(info => (
          <RoomListItem info={info} />
        ))}
      </div>
    </div>
  );
}

export default RoomList;
