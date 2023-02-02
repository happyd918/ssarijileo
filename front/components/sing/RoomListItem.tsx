import Image from 'next/image';
import classNames from 'classnames';
import { RoomInfo } from './RoomList';
import styles from '@/styles/sing/RoomListItem.module.scss';

type RoomProps = {
  info: RoomInfo;
};

function RoomListItem({ info }: RoomProps) {
  const { title, type, lock, member } = info;
  let backClassName = classNames({
    [styles.back]: true,
    [styles.nomal]: true,
  });
  let typeClassName = classNames({
    [styles.type]: true,
    [styles.nomal]: true,
  });
  if (type === '퍼펙트싱어') {
    backClassName = classNames({
      [styles.back]: true,
      [styles.perfect]: true,
    });
    typeClassName = classNames({
      [styles.type]: true,
      [styles.perfect]: true,
    });
  } else if (type === '이어부르기') {
    backClassName = classNames({
      [styles.back]: true,
      [styles.relay]: true,
    });
    typeClassName = classNames({
      [styles.type]: true,
      [styles.relay]: true,
    });
  } else if (type === '가사맞추기') {
    backClassName = classNames({
      [styles.back]: true,
      [styles.guess]: true,
    });
    typeClassName = classNames({
      [styles.type]: true,
      [styles.guess]: true,
    });
  }

  const openWindow = () => {
    const popupWindow = window.open('room/', 'windowName', 'resizeable');
    if (!popupWindow) return;
    popupWindow.resizeTo(1920, 1080);
    popupWindow.onresize = _ => {
      popupWindow.resizeTo(1920, 1080);
    };
  };

  return (
    <div className={styles.component} onClick={openWindow}>
      <div className={styles.container}>
        <div className={backClassName}>
          <div className={styles.top}>
            <div className={styles.title}>{title}</div>
            <div className={typeClassName}>{type}</div>
          </div>
          <Image
            src="img/common/common_play_image.svg"
            width={43}
            height={43}
            alt="play"
            className={styles.play}
          />
          <div className={styles.bottom}>
            <div className={styles.lock}>
              <div className={styles.type}>{lock ? '비공개방' : '공개방'}</div>
              <Image
                src="img/room/room_private_image.svg"
                width={18}
                height={18}
                alt="lock"
              />
            </div>
            <div className={styles.member}>
              <Image
                src="img/room/room_member_image.svg"
                width={35}
                height={36}
                alt="member"
              />
              <div className={styles.count}>{member}/10</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default RoomListItem;
