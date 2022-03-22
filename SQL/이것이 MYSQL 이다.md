# 이것이 MYSQL 이다.

- 데이터베이스 = 데이터들의 집합
- 데이터베이스는 여러명의 사용자와 동시에 사용함
- DBMS의 장점
  - 데이터의 무결성 : 데이터는 어떤 경로로 들어오든 오류가 없어야 함
  - 데이터의 독립성 : 데이터베이스의 크기를 변경하거나 파일의 저장소를 변경하더라도 영향을 받지 않아야 한다
  - 보안 : 사용자의 계정에 따라 다른 권한을 가져야 함
  - 데이터의 중복 최소화 : 동일 데이터가 여러개 중복 저장되는 것을 방지 함
  - 응용 프로글매 제작 및 수정이 쉬워짐 : 유지보수 또한 쉬워짐
  - 데이터의 안전성 향상 : DBMS가 제공하는 백업/복원 기능을 이용해 데이터가 깨지는 문제시 원상복구 방법이 있음
- 데이터베이스 관리시스템 
  - 파일 시스템의 단점을 보완하고 대량의 데이터를 사용하는 것 = DBMS
  - DBMS : 데이터베이스를 잘 관리하고 운영하기 위한 시스템 또는 소프트웨어
  - SQL : DBMS를 구축하고 관리하기 위해 사용되는 언어



## 03. MySQL 전체 운영 실습

- 데이터베이스 모델링 : 분석과 설계 과정 중에서 가장 중요한 과정

![19](D:\workspace\00.TIL\SQL\IMAGE\19.png)

- 데이터 베이스 개체
  - 인덱스 : 책의 제일 뒤 찾아보기와 비슷한 기능
  - 뷰 : 가상의 테이블
  - 스토어드 프로시저 : 프로그래밍 기능 (다른 프로그래밍 언어와 같은 기능)
  - 트리거 : 다른테이블에 부착되어서 INSERT, UPDATE, DELETE 작업이 발생되면 실행되는 코드
  - 함수 : 
- 백업과 복원
  - 백업은 현재의 데이터베이스를 다른 매체에 보관하는 작업
  - 복원은 데이터베이스에 문제 발생시 백업 데이터를 이용해 원상태로 돌려놓는 작업



## 04. 데이터베이스 모델링

- 프로젝트 : 현실세계의 업무를 컴퓨터 시스템으로 옮겨놓는 일련의 과정, 대규모의 프로그램을 작성하기 위한 전체 과정
- 폭포수 모델 : 각 단계가 명확히 구분되어 프로젝트의 진행 단계가 명확함.



## 05. MySQL 유틸리티 사용법

- MySQL Workbench 주요기능
  1. 데이터베이스 연결 기능
  2. 인스턴스 관리
  3. 위저드를 이용한 MySQL의 동작
  4. 통합된 기능의 SQL 편집기
  5. 데이터베이스 모델링 기능
  6. 포워드/리버스 엔지니어링 기능
  7. 데이터베이스 인스턴스 시작/종료
  8. 데이터베이스 내보내기/가져오기
  9. 데이터베이스 계정 관리

- 쿼리 창
  - 데이터베이스를 학습시 가장 먼저 배우는 것은 SQL문

- 리눅스 가상머신으로도 MySQL서버 접근 가능



## 06. SQL 기본

- SELECT 문
  - 데이터의 값을 추출하는 명령어
  - 구조 <SELECT ~ FROM>

```SQL
SELECT 열이름 *
	FROM 테이블이름
	WHERE 조건
	GROUP BY ~
	HAVING 그룹바이 조건
	ORDER BY 정렬 방법 ASC, DESC
```

- SHOW, USE, DESCRIBE : 정보 조회 명령

```SQL
SHOW DATABASES;
-- 현재 저장되어 있는 모든 데이터베이스의 이름을 조회함
USE employees;
-- 특정 데이터베이스 사용 지정 명령
SHOW TABLES;
-- 데이터베이스의 테이블 리스트 조회
DESCRIBE employees; -- DESC employees 와 같은 명령어
-- 테이블의 각각의 열 정보 조회 (열이름, 타입, 널여부, 키설정 등)

```

- CREATE TABLE 테이블명 (열 특성) : 테이블 생성

