import React, { useEffect, useState } from 'react';
import Image from 'next/image';
import axios from 'axios';
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

const GET_SESSIONS_URL =
  'https://taehakssarifirst.store/openvidu/api/sessions/';
const GET_SESSIONS_HEADER = 'Basic T1BFTlZJRFVBUFA6MTE5NA==';

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

  const roomInfo: any[] = [];
  for (let i = 0; i < 100; i++) {
    const num = Math.floor(Math.random() * 4) + 1;
    const room = {
      id: i,
      title: `방 ${i + 1}`,
      type: sortType[num].mode,
      lock: i % 2 === 0,
      member: i % 2 === 0 ? 2 : 1,
    };
    roomInfo.push(room);
  }

  // 방 목록 (23.02.02 : openvidu와 직접 api 수신중, 콘솔에 결과 출력)
  const [rooms, setRooms] = useState<any>(null);

  async function getRoomsInfo() {
    const response = await axios.get(GET_SESSIONS_URL, {
      headers: { Authorization: GET_SESSIONS_HEADER },
    });
    setRooms(response.data);
    console.log('response success, roomList : ', response.data);
  }

  useEffect(() => {
    console.log('RoomList 입장, 초기 roomList : ', rooms);
    getRoomsInfo();
  }, []);

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
          <RoomListItem info={info} key={info.id} />
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
