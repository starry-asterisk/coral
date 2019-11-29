CREATE TABLE tbl_product (
product_id NUMBER,              -- 상품 번호
product_name VARCHAR2(50),      -- 상품 이름
product_price NUMBER DEFAULT 0, -- 상품 가격
product_desc VARCHAR2(500),     -- 상품 상세설명
product_url VARCHAR2(500),      -- 상품 사진
PRIMARY KEY(product_id)
);


INSERT INTO tbl_product VALUES (1,'나이키',100000,'나이키 2017년 신상제품입니다.','nike.png');
INSERT INTO tbl_product VALUES (2,'아디다스',80000,'아디다스의 스테디 셀러!','adidas.png');
INSERT INTO tbl_product VALUES (3,'뉴발란스',110000,'뉴발란스의 2016년 최고의 신발','newbalance.png');
INSERT INTO tbl_product VALUES (4,'푸마',98000,'푸마 30프로 특가할인 제품입니다.','puma.png');
INSERT INTO tbl_product VALUES (5,'팀버랜드',150000,'팀버랜드 스테디 셀러! 특별할인 중입니다.','timberland.png');
INSERT INTO tbl_product VALUES (6,'락포트',99000,'편안한 로퍼 락포트입니다.','rockport.png');
INSERT INTO tbl_product VALUES (7,'리복',120000,'2017 신상 퓨리 입고되었습니다.','reebok.png');
INSERT INTO tbl_product VALUES (8,'컨버스',60000,'컨버스 특가할인 중입니다.','converse.png');

commit;




-- 장바구니 테이블 생성
CREATE TABLE tbl_cart(
cart_id NUMBER NOT NULL PRIMARY KEY,
user_id VARCHAR2(50) NOT NULL,
product_id NUMBER NOT NULL,
amount NUMBER DEFAULT 0
);

-- 장바구니 테이블 시퀀스 생성
CREATE SEQUENCE seq_cart START WiTH 10 INCREMENT BY 1;
COMMIT;

-- 장바구니 테이블 제약조건(외래키) 생성
ALTER TABLE tbl_cart ADD CONSTRAINT cart_userid_fk FOREIGN KEY(user_id) REFERENCES tbl_member(user_id);
ALTER TABLE tbl_cart ADD CONSTRAINT cart_product_fk FOREIGN KEY(product_id) REFERENCES tbl_product(product_id);
COMMIT;


CREATE TABLE tbl_admin (
    user_id VARCHAR(50) NOT NULL,
    user_pw VARCHAR(50) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    user_email VARCHAR(100),
    user_regdate DATE DEFAULT SYSDATE,
    user_updatedate DATE DEFAULT SYSDATE,
    PRIMARY KEY(user_id));
    
  
insert into tbl_admin(user_id, user_pw, user_name, user_email) values('0000', '1234', '관리자', '1234');  
    
CREATE TABLE tbl_member (
    user_id VARCHAR(50) NOT NULL,
    user_pw VARCHAR(50) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    user_email VARCHAR(100),
    user_regdate DATE DEFAULT SYSDATE,
    user_updatedate DATE DEFAULT SYSDATE,
    PRIMARY KEY(user_id));
    
    
CREATE TABLE tbl_schedule(
schdule_id NUMBER NOT NULL PRIMARY KEY,
user_id VARCHAR2(50) NOT NULL,
schedule_name VARCHAR(50) NOT NULL,
start_date DATE DEFAULT SYSDATE,
end_date DATE DEFAULT SYSDATE
);

-- 스케쥴 테이블 시퀀스 생성
CREATE SEQUENCE seq_schedule START WiTH 10 INCREMENT BY 1;
COMMIT;

alter table  tbl_schedule add (schedule_content varchar(2000));
-- 스케쥴 테이블 제약조건(외래키) 생성
ALTER TABLE tbl_schedule ADD CONSTRAINT schedule_admin_id_fk FOREIGN KEY(user_id) REFERENCES tbl_admin(user_id);
ALTER TABLE tbl_cart ADD CONSTRAINT cart_product_fk FOREIGN KEY(product_id) REFERENCES tbl_product(product_id);
COMMIT;


select * from tbl_schedule;

insert into tbl_schedule(schdule_id, user_id, schedule_name, schedule_content) values(seq_schedule.NEXTVAL, '0000', '일정 테스트', '일정 테스트 입니다.');





select * from tbl_board;