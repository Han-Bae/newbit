-- 시스템 계정으로 실행

-- 뉴빗 계정 생성
CREATE ACCOUNT newbit IDENTIFIED BY 12345;
-- 데이터베이스 접속 권한
GRANT CREATE SESSION TO newbit;
-- 모든 권한 주기
GRANT CONNECT, RESOURCE, DBA TO newbit;
commit;


-- newbit 계정으로 실행
-- ACCOUNT 관련 테이블 생성

	-- 이메일 확인 테이블
CREATE TABLE email(
    email VARCHAR2(50 CHAR)
        CONSTRAINT MAIL_PK PRIMARY KEY
        CONSTRAINT MAIL_NN NOT NULL,
    isokay CHAR(1) DEFAULT 'N'
        CONSTRAINT MAIL_OKAY_CK CHECK(isokay IN('Y','N'))
        CONSTRAINT MAIL_OKAY_NN NOT NULL,
    ck_mail NUMBER(4)
        CONSTRAINT MAIL_CK NOT NULL
);

drop table account;
	-- 사용자 테이블
CREATE TABLE account(
    no NUMBER(4)
        CONSTRAINT ACCOUNT_NO_PK PRIMARY KEY,
    id VARCHAR2(20 CHAR)
        CONSTRAINT ACCOUNT_NAME_UK UNIQUE
        CONSTRAINT ACCOUNT_NAME_NN NOT NULL,
    pw VARCHAR2(20 CHAR)
        CONSTRAINT ACCOUNT_PW_NN NOT NULL,
    nickname VARCHAR2(15 CHAR)
        CONSTRAINT ACCOUNT_NICK_UK UNIQUE
        CONSTRAINT ACCOUNT_NICK_NN NOT NULL,
    email VARCHAR2(50 CHAR)
        CONSTRAINT ACCOUNT_MAIL_FK REFERENCES email(email)
        CONSTRAINT ACCOUNT_MAIL_NN NOT NULL,
    istype CHAR(1)
        CONSTRAINT ACCOUNT_TYPE_CK CHECK(istype IN('U','D','A'))
        CONSTRAINT ACCOUNT_TYPE_NN NOT NULL,
    isshow CHAR(1) DEFAULT 'Y'
        CONSTRAINT ACCOUNT_SHOW_CK CHECK(isshow IN('Y','N'))
        CONSTRAINT ACCOUNT_SHOW_NN NOT NULL,
    joindate DATE DEFAULT sysdate
        CONSTRAINT ACCOUNT_JDATE_NN NOT NULL
);

	-- 아바타 정보 테이블
CREATE TABLE img(
   no NUMBER(4)
        CONSTRAINT IMG_NO_PK PRIMARY KEY,
   savename VARCHAR2(20 CHAR)
        CONSTRAINT IMG_SAVENAME_UK UNIQUE
        CONSTRAINT IMG_SAVENAME_NN NOT NULL,
   account_no NUMBER(4)
        CONSTRAINT IMG_ACCOUNTNUM_FK REFERENCES account(no)
        CONSTRAINT IMG_ACCOUNTNUM_NN NOT NULL
);

	-- 찜 테이블
CREATE TABLE pick(
   account_no NUMBER(4)
        CONSTRAINT PICK_ACCOUNTNUM_FK REFERENCES account(no)
        CONSTRAINT PICK_ACCOUNTNUM_NN NOT NULL,
   game_id VARCHAR2(15 CHAR),		-- API확인
    isnewbit CHAR(1)
        CONSTRAINT PICK_NEWBIT_CK CHECK(isnewbit IN('Y','N'))
        CONSTRAINT PICK_NEWBIT_NN NOT NULL,
    isshow CHAR(1)
        CONSTRAINT PICK_SHOW_CK CHECK(isshow IN('Y','N'))
        CONSTRAINT PICK_SHOW_NN NOT NULL
);

	-- 장바구니 테이블
