import Image from 'next/image';
import styles from '@/styles/room/RoomFriend.module.scss';

function RoomFriend({ setModalOpen }: any) {
  const friend = [
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '김태학',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '길상욱',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '김명준',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '김소윤',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '서예지',
    },
    {
      profile: 'icon/header/dark/dark_profile_icon.svg',
      name: '이수민',
    },
  ];
  const listItems = friend.map(item => {
    return (
      <div className={styles.item}>
        <Image
          src={item.profile}
          width={40}
          height={40}
          alt="profile"
          className={styles.profile}
        />
        <div className={styles.name}>{item.name}</div>
        <Image
          src="img/mypage/mypage_add_friend_image.svg"
          width={20}
          height={20}
          alt="addFriend"
          className={styles.addFriend}
        />
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
          <div>친구 목록</div>
          <Image
            src="img/common/common_close_image.svg"
            width={25}
            height={25}
            alt="close"
            onClick={() => {
              setModalOpen(false);
            }}
          />
        </div>
        <div className={styles.main}>
          <div className={styles.search}>
            <input
              className={styles.input}
              type="text"
              placeholder="닉네임을 입력하세요..."
            />
            <Image
              src="img/common/light/light_common_find_image.svg"
              width={27}
              height={27}
              alt="find"
              className={styles.find}
            />
          </div>
          <div className={styles.friendList}>{listItems}</div>
        </div>
        <div className={styles.bottom}>
          <button type="button" className={styles.btn}>
            {' '}
            초대
          </button>
        </div>
      </div>
    </div>
  );
}

export default RoomFriend;
