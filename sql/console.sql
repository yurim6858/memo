show databases;
create database academy;
use academy;
show tables;

create table major
(
    major_code varchar(100) primary key comment '주특기코드',
    major_name varchar(100) not null comment '주특기명',
    tutor_name varchar(100) not null comment '튜터'
);

create table exam
(
    student_code varchar(100) not null comment '수강생코드',
    exam_seq int not null comment '시험주차',
    score decimal(10,2) not null comment '시험점수',
    result varchar(1) not null comment '합불'
);

CREATE TABLE student(
    student_code VARCHAR(100) PRIMARY KEY COMMENT '수강생코드',
    name VARCHAR(100) NOT NULL COMMENT '이름',
    birth VARCHAR(8) NULL COMMENT '생일',
    gender VARCHAR(1) NOT NULL COMMENT '성별',
    phone VARCHAR(11) NULL COMMENT '전화번호',
    major_code VARCHAR(100) NOT NULL COMMENT '주특기코드',
    FOREIGN KEY(major_code) references major(major_code)
);

alter table exam
    add primary key(student_code, exam_seq);
alter table exam
    add constraint exam_fk_student_code foreign key (student_code) references student(student_code);

#DML
INSERT INTO major value('m1', '스프링','김남일');
INSERT INTO major value('m2', '리엑트','김남이');
INSERT INTO major value('m3', '노드','김남삼');
INSERT INTO major value('m4', 'HTML','김남사');
INSERT INTO major value('m5', '자바','김남오');
INSERT INTO major value('m6', '루비온레일즈','김남육');
INSERT INTO major value('m7', '뷰','김남칠');
INSERT INTO major value('m8', '앵귤러','김남팔');

INSERT INTO student value('s1','최원빈','20050331','M','01091919191','m1');
INSERT INTO student value('s2','최투빈','20050330','M','01091919192','m1'),
    ('s3','최삼빈','20050329','M','01091919193','m1');
INSERT INTO student value('s4','최사빈','20050330','M','01091919194','m1'),
    ('s5','최오빈','20050329','M','01091919195','m1'),
    ('s6','최육빈','20050329','M','01091919196','m1'),
    ('s7','최칠빈','20050329','M','01091919197','m1'),
    ('s8','최팔빈','20050329','M','01091919198','m1'),
    ('s9','최구빈','20050329','M','01091919199','m1');

INSERT INTO student(student_code,name,gender,major_code) values('s10','가아아','F','m2');
INSERT INTO student(student_code,name,gender,major_code) values('s11','오오오','F','m3');

INSERT INTO STUDENT(student_code, name, gender, major_code) VALUES('s12', '권오빈', 'M', 'm3');
INSERT INTO STUDENT VALUES('s13', '김가은', '20220121', 'F', '01000000030', 'm1');

INSERT INTO STUDENT(student_code, name, gender, major_code) VALUES('s14', '김동현', 'M', 'm4');
INSERT INTO STUDENT VALUES('s15', '박은진', '20221101', 'F', '01000000040', 'm1');

INSERT INTO STUDENT(student_code, name, birth, gender, phone, major_code) VALUES('s16', '정영호', '20221105', 'M', '01000000050', 'm5');
INSERT INTO STUDENT(student_code, name, gender, major_code) VALUES('s17', '박가현', 'F', 'm7');
INSERT INTO STUDENT(student_code, name, birth, gender, phone, major_code) VALUES('s18', '박용태', '20220508', 'M', '01000000060', 'm6');

INSERT INTO STUDENT VALUES('s19', '김예지', '20220505', 'F', '01000000070', 'm2');
INSERT INTO STUDENT VALUES('s20', '윤지용', '20220909', 'M', '01000000080', 'm3');
INSERT INTO STUDENT VALUES('s21', '손윤주', '20220303', 'F', '01000000090', 'm6');

