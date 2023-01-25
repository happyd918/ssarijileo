CREATE DATABASE IF NOT EXISTS ssarijileo;
USE ssarijileo;

/*
	추후 로깅용 테이블 하나 생성 (권장)
*/

-- 사용자 테이블
/*
	탈퇴한 회원 정보는 삭제하지 않고 N년 동안 보관
    위 내용은 이용약관에 공지
*/

CREATE TABLE IF NOT EXISTS `user` (
	`user_id` VARCHAR(13) COMMENT 'PK',
    `email` VARCHAR(30) NOT NULL UNIQUE COMMENT '이메일',
    `nickname` VARCHAR(30) NOT NULL UNIQUE COMMENT '닉네임',
    `token` VARCHAR(255) COMMENT '토큰',
    `image` VARCHAR(255) NOT NULL DEFAULT "default.jpg" COMMENT '프로필이미지',
    `status` CHAR(1) NOT NULL DEFAULT 'A' COMMENT '상태(A:활동회원,X:탈퇴회원,B:차단회원)',
    PRIMARY KEY (`user_id`)
)
ENGINE = InnoDB;

-- 친구 테이블

CREATE TABLE IF NOT EXISTS `friend` (
	`friend_id` INT AUTO_INCREMENT COMMENT 'PK',
	`sending_user_id` VARCHAR(13) NOT NULL COMMENT '보낸사람ID',
	`receiving_user_id` VARCHAR(13) NOT NULL COMMENT '받는사람ID',
	`status` CHAR(1) NOT NULL DEFAULT 'W' COMMENT '상태(W:대기,A:수락,X:취소)',
	PRIMARY KEY (`friend_id`),
	CONSTRAINT `fk_user_friend_1`
		FOREIGN KEY (`sending_user_id`)
		REFERENCES `user` (`user_id`) ON DELETE CASCADE,
	CONSTRAINT `fk_user_friend_2`
		FOREIGN KEY (`receiving_user_id`)
		REFERENCES `user` (`user_id`) ON DELETE CASCADE
  )
  ENGINE = InnoDB;

-- 노래 테이블
/*
	좋아요수는 로그파일로 분리
*/

CREATE TABLE IF NOT EXISTS `song` (
	`song_id` INT AUTO_INCREMENT COMMENT 'PK',
    `title` VARCHAR(30) NOT NULL COMMENT '제목',
    `singer` VARCHAR(30) NOT NULL COMMENT '가수',
    `album` VARCHAR(50) NOT NULL COMMENT '앨범명',
	`time` TIME NOT NULL COMMENT '시간',
	`image` VARCHAR(255) NOT NULL DEFAULT "default.jpg" COMMENT '앨범이미지',
    `release_date` DATE NOT NULL COMMENT '발매일자',
    PRIMARY KEY (`song_id`)
)
ENGINE = InnoDB;

-- 음정 테이블
/*
	퍼펙트스코어 모드에서 사용
    노래 시작 이후 해당 시간에 대한 음표 값 저장
*/

CREATE TABLE IF NOT EXISTS `pitch` (
	`pitch_id` INT AUTO_INCREMENT COMMENT 'PK',
    `song_id` INT NOT NULL COMMENT '노래ID',
    `time` DECIMAL NOT NULL COMMENT '시간',
    `note` VARCHAR(30) NOT NULL COMMENT '음표',
    PRIMARY KEY (`pitch_id`),
	CONSTRAINT `fk_song_pitch`
		FOREIGN KEY (`song_id`)
        REFERENCES `song` (`song_id`) ON DELETE CASCADE
)
ENGINE = InnoDB;

-- 가사 테이블
/*
	이어부르기 모드에서 사용
    상태값이 'O'랑 'H' 인 경우 가사순서맞추기 모드에서 사용
    'H(하이라이트)' 인 경우 가사를 랜덤으로 쪼개기
*/

CREATE TABLE IF NOT EXISTS `lyrics` (
	`lyrics_id` INT AUTO_INCREMENT COMMENT 'PK',
	`song_id` INT NOT NULL COMMENT '노래ID',
    `verse` VARCHAR(255) NOT NULL COMMENT '한소절',
    `status` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '상태(N:기본,O:가사순서맞추기,H:하이라이트)',
    PRIMARY KEY (`lyrics_id`),
	CONSTRAINT `fk_song_lyrics`
		FOREIGN KEY (`song_id`)
        REFERENCES `song` (`song_id`) ON DELETE CASCADE
)
ENGINE = InnoDB;

