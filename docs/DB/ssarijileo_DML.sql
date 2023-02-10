-- 더미 데이터

-- Business DB

USE ssarijileo;

-- 프로필 데이터

INSERT INTO `profile` (`profile_id`, `nickname`, `image`)
VALUE ("USER1", "금쪽수민", "A"),
      ("USER2", "쁘띠태학", "A"),
      ("USER3", "굠굠명준", "A"),
      ("USER4", "최강상욱", "A"),
      ("USER5", "천사소윤", "A"),
      ("USER6", "꼬미예지", "A");

-- 친구 데이터

INSERT INTO `friend` (`from_user_nickname`, `to_user_nickname`, `status`)
VALUE ("금쪽수민", "굠굠명준", "A"),
      ("금쪽수민", "최강상욱", "A"),
      ("쁘띠태학", "금쪽수민", "W"),
      ("천사소윤", "꼬미예지", "A"),
      ("굠굠명준", "쁘띠태학", "A"),
      ("굠굠명준", "꼬미예지", "X");
      
-- 노래 데이터

INSERT INTO `song` (`title`, `singer`, `album`, `time`, `image`, `release_date`)
VALUE ("삐삐(BBIBBI),", "아이유(IU),", "아이유 디지털 싱글 [삐삐]", "00:03:28", "https://image.bugsm.co.kr/album/images/1000/201891/20189158.jpg", "2018-10-10"),
	  ("아무노래", "지코(ZICO),", "아무노래","00:03:47", "https://image.bugsm.co.kr/album/images/1000/203001/20300127.jpg", "2020-01-13"),
      ("광화문에서", "규현", "The 1st Mini Album '광화문에서 (At Gwanghwamun),'", "00:04:40", "https://image.bugsm.co.kr/album/images/1000/4631/463165.jpg", "2014-11-13"),
      ("사랑은 늘 도망가", "임영웅", "신사와 아가씨 OST Part 2","00:04:02", "https://image.bugsm.co.kr/album/images/1000/40658/4065831.jpg", "2021-10-11"),
      ("아로하", "조정석", "슬기로운 의사생활 OST Part 3", "00:04:03", "https://image.bugsm.co.kr/album/images/1000/203142/20314288.jpg", "2020-03-27"),
      ("Hype boy", "NewJeans", "The 1st Extended Play 〈New Jeans〉", "00:02:59", "https://image.bugsm.co.kr/album/images/500/40780/4078016.jpg", "2022-08-01"),
      ("Ditto", "NewJeans", "The Pre-Single 〈Ditto〉", "00:03:05","https://image.bugsm.co.kr/album/images/500/40824/4082425.jpg", "2022-12-18"),
      ("ANTIFRAGILE", "LESSERAFM", "The 2nd Mini Album 〈ANTIFRAGILE〉", "00:03:08","https://image.bugsm.co.kr/album/images/500/40807/4080706.jpg", "2022-10-17"),
      ("안녕", "조이(JOY),", "안녕 (Hello),", "00:03:35","https://image.bugsm.co.kr/album/images/500/203988/20398895.jpg", "2021-05-31"),
      ("After LIKE", "IVE 아이브", "The 3rd Single Album 〈After LIKE〉", "00:02:55","https://image.bugsm.co.kr/album/images/500/40789/4078936.jpg", "2022-08-22"),
      ("내꺼하자", "인피니트", "Over The Top", "00:03:24", "https://image.bugsm.co.kr/album/images/200/2942/294231.jpg", "2011-11-07"),
      ("선물", "멜로망스", "Moonlight", "00:04:18", "https://image.bugsm.co.kr/album/images/200/201074/20107410.jpg", "2017-07-10"),
      ("신호등", "이무진", "신호등", "00:04:18", "https://image.bugsm.co.kr/album/images/200/40448/4044879.jpg", "21-05-14"),
      ("여행", "볼빨간 사춘기", "Red Diary Page.2", "00:03:41", "https://image.bugsm.co.kr/album/images/200/201687/20168753.jpg", "18-05-24"),
      ("밤편지", "아이유(IU),", "밤편지", "00:04:18", "https://image.bugsm.co.kr/album/images/200/200890/20089092.jpg", "17-03-24"),
      ("12:45", "etham", "Stripped - EP", "00:02:57", "https://image.bugsm.co.kr/album/images/original/8109/810979.jpg?version=undefined", "2018-10-26"),
      ("시작", "가호(Gaho),", "이태원 클라쓰 OST", "00:03:20", "https://image.bugsm.co.kr/album/images/original/203131/20313188.jpg?version=undefined", "2020-03-20"),
      ("서른 즈음에", "김광석", "김광석 네번째", "00:04:42", "https://image.bugsm.co.kr/album/images/original/30/3029.jpg?version=undefined", "1894-06-25"),
      ("가시", "버즈", "Buzz Effect", "00:04:01", "https://image.bugsm.co.kr/album/images/original/80070/8007002.jpg?version=undefined", "2005-03-03"),
      ("지친하루", "윤종신", "2014 월간 윤종신 12월호", "00:04:26", "https://image.bugsm.co.kr/album/images/original/4725/472597.jpg?version=undefined", "2014-12-20"),
      ("딱 10CM만", "10CM", "딱 10CM만", "00:03:16", "https://image.bugsm.co.kr/album/images/500/40800/4080055.jpg", "2022-09-25"),
      ("오랜 날 오랜 밤", "AKMU", "사춘기 하", "00:04:48", "https://image.bugsm.co.kr/album/images/500/200752/20075281.jpg", "2017-01-03"),
      ("검정색하트", "TOIL", "Between Sat & Sun", "00:03:56", "https://image.bugsm.co.kr/album/images/500/204481/20448106.jpg", "2022-01-30"),
      ("Trip", "릴러말즈", "Trip", "00:03:53", "https://image.bugsm.co.kr/album/images/500/201828/20182846.jpg?", "2018-09-10"),
      ("너를 만나", "폴킴", "너를 만나", "00:04:40",  "https://image.bugsm.co.kr/album/images/500/202037/20203701.jpg?", "2018-10-29"),
      ("Way Back Home", "숀 (SHAUN),", "Take", "00:03:34", "https://image.bugsm.co.kr/album/images/500/7573/757375.jpg", "2018-06-27"),
      ("Bye bye my blue", "백예린", "Bye bye my blue", "00:03:23", "https://image.bugsm.co.kr/album/images/500/200412/20041270.jpg", "2016-06-20"),
      ("나의 사춘기에게", "볼빨간사춘기", "Red Diary Page.1", "00:03:42",  "https://image.bugsm.co.kr/album/images/500/201221/20122134.jpg", "2017-09-28"),
      ("오르트구름", "윤하 (Younha),", "YOUNHA 6th Album 'END THEORY'", "00:03:26", "https://image.bugsm.co.kr/album/images/500/40675/4067509.jpg", "2021-11-16"),
      ("네가 없는 밤 (Feat. ASH ISLAND), (Prod. GRAY),", "BE'O (비오),", "쇼미더머니 10 Final", "00:03:47", "https://image.bugsm.co.kr/album/images/500/204369/20436980.jpg", "2021-12-04"),
      ("사건의 지평선", "윤하", "END THEORY : Final Edition", "00:05:00", "https://image.bugsm.co.kr/album/images/500/40734/4073469.jpg", "2022-03-30");