CREATE TABLE basket(
   account_no NUMBER(4)
        CONSTRAINT BASKET_ACCOUNTNUM_FK REFERENCES account(no)
        CONSTRAINT BASKET_ACCOUNTNUM_NN NOT NULL,
   game_id VARCHAR2(15 CHAR),	-- API 확인
   isnewbit CHAR(1)
        CONSTRAINT BASKET_NEWBIT_CK CHECK(isnewbit IN('Y','N'))
        CONSTRAINT BASKET_NEWBIT_NN NOT NULL,
   isshow CHAR(1)
        CONSTRAINT BASKET_SHOW_CK CHECK(isshow IN('Y','N'))
        CONSTRAINT BASKET_SHOW_NN NOT NULL
);

	-- 친구 관리 테이블
CREATE TABLE follower(
   me NUMBER(4)
       CONSTRAINT FOLLOW_ME_FK REFERENCES account(no)
       CONSTRAINT FOLLOW_ME_NN NOT NULL,
   friend NUMBER(4)
       CONSTRAINT FOLLOW_FRIEND_FK REFERENCES account(no)
       CONSTRAINT FOLLOW_FRIEND_NN NOT NULL,
   isshow CHAR(1) DEFAULT 'Y'
        CONSTRAINT FOLLOW_SHOW_CK CHECK(isshow IN('Y','N'))
        CONSTRAINT FOLLOW_SHOW_NN NOT NULL
);

--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------

-- 태그(장르) 관련 테이블 생성
CREATE TABLE tag(
    no NUMBER(4)
	CONSTRAINT TAG_NO_PK PRIMARY KEY,
	category NUMBER(4),
    name VARCHAR2(10 CHAR)
	CONSTRAINT TAG_NAME_UK UNIQUE
	CONSTRAINT TAG_NAME_NN NOT NULL
);
	-- 이용자 선호장르 테이블
CREATE TABLE account_tag(
   account_no NUMBER(4)
        CONSTRAINT UTAG_ACCOUNTNUM_FK REFERENCES account(no)
        CONSTRAINT UTAG_ACCOUNTNUM_NN NOT NULL,
   tag_no NUMBER(4)
        CONSTRAINT UTAG_TAGNUM_FK REFERENCES tag(no)
        CONSTRAINT UTAG_TAGNUM_NN NOT NULL 
);
	-- 독점게임 장르 테이블
CREATE TABLE newbit_tag(
   newbit_no VARCHAR2(15 CHAR)
	CONSTRAINT NBTAG_NBNUM_FK REFERENCES newbit_game(id)
	CONSTRAINT NBTAG_NBNUM_NN NOT NULL,
   tag_no NUMBER(4)
        CONSTRAINT NBTAG_TAGNUM_FK REFERENCES tag(no)
        CONSTRAINT NBTAG_TAGNUM_NN NOT NULL
);

--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------


-- 게임 관련 테이블 생성
	-- 독점 게임 테이블
CREATE TABLE newbit_game(
    id VARCHAR2(15 CHAR)
        CONSTRAINT NB_ID_PK PRIMARY KEY,
   account_no NUMBER(4)
        CONSTRAINT NB_ACCOUNTNUM_FK REFERENCES account(no)
        CONSTRAINT NB_ACCOUNTNUM_NN NOT NULL,
   name VARCHAR2(50 CHAR)
        CONSTRAINT NB_NAME_NN NOT NULL,
   price VARCHAR2(10 CHAR)
        CONSTRAINT NB_PRICE_NN NOT NULL,
   describe VARCHAR2(4000)		-- 엄청 긺
        CONSTRAINT NB_DESCRIBE_NN NOT NULL,
   imgname VARCHAR2(20 CHAR)
        CONSTRAINT NB_IMGNAME_UK UNIQUE
        CONSTRAINT NB_IMGNAME_NN NOT NULL,
   registdate DATE DEFAULT sysdate
        CONSTRAINT NB_RDATE_NN NOT NULL,
   isshow CHAR(1) DEFAULT 'Y'
        CONSTRAINT NB_SHOW_CK CHECK(isshow IN('Y','N'))
        CONSTRAINT NB_SHOW_NN NOT NULL
);

	-- 라이브러리 테이블
