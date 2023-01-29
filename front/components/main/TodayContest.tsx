import Image from 'next/image';

import styles from '@/styles/main/TodayContest.module.scss';

function TodayContest() {
  const ranking = [
    {
      medal: 'img/main/main_medal_gold_image.svg',
      profile: 'icon/header/light/light_profile_icon.svg',
      name: '나는이수민',
      title: 'Hype boy',
      singer: 'NewJeans',
      like: '4k',
    },
    {
      medal: 'img/main/main_medal_sliver_image.svg',
      profile: 'icon/header/light/light_profile_icon.svg',
      name: '김맹준',
      title: 'Hype boy',
      singer: 'NewJeans',
      like: '4k',
    },
    {
      medal: 'img/main/main_medal_bronze_image.svg',
      profile: 'icon/header/light/light_profile_icon.svg',
      name: 'zㅣ존예지',
      title: 'Hype boy',
      singer: 'NewJeans',
      like: '4k',
    },
  ];
  const rank = ranking.map(item => (
    <tr className={styles.item}>
      <td className={styles.medal}>
        <Image
          src={item.medal}
          width={50}
          height={50}
          alt="medal"
          className={styles.medalIcon}
        />
      </td>
      <td className={styles.profile}>
        <div className={styles.content}>
          <Image src={item.profile} width={30} height={30} alt="profile" />
        </div>
      </td>
      <td className={styles.name}>{item.name}</td>
      <td className={styles.title}>{item.title}</td>
      <td className={styles.singer}>{item.singer}</td>
      <td className={styles.like}>{item.like}</td>
    </tr>
  ));
  const simpleRank = ranking.map(item => {
    return (
      <div className={styles.simpleItem}>
        <td className={styles.simpleMedal}>
          <Image
            src={item.medal}
            width={50}
            height={50}
            alt="medal"
            className={styles.simpleMedalIcon}
          />
        </td>
        <th className={styles.simpleName}>{item.name}</th>
      </div>
    );
  });

  return (
    <div className={styles.container}>
      <div className={styles.contestTitle}>
        <Image
          src="img/main/main_crown_image.svg"
          width={63}
          height={63}
          alt="crown"
          className={styles.contestIcon}
        />
        <div className={styles.title}>오늘의 노래왕</div>
        <Image
          src="img/main/main_crown_image.svg"
          width={63}
          height={63}
          alt="crown"
          className={styles.contestIcon}
        />
      </div>
      <div className={styles.contestRank}>
        <table className={styles.rankTable}>
          <thead>
            <tr>
              <th role="row" />
              <th role="row" />
              <th>닉네임</th>
              <th>제목</th>
              <th>가수</th>
              <th>LIKE</th>
            </tr>
          </thead>
          <tbody>{rank}</tbody>
          <div className={styles.simpleRank}>{simpleRank}</div>
        </table>
      </div>
    </div>
  );
}

export default TodayContest;
