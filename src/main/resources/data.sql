/*
INSERT INTO roles (id, rolename) VALUES (1, 'ADMIN'),
                                        (2, 'USER');

INSERT INTO users (id, member_id, password, name, nickname, address, search_address, phone, sex, birth, license, exp, point) VALUES
(3, 'admin', '$2a$10$41lQC1sYC4N97nfnlLIdK.iLu4YU.fkrbGCh0XnlDQT1aLjCv3d4O','어드민', 'imAdmin', '경기도 안양시 동안구 관악대로 263번길 10-8', '경기 안양시 동안구 비산동','010-1111-2222', 'F', '1999-11-01','N','Y',0),
(4, 'user01', '$2a$10$QuYYcgaP6LFfV53Qssvtp.hcj1BJVmgzO7pCValc27jm4SsI25Lf2','김철수', '철수찰스', '경기도 수원시', '경기 수원시 장안구 영화동', '010-3344-5663','M', '1999-07-01','N','Y',100),
(5, 'admin2', '$2a$10$OBpYcVZxwzz1AuwIHFv5k.bFkx1IS4pW989XuXH2VJDJxKDVaTPF6', '김민수','어드민22','서울시 동작구','서울시 동작구','010-2222-3333','M','1999-02-20','Y','Y',0);



insert into pet(pet_id,name,sex,birth,kind,neutering,vaccination,personality,img_url,user_id)values
    (1,'milky','F','2021-05-21','진도','Y','Y','온순','yyyyyyyy',5);
insert into pet(pet_id,name,sex,birth,kind,neutering,vaccination,personality,img_url, user_id)values
    (2,'choco','F','2021-04-10','푸들','Y','Y','소심','yyyyyyyy',5);

insert into post(id, content, posttype, status, member_id) VALUES (1,'산책해주실분구해요','work','ing...',3);
insert into post(id, content, posttype, status, member_id) VALUES (2,'대신산책해드립니다','walk','ing...',4);


insert into user_role(user_id, role_id) values (3,1), (3,2), (4,2),(5,2);
update hibernate_sequence set next_val= 6;
*/