CREATE TABLE library(
   no NUMBER(4)
	CONSTRAINT LIB_NO_PK PRIMARY KEY,
   account_no NUMBER(4)
        CONSTRAINT LIB_ACCOUNTNUM_FK REFERENCES account(no)
        CONSTRAINT LIB_ACCOUNTNUM_NN NOT NULL,
   game_id VARCHAR2(15 CHAR),	-- API 확인
   playtime VARCHAR2(15 CHAR)
        CONSTRAINT LIB_PLAYTIME_NN NOT NULL,
    isnewbit CHAR(1)
        CONSTRAINT LIB_NEWBIT_CK CHECK(isnewbit IN('Y','N'))
        CONSTRAINT LIB_NEWBIT_NN NOT NULL,
   isshow CHAR(1) DEFAULT 'Y'
        CONSTRAINT LIB_SHOW_CK CHECK(isshow IN('Y','N'))
        CONSTRAINT LIB_SHOW_NN NOT NULL,
   buydate DATE DEFAULT sysdate
        CONSTRAINT LIB_BDATE_NN NOT NULL
);

	-- 등록된 도전과제
CREATE TABLE newbit_challenge(
   no NUMBER(4)
	CONSTRAINT NBC_NO_PK PRIMARY KEY,
   newbit_id VARCHAR2(15 CHAR)
	CONSTRAINT NBC_NBNUM_FK REFERENCES newbit_game(id)
	CONSTRAINT NBC_NBNUM_NN NOT NULL,
   body VARCHAR2(10 CHAR)
	CONSTRAINT NBC_BODY_NN NOT NULL,
   isshow CHAR(1) DEFAULT 'Y'
        CONSTRAINT NBC_SHOW_CK CHECK(isshow IN('Y','N'))
        CONSTRAINT NBC_SHOW_NN NOT NULL
);

	-- 사용자의 도전과제
CREATE TABLE account_challenge(
   account_no NUMBER(4)
	CONSTRAINT ACCOUNTC_ACCOUNTNUM_FK REFERENCES account(no)
	CONSTRAINT ACCOUNTC_ACCOUNTNUM_NN NOT NULL,
   newbit_id  VARCHAR2(15 CHAR)
	CONSTRAINT ACCOUNTC_NBNUM_FK REFERENCES newbit_game(id)
	CONSTRAINT ACCOUNTC_NBNUM_NN NOT NULL,
   cleardate DATE,
   isclear CHAR(1) DEFAULT 'N'
        CONSTRAINT ACCOUNTC_CLEAR_CK CHECK(isclear IN('Y','N'))
        CONSTRAINT ACCOUNTC_CLEAR_NN NOT NULL
);
	-- 할인 테마 테이블
CREATE TABLE theme(
   no NUMBER(4)
	CONSTRAINT THEME_NO_PK PRIMARY KEY,
   name VARCHAR2(20 CHAR)
	CONSTRAINT THEME_NAME_NN NOT NULL,
   startdate DATE
	CONSTRAINT THEME_START_NN NOT NULL,
   enddate DATE
	CONSTRAINT THEME_END_NN NOT NULL,
   isshow CHAR(1) DEFAULT 'Y'
        CONSTRAINT THEME_SHOW_CK CHECK(isshow IN('Y','N'))
        CONSTRAINT THEME_SHOW_NN NOT NULL
);

	-- 할인 정보 테이블
CREATE TABLE sale(
   no NUMBER(4)
	CONSTRAINT SALE_NO_PK PRIMARY KEY,
   newbit_id VARCHAR2(15 CHAR)
	CONSTRAINT SALE_NBNUM_FK REFERENCES newbit_game(id)
	CONSTRAINT SALE_NBNUM_NN NOT NULL,
   rate NUMBER(3) DEFAULT 0
	CONSTRAINT SALE_RATE_NN NOT NULL,
   theme_no NUMBER(4)
	CONSTRAINT SALE_THMNUM_FK REFERENCES theme(no),
   isshow CHAR(1) DEFAULT 'N'
        CONSTRAINT SALE_SHOW_CK CHECK(isshow IN('Y','N'))
        CONSTRAINT SALE_SHOW_NN NOT NULL
);

	-- 배너 테이블
CREATE TABLE banner(
   no NUMBER(4)
	CONSTRAINT BANNER_NO_PK PRIMARY KEY,
   savename VARCHAR2(20 CHAR)
	CONSTRAINT BANNER_SAVENAME_UK UNIQUE
	CONSTRAINT BANNER_SAVENAME_NN NOT NULL,
   isshow CHAR(1) DEFAULT 'Y'
	CONSTRAINT BANNER_SHOW_CK CHECK(isshow IN('Y','N'))
	CONSTRAINT BANNER_SHOW_NN NOT NULL
);

