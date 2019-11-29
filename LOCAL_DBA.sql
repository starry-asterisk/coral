CREATE TABLE tbl_product (
product_id NUMBER,              -- ��ǰ ��ȣ
product_name VARCHAR2(50),      -- ��ǰ �̸�
product_price NUMBER DEFAULT 0, -- ��ǰ ����
product_desc VARCHAR2(500),     -- ��ǰ �󼼼���
product_url VARCHAR2(500),      -- ��ǰ ����
PRIMARY KEY(product_id)
);


INSERT INTO tbl_product VALUES (1,'����Ű',100000,'����Ű 2017�� �Ż���ǰ�Դϴ�.','nike.png');
INSERT INTO tbl_product VALUES (2,'�Ƶ�ٽ�',80000,'�Ƶ�ٽ��� ���׵� ����!','adidas.png');
INSERT INTO tbl_product VALUES (3,'���߶���',110000,'���߶����� 2016�� �ְ��� �Ź�','newbalance.png');
INSERT INTO tbl_product VALUES (4,'Ǫ��',98000,'Ǫ�� 30���� Ư������ ��ǰ�Դϴ�.','puma.png');
INSERT INTO tbl_product VALUES (5,'��������',150000,'�������� ���׵� ����! Ư������ ���Դϴ�.','timberland.png');
INSERT INTO tbl_product VALUES (6,'����Ʈ',99000,'����� ���� ����Ʈ�Դϴ�.','rockport.png');
INSERT INTO tbl_product VALUES (7,'����',120000,'2017 �Ż� ǻ�� �԰�Ǿ����ϴ�.','reebok.png');
INSERT INTO tbl_product VALUES (8,'������',60000,'������ Ư������ ���Դϴ�.','converse.png');

commit;




-- ��ٱ��� ���̺� ����
CREATE TABLE tbl_cart(
cart_id NUMBER NOT NULL PRIMARY KEY,
user_id VARCHAR2(50) NOT NULL,
product_id NUMBER NOT NULL,
amount NUMBER DEFAULT 0
);

-- ��ٱ��� ���̺� ������ ����
CREATE SEQUENCE seq_cart START WiTH 10 INCREMENT BY 1;
COMMIT;

-- ��ٱ��� ���̺� ��������(�ܷ�Ű) ����
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
    
  
insert into tbl_admin(user_id, user_pw, user_name, user_email) values('0000', '1234', '������', '1234');  
    
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

-- ������ ���̺� ������ ����
CREATE SEQUENCE seq_schedule START WiTH 10 INCREMENT BY 1;
COMMIT;

alter table  tbl_schedule add (schedule_content varchar(2000));
-- ������ ���̺� ��������(�ܷ�Ű) ����
ALTER TABLE tbl_schedule ADD CONSTRAINT schedule_admin_id_fk FOREIGN KEY(user_id) REFERENCES tbl_admin(user_id);
ALTER TABLE tbl_cart ADD CONSTRAINT cart_product_fk FOREIGN KEY(product_id) REFERENCES tbl_product(product_id);
COMMIT;


select * from tbl_schedule;

insert into tbl_schedule(schdule_id, user_id, schedule_name, schedule_content) values(seq_schedule.NEXTVAL, '0000', '���� �׽�Ʈ', '���� �׽�Ʈ �Դϴ�.');





select * from tbl_board;