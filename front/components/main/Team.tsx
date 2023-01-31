import Image from 'next/image';

import styles from '@/styles/main/Team.module.scss';

function Team(props: { img: { team: string } }) {
  return (
    <div className={styles.team}>
      <Image src={props.img.team} width={1300} height={800} alt="team" />
    </div>
  );
}

export default Team;