--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------

-- 신고 테이블
CREATE TABLE report(
   no NUMBER(4)
	CONSTRAINT REPORT_NO_PK PRIMARY KEY,
   account_no NUMBER(4)
	CONSTRAINT REPORT_ACCOUNTNUM_FK REFERENCES account(no)
	CONSTRAINT REPORT_ACCOUNTNUM_NN NOT NULL,
   reason VARCHAR2(15 CHAR)
	CONSTRAINT REPORT_REASON_NN NOT NULL,
   reportdate DATE DEFAULT sysdate
	CONSTRAINT REPORT_RDATE_NN NOT NULL,
			-- 스크린샷, 후기, 게임
   link VARCHAR2(4000)		-- 신고한 게시글 링크
	CONSTRAINT REPORT_LINK_NN NOT NULL,
   istype CHAR(1)
	CONSTRAINT REPORT_TYPE_CK CHECK(istype IN('S', 'R', 'G'))
	CONSTRAINT REPORT_TYPE_NN NOT NULL,
   ischeck CHAR(1) DEFAULT 'N'
	CONSTRAINT REPORT_CHECK_CK CHECK(ischeck IN('Y','N'))
	CONSTRAINT REPORT_CHECK_NN NOT NULL
);
	-- 사유가 기타일때 사용되는 테이블
CREATE TABLE etc(
   report_no NUMBER(4)
	CONSTRAINT ETC_NO_FK REFERENCES report(no)
	CONSTRAINT ETC_NO_NN NOT NULL,
   body VARCHAR2(150 CHAR)
	CONSTRAINT ETC_BODY_NN NOT NULL
);


--------------------------------------------------------------------------


-- 후기 및 스크린샷 테이블
	-- 후기 테이블
CREATE TABLE review( 
   no NUMBER(4)
        CONSTRAINT REVIEW_NO_PK PRIMARY KEY,
   account_no NUMBER(4)
    	CONSTRAINT REVIEW_ACCOUNTNUM_FK REFERENCES account(no)
        CONSTRAINT REVIEW_ACCOUNTNUM_NN NOT NULL,
    game_id VARCHAR2(15 CHAR), --API확인
    body VARCHAR2(4000)
        CONSTRAINT REVIEW_BODY_NN NOT NULL,
    registdate DATE DEFAULT sysdate
        CONSTRAINT REVIEW_RDATE_NN NOT NULL,
    isnewbit CHAR(1)
    	CONSTRAINT REVIEW_NB_CK CHECK(isnewbit IN('Y','N'))
        CONSTRAINT REVIEW_NB_NN NOT NULL,
    isgood CHAR(1)
    	CONSTRAINT REVIEW_GOOD_CK CHECK(isgood IN('G','B'))
        CONSTRAINT REVIEW_GOOD_NN NOT NULL,		
    isshow CHAR(1) DEFAULT 'Y'
    	CONSTRAINT REVIEW_SHOW_CK CHECK(isshow IN('Y','N'))
        CONSTRAINT REVIEW_SHOW_NN NOT NULL
);

	-- 후기 테이블 좋아요 싫어요 수
CREATE TABLE review_gb(
   account_no NUMBER(4)
	CONSTRAINT REGB_ACCOUNTNUM_FK REFERENCES account(no)
	CONSTRAINT REGB_ACCOUNTNUM_NN NOT NULL,
   review_no NUMBER(4)
	CONSTRAINT REGB_RENUM_FK REFERENCES review(no)
	CONSTRAINT REGB_RENUM_NN NOT NULL,
   good NUMBER(4) DEFAULT 0
	CONSTRAINT REGB_GOOD_NN NOT NULL,
   bad NUMBER(4) DEFAULT 0
	CONSTRAINT REGB_BAD_NN NOT NULL
);

	-- 스크린샷 테이블