INSERT INTO EXAM VALUES('s1', 1, 8.5, 'P'),
('s1', 2, 9.5, 'P'),
('s1', 3, 3.5, 'F'),
('s2', 1, 8.2, 'P'),
('s2', 2, 9.5, 'P'),
('s2', 3, 7.5, 'P'),
('s3', 1, 9.3, 'P'),
('s3', 2, 5.3, 'F'),
('s3', 3, 9.9, 'P'),
('s4', 1, 8.4, 'P'),
('s5', 1, 9.5, 'P'),
('s5', 2, 3.5, 'F'),
('s6', 1, 8.3, 'P'),
('s7', 1, 9.2, 'P'),
('s7', 2, 9.9, 'P'),
('s7', 3, 3.6, 'F'),
('s8', 1, 8.4, 'P'),
('s9', 1, 9.7, 'P'),
('s10', 1, 8.4, 'P'),
('s10', 2, 9.8, 'P'),
('s10', 3, 8.4, 'P'),
('s11', 1, 8.6, 'P'),
('s12', 1, 9.2, 'P'),
('s13', 1, 8.1, 'P'),
('s13', 2, 9.5, 'P'),
('s13', 3, 2.1, 'F'),
('s14', 1, 9.2, 'P'),
('s15', 1, 9.7, 'P'),
('s15', 2, 1.7, 'F'),
('s16', 1, 8.4, 'P'),
('s17', 1, 9.3, 'P'),
('s17', 2, 9.9, 'P'),
('s17', 3, 1.3, 'F'),
('s18', 1, 9.9, 'P'),
('s19', 1, 9.4, 'P'),
('s19', 2, 8.9, 'P'),
('s19', 3, 7.4, 'F'),
('s20', 1, 8.1, 'P'),
('s20', 2, 6.4, 'F'),
('s21', 1, 9.5, 'P'),
('s21', 2, 8.8, 'P'),
('s21', 3, 8.2, 'P');

# 업데이트
INSERT INTO student values('s0','김수정','20030401','M','01010101010','m1');
select * FROM student WHERE student_code = 's0';

UPDATE student SET major_code = 'm2' WHERE student_code = 's0';

# 삭제
DELETE FROM student WHERE student_code = 's0';

# 조회
-- 모든 테이블 조회
SELECT * FROM student;
-- 테이블의 특정 조건의 데이터 조회
SELECT * FROM student WHERE major_code = 'm2';
-- 특정 학생의 이름과 전공 컬럼만 조회
SELECT name, major_code FROM student WHERE student_code = 's2';

# 조회 심화
-- 명시적 조인
-- JOIN 키워드를 사용하여 두 테이블을 조인합니다.
-- ON 절을 사용하여 조인 조건을 지정
-- 가독성 높음, 복잡한 조인 조건을 명확하게 구분, 표현하기 쉽다.
-- INNER JOIN(default), LEFT JOIN, RIGHT JOIN 등 여러 조인 방식이 있음
SELECT s.name, s.major_code, m.major_name
 FROM student s
 JOIN major m
    ON s.major_code = m.major_code;

-- 암시적 조인
-- 테이블을 콤마(,)로 나열합니다.
-- WHERE 절에서 조인 조건을 지정한다.
-- 간단한 쿼리에서는 코드가 짧고 빠르게 작성할 수 있지만, 복잡해질수록 가독성이 낮다.
-- 다양한 조인 방식을 사용 할 수 없음
SELECT s.name, s.major_code, m.major_name
 FROM student s,major m
WHERE s.major_code = m.major_code;

#--------------------------

# 1번
CREATE TABLE MANAGER
(
    id bigint primary key comment '아이디',
    name varchar(100) not null comment '이름',
    student_code varchar(100) not null comment '수강생코드',
    constraint manager_fk_student_code foreign key (student_code) references student(student_code)
);

# 2번
ALTER table MANAGER modify id bigint AUTO_INCREMENT;

# 3번
INSERT INTO MANAGER (name, student_code) VALUES
('managerA','s1'),
('managerA','s2'),
('managerA','s3'),
('managerA','s4'),
('managerA','s5');

INSERT INTO MANAGER (name, student_code) VALUES
('managerB','s6'),
('managerB','s7'),
('managerB','s8'),
('managerB','s9');

# 4번
# 3개 이상 조건 있을시 join 여러개 쓰면 됨
# 특정 컬럼만 보고 싶은시 where
SELECT s.name,e.exam_seq,e.score
 FROM exam e
 JOIN student s
    ON s.student_code= e.student_code
 JOIN MANAGER m
    ON e.student_code = m.student_code
 WHERE m.name = 'managerA';

# 5번 - 이미 제약 조건이 걸어진 상태라면 alter drop으로 삭제후 생성하면서 cascade 걸어야됨
# drop을 사용해 foreign key 드랍
ALTER TABLE exam DROP foreign key exam_fk_student_code;
ALTER TABLE MANAGER DROP foreign key manager_fk_student_code;

# 다시 제약 조건 걸어주고 on delete cascade 옵션 추가
ALTER TABLE exam
    add constraint exam_fk_student_code
        foreign key (student_code) references student(student_code)
            on delete cascade;

ALTER TABLE MANAGER
    add constraint MANAGER_fk_student_code
        foreign key (student_code) references student(student_code)
            on delete cascade;

delete from student
    where student_code = 's1';

