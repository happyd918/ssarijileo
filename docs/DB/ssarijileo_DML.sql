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

INSERT INTO `friend` (`sending_user_id`, `receiving_user_id`, `status`)
VALUE ("USER1", "USER3", "A"),
      ("USER1", "USER4", "A"),
      ("USER2", "USER1", "W"),
      ("USER5", "USER6", "A"),
      ("USER3", "USER2", "A"),
      ("USER3", "USER6", "X");
      
-- 노래 데이터

INSERT INTO `song` (`title`, `singer`, `album`, `time`, `image`, `release_date`)
VALUE ("서른 즈음에", "김광석", "김광석 네번째", "00:04:43", "default.jpg", "1994-06-25"),
      ("Canada", "Lauv(라우브)", "~how i'm feeling~", "00:03:04", "default.jpg", "2020-03-06"),
      ("검정색하트 (Feat. Leellamarz, BE'O)", "TOIL", "Between Sat & Sun", "00:03:55", "default.jpg", "2022-01-30"),
      ("사건의 지평선", "윤하", "END THEORY : Final Edition", "00:05:00", "default.jpg", "2022-03-30"),
      ("Way Back Home", "숀(SHAUN)", "Take", "00:03:34", "default.jpg", "2018-06-27"),
      ("Ditto", "NewJeans", "OMG", "00:03:32", "default.jpg", "2023-01-02");

-- 음정 데이터

INSERT INTO `pitch` (`song_id`, `time`, `note`)
VALUE (1, 10, "C#"),
	(1, 12.5, "D0"),
      (1, 17.22, "D#0"),
      (1, 18.01, "E0"),
      (1, 20.2, "C0"),
      (2, 5, "C#"),
	(2, 6, "D0"),
      (2, 7, "D#0"),
      (3, 5, "C#"),
	(3, 6, "D0"),
      (3, 7, "D#0"),
      (4, 5, "C#"),
	(4, 6, "D0"),
      (4, 7, "D#0"),
      (5, 5, "C#"),
	(5, 6, "D0"),
      (5, 7, "D#0"),
      (6, 5, "C#"),
	(6, 6, "D0"),
      (6, 7, "D#0");

-- 가사 데이터

INSERT INTO `lyrics` (`song_id`, `verse`, `status`)
VALUE (1, "또 하루 멀어져 간다", "O"),
	(1, "내뿜은 담배연기처럼", "O"),
      (1, "작기만한 내 기억속엔", "O"),
      (1, "무얼 채워 살고 있는지", "O"),
      (1, "점점 더 멀어져 간다", "O"),
      (1, "머물러 있는 청춘인줄 알았는데", "H"),
      (1, "비어가는 내 가슴속엔", "H"),
      (1, "더 아무것도 찾을 수 없네", "H"),
      (1, "계절은 다시 돌아오지만", "N"),
      (1, "떠나간 내 사랑은 어디에", "N"),
      (1, "내가 떠나보낸 것도 아닌데", "N"),
      (1, "내가 떠나온 것도 아닌데", "N"),
      (1, "조금씩 잊혀져 간다", "N"),
      (2, "Waking up in your bed", "N"),
      (2, "It's almost like I've been here forever", "O"),
      (2, "What if we moved to Canada and buy some things we don't need", "H"),
      (3, "자꾸 잦아지는 다툼", "N"),
      (3, "안아줄래 내 마음을", "O"),
      (3, "오늘은 검정색의 하트를 보내", "H"),
      (4, "익숙함에 진심을 속이지 말자", "O"),
      (4, "사건의 지평선 너머로", "H"),
      (4, "마지막 선물은 산뜻한 안녕", "N"),
      (5, "멈춘 시간 속 잠든 너를 찾아가", "O"),
      (5, "다시 way back home", "H"),
      (5, "조용히 잠든 방을 열어", "N"),
      (6, "Woo woo woo woo ooh", "N"),
      (6, "Do you think about me now yeah", "O"),
      (6, "Ra-ta-ta-ta 울린 심장 (Ra-ta-ta-ta)", "H");

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
