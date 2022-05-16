/*
INSERT INTO roles (id, rolename) VALUES (1, 'ADMIN'),
                                        (2, 'USER');
INSERT INTO users (id, member_id, password, name, nickname, address, search_address, phone, sex, birth, license, exp, point) VALUES
(3, 'admin', '$2a$10$41lQC1sYC4N97nfnlLIdK.iLu4YU.fkrbGCh0XnlDQT1aLjCv3d4O','어드민', 'imAdmin', '경기도 안양시 동안구 관악대로 263번길 10-8', '경기 안양시 동안구 비산동','010-1111-2222', 'F', '1999-11-01','N','Y',0),
(4, 'user01', '$2a$10$QuYYcgaP6LFfV53Qssvtp.hcj1BJVmgzO7pCValc27jm4SsI25Lf2','김철수', '철수찰스', '경기도 수원시', '경기 수원시 장안구 영화동', '010-3344-5663','M', '1999-07-01','N','Y',100);

insert into user_role(user_id, role_id) values (3,1), (3,2), (4,2);
update hibernate_sequence set next_val= 5;


*/
