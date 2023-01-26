import { useEffect, useState } from 'react';
import Image from 'next/image';

import styles from '@/styles/Footer.module.scss';
import { useSelector } from 'react-redux';
import { RootState } from '@/redux/store';

function Footer() {
  const [themeMode, setThemeMode] = useState('light');

  const storeTheme = useSelector<RootState>(state => state.theme);
  useEffect(() => {
    setThemeMode(localStorage.getItem('theme') || 'light');
  }, [themeMode, storeTheme]);

  const socials = [
    {
      name: 'Facebook',
      icon: `icon/footer/${themeMode}/${themeMode}_facebook_icon.svg`,
    },
    {
      name: 'YouTube',
      icon: `icon/footer/${themeMode}/${themeMode}_youtube_icon.svg`,
    },
    {
      name: 'Instagram',
      icon: `icon/footer/${themeMode}/${themeMode}_instagram_icon.svg`,
    },
    {
      name: 'Google',
      icon: `icon/footer/${themeMode}/${themeMode}_google_icon.svg`,
    },
  ];

  const icons = {
    logo: `icon/Header/${themeMode}/${themeMode}_logo.svg`,
    phone: `icon/Footer/${themeMode}/${themeMode}_phone_icon.svg`,
    place: `icon/Footer/${themeMode}/${themeMode}_place_icon.svg`,
  };

  const socialsIcons = socials.map(social => (
    <Image
      key={social.name}
      src={social.icon}
      alt={social.name}
      width={24}
      height={24}
    />
  ));

  return (
    <footer className={styles.footer}>
      <div className={styles.footer_container}>
        <div className={styles.img_div}>
          <Image src={icons.logo} alt="home" width={300} height={200} />
        </div>
        <div className={styles.item_div}>
          <div className={styles.footer_item_upper}>
            <Image src={icons.phone} alt="place" width={24} height={24} />
            <span>대전광역시 유성구 덕명동 124</span>
          </div>
          <div className={styles.footer_item_upper}>
            <Image src={icons.place} alt="phone" width={24} height={24} />
            <span>042-123-4567</span>
          </div>
          <div className={styles.footer_item_lower}>
            <span>Social Media</span>
            {socialsIcons}
          </div>
        </div>
      </div>
      <div className={styles.footer_lower}>
        <div className={styles.footer_lower_left}>
          <span>ABOUT US</span>
          <span>CONTACT US</span>
          <span>HELP</span>
          <span>PRIVACY POLICY</span>
          <span>DISCLAIMER</span>
        </div>
        <span>Copyright© 2021. SSarijileo All rights reserved.</span>
      </div>
    </footer>
  );
}

export default Footer;
