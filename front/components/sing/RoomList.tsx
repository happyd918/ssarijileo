import React, { useState } from 'react';
import Image from 'next/image';
import styles from '@/styles/sing/RoomList.module.scss';
import Search from '@/components/common/Search';
import RoomListItem from '@/components/sing/RoomListItem';
import RoomModal from './RoomModal';
import Pagination from '@/components/common/Pagination';

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

  //  페이지
  const [page, setPage] = useState(1);
  //  방 목록이 보일 개수
  const limit = 12;

  //  방 목록
  const sortType = [
    { mode: 'Default' },
    { mode: '일반모드' },
    { mode: '퍼펙트스코어' },
    { mode: '이어부르기' },
    { mode: '가사 맞추기' },
  ];
  const roomInfo = [
    {
      title: '1아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '2아무나 들어와~',
      type: '퍼펙트싱어',
      lock: true,
      member: 4,
    },
    {
      title: '3아무나 들어와~',
      type: '이어부르기',
      lock: true,
      member: 4,
    },
    {
      title: '4아무나 들어와~',
      type: '이어부르기',
      lock: true,
      member: 4,
    },
    {
      title: '5아무나 들어와~',
      type: '가사맞추기',
      lock: true,
      member: 4,
    },
    {
      title: '6아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '7아무나 들어와~',
      type: '퍼펙트싱어',
      lock: true,
      member: 4,
    },
    {
      title: '8아무나 들어와~',
      type: '이어부르기',
      lock: true,
      member: 4,
    },
    {
      title: '9아무나 들어와~',
      type: '이어부르기',
      lock: true,
      member: 4,
    },
    {
      title: '10아무나 들어와~',
      type: '가사맞추기',
      lock: true,
      member: 4,
    },
    {
      title: '11아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '12아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '13아무나 들어와~',
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
    {
      title: '24아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '25아무나 들어와~',
      type: '퍼펙트싱어',
      lock: true,
      member: 4,
    },
    {
      title: '26아무나 들어와~',
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
    {
      title: '36아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '37아무나 들어와~',
      type: '퍼펙트싱어',
      lock: true,
      member: 4,
    },
    {
      title: '38아무나 들어와~',
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
      title: '48아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '49아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '50아무나 들어와~',
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
      title: '60아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '61아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '62아무나 들어와~',
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
    {
      title: '72아무나 들어와~',
      type: '일반노래방',
      lock: true,
      member: 4,
    },
    {
      title: '73아무나 들어와~',
      type: '퍼펙트싱어',
      lock: true,
      member: 4,
    },
    {
      title: '74아무나 들어와~',
      type: '이어부르기',
      lock: true,
      member: 4,
    },
    {
      title: '75아무나 들어와~',
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

  // 게시할 부분만 잘라서 전달
  const offset = (page - 1) * limit;
  const postData = roomInfo.slice(offset, offset + limit);

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
        {postData.map(info => (
          <RoomListItem info={info} />
        ))}
      </div>
      <Pagination
        limit={limit}
        page={page}
        totalPosts={roomInfo.length}
        setPage={setPage}
      />
    </div>
  );
}

export default RoomList;