CREATE TABLE screenshot(
    no NUMBER(4)
        CONSTRAINT SCREEN_NO_PK PRIMARY KEY,
    account_no NUMBER(4)
        CONSTRAINT SCREEN_ACCOUNTNUM_FK REFERENCES account(no)
        CONSTRAINT SCREEN_ACCOUNTNUM_NN NOT NULL,
    game_id VARCHAR2(15 CHAR),
    registdate DATE DEFAULT sysdate
        CONSTRAINT SCREEN_RDATE_NN NOT NULL,
    isnewbit CHAR(1)
        CONSTRAINT SCREEN_NB_CK CHECK(isnewbit IN('Y','N'))
        CONSTRAINT SCREEN_NB_NN NOT NULL,
    isshow CHAR(1) DEFAULT 'Y'
        CONSTRAINT SCREEN_SHOW_CK CHECK(isshow IN('Y','N'))
        CONSTRAINT SCREEN_SHOW_NN NOT NULL
);
	-- 스크린샷 테이블 좋아요 싫어요 수
CREATE TABLE screenshot_gb(
   ss_no NUMBER(4)
	CONSTRAINT SSGB_SSNUM_FK REFERENCES screenshot(no)
	CONSTRAINT SSGB_SSNUM_NN NOT NULL,
   good NUMBER(4) DEFAULT 0
	CONSTRAINT SSGB_GOOD_NN NOT NULL,
   bad NUMBER(4) DEFAULT 0
	CONSTRAINT SSGB_BAD_NN NOT NULL
);
	-- 스크린샷 테이블 스크린샷 정보
CREATE TABLE screenshot_img(
   ss_no NUMBER(4)
	CONSTRAINT SSIMG_SSNUM_FK REFERENCES screenshot(no)
	CONSTRAINT SSIMG_SSNUM_NN NOT NULL,
   savename VARCHAR2(20CHAR)
	CONSTRAINT SSIMG_SAVENAME_UK UNIQUE
	CONSTRAINT SSIMG_SAVENAME_NN NOT NULL
);
	-- 스크린샷 테이블 댓글 정보
CREATE TABLE screenshot_reply(
   no NUMBER(4)
	CONSTRAINT SSRE_NO_PK PRIMARY KEY,
   reply_no NUMBER(4)
	CONSTRAINT SSRE_REPLYNUM_FK REFERENCES account(no)
	CONSTRAINT SSRE_REPLYNUM_NN NOT NULL,
   ss_no NUMBER(4)
	CONSTRAINT SSRE_SSNUM_FK REFERENCES screenshot(no)
	CONSTRAINT SSRE_SSNUM_NN NOT NULL,
   body VARCHAR2(20CHAR)
	CONSTRAINT SSRE_BODY_NN NOT NULL,
   registdate DATE DEFAULT sysdate
	CONSTRAINT SSRE_RDATE_NN NOT NULL,
    isshow CHAR(1) DEFAULT 'Y'
	CONSTRAINT SSRE_SHOW_CK CHECK(isshow IN('Y','N'))
	CONSTRAINT SSRE_SHOW_NN NOT NULL
);

-- 결제 내역 저장 테이블
CREATE TABLE payhistory(
	imp_uid VARCHAR2(50 CHAR)
		CONSTRAINT PAY_IMP_NN NOT NULL,
	merchant_uid VARCHAR2(50 CHAR)
		CONSTRAINT PAY_MERCHANT_NN NOT NULL,
	game_id VARCHAR2(15 CHAR)
		CONSTRAINT PAY_GAMEID_NN NOT NULL,
    game_price NUMBER(10)
        CONSTRAINT PAY_PRICE_NN NOT NULL,
   account_no NUMBER(4) -- 게임 가진 사람
        CONSTRAINT PAY_ACCOUNTNUM_FK REFERENCES account(no)
        CONSTRAINT PAY_ACCOUNTNUM_NN NOT NULL,
    buy_no NUMBER(4)    -- 구매자
        CONSTRAINT PAY_BUYER_FK REFERENCES account(no),
    paydate DATE DEFAULT sysdate
        CONSTRAINT PAY_DATE_NN NOT NULL,
    isrefund CHAR(1) DEFAULT 'N'
        CONSTRAINT PAY_REFUND_CK CHECK(isrefund IN('Y', 'N'))
        CONSTRAINT PAY_REFUND_NN NOT NULL
);
commit;