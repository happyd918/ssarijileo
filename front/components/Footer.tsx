import Image from 'next/image';

import styles from '@/styles/Footer.module.scss';

function Footer() {
  const lightSocials = [
    {
      name: 'Facebook',
      icon: 'icon/footer/light/light_facebook_icon.svg',
    },
    {
      name: 'YouTube',
      icon: 'icon/footer/light/light_youtube_icon.svg',
    },
    {
      name: 'Instagram',
      icon: 'icon/footer/light/light_instagram_icon.svg',
    },
    {
      name: 'Google',
      icon: 'icon/footer/light/light_google_icon.svg',
    },
  ];

  const lightSocialsIcons = lightSocials.map(social => (
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
          <Image
            src="icon/header/light/light_logo.svg"
            alt="home"
            width={300}
            height={200}
          />
        </div>
        <div className={styles.item_div}>
          <div className={styles.footer_item_upper}>
            <Image
              src="icon/footer/light/light_phone_icon.svg"
              alt="place"
              width={24}
              height={24}
            />
            <span>대전광역시 유성구 덕명동 124</span>
          </div>
          <div className={styles.footer_item_upper}>
            <Image
              src="icon/footer/light/light_place_icon.svg"
              alt="phone"
              width={24}
              height={24}
            />
            <span>042-123-4567</span>
          </div>
          <div className={styles.footer_item_lower}>
            <span>Social Media</span>
            {lightSocialsIcons}
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
