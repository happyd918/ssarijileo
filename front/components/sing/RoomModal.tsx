import React, { useState } from 'react';
import Image from 'next/image';
import styles from '@/styles/sing/RoomModal.module.scss';

function RoomModal({ setModalOpen }: any) {
  // 모달창 open 여부
  const closeModal = () => {
    setModalOpen(false);
  };

  // 라디오 값 관리
  const [mode, setMode] = useState('');
  const handleClickRadioBtn = (e: React.ChangeEvent<HTMLInputElement>) => {
    const eventTarget = e.target as HTMLInputElement;
    setMode(eventTarget.value);
  };

  return (
    <div className={styles.back}>
      <div className={styles.container}>
        <div className={styles.top}>
          <div>방 만들기</div>
          <button type="button" className={styles.close} onClick={closeModal}>
            <Image
              src="img/common/common_close_image.svg"
              alt="close"
              width={25}
              height={25}
            />
          </button>
        </div>

        <div className={styles.main}>
          <input
            type="text"
            placeholder="방 제목을 입력하세요..."
            className={styles.input}
          />
          <div className={styles.mode}>
            <label className={styles.nomal} htmlFor="nomal">
              <input
                id="nomal"
                type="radio"
                value="nomal"
                checked={mode === 'nomal'}
                className={styles.input}
                onChange={handleClickRadioBtn}
              />
              일반 노래방
            </label>
            <label className={styles.perfect} htmlFor="perfect">
              <input
                id="perfect"
                type="radio"
                value="perfect"
                checked={mode === 'perfect'}
                className={styles.input}
                onChange={handleClickRadioBtn}
              />
              퍼펙트싱어
            </label>
            <label className={styles.relay} htmlFor="relay">
              <input
                id="relay"
                type="radio"
                value="relay"
                checked={mode === 'relay'}
                className={styles.input}
                onChange={handleClickRadioBtn}
              />
              이어부르기
            </label>
            <label className={styles.guess} htmlFor="guess">
              <input
                id="guess"
                type="radio"
                value="guess"
                checked={mode === 'guess'}
                className={styles.input}
                onChange={handleClickRadioBtn}
              />
              가사 맞추기
            </label>
          </div>
          <div className={styles.type}>
            <div className={styles.lock}>
              <div className={styles.title}>
                <input type="checkbox" />
                <div className={styles.name}>비공개방</div>
                <Image
                  src="img/room/room_lock_image.svg"
                  width={16}
                  height={16}
                  alt="lock"
                />
              </div>
              <input
                type="password"
                placeholder="비밀번호"
                className={styles.pwd}
              />
            </div>
            <div className={styles.unlock}>
              <input type="checkbox" />
              <div className={styles.name}>공개방</div>
              <Image
                src="img/room/room_unlock_image.svg"
                width={16}
                height={16}
                alt="lock"
              />
            </div>
          </div>
          <div className={styles.member}>
            <div className={styles.context}>최대인원 설정</div>
            <select
              className={styles.select}
              defaultValue="인원수"
              name="인원수"
              id="member"
            >
              <option value="인원수">인원수</option>;
              <option value="1">1</option>;<option value="2">2</option>;
              <option value="3">3</option>;<option value="4">4</option>;
              <option value="5">5</option>;<option value="6">6</option>;
            </select>
          </div>
        </div>

        <div className={styles.bottom}>
          <button
            type="button"
            className={styles.createBtn}
            onClick={closeModal}
          >
            생성
          </button>

          <button
            type="button"
            className={styles.closeBtn}
            onClick={closeModal}
          >
            취소
          </button>
        </div>
      </div>
    </div>
  );
}

export default RoomModal;