```sql
USE sqldb; -- 데이터베이스 선택
CREATE TABLE usertbl -- 회원 테이블 생성 테이블 이름 usertbl
( userID  	CHAR(8) NOT NULL PRIMARY KEY, -- 사용자 아이디(PK)
  name    	VARCHAR(10) NOT NULL, -- 이름
  birthYear   INT NOT NULL,  -- 출생년도
  addr	  	CHAR(2) NOT NULL, -- 지역(경기,서울,경남 식으로 2글자만입력)
  mobile1	CHAR(3), -- 휴대폰의 국번(011, 016, 017, 018, 019, 010 등)
  mobile2	CHAR(8), -- 휴대폰의 나머지 전화번호(하이픈제외)
  height    	SMALLINT,  -- 키
  mDate    	DATE  -- 회원 가입일
);

-- 테이블을 복사해서 생성하는 방법
CREATE TABLE buytbl2 (SELECT * FROM buytbl); -- 복사할 테이블을 전체 선택함
SELECT * FROM buytbl2;

-- 테이블의 특정열을 새로운 테이블로 생성 하는 방법
CREATE TABLE buytbl3 (SELECT userID, prodName FROM buytbl); -- 복사할 테이블의 열만 선택함
SELECT * FROM buytbl3;
```

- INSERT INTO 테이블명 VALUES (열별추가할 데이터1, 데이터2 ...) : 테이블에 값 추가

```sql
INSERT INTO usertbl VALUES('LSG', '이승기', 1987, '서울', '011', '1111111', 182, '2008-8-8');
-- usertbl테이블에 각각의 열에 값 추가
```

- WHERE 절 : 특정한 데이터만 조회

```SQL
SELECT 필드이름 FROM 테이블이름 WHERE 조건식; -- 기본식

SELECT * FROM usertbl WHERE name = '이승기'; -- usertbl테이블에 name이 이승기인 자료 모두 출력

-- 특정 열의 특정 값 사이의 값 조회시 AND를 사용하거나 BETWENN AND를 사용
SELECT name, height FROM usertbl WHERE height >= 180 AND height <= 183;
SELECT name, height FROM usertbl WHERE height BETWEEN 180 AND 183;

-- 특정 열에 포함된 단어 여러개를 조회할 경우 OR을 여러번 쓰거나 IN으로 묶어서 표현 가능
SELECT name, addr FROM usertbl WHERE addr='경남' OR  addr='전남' OR addr='경북';
SELECT name, addr FROM usertbl WHERE addr IN ('경남','전남','경북');

-- 특정 단어로 시작하는 값을 조회하려면 LIKE '첫단어%'
	# %는 단어갯수 제한이 없어 '%김', '%김%' 과 같이 사용 가능
SELECT name, height FROM usertbl WHERE name LIKE '김%';

-- 이름이 종신인 사람 모두 검색, _는 갯수만큼 글자수를 제한하여 검색 가능, __종신 일경우 성이 2자 여야함
SELECT name, height FROM usertbl WHERE name LIKE '_종신';

```

- ORDER BY 절 : 원하는 순서대로 출력

```SQL
-- 정렬을 여러개로 할경우
SELECT name, height FROM usertbl 
	ORDER BY height DESC, name ASC; -- 키를 순서로 정렬, 키가같다면 이름순으로 정렬

USE employees;
SELECT emp_no, hire_date FROM employees 
   ORDER BY hire_date ASC/DESC -- hire_date열을 asc오름차순,desc내림차순 중 선택해서 정렬
   LIMIT 5; -- 출력 개수를 5개로 제한

SELECT emp_no, hire_date FROM employees 
   ORDER BY hire_date ASC
   LIMIT 5, 5;  -- 시작값, 출력 개수
   -- LIMIT 5 OFFSET 0 == LIMIT 0, 5
```

- DISTINCT : 중복 제거

```SQL
SELECT DISTINCT 열 FROM 테이블; -- 기본식

SELECT addr FROM usertbl; -- DISTINCT 없이 조회시 모든 값 출력(중복 그대로)
SELECT DISTINCT addr FROM usertbl; -- DISTINCT로 조회할 경우 중복값 제거해 1개의 값만 보여줌
```











































