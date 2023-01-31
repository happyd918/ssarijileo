import { useEffect, useState } from 'react';
import Image from 'next/image';
import { useSelector } from 'react-redux';
import classNames from 'classnames';
import styles from '@/styles/room/ReservList.module.scss';

function ReservList() {
  const [themeMode, setThemeMode] = useState('light');
  const [modalOpen, setModalOpen] = useState(false);

  // 예약목록 모달 관리
  const showModal = () => {
    setModalOpen(!modalOpen);
  };

  const modalClass = classNames({
    [styles.modal]: true,
    [styles.modalOpen]: modalOpen,
  });

  // 다크모드 관리
  const storeTheme = useSelector<any>(state => state.theme);
  useEffect(() => {
    setThemeMode(localStorage.getItem('theme') || 'light');
  }, [themeMode, storeTheme]);

  // 다크모드에 따라 아이콘 경로 변경
  const toggleIcon = `img/ssari/${themeMode}/${themeMode}_ssari_toggle_image.svg`;

  // 노래가 끝나면 0 인덱스 요소 삭제
  // 예약 취소 시 해당 인덱스 요소 삭제
  // 우선 예약 시 1 인덱스로 추가
  // 노래 시작 시 0 인덱스 곡 정보 요청
  const arr = [
    {
      title: 'OMG1',
      singer: 'NewJeans',
    },
    {
      title: 'OMG2',
      singer: 'NewJeansssss',
    },
    {
      title: 'OMG3',
      singer: 'NewJeansssss',
    },
    {
      title: 'OMG4',
      singer: 'NewJeansssss',
    },
    {
      title: 'OMG4',
      singer: 'NewJeansssss',
    },
    {
      title: 'OMG4',
      singer: 'NewJeansssss',
    },
  ];

  //   현재 곡 제외 예약 목록만 뽑아내기
  const reserv = arr.slice(1);

  const ReservItem = reserv.map(item => {
    return (
      <div className={styles.item}>
        {item.title}-{item.singer}
      </div>
    );
  });
  return (
    <div className={styles.container}>
      <div className={modalClass}>
        {reserv.map((item, idx) => (
          <div className={styles.modalItem}>
            <div className={styles.number}>{idx + 2}</div>
            <div className={styles.title}>{item.title}</div>
            <div className={styles.singer}>{item.singer}</div>
            <button
              type="button"
              onClick={() => {
                arr.splice(idx, 1);
              }}
            >
              예약취소
            </button>
          </div>
        ))}
      </div>
      <button type="button" className={styles.reservList} onClick={showModal}>
        <div className={styles.list}>
          <div className={styles.nowPlay}>
            <div className={styles.title}>{arr[0].title}</div>-{arr[0].singer}
          </div>
          <div className={styles.out}>
            <div className={styles.listItem}>{ReservItem}</div>
          </div>
        </div>

        <Image
          src={toggleIcon}
          width={21}
          height={12}
          alt="toggle"
          className={styles.toggleIcon}
        />
      </button>
    </div>
  );
}

export default ReservList;