-- 가사 데이터 (별도 파일 첨부)

-- 사용자 노래 데이터

INSERT INTO `singing` (`user_id`, `song_id`, `mode`, `score`, `total_singing_time`, `singing_date`)
VALUE ("USER1", 1, "N", 50, "00:04:43", "2023-01-01 09:00:00"),
	  ("USER2", 2, "P", 70, "00:02:22", "2023-01-01 14:24:00"),
      ("USER3", 3, "O", 100, "00:02:40", "2023-01-06 12:30:00"),
      ("USER1", 6, "N", 90, "00:02:30", "2023-01-12 09:20:00"),
      ("USER4", 4, "R", 90, "00:02:30", "2023-01-12 18:30:00"),
      ("USER5", 5, "R", 30, "00:01:24", "2023-01-12 18:30:25"),
      ("USER6", 6, "N", 80, "00:03:30", "2023-01-25 15:20:00"),
      ("USER1", 1, "N", 70, "00:04:40", "2023-01-25 10:00:00");

-- 애창곡 데이터

INSERT INTO `favorite_song` (`user_id`, `song_id`, `is_like`)
VALUE ("USER1", 1, "Y"),
	  ("USER1", 6, "Y"),
      ("USER2", 2, "Y"),
      ("USER1", 2, "Y"),
      ("USER1", 2, "N"),
      ("USER3", 3, "Y"),
      ("USER4", 4, "Y"),
      ("USER1", 2, "Y"),
      ("USER5", 5, "Y"),
      ("USER6", 6, "Y"),
      ("USER4", 4, "N");

-- 노래 환경설정 데이터

INSERT INTO `song_setting` (`song_setting_id`, `eco`, `volume`)
VALUE ("USER1", 70, 70),
	  ("USER2", 50, 50),
      ("USER3", 50, 80),
      ("USER4", 60, 90),
      ("USER5", 30, 50),
      ("USER6", 50, 50);

-- 녹화 데이터

INSERT INTO `recording` (`user_id`, `song_id`, `file`, `register_date`)
VALUE ("USER1", 1, "file1", "2023-01-01 09:05:00"),
	  ("USER2", 2, "file2", "2023-01-01 14:29:00"),
      ("USER3", 3, "file3", "2023-01-06 12:35:00"),
      ("USER4", 4, "file4", "2023-01-12 18:35:00"),
      ("USER5", 5, "file5", "2023-01-12 18:35:25"),
      ("USER6", 6, "file6", "2023-01-25 15:25:00");
    
-- 노래자랑 데이터

INSERT INTO `singing_contest` (`recording_id`, `register_date`, `status`)
VALUE (1, "2023-01-01 09:10:00", "V"),
	  (2, "2023-01-01 16:00:00", "V"),
      (3, "2023-01-08 12:00:00", "V"),
      (4, "2023-01-12 20:50:00", "B"),
      (5, "2023-01-13 02:30:00", "D"),
      (6, "2023-01-25 16:10:00", "V");
