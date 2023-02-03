import { Swiper, SwiperSlide } from 'swiper/react';
import { Autoplay } from 'swiper';
import Image from 'next/image';

import Title from '@/components/main/Title';

import 'swiper/swiper.min.css';
import styles from '@/styles/sing/SingTop.module.scss';

function SingTop() {
  const slides = [1, 2, 3];
  const swiperSlide = slides.map(slide => {
    return (
      <SwiperSlide key={slide} className={styles.swiperSlide}>
        <Title />
        <Image
          src="img/common/common_play_image.svg"
          width={600}
          height={358}
          alt="img"
          className={styles.topImg}
        />
      </SwiperSlide>
    );
  });

  return (
    <Swiper
      spaceBetween={50}
      slidesPerView={1}
      loop
      autoplay={{
        delay: 3000,
        disableOnInteraction: false,
      }}
      modules={[Autoplay]}
      className={styles.swiper}
    >
      {swiperSlide}
    </Swiper>
  );
}

export default SingTop;