-- 사용자 노래 테이블

CREATE TABLE IF NOT EXISTS `singing` (
	`singing_id` INT AUTO_INCREMENT COMMENT 'PK',
    `user_id` VARCHAR(13) NOT NULL COMMENT '사용자ID',
    `song_id` INT NOT NULL COMMENT '노래ID',
    `mode` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '모드(N:일반,P:퍼펙트스코어,O:가사순서맞추기,R:이어부르기)',
    `score` INT COMMENT '점수',
    `total_singing_time` TIME NOT NULL COMMENT '총부른시간',
    `singing_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '노래일시',
    PRIMARY KEY (`singing_id`),
    CONSTRAINT `fk_user_singing`
		FOREIGN KEY (`user_id`)
		REFERENCES `user` (`user_id`) ON DELETE CASCADE,
	CONSTRAINT `fk_song_singing`
		FOREIGN KEY (`song_id`)
        REFERENCES `song` (`song_id`) ON DELETE CASCADE
)
ENGINE = InnoDB;

-- 애창곡 테이블
/*
	데이터가 홀수면 애창곡
    짝수면 애창곡 취소
*/

CREATE TABLE IF NOT EXISTS `fovorite_song` (
	`fovorite_song_id` INT AUTO_INCREMENT COMMENT 'PK',
	`user_id` VARCHAR(13) NOT NULL COMMENT '사용자ID',
    `song_id` INT NOT NULL COMMENT '노래ID',
    `is_like` CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '좋아요여부(Y:좋아요,N:좋아요취소)',
    PRIMARY KEY (`fovorite_song_id`),
    CONSTRAINT `fk_user_fovorite_song`
		FOREIGN KEY (`user_id`)
		REFERENCES `user` (`user_id`) ON DELETE CASCADE,
	CONSTRAINT `fk_song_favorite_song`
		FOREIGN KEY (`song_id`)
        REFERENCES `song` (`song_id`) ON DELETE CASCADE
)
ENGINE = InnoDB;

-- 노래 환경설정 테이블

CREATE TABLE IF NOT EXISTS `song_setting` (
	`song_setting_id` VARCHAR(13) COMMENT 'PK',
    `eco` INT NOT NULL DEFAULT 50 COMMENT '에코',
    `volume` INT NOT NULL DEFAULT 50 COMMENT '음량',
    PRIMARY KEY (`song_setting_id`),
	CONSTRAINT `fk_user_song_setting_id`
		FOREIGN KEY (`song_setting_id`)
        REFERENCES `user` (`user_id`) ON DELETE CASCADE
)
ENGINE = InnoDB;

-- 녹화 테이블

CREATE TABLE IF NOT EXISTS `recording` (
	`recording_id` INT AUTO_INCREMENT COMMENT 'PK',
	`user_id` VARCHAR(13) NOT NULL COMMENT '사용자ID',
    `song_id` INT NOT NULL COMMENT '노래ID',
    `file` VARCHAR(255) NOT NULL COMMENT '녹화파일',
    `register_date` DATE COMMENT '날짜',
    PRIMARY KEY (`recording_id`),
    CONSTRAINT `fk_user_recording`
		FOREIGN KEY (`user_id`)
		REFERENCES `user` (`user_id`) ON DELETE CASCADE,
	CONSTRAINT `fk_song_recording`
		FOREIGN KEY (`song_id`)
        REFERENCES `song` (`song_id`) ON DELETE CASCADE
)
ENGINE = InnoDB;

-- 노래자랑 테이블
/*
	좋아요수는 로그파일로 분리
*/

CREATE TABLE IF NOT EXISTS `singing_contest` (
	`singing_contest_id` INT AUTO_INCREMENT COMMENT 'PK',
    `recording_id`  INT NOT NULL COMMENT '녹화ID',
    `image` VARCHAR(255) NOT NULL DEFAULT "default.jpg" COMMENT '이미지',
	`register_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
    `status` CHAR(1) NOT NULL DEFAULT 'V' COMMENT '상태(V:노출,D:삭제,B:신고)',
    PRIMARY KEY (`singing_contest_id`),
	CONSTRAINT `fk_recording_singing_contest`
		FOREIGN KEY (`recording_id`)
        REFERENCES `recording` (`recording_id`) ON DELETE CASCADE
)
ENGINE = InnoDB;

