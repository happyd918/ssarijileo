import React, { useEffect, useState } from 'react';
import Image from 'next/image';
import { useSelector } from 'react-redux';
import styles from '@/styles/common/Search.module.scss';

function Search({ optionItem }: any) {
  // 다크모드 상태 관리
  const [themeMode, setThemeMode] = useState('light');

  const storeTheme = useSelector<any>(state => state.theme);
  useEffect(() => {
    setThemeMode(localStorage.getItem('theme') || 'light');
  }, [themeMode, storeTheme]);

  const toggle = `img/common/${themeMode}/${themeMode}_toggle_open_image.svg`;

  // select option item 목록 props로 전달 받기
  const [sortType, setSortType] = useState('Default');
  const changeMode = (e: React.MouseEvent<HTMLElement>) => {
    const eventTarget = e.target as HTMLElement;
    setSortType(eventTarget.innerText);
  };
  const optionList = optionItem.map((item: any) => (
    <div className={styles.optionItem} onClick={changeMode}>
      {item.mode}
    </div>
  ));
  return (
    <div className={styles.container}>
      <div className={styles.sort}>
        <div className={styles.context}>Sort By : </div>
        <div className={styles.select}>
          <div className={styles.type}>{sortType}</div>
          <Image src={toggle} width={15} height={15} alt="toggle" />
        </div>
        <div className={styles.option}>{optionList}</div>
      </div>
      <div className={styles.search}>
        <div className={styles.context}>Search : </div>
        <input
          className={styles.input}
          type="text"
          placeholder="제목을 입력하세요"
        />
        <Image
          src="img/common/light/light_common_find_image.svg"
          width={37}
          height={37}
          alt="find"
          className={styles.find}
        />
      </div>
    </div>
  );
}

export default Search;
