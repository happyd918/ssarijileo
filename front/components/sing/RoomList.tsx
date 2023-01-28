import React, { useState } from 'react';
import styles from '@/styles/sing/RoomList.module.scss';
import RoomListItem from './RoomListItem';

export interface RoomInfo {
  title: string;
  type: string;
  lock: boolean;
  member: number;
}

function RoomList() {
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
      {roomInfo.map(info => (
        <RoomListItem info={info} />
      ))}
    </div>
  );
}

export default RoomList;
