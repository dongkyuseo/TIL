# [이것이 MYSQL 이다.](https://www.youtube.com/playlist?list=PLVsNizTWUw7Hox7NMhenT-bulldCp9HP9)

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

- GROUP BY 및 HAVING 그리고 집계 함수
  - 그룹으로 묶어주는 역할

```SQL
SELECT userID AS '사용자', SUM(price*amount) AS '총구매액'  -- 묶을열선택, 값 출력 형식 함수로 지정
   FROM buytbl 
   GROUP BY userID ; -- 묶어줄 열 이름을 지정
   
SELECT userID AS '사용자', SUM(price*amount) AS '총구매액'  
   FROM buytbl 
   GROUP BY userID
   HAVING SUM(price*amount) > 1000 ; -- 그룹바이로 지정한 열에 추가 조건을 지정 (WHERE절에 거는 조건문)
```

- ROLLUP
  - 총합 또는 중간 합계 필요시 GROUP BY 절과 함께 WITH ROLLUP문을 사용

```sql
SELECT groupName, SUM(price * amount) AS '비용' 
   FROM buytbl
   GROUP BY  groupName
   WITH ROLLUP; -- 그룹바이로 지정된 열 기준으로 중간 합계, 총합을 계산해줌
```



### SQL의 분류

### DML / SELECT, INSERT, UPDATE, DELETE

- Data Manipulation Language: 데이터 조작 언어
  - 데이터를 조작(선택, 삽입, 수정, 삭제)하는 데 사용되는 언어임. DML 구문이 사용되는 대상은 테이블의 행임. 
  - 트랜잭션이 발생하는 SQL임
  - 트랜잭션(Transactions) : 테이블의 데이터를 변경할 때 실제 테이블에 완전히 적용하지 않고, 임시로 적용시키는 것 (실수 할 경우 취소 가능)

### DDL / CREATE, DROP, ALTER

- Data Definition Language: 데이터 정의 언어
  - 데이터베이스, 테이블, 뷰, 인덱스 등의 데이터베이스 개체를 생성/삭제/변경 하는 역할
  - DDL은 트랜잭션을 발생시키지 않음
    - 되돌리거나 완전적용(COMMIT)을 시킬 수 없음. = 즉시 적용

### DCL / GRANT, REVOKE, DENY

- Data Control Language : 데이터 제어 언어
  - 사용자에게 권한을 부여하거나 뺏을때 주로 사용하는 구문

### 데이터의 변경을 위한 SQL문

### 데이터의 삽입: INSERT

```sql
-- 데이터 삽입의 기본 구문
INSERT [INTO] 테이블 [(열이름1, 2, 3 ...)] VALUES (값1, 2, 3 ....)
```

- 자동으로 증가하는 AUTO_INCREMENT
  - 테이블의 속성이 AUTO_INCREMENT로 지정될 경우 자동으로 1부터 증가하는 값을 입력해 줌
  - AUTO_INCREMENT는 PRIMARY KEY 또는 UNIQUE로 지정해 줘야 하며 숫자형만 사용 가능
  - AUTO_INCREMENT로 지정된 열은 INSERT 문에서 NULL 값을 지정하면 자동으로 값이 입력 됨
  - SELECT LAST_INSERT_ID(); 쿼리를 사용하면 마지막에 입력된 값을 확인 가능
  - AUTO_INCREMENT 입력값을 지정해서 시작할수 있음
    - ALTER TABLE 테이블명 AUTO_INCREMENT=시작할숫자; 시작값을 바꿔줌
    - SET @@auto_increment_increment=증가값; 증가 값을 바꿔즘

```sql
USE  sqldb;
CREATE TABLE testTbl2 -- 테이블 생성
  (id  int AUTO_INCREMENT PRIMARY KEY, -- ID 열을 AUTO_INCREMENT, PRIMARY KEY 지정
   userName char(3), 
   age int );
INSERT INTO testTbl2 VALUES (NULL, '지민', 25); -- ID 열에 입력값은 NULL, 1, 지민
INSERT INTO testTbl2 VALUES (NULL, '유나', 22); -- 2, 유나 
INSERT INTO testTbl2 VALUES (NULL, '유경', 21); -- 3, 유경

ALTER TABLE testTbl2 AUTO_INCREMENT=100; -- AUTO_INCREMENT 시작 숫자를 바꿈 100으로
INSERT INTO testTbl2 VALUES (NULL, '찬미', 23); -- 찬미는 100번으로 등록됨
SET @auto_increment_increment=3; -- AUTO_INCREMENT 가 3씩 증가됨

INSERT INTO testTbl3 VALUES (NULL, '나연', 20); -- 103, 나연
INSERT INTO testTbl3 VALUES (NULL, '정연', 18); -- 106, 정연
INSERT INTO testTbl3 VALUES (NULL, '모모', 19); -- 109, 모모

```



### 데이터의 수정 : UPDATE

```sql
-- 기본 구문
UPDATE 테이블명
	SET 열1=값1, 열2=값2 ..
	WHERE 조건;
```

- UPDATE 사용시 WHERE 절을 생략하면 테이블의 전체행이 같은 값으로 바뀌게 됨

```sql
-- WHERE 절을 생략하는 경우
UPDATE buytbl SET price = price * 1.5 ;
-- 단가가 1.5배 올라서 전체 적용을 해야 할 경우,,
-- 거의 사용할 일이 없음
```



### 데이터의 삭제 : DELETE FROM

```sql
-- 기본 구문
DELETE FROM 테이블이름
	WHERE 조건;
```

- WHERE 조건 없이 삭제할 경우 모든 테이블의 데이터가 삭제됨



### 조건부 데이터 입력, 변경

- 데이터 입력시 중복된 데이터 입력할 경우 오류로 인해 입력이 중단됨
- 오류가 날경우 해당 구문만 제외하고 나머지 명령을 수행하게 하는 코드는 아래와 같음
  - INSERT IGNORE INTO 테이블명 VALUES (값1, 값2, ...)
- 중복 값 발생시 기존의 값을 입력한 값으로 변경하는 명령
  - INSERT INTO 테이블명 VALUES(값1, 값2, ..) ON DUPLICATE KEY UPDATE 열1=값1, 열2=값2 ...;

```sql
-- 중복값 무시
INSERT INTO memberTBL VALUES('BBK' , '비비코', '미국'); -- 중복값 입력으로 오류 발생
INSERT INTO memberTBL VALUES('SJH' , '서장훈', '서울'); -- 중복값 때문에 입력이 안됨
INSERT INTO memberTBL VALUES('HJY' , '현주엽', '경기'); -- 중복값 때문에 입력이 안됨
SELECT * FROM memberTBL;

INSERT IGNORE INTO memberTBL VALUES('BBK' , '비비코', '미국'); -- 중복값 오류 무시
INSERT IGNORE INTO memberTBL VALUES('SJH' , '서장훈', '서울'); -- 정상 입력
INSERT IGNORE INTO memberTBL VALUES('HJY' , '현주엽', '경기'); -- 정상 입력
SELECT * FROM memberTBL;

-- 중복값 발생시 기존값 업데이트 명령
INSERT INTO memberTBL VALUES('BBK' , '비비코', '미국') -- 새로운 값이면 추가
	ON DUPLICATE KEY UPDATE name='비비코', addr='미국'; -- 키가 중복된다면 내용 업데이트(변경)

```



### WITH절과 CTE

- 비재귀적 CTE
  - 재귀적이지 않은 CTE, 단순한 형태로 복잡한 쿼리를 단순화 시키는데 적합함

```sql
-- 기본 구문
WITH CTE_테이블명(열이름)
AS
(
	<쿼리문>
)
SELECT 열이름 FROM CTE_테이블명;

-- 예시
WITH abc(userid, total) -- ABC라는 CTE테이블 생성
AS
(SELECT userid, SUM(price*amount)  -- 테이블로 취급할 쿼리문
  FROM buyTBL GROUP BY userid )
SELECT * FROM abc ORDER BY total DESC ; -- 테이블처럼 호출 
```



## 07. SQL 고급

### 숫자 데이터 형식

![20](D:\workspace\00.TIL\SQL\IMAGE\20.png)

- 정수를 지정할때 UNSIGNED 예약어를 사용하면 숫자의 범위가 정수만 사용하게 됨

### 문자 데이터 형식

![21](D:\workspace\00.TIL\SQL\IMAGE\21.png)

### 날짜와 시간 데이터 형식

![22](D:\workspace\00.TIL\SQL\IMAGE\22.png)

### 기타 데이터 형식

![23](D:\workspace\00.TIL\SQL\IMAGE\23.png)

```sql
-- PREPARE 를 활용한 변수 사용하기 예시
SET @myVar1 = 3 ; -- 변수 저장
PREPARE myQuery -- 임시 쿼리 생성
    FROM 'SELECT Name, height FROM usertbl ORDER BY height LIMIT ?'; -- ? 자리에 변수를 사용
EXECUTE myQuery USING @myVar1 ; -- EXCUTE로 저장한 임시쿼리를 실행, ?자리는 저장해 놓은 변수를 불러옴

```

### 데이터 형식 변환 함수

- CAST(), CONVERT() 함수를 사용하는 데이터 형식 변환이 일반적임

```sql
-- 기본식
CAST ( EXPRESSION AS 데이터형식 [ (길이) ])
CONVERT ( EXPRESSION, 데이터형식 [ (길이) ])
```

- 데이터 형식중 가능한 것
  - BINARY
  - CHAR
  - DATE
  - DATETIME
  - DECIMAL
  - JSON
  - SIGNED INTEGER
  - TIME
  - UNSIGNED INTEGER

```sql
SELECT AVG(amount) AS '평균 구매 개수' FROM buytbl ; -- 결과가 소숫점으로 나옴

-- CAST/CONVERT, AS SIGNED INTEGER 사용시 반올림한 값이 출력됨
SELECT CAST(AVG(amount) AS SIGNED INTEGER) AS '평균 구매 개수'  FROM buytbl ; 
-- 또는
SELECT CONVERT(AVG(amount) , SIGNED INTEGER) AS '평균 구매 개수'  FROM buytbl ;

-- 날짜 형식사이에 특수문자를 넣고 CAST/CONVER 사용하면 자동으로 날짜형식으로 바꿔서 출력해줌
SELECT CAST('2020$12$12' AS DATE);
```

### 내장 함수

```sql
-- 기본식
IF(수식, 참, 거짓)
-- 아래는 '거짓이다' 가 출력 됨
SELECT IF (100>200, '참이다', '거짓이다');
-- IFNULL은 값이 비어있을 경우 대신 출력할 값을 넣어 출력 가능
SELECT IFNULL(NULL, '널이군요'), IFNULL(100, '널이군요');
-- NULLIF 는 값1, 값2가 같으면 NULL을 출력함
SELECT NULLIF(100,100), IFNULL(200,100);
```

### 연산자

```sql
-- 기본식
CASE ~ WHEN ~ ELSE ~ END
-- 예시
SELECT 	CASE 10 -- CASE 비교대상값
		-- 값이 WHEN 비교값 과 같으면 "값출력"
		WHEN 1  THEN  '일' 
		WHEN 5  THEN  '오'
		WHEN 10 THEN  '십' -- 비교 값과 같음
		ELSE '모름'
	END AS 'CASE연습'; -- 열 이름 지정
```

### 문자열 함수

```sql
ASCII(아스키코드) -- 문자의 아스키 코드값을 돌려줌
CHAR(숫자) -- 아스키 코드값에 해당하는 문자를 돌려줌
CHAR_LENGTH(문자열) -- 글자의 개수 반환 / 가장 일반적으로 많이 쓰임
BIT_LENGTH(문자열) -- 글자의 BIT크기
LENGTH(문자열) -- 문자열의 크기 반환
CONCAT(문자열1, 문자열2,..) -- 문자열을 이어줌
CONCAT_WS(구분자, 문자열1,..) -- 구분자를 넣어서 문자열을 이어줌
# ELT (위치, 문자열1,...)
ELT(2, '하나', '둘', '셋') -- '둘' 을 반환함
# FIELD(위치, 문자열1,...)
FIELD('둘', '하나', '둘', '셋') -- 2를 반환함
# FIND_IN_SET(위치, 문자열)
FIND_IN_SET('둘', '하나,둘,셋') -- 2를 반환함
# INSERT(문자열, 위치)
INSTR('하나둘셋', '둘') -- 3을 반환함
# LOCATE(위치, 문자열)
LOCATE('둘', '하나둘셋') -- 3을 반환함
# FORMAT은 소숫점 자리수 제한 및 천단위를,로 표기해줌
# FORAMT(소숫점숫자, 소숫점제한할자리수)
FORMAT(123456.123456, 4); -- 123,456.1235를 반환
BIN(31) -- 2진수 반환
HEX(31) -- 16진수 반환
OCT(31) -- 8진수 반환
# INSERT(기준문자열, 위치, 길이, 삽입할 문자열)
INSERT('abcdefghi', 3, 4, '@@@@') -- ab@@@@ghi 반환
# LEFT/RIGHT(문자열, 길이)
LEFT('abcdefghi', 3) -- ABC 반환
RIGHT('abcdefghi', 3) -- GHI 반환
LOWER('abcdEFGH') -- 대문자로 변경
UPPER('abcdEFGH') -- 소문자로 변경
## L/RPAD(문자열, 길이, 채울문자열)
LPAD('이것이', 5, '##') -- ##이것이 반환
RPAD('이것이', 5, '##') -- 이것이## 반환
## L/R TRIM(문자열) : 빈칸 삭제
LTRIM('   이것이') -- 이것이 반환
RTRIM('이것이   ') -- 이것이 반환
TRIM('   이것이   ') -- 이것이 반환
# BOTH 삭제할단어 FROM 대상문자
TRIM(BOTH 'ㅋ' FROM 'ㅋㅋㅋ재밌어요.ㅋㅋㅋ') -- 재밌어요.
# REPEAT(문자열, 반복횟수)
REPEAT('이것이', 3) -- 이것이이것이이것이 반환
# REPLACE(문자열, 변경할대상, 변경할단어)
REPLACE ('이것이 MySQL이다', '이것이' , 'This is') -- This is MySQL이다 반환
REVERSE ('MySQL') -- LQSyM 반환 / 문자열을 거꾸로 만듬
CONCAT('이것이', SPACE(10), 'MySQL이다') -- 이것이          MySQL이다 반환 / 문자열 연결
# substring(문자열, 시작위치, 길이)
SUBSTRING('대한민국만세', 3, 2) -- 민국 반환
# SUBSTRING_INDEX(문자열, 구분자, 횟수) 문자열에서 구분자가 왼쪽부터 나오면 그이후의 오른쪽은 버림
# 횟수가 음수이면 오른쪽부터 세고 왼쪽을 버림
SUBSTRING_INDEX('cafe.naver.com', '.', 2) -- cafe.naver 반환
SUBSTRING_INDEX('cafe.naver.com', '.', -2) -- naver.com 반환
```

### 수학함수

```sql
# 숫자의 절대 값 반환
ABS(-100) -- 100 반환
CEILING(47) -- 올림값 반환
FLOOR(4.7) -- 내림값 반환
ROUND(4.7) -- 반올림값 반환
# MODE(값1, 값2) 값1을 값2로 나눈 나머지 출력
MOD(157, 10) --  157 % 10 = 7 반환
157 MOD 10 -- 7 반환
POW(2,3) -- 8 거듭제곱 값 반환
SQRT(9) -- 3 루트9 값 반환
# 랜덤값 반환
RAND() -- 0이상 1미만의 실수
FLOOR(1 + (RAND() * (N-M)) ) -- M<= 랜덤 < N 특정 범위의 랜덤값 구하기
# SIGN(숫자) / 양수,0,음수 를 구분하여 1, 0 ,-1을 반환
SIGN(100), SIGN(0), SIGN(-100.123) -- 1, 0, -1 반환
# TRUNCATE(소수, 버릴소숫점위치) / 소숫점을 정수위치까지 구하고 나머지는 버림
TRUNCATE(12345.12345, 2) -- 12345.12 반환
TRUNCATE(12345.12345, -2) -- 12300 반환
```

### 날짜 및 시간 함수

```sql

# ADD/SUBDATE(날짜, INTERVAL 차이) 더하기/빼기 날짜를 기준으로 이전 이후 일을 계산해줌
ADDDATE('2025-01-01', INTERVAL 31 DAY) -- 2025-02-01 반환
ADDDATE('2025-01-01', INTERVAL 1 MONTH) -- 2025-02-01 반환
SUBDATE('2025-01-01', INTERVAL 31 DAY) -- 2024-12-01 반환
SUBDATE('2025-01-01', INTERVAL 1 MONTH) -- 2024-12-01 반환
# ADD/SUBTIME(날짜/시간, 시간) 날짜/시간을 기준으로 이전시간 이후 시간을 계산해줌
ADDTIME('2025-01-01 23:59:59', '1:1:1') -- 2025-01-02 01:01:00 반환
ADDTIME('15:00:00', '2:10:10') -- 17:10:10 반환
SUBTIME('2025-01-01 23:59:59', '1:1:1') -- 2025-01-01 22:58:58 반환
SUBTIME('15:00:00', '2:10:10') -- 12:49:50 반환

CURDATE() -- 현재 연-월-일
CURTIME() -- 현재 시:분:초
NOW()/SYSDATE() -- 현재 연-월-일 시:분:초

YEAR(날짜) -- 날짜에서 연만 반환
MONTH(날짜) -- 날짜에서 월만 반환
DAY(날짜) -- 날짜에서 일만 반환
DAYOFMONTH(날짜) -- 날짜에서 일만 반환
HOUR() -- 날짜에서 시간 반환
MINUTE() -- 날짜에서 분 반환
SECOND() -- 날짜에서 초 반환
MICROSECOND() -- 날짜에서 밀리초 반환

DATE(NOW()) -- 현재 날짜만 연-월-일 반환
TIME(NOW()) -- 현재 시간만 시:분:초 반환

DATEDIFF(날짜1, 날짜2) -- 날짜1 - 날짜2
TIMEDIFF(시간1, 시간2) -- 시간1 - 시간2

DAYOFWEEK(CURDATE()) -- 날짜에서 일을 숫자로 반환(일은 일:1, 월:2 ~ 토:7)
MONTHNAME(CURDATE()) -- 현재 몇월인지 영문자로
DAYOFYEAR(CURDATE()) -- 일년중 몇일이 지났는지 알 수 있음

LAST_DAY('2025-02-01') -- 날짜의 달이 몇일까지 있는지 확인
MAKEDATE(2025, 32) -- 연도에서 정수만큼 지난 날짜를 구함
MAKETIME(12, 11, 10) -- 시, 분, 초 형식의 시간을 만들어줌
PERIOD_ADD(202501, 11) -- 연월YYYYMM, 개월수 / 연월에서 개월만큼의 개월이 지난 연월을 구함
PERIOD_DIFF(202501, 202312) -- 연월1 - 연월2 의 개월수
QUARTER('2025-07-07') -- 날짜가 4분기중 몇분기인지 반환
TIME_TO_SEC('12:11:10') -- 시간을 초단위로 반환
```

### 시스템 정보 함수

```SQL
CURRENT_USER() -- 현재 사용자
DATABASE() -- 현재 선택된 데이터베이스 반환
FOUND_ROWS() -- 바로 앞의 SELECT 문에서 조회된 행의 개수를 반환
ROW_COUNT() -- 바로 앞의 INSERT, UPDATE, DELETE문에서 입력, 수정, 삭제된 행의 개수 반환
# CREATE, DROP문은 0을 반환하고 SELECT 문은 -1을 반환
VERSION() -- 현재 MySQL 버전 반환
SLEEP(초) -- 지정한 초동안 쿼리의 실행을 멈춤
```

### 피벗의 구현

- 한 열에 포함된 여러 값을 출력하고, 이를 여러 열로 변환하여 테이블 반환 식을 회전하고 필요하면 집계까지 수행하는 것

```sql
USE sqldb;
CREATE TABLE pivotTest -- 피봇으로 사용할 테이블 생성
   (  uName CHAR(3),
      season CHAR(2),
      amount INT );

INSERT  INTO  pivotTest VALUES -- 피봇 테이블 값 입력
	('김범수' , '겨울',  10) , ('윤종신' , '여름',  15) , ('김범수' , '가을',  25) , ('김범수' , '봄',    3) ,
	('김범수' , '봄',    37) , ('윤종신' , '겨울',  40) , ('김범수' , '여름',  14) ,('김범수' , '겨울',  22) ,
	('윤종신' , '여름',  64) ;
SELECT * FROM pivotTest;

SELECT uName, -- 피봇 실행, 각 계절별로 합계, 묶을 그룹은 이름으로 지정
  SUM(IF(season='봄', amount, 0)) AS '봄', 
  SUM(IF(season='여름', amount, 0)) AS '여름',
  SUM(IF(season='가을', amount, 0)) AS '가을',
  SUM(IF(season='겨울', amount, 0)) AS '겨울',
  SUM(amount) AS '합계' FROM pivotTest GROUP BY uName ;
```

![24](D:\workspace\00.TIL\SQL\IMAGE\24.png)

![25](D:\workspace\00.TIL\SQL\IMAGE\25.png)

### JSON 데이터

- 현대의 웹과 모바일 응용 프로그램 등과 데이터를 교환하기 위한 개방형 표준 포맷
- 속성(KEY)과 값(VALUES)으로 쌍을 이루며 구성됨
- 특정 프로그래밍 언어에 종속되어 있지 않은 독립적인 데이터 포맷
- 포맷이 단순하고 공개되어 있어 거의 대부분의 프로그래밍 언어에서 쉽게 읽거나 쓸 수 있음

![26](D:\workspace\00.TIL\SQL\IMAGE\26.png)

![27](D:\workspace\00.TIL\SQL\IMAGE\27.png)

```sql
USE sqldb;
SELECT JSON_OBJECT('name', name, 'height', height) AS 'JSON 값'
	FROM usertbl 
    WHERE height >= 180; -- JSON 테이블 생성을 위한 값 조회

SET @json='{ "usertbl" :
  [
	{"name":"임재범","height":182},
	{"name":"이승기","height":182},
	{"name":"성시경","height":186}
  ]
}' ; -- JSON 형태로 만드는 구문

SELECT JSON_VALID(@json) AS JSON_VALID; -- JSON 형식이 맞으면 1, 틀리면 0을 반환
SELECT JSON_SEARCH(@json, 'one', '성시경') AS JSON_SEARCH; -- ONE이면 하나 ALL이면 전부, 검색어의 위치를 반환
SELECT JSON_EXTRACT(@json, '$.usertbl[2].name') AS JSON_EXTRACT; -- 위치로 해당 데이터 반환
SELECT JSON_INSERT(@json, '$.usertbl[0].mDate', '2009-09-09') AS JSON_INSERT; -- 열삽입 구문, 날짜 열 추가
SELECT JSON_REPLACE(@json, '$.usertbl[0].name', '홍길동') AS JSON_REPLACE; -- 0번째 값을 변경
SELECT JSON_REMOVE(@json, '$.usertbl[0]') AS JSON_REMOVE; -- 행 삭제 구문
```

### JOIN

- 두 개 이상의 테이블을 서로 묶어서 하나의 결과 집합으로 만들어 내는 것

#### INNER JOIN

- 일반적인 JOIN은 INNER JOIN을 의미

```sql
-- 기본 형식
SELECT 열 목록
FROM 테이블1
	[INNER] JOIN 테이블2 -- INNER JOIN = JOIN 같은 명령
	ON 조인 조건
[WHERE 검색 조건]

-- 기본적인 이너조인 예시
SELECT U.userID, U.name, B.prodName, U.addr, U.mobile1 + U.mobile2  AS '연락처' -- 알리안스로 구분하여 열 이름 지정
   FROM usertbl U -- 기준 테이블
     INNER JOIN buytbl B -- 조인할 테이블
        ON U.userID = B.userID  -- JOIN 시 사용할 키 지정
   ORDER BY U.userID; -- 정렬
   
-- 3개의 테 이블 이너조인
```

#### OUTER JOIN

- 조건에 만족하지 않는 행까지도 포함시키는 것

``` sql
-- 기본 형식
SELECT 열 목록
FROM 테이블1
	<LEFT | RIGHT | FULL > OUTER JOIN 테이블2
	ON 조인 조건
[WHERE 검색 조건]
```

#### CROSS JOIN

- 한쪽테이블과 다른쪽 테이블 모두를 조인하는것
- 대량의 샘플데이터를 생성할때 사용

``` sql
USE employees;
SELECT  COUNT(*) AS '데이터개수'
   FROM employees 
     CROSS JOIN titles;
```

#### UNION / UNION ALL / NOT IN  / IN

- UNION만 사용시 중복열 제거되고 데이터가 정렬되어 나옴
- UNION ALL 사용시 중복된 열까지 모두 출력됨
- NOT IN 첫 번째 쿼리의 결과 중 두 번째 쿼리에 해당하는 것을 제외하는 구문
- IN 첫 번째 쿼리의 결과 중 두 번째 쿼리에 해당되는 것만 조회

```sql
-- 기본 형식
SELECT 문장1
	UNION [ALL]
SELECT 문장2
```

### SQL 프로그래밍

- 스토어드 프로시저

```SQL
-- 스토어드 프로시저 기본 형식
DELIMITER $$
CREATE PROCEDURE 스토어드프로시저 이름()
BEGIN
	
	이 부분에 SQL 프로그래밍 코딩
	
END $$
DELIMITER ;
CALL 스토어드프로시저 이름();
```

- CASE문

```sql
-- IF문으로 만들 경우
DELIMITER $$
CREATE PROCEDURE ifProc3()
BEGIN
	DECLARE point INT ;
	DECLARE credit CHAR(1);
	SET point = 77;
	
	IF point >= 90 THEN
		SET credit = 'A';
	ELSEIF point >= 80 THEN
		SET credit = 'B';
	ELSEIF point >= 70 THEN
		SET credit = 'C';
	ELSEIF point >= 60 THEN
		SET credit = 'D';
	ELSE
		SET credit = 'F';
	END IF;
	SELECT CONCAT('취득점수==>', point), CONCAT('학점==>', credit);
END $$
DELIMITER ;
CALL ifProc3();

-- CASE 문으로 만들경우
DELIMITER $$
CREATE PROCEDURE caseProc()
BEGIN
	DECLARE point INT ;
	DECLARE credit CHAR(1);
	SET point = 77;
	
	CASE
        WHEN point >= 90 THEN
            SET credit = 'A';
        WHEN point >= 80 THEN
            SET credit = 'B';
        WHEN point >= 70 THEN
            SET credit = 'C';
        WHEN point >= 60 THEN
            SET credit = 'D';
        ELSE
            SET credit = 'F';
	END CASE;
	SELECT CONCAT('취득점수==>', point), CONCAT('학점==>', credit);
END $$
DELIMITER ;
CALL ifProc3();
```



## 08. 테이블과 뷰 

### 테이블

- 기본 테이블 생성 및 값 입력

```sql
-- 기본 테이블 생성 및 키 지정
CREATE TABLE usertbl -- 회원 테이블
( userID  CHAR(8) [NOT NULL] PRIMARY KEY, -- 사용자 아이디
  name    VARCHAR(10) NOT NULL, -- 이름
  birthYear   INT NOT NULL,  -- 출생년도
  addr	  CHAR(2) NOT NULL, -- 지역(경기,서울,경남 등으로 글자만 입력)
  mobile1  CHAR(3) NULL, -- 휴대폰의국번(011, 016, 017, 018, 019, 010 등)
  mobile2  CHAR(8) NULL, -- 휴대폰의 나머지 전화번호(하이픈 제외)
  height    SMALLINT NULL,  -- 키
  mDate    DATE NULL  -- 회원 가입일
);
CREATE TABLE buytbl -- 구매 테이블
(  num INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 순번(PK)
   userid  CHAR(8) NOT NULL,-- 아이디(FK)
   prodName CHAR(6) NOT NULL, -- 물품명
   groupName CHAR(4) NOT NULL, -- 분류
   price     INT NOT NULL, -- 단가
   amount SMALLINT NOT NULL -- 수량
   , FOREIGN KEY(userid) REFERENCES usertbl(userID)
);

-- 값 입력
INSERT INTO usertbl VALUES('LSG', '이승기', 1987, '서울', '011', '1111111', 182, '2008-8-8');
```

- 제약 조건

  - 데이터의 무결성을 지키기위한 제한된 조건
  - 데이터 무결성 : 데이터 결함을 없앰
  - MySQL의 데이터 무결성을 위한 6가지 제약 조건
    - PRIMARY KEY 제약 조건
    - FOREIGN KEY 제약 조건
    - UNIQUE 제약 조건
    - CHECK 제약 조건
    - DFAULT 정의
    - NULL 값 허용

  ```sql
  DESCRIBE usertbl; -- 테이블 정보를 보기 위한 호출
  -- PRIMARY KEY 이름 지정하는 방법 : CONSTRAINT PRIMARY KEY 이름(열이름)
  # 방법1
  CREATE TABLE usertbl 
  ( userID  CHAR(8) NOT NULL, 
    name    VARCHAR(10) NOT NULL, 
    birthYear   INT NOT NULL,  
    CONSTRAINT PRIMARY KEY PK_usertbl_userID (userID) -- 지정 방법 예시
  );
  # 방법2
  CREATE TABLE usertbl 
  (   userID  CHAR(8) NOT NULL, 
      name    VARCHAR(10) NOT NULL, 
      birthYear   INT NOT NULL
  );
  ALTER TABLE usertbl -- 테이블 수정
       ADD CONSTRAINT PK_usertbl_userID -- ADD CONSTRAINT : 제약조건 추가, 추가할 제약조건 이름 지정
       PRIMARY KEY (userID); -- 추가할 제약 조건은 기본 키 제약 조건, 제약 조건을 설정할 열 이름 지정
  -- 2개의 열을 기본키로 지정할 경우
  CREATE TABLE prodTbl
  ( prodCode CHAR(3) NOT NULL,
    prodID   CHAR(4)  NOT NULL,
    prodDate DATETIME  NOT NULL,
    prodCur  CHAR(10) NULL,
    CONSTRAINT PK_prodTbl_proCode_prodID 
  	PRIMARY KEY (prodCode, prodID) -- 괄호로 묶어 콤마로 구분해 지정
  );
  
  SHOW INDEX FROM prodTbl; -- 2개로 지정한 기본키를 호출해 확인하는 쿼리
  
  -- 외래키 설정 예시
  CREATE TABLE usertbl -- 기준 테이블, 부모 테이블
  ( userID  CHAR(8) NOT NULL PRIMARY KEY, 
    name    VARCHAR(10) NOT NULL, 
    birthYear   INT NOT NULL 
  );
  CREATE TABLE buytbl -- 외래키 테이블, 자식 테이블
  (  num INT AUTO_INCREMENT NOT NULL PRIMARY KEY , 
     userID  CHAR(8) NOT NULL, 
     prodName CHAR(6) NOT NULL,
     CONSTRAINT FK_usertbl_buytbl FOREIGN KEY(userID) REFERENCES usertbl(userID) -- 해당내용을 작성
   -- CONSTRAINT 외래키이름 FOREIGN KEY(외래키열) REFERENCES 기준테이블(외래키와 연동할 열)
  );
  -- 외래키 설정시 기준테이블의 연동한 열 값 변경시 같이 변경하도록 설정 
  -- ON UPDATE CASCADE/ON DELETE CASCADE
  ALTER TABLE buytbl
  	ADD CONSTRAINT FK_usertbl_buytbl
  	FOREIGN KEY (userID)
  	REFERENCES usertbl (userID)
  	ON UPDATE CASCADE;
  
  -- 테이블 삭제, 외래키 테이블 먼저 삭제 후 기준 테이블을 삭제해야 함
  DROP TABLE 테이블 이름;
  
  -- 테이블 수정
  ALTER TABLE 테이블명
  	ADD 열이름 데이터타입 -- 열 추가
  		DEFAULT '디폴트값' -- 디폴트 값
  		NULL -- 널값 허용 여부
  		FIRST / AFTER 열이름 -- 열의 순서 지정
  		;
  		
  ALTER TABLE 테이블명
  	DROP COLUMN 열이름; -- 열 삭제, 열의 데이터 삭제 및 제약조건이 있을 경우 선조건삭제 후 삭제가능
  	
  ALTER TABLE 테이블명
  	CHANGE COLUMN 기존열이름 바꿀열이름 데이터형식 널값여부; -- 열 이름 및 데이터 형식 변경
  	
  ALTER TABLE 테이블명
  	DROP PRIMARY KEY; -- 열의 제약 조건 삭제 기본키
  	DROP FOREIGN KEY 외래키이름; -- 열의 제약 조건 삭제 외래키
  ```

### 뷰

- 일반 사용자 입장에서는 테이블과 동일하게 사용하는 개체
- 한번 생성해 놓으면 테이블이라고 생각하고 사용해도 될 정도로 동일한 개체

```sql
-- 뷰 생성 기본 쿼리
CREATE VIEW 뷰이름
AS
	SELECT 열이름 FROM 테이블명;

-- 뷰 내용 확인
DESCRIBE 뷰이름;

-- 뷰의 소스코드 확인
SHOW CREATE VIEW 뷰이름;


```

- 뷰의 장점
  - 보안에 도움이 됨
  - 복잡한 쿼리를 단순화 시켜줄 수 있음

### 테이블 스페이스

- 대용량의 데이터 사용시 신경써야 함
- 성능 향상을 위해 테이블 스페이스 추가 가능(디스크마다 다른 테이블이 존재해 속도 향상)



## 09. 인덱스

- 장점
  - 검색 속도가 빨라짐
  - 쿼리의 부하가 줄어들어 시스템 전체의 성능 향상
- 단점
  - 인덱스가 데이터베이스 공간을 차지함, 대략 데이터베이스 크기의 10% 정도의 추가 공간 필요
  - 처음 인덱스 생성시 시간이 많이 소요됨
  - 데이터의 변경 작업이 자주 일어날 경우 오히려 성능이 나빠질 수 있음

- 인덱스의 종류
  - 클러스터형 인덱스 Clustered Index : 영어 사전과 같은 정렬된 개념
  - 보조 인덱스 Secondary Index : 책 뒤에 찾아보기 개념
- 자동으로 생성되는 인덱스
  - 테이블 생성 시에 제약 조건 기본키 또는 유니크를 사용하면 자동으로 생성 됨
- 결론
  - PRIMARY KEY로 지정한 열은 클러스터형 인덱스가 생성됨
  - UNIQUE NOT NULL로 지정한 열은 클러스터형 인덱스가 생성 됨(PRIMARY KEY가 없을 경우)
  - UNIQUE(UNIQUE NULL) 로 지정한 열은 보조 인덱스가 생성 됨
  - PRIMARY KEY로 지정한 열은 데이터가 오름차순 정렬 됨
- 인덱스의 내부 작동
  - B-Tree (Balanced Tree, 균형 트리)
    - 하나의 루트노드와 다수의 리프노드의 구조

![28](D:\workspace\00.TIL\SQL\IMAGE\28.png)

- 클러스터형 인덱스
  - 클러스터형 인덱스는 생성시 데이터 페이지 전체가 다시 정렬됨
  - 대용량 데이터에서 클러스터형 인덱스 생성시 심각한 시스템 부하가 생길 수 있음
  - 인덱스 자체의 리프페이지가 곧 데이터로 인덱스 자체에 데이터가 포함되어 있음
  - 보조 인덱스보다 검색 속도는더 빠르지만, 데이터의 입력/수정/삭제는 더느림
  - 클러스터 인덱스는 성능이 좋지만 테이블에 한 개만 생성할 수 있어 어느열에 클러스터형 인덱스를 생성하는지에 따라 시스템 성능이 달라짐
- 보조 인덱스
  - 데이터 페이지를 그냥 두고 별도의 페이지에 인덱스를 구성함
  - 보조 인덱스는 데이터가 위치하는 주소값 임
  - 클러스터형보다 검색 속도는 더 느리지만, 데이터의 입력/수정/삭제는 덜 느림
  - 보조 인덱스는 여러개 생성 가능하지만, 남용할 경우 시스템 성능을 떨어뜨림

```sql
-- 인덱스 생성
CREATE INDEX 인덱스 이름
	ON 테이블이름(열이름);
ANALYZE TABLE 테이블 이름; -- 인덱스를 실제로 적용시키는 명령
	
-- 인덱스 제거, 인덱스 제거시 보조인덱스 이후 클러스터형 인덱스 삭제해야 함
DROP INDEX 인덱스 이름 
	ON 테이블이름;
```



## 10. 스토어드  프로그램

### 스토어드 프로시저 (stored procedure, 저장프로시저)

- 쿼리문의 집합

```sql
-- 기본 형태
DELIMITER $$
CREATE PROCEDURE 스토어드 프로시저이름( IN / OUT 파라미터 )
BEGIN

	SQL 프로그래밍 코딩

END $$
DELIMITER ;

CALL 스토어드 프로시저 이름();

-- 스토어드 프로시저의 수정과 삭제
# 수정
ALTER PROCEDURE 스토어드프로시저이름();
# 삭제
DROP PROCEDURE 스토어드프로시저이름();
```

- 매개 변수의 사용

```sql
-- 입력 매개변수 형식
IN 입력_매개변수_이름 데이터_형식

-- 출력 매개변수 형식
OUT 추력_매개변수_이름 데이터_형식

-- 변수를 사용할 수도 있음
CALL 프로시저_이름(@변수명);
SELECT @변수명;

-- 예시
DROP PROCEDURE IF EXISTS userProc3;
DELIMITER $$
CREATE PROCEDURE userProc3(
    IN txtValue CHAR(10),	-- 입력 매개변수
    OUT outValue INT	    -- 출력 매개변수
)
BEGIN
  INSERT INTO testTBL VALUES(NULL,txtValue); -- 입력 매개변수를 이용한 쿼리
  SELECT MAX(id) INTO outValue FROM testTBL; -- 출력 매개변수에 넣을 값 
END $$
DELIMITER ;

-- 스토어드프로시저 생성시 테이블이 없어도 생성 가능, 이후 작동하려면 테이블이 있어야 함
CREATE TABLE IF NOT EXISTS testTBL(
    id INT AUTO_INCREMENT PRIMARY KEY, 
    txt CHAR(10)
);

CALL userProc3 ('테스트값', @myValue); -- 스토어드프로시저 사용
SELECT CONCAT('현재 입력된 ID 값 ==>', @myValue); -- 출력매개변수 값 확인

-- 프로시저의 코드 형태 확인하는 쿼리
SHOW CREATE PROCEDURE sqldb.userProc3;
```

- 저장 프로시저의 특징
  - MySQL 의 성능이 향상됨
  - 유지관리가 간편함
  - 모듈식 프로그래밍 가능
  - 보안 강화

### 스토어드 함수

- 내장함수에서 모든 함수를 제공하지 않아 직접 함수를 만들어 사용 하는 방식
- 스토어드 프로시저와 상당히 유사하지만 형태와 사용 용도에는 약간의 차이가 있음

```sql
-- 스토어드 함수 기본 형식
DELIMITER $$
CREATE FUNCTION 스토어드 함수 이름( 파라미터 )
	RETURNS 반환형식
BEGIN
	이부분에 프로그래밍 코딩
	
	RETURN 반환값;
	
END $$
DELIMITER ;

SELECT 스토어드_함수이름();
```

- 스토어드 함수의 정의
  - 스토어드 프로시저의 파라미터와 달리 IN, OUT 등을 사용할 수 없고 스토어드 함수의 파라미터는 모두 입력 파라미터로 사용됨
  - 스토어드 함수는 RETURNS문으로 반환할 값의 데이터 형식을 지정하고, 본문 안에서는 RETURN문으로 하나의 값을 반환해야 함
  - 스토어드 프로시저는 별도의 반환하는 구문이 없으며, 꼭 필요하다면 여러 개의 OUT 파라미터를 사용해서 값을 반환할 수 있음
  - 스토어드 프로시저는 CALL로 호출
  - 스토어드 함수는 SELECT 문장 안에서 호출됨
  - 스토어드 프로시저 안에는 SELECT문을 사용할 수 있지만, 스토어드 함수 안에서는 집합 결과를 반환하는 SELECT를 사용할 수 없음

```sql
-- 예시
SET GLOBAL log_bin_trust_function_creators = 1; -- 스토어드 함수를 만들기 위한 선언 쿼리

-- 더하기 프로시저 함수 만들기
USE sqlDB;
DROP FUNCTION IF EXISTS userFunc;
DELIMITER $$
CREATE FUNCTION userFunc(value1 INT, value2 INT) -- 함수 생성(값1 데이터형식, 값2 데이터형식)
    RETURNS INT -- 반환 데이터 형식
BEGIN -- 함수 부분
    RETURN value1 + value2; -- 값 1 + 값 2 수식 생성
END $$ -- 함수 종료
DELIMITER ;

SELECT userFunc(100, 200); -- 함수 호출

-- 나이 계산 함수 만들기
USE sqlDB;
DROP FUNCTION IF EXISTS getAgeFunc;
DELIMITER $$
CREATE FUNCTION getAgeFunc(bYear INT)
    RETURNS INT
BEGIN
    DECLARE age INT;
    SET age = YEAR(CURDATE()) - bYear;
    RETURN age;
END $$
DELIMITER ;
```



### 커서

- 스토어드 프로시저 내부에서 쓰임

![29](D:\workspace\00.TIL\SQL\IMAGE\29.png)

```sql
-- 커서 활용 예시
DROP PROCEDURE IF EXISTS cursorProc;
DELIMITER $$
CREATE PROCEDURE cursorProc()
BEGIN
    DECLARE userHeight INT; -- 고객의 키
    DECLARE cnt INT DEFAULT 0; -- 고객의 인원 수(=읽은 행의 수)
    DECLARE totalHeight INT DEFAULT 0; -- 키의 합계
    
    DECLARE endOfRow BOOLEAN DEFAULT FALSE; -- 행의 끝 여부(기본을 FALSE)

    DECLARE userCuror CURSOR FOR-- 커서 선언
        SELECT height FROM userTbl;

    DECLARE CONTINUE HANDLER -- 행의 끝이면 endOfRow 변수에 TRUE를 대입 
        FOR NOT FOUND SET endOfRow = TRUE;
    
    OPEN userCuror;  -- 커서 열기

    cursor_loop: LOOP
        FETCH  userCuror INTO userHeight; -- 고객 키 1개를 대입
        
        IF endOfRow THEN -- 더이상 읽을 행이 없으면 Loop를 종료
            LEAVE cursor_loop;
        END IF;

        SET cnt = cnt + 1;
        SET totalHeight = totalHeight + userHeight;        
    END LOOP cursor_loop;
    
    -- 고객 키의 평균을 출력한다.
    SELECT CONCAT('고객 키의 평균 ==> ', (totalHeight/cnt));
    
    CLOSE userCuror;  -- 커서 닫기
END $$
DELIMITER ;

CALL cursorProc();
```

### 트리거

- 테이블에 부착되어 테이블의 삽입/수정/삭제 시 작동하게 됨
- 활용사례
  - 테이블의 행 삭제시, 해당 내용을 지워지기 전에 다른 테이블에 기록 하는 것
- 트리거의 개요 : 데이터의 무결성을 위한 제약 조건,DML의 이벤트 발생시 작동하는 데이터베이스 개념

```sql
-- 트리거 예시
DROP TRIGGER IF EXISTS testTrg;
DELIMITER // 
CREATE TRIGGER testTrg  -- 트리거 이름
    AFTER  DELETE -- 삭제후에 작동하도록 지정
    ON testTbl -- 트리거를 부착할 테이블
    FOR EACH ROW -- 각 행마다 적용시킴
BEGIN
	SET @msg = '가수 그룹이 삭제됨' ; -- 트리거 실행시 작동되는 코드들
END // 
DELIMITER ;
```

- 트리거의 종류
  - AFTER 트리거 : INSERT, UPDATE, DELETE 등 작업시 작동하는 트리거 의미, 작업 후에 작동함
  - BEFORE 트리거 : 테이블에 이벤트가 작동한 후 실행되지만 이벤트(INSERT, UPDATE, DELETE)가 발생하기 전에 작동하는 트리거
  - 트리거는 ALTER TRIGGER 문으로 수정이 불가하여 DORP TRIGGER를 통해 삭제후 새로 생성해야 함

```sql
-- 예시
-- AFTER 트리거
-- 백업 테이블 생성
USE sqlDB;
DROP TABLE buyTbl; -- 구매테이블은 실습에 필요없으므로 삭제.
CREATE TABLE backup_userTbl
( userID  CHAR(8) NOT NULL PRIMARY KEY, 
  name    VARCHAR(10) NOT NULL, 
  birthYear   INT NOT NULL,  
  addr	  CHAR(2) NOT NULL, 
  mobile1	CHAR(3), 
  mobile2   CHAR(8), 
  height    SMALLINT,  
  mDate    DATE,
  modType  CHAR(2), -- 변경된 타입. '수정' 또는 '삭제'
  modDate  DATE, -- 변경된 날짜
  modUser  VARCHAR(256) -- 변경한 사용자
);

-- 백업 테이블에 업데이트 기록
DROP TRIGGER IF EXISTS backUserTbl_UpdateTrg;
DELIMITER // 
CREATE TRIGGER backUserTbl_UpdateTrg  -- 트리거 이름
    AFTER UPDATE -- 변경 후에 작동하도록 지정
    ON userTBL -- 트리거를 부착할 테이블
    FOR EACH ROW 
BEGIN
    INSERT INTO backup_userTbl VALUES( OLD.userID, OLD.name, OLD.birthYear, 
        OLD.addr, OLD.mobile1, OLD.mobile2, OLD.height, OLD.mDate, 
        '수정', CURDATE(), CURRENT_USER() ); -- OLD 는 방금 삭제된 행 내용을 의미
END // 
DELIMITER ;

-- 백업 테이블에 삭제 기록
DROP TRIGGER IF EXISTS backUserTbl_DeleteTrg;
DELIMITER // 
CREATE TRIGGER backUserTbl_DeleteTrg  -- 트리거 이름
    AFTER DELETE -- 삭제 후에 작동하도록 지정
    ON userTBL -- 트리거를 부착할 테이블
    FOR EACH ROW 
BEGIN
    INSERT INTO backup_userTbl VALUES( OLD.userID, OLD.name, OLD.birthYear, 
        OLD.addr, OLD.mobile1, OLD.mobile2, OLD.height, OLD.mDate, 
        '삭제', CURDATE(), CURRENT_USER() );
END // 
DELIMITER ;

-- 변경,삭제 실행 예시
UPDATE userTbl SET addr = '몽고' WHERE userID = 'JKW'; -- 값 변경
DELETE FROM userTbl WHERE height >= 177; -- 값 삭제

SELECT * FROM backup_userTbl; -- 조회시 변경전 값 기록됨 확인

TRUNCATE TABLE userTbl; -- DDL문으로 테이블 전체 값 삭제 

SELECT * FROM backup_userTbl; -- 기록안됨, DDL문으로 삭제시 기록 안됨

-- BEFORE 트리거
-- 입력 전 트리거 실행
DROP TRIGGER IF EXISTS userTbl_InsertTrg;
DELIMITER // 
CREATE TRIGGER userTbl_InsertTrg  -- 트리거 이름
    AFTER INSERT -- 입력 후에 작동하도록 지정
    ON userTBL -- 트리거를 부착할 테이블
    FOR EACH ROW 
BEGIN
    SIGNAL SQLSTATE '45000' -- 발생시킬 오류
        SET MESSAGE_TEXT = '데이터의 입력을 시도했습니다. 귀하의 정보가 서버에 기록되었습니다.'; -- 전달 메시지
END // 
DELIMITER ;

INSERT INTO userTbl VALUES('ABC', '에비씨', 1977, '서울', '011', '1111111', 181, '2019-12-25'); -- 값 입력시 트리거 실행됨
```

### 트리거가 생성하는 임시 테이블

- 트리거에서 INSERT, UPDATE, DELETE 작업이 수행되면 임시로 사용되는 시스템 테이블 2개가 있음
  - NEW 테이블
    - INSERT (새 값) 실행시 NEW테이블을 거쳐 입력 테이블에 새값이 입력 됨
  - OLD 테이블
    - DELETE (예전 값) 실행시 테이블에 값을 지우고 OLD 테이블에 잠깐 기록 됨
  - UPDATE(새 값, 예전 값) 실행시 NEW와 OLD가 둘다 사용 됨

![30](D:\workspace\00.TIL\SQL\IMAGE\30.png)

``` sql
-- BEFORE 트리거 예시
USE sqlDB;
DROP TRIGGER IF EXISTS userTbl_BeforeInsertTrg;
DELIMITER // 
CREATE TRIGGER userTbl_BeforeInsertTrg  -- 트리거 이름
    BEFORE INSERT -- 입력 전에 작동하도록 지정
    ON userTBL -- 트리거를 부착할 테이블
    FOR EACH ROW 
BEGIN -- 출생년도가 1900 미만이면 0으로 출생년도를 현재 이후로 할경우 지금의 년도로 입력되도록
    IF NEW.birthYear < 1900 THEN
        SET NEW.birthYear = 0;
    ELSEIF NEW.birthYear > YEAR(CURDATE()) THEN
        SET NEW.birthYear = YEAR(CURDATE());
    END IF;
END // 
DELIMITER ;

INSERT INTO userTbl VALUES
  ('AAA', '에이', 1877, '서울', '011', '1112222', 181, '2022-12-25');
INSERT INTO userTbl VALUES
  ('BBB', '비이', 2977, '경기', '011', '1113333', 171, '2019-3-25');


SHOW TRIGGERS FROM sqlDB;

DROP TRIGGER userTbl_BeforeInsertTrg;
```



### 트리거 기타 내용

- 다중 트리거
  - 하나의 테이블에 동일한 트리거가 여러개 부착되어 있는 것
- 중첩 트리거
  - 트리거가 다른 트리거를 작동하는 것을 의미

![31](D:\workspace\00.TIL\SQL\IMAGE\31.png)



## 11. 전체 텍스트 검색과 파티션

- 긴 문자로 구성된 구조화되지 않은 텍스트 데이터를 빠르게 검색하기 위한 부가적인 MySQL 기능

```sql
-- 전체 텍스트 인덱스 예시
CREATE FULLTEXT INDEX 생성할_인덱스_이름 ON 테이블명(열이름);

SHOW INDEX FROM 생성할_인덱스_이름;
```

- 전체 텍스트 인덱스
  - 문자의 각각의 단어들로 인덱스를 만드는 것
  - 전체 텍스트는 InnoDB와 MyISAM 테이블만 지원함
  - 전체 텍스트 인덱스는 char, varchar, text의 열에만 생성 가능
  - 인덱스 힌트의 사용이 일부 제한됨
  - 여러 개 열에 FULLTEXT 인덱스를 지정할 수 있음
- 중지 단어
  - 전체 텍스트는 긴 문장에 대해 인덱스를 생성해 양이 커지므로, 무시할 단어를 전체 텍스트 인덱스로 생성하지 않는 것이 좋음

```sql
-- 중지단어 예시
CREATE TABLE user_stopword (value VARCHAR(30)); -- 중지단어를 모아둘 테이블 생성

INSERT INTO user_stopword VALUES ('그는'), ('그리고'), ('극에'); -- 중지단어 값 추가
-- 중지단어 글로벌 설정
SET GLOBAL innodb_ft_server_stopword_table = 'fulltextdb/user_stopword'; -- 모두 소문자로 입력

SHOW GLOBAL VARIABLES LIKE 'innodb_ft_server_stopword_table'; -- 중지단어 추가 확인
-- 이후 텍스트 인덱스 생성
```



- 자연어 검색

```sql
-- '영화'라는 단어 검색 예시
SELECT * FROM newspaper
	WHERE MATCH(article) AGAINST('영화');
	-- '영화가', '영화는', '한국영화' 등과같은 단어는 검색 안됨

-- '영화' 또는 '배우' 라는 단어중 하나가 포함된 기사를 찾으려면
SELECT * FROM newspaper
	WHERE MATCH(article) AGAINST('영화 배우'); -- 띄어쓰기를 해주면 두개의 조건에 대해 검색해 줌

-- 불린모드 검색 : 단어나 문장이 정확히 일치하지 않아도 검색하는 것
SELECT * FROM newspaper
	WHERE MATCH(article) AGAINST('영화*' IN BOOLEAN MODE);
	-- '영화를', '영화가', '영화는' 등 영화가 앞에들어간 모든 결과 검색

-- '영화' 또는 '배우' 가 아닌 '영화 배우'라는 단어를 검색하려면
SELECT * FROM newspaper
	WHERE MATCH(article) AGAINST('영화 배우' IN BOOLEAN MODE); -- 불린모드 사용시 해당 단어 검색해줌
	
-- '영화 배우'가 있는 기사 중 '공포' 의 내용이 꼭 들어간 결과만 검색하고 싶을 경우
SELECT * FROM newspaper
	WHERE MATCH(article) AGAINST('영화 배우 +공포' IN BOOLEAN MODE); -- [+찾을단어] 를 추가
	
-- '영화 배우'가 있는 기사 중 '남자' 의 내용이 없는 결과만 검색하고 싶을 경우
SELECT * FROM newspaper
	WHERE MATCH(article) AGAINST('영화 배우 -남자' IN BOOLEAN MODE); -- [-뺄단어] 를 추가
```



### 파티션

- 대량의 테이블을 물리적으로 여러개로 쪼개는 것을 의미
- 대량의 테이블 이용시 부하를 줄이기 위해 사용함

![32](D:\workspace\00.TIL\SQL\IMAGE\32.png)

```sql
-- 파티션 분할 예시

CREATE DATABASE IF NOT EXISTS partDB;
USE partDB;
DROP TABLE IF EXISTS partTBL;
-- 파티션 분할을 사용할 테이블 생성
CREATE TABLE partTBL (
  userID  CHAR(8) NOT NULL, -- Primary Key로 지정하면 안됨
  name  VARCHAR(10) NOT NULL,
  birthYear INT  NOT NULL,
  addr CHAR(2) NOT NULL )
-- 파티션 조건 설정
PARTITION BY RANGE(birthYear) (
    PARTITION part1 VALUES LESS THAN (1971), -- 1971년생 미만은 PART1
    PARTITION part2 VALUES LESS THAN (1979), -- 1979년생 미만은 PART2
    PARTITION part3 VALUES LESS THAN MAXVALUE -- 최대값 미만은 PART3
);
INSERT INTO partTBL 
	SELECT userID, name, birthYear, addr FROM sqlDB.userTbl; -- 값 입력

SELECT * FROM partTBL; -- 조회시 각 파티션별 데이터가 [기본키]순서대로 조회됨

-- 테이블에 파티션 정보 확인하기 위한 쿼리문
SELECT TABLE_SCHEMA, TABLE_NAME, PARTITION_NAME, PARTITION_ORDINAL_POSITION, TABLE_ROWS
    FROM INFORMATION_SCHEMA.PARTITIONS
    WHERE TABLE_NAME =  'parttbl';
```

![33](D:\workspace\00.TIL\SQL\IMAGE\33.jpg)

```sql
-- EXPLAIN 실제로 사용한 파티션을 확인하기 위한 쿼리문 
EXPLAIN  SELECT * FROM partTBL WHERE birthYear <= 1965;
```

![34](D:\workspace\00.TIL\SQL\IMAGE\34.jpg)

```sql
-- 파티션 추가 분할
ALTER TABLE partTBL -- 분할할 테이블 선택
	REORGANIZE PARTITION part3 INTO (				-- 분할할 파티션 선택
		PARTITION part3 VALUES LESS THAN (1986),     -- 분할할 기준1
		PARTITION part4 VALUES LESS THAN MAXVALUE	-- 분할할 기준2
	);
OPTIMIZE TABLE partTBL; -- 분할 적용 쿼리

-- 파티션 병합
ALTER TABLE partTBL -- 합칠 테이블 선택
	REORGANIZE PARTITION part1, part2 INTO (		-- 합칠 파티션 선택
		PARTITION part12 VALUES LESS THAN (1979)	-- 파티션 기준 정의
	);
OPTIMIZE TABLE partTBL; -- 병합 적용

-- 파티션 삭제
ALTER TABLE partTBL DROP PARTITION part12; -- DROP PARTITION 삭제할파티션이름
OPTIMIZE TABLE partTBL;
# 주의 삭제하면 파티션에 입력된 데이터도 같이 삭제 됨
```

- 파티션 정리
  - 범위 가아닌 지역과 같은 것으로도 파티션을 구분할 수 있음
  - 파티션 테이블에 외래키를 설정할 수 없음 (단독으로 사용되는 테이블에만 파티션 설정 가능)
  - 스토어드 프로시저, 스토어드 하뭇, 사용자 변수 등을 파티션 함수나 식에 사용할 수 없음
  - 임시 테이블은 파티션 기능을 사용할 수 없음
  - 파티션 키에는 일부 함수만 사용할 수 있음
  - 파티션 개수는 최대 8192개
  - 레인지 파티션은 숫자형의 연속된 범위를 사용함
  - 리스트 파티션은 숫자형 또는 문자형의 연속되지 않은 하나하나씩 파티션 키 값을 지정함
  - 리스트 파티션에는 MAXVALUE를 사용할 수 없음 (모든 경우의 파티션 키 값을 지정해야 함)

## 12. PHP 기본 프로그래밍

### 웹사이트 개발환경 구축

- XAMPP 소개
  - 웹서버의 종류는 다양하지만, 오랫동안 다양한 운영체제에서 작동을 지원하는 아파치(Apache) 웹 서버가 가장 많이 다양한 분야의 웹 사이트에서 사용되고 있음
  - 데이터베이스는 MySQL이 대부분 사용 되어 왔음
  - 웹프로그래밍 언어는 PHP가 Apache 및 MySQL과 함께 인기를 얻게 됨
  - Apache, MySQL, PHP 세 소프트웨어 제작사가 달라 별도로 설치할 경우 소프트웨어 버전에 따른 충돌이나 설정을 사용자가 직접 해야함
  - 이러한 문제해결을 위해 세 소프트웨어를 함께 묶어 상호호환성이나 충돌 문제를 미리 해결해서 배포하는 소프트웨어가 XAMPP임
- 서버 스크립트와 클라이언트 스크립트

### HTML 태그

- HTML 태그의 공통적인 특징
  - HTML 파일의 확장자는 *.htm 또는 *.html
  - HTML 파일은 텍스트 파일이므로 메모장 등에서 작성한다. 단 웹 브라우저에서 한글이 깨져 보일수 있으므로 인코딩 방식은 UTF-8로 저장
  - HTML의 태그는 대부분 <> 안에 쓴다.
  - HTML은 대문자와 소문자를 구분하지 않는다.
  - HTML 파일은 <HTML> 태그로 시작해서 </HTML> 태그로 종료한다.

```php
<HTML>
<HEAD>
	화면에 표시되지 않는 정보(타이틀, 인코딩 정보 등)
</HEAD>

<BODY>
	화면에 보이는 본체(주로 태그들을 표현)
</BODY>
</HTML>
```

### HTML 태그 기본

- <META>
  - 웹 페이지의 정보를 설정하는데 검색 엔진에게 문서의 내용을 요약해서 보여줌
  - 웹 페이지의 문자 코딩을 UTF-8로 인식되게 함

```php
<MEATA http-equiv="content-type" content="text/html; charset=utf-8">
```

- <BR>
  - 글자의 줄을 바꿔줌
  - 결과는 2줄로 출력됨

```php
안녕하세요? <BR>  MySQL 학습 중 입니다.
```

- <U>~</U>, <B>~</B>, <I>~</I>
  - 글자에 밑줄, 굵은 글씨, 이탤릭체의 모양을 지정함

```php
<U>이건 밑줄</U> <BR>
<B>이건 굵게</B> <BR>
<I>이건 이탤릭</I>
```

- <FONT>~</FONT>
  - 글자의 크기나 색상을 지정함
  - 궁서체로 10크기의 빨간색 글자 출력

```php
<FONT COLOR='RED' SIZE='10' FACE='궁서'> 폰트 변경 </FONT>
```

- <HR>

  - 수평선을 그어줌 <HR SIZE=’픽셀수’> 는 픽셀 수의 폭으로 선을 그어줌

  ```php
  <HR SIZE='10'>
  ```

- <A>~</A>

  - 클릭하면 다른 페이지가 연결되는 링크를 설정함. 주로 herf 속성으로 연결된 홈페이지를 지정

```php
<A HREF='<http://www.naver.com>' target='_blank'> 네이버 홈페이지 연결 </A>
```

- <IMG>
  - 이미지 파일을 화면에 표시함

```jsx
<IMG src='mouse.png' width=100 height=100>
```

- <TABLE>~</TABLE>, <TR>~</TR>, <TH>~</TH>, <TD>~</TD>
  - 표를 만드는 태그들
  - TABLE 안의 행은 TR로 구성됨
  - TR 안에 열이 TH, TD로 구성됨
    - TH는 제목열을 표현해 두꺼운 글씨체로 보임
    - TD는 일반 열로 표현됨

```jsx
<TABLE>
<TR>
			<TH>아이디</TH>
			<TH>이름</TH>
</TR>
<TR>
			<TD>BBK</TD>
			<TD>바비킴</TD>
</TR>
<TR>
			<TD>LSG</TD>
			<TD>이승기</TD>
</TR>
</TABLE>
```

### PHP 기본 문법

1. 변수와 데이터 형식

- PHP의 기본 구조와 주석

```php
// PHP 기본 틀
<?php

?>

// php 주석
<?php
// 한 줄 주석용
/* 
	여러 줄
	주석 용
*/
?>

// 변수
$a = 100;

// 변수와 출력 print / echo
<?php

	$a = 100
	print $a;

	$b = "안녕하세요? MySQL";
	echo $b

?>
```

- PHP의 변수 이름 규칙
  - 제일 앞에 $가 붙어야 한다
  - 문자와 숫자, 언더바(_)를 사용할 수 있지만 숫자로 시작할 수 없음.

![35](D:\workspace\00.TIL\SQL\IMAGE\35.PNG)

1. 데이터 형식

- PHP 데이터 형식
  - 정수 INT
  - 실수 DOUBLE
  - 문자열 STRING
  - 불형 BOOLEAN
  - 객체 OBJECT
  - 배열 ARRAY 등이 있음

```php
<?php

	$a = 123; echo gettype($a), "<br>";
	$a = 123.123; echo gettype($a), "<br>";
	$a = "MySQL"; echo gettype($a), "<br>";
	$a = true; echo gettype($a), "<br>";
	$a = array( 1, 2, 3 ); echo gettype($a), "<br>";

?>
```

![36](D:\workspace\00.TIL\SQL\IMAGE\36.png)

- 문자열
  - 문자열은 큰 따옴표(”) 또는 작은 따옴표(’)로 묶어야 함
  - 일반적으로는 아무거나 사용가능 하지만 SQL문을 문자열로 지정하기 위해서 큰 따옴표로 묶고, 그 내부에 필요할 경우 작은 따옴표로 묶어주는 방식이 바람직함

![37](D:\workspace\00.TIL\SQL\IMAGE\37.png)



### HTML 과 PHP 관계

- HTML과 PHP 데이터 전송 개념

![38](D:\workspace\00.TIL\SQL\IMAGE\38.png)

```php+HTML
// send.html 파일
<HTML>
<HEAD>
	<META http-equiv="content-type" content="text/html; charset=utf-8">
</HEAD>
<BODY>

<FORM METHOD="post" ACTION="receive.php">
    아이디 : <INPUT TYPE ="text" NAME="userID">
    이름 : <INPUT TYPE ="text" NAME="userName">
    <BR><BR>
    <INPUT TYPE="submit" VALUE="전송">
</FORM      

</BODY>
</HTML>
        
// receive.php 파일
<?php
        $userID = $_POST(["userID"]);
        $userNAm = $_POST(["userName"]);
        
        echo " 전달 받은 아이디 : ", $userID, "<br>";
        echo " 전달 받은 이름 : ", $userName, "<br>";
?>
```



- POST와 GET 전달 방식
  - POST는 정보가 보이지 않는 전달 방식
  - GET은 정보가 보이는 전달방식



### HTML과 PHP 혼용

- HTML 문법으로만 구성된 파일의 확장명을 *.php로 저장해 사용해도 상관 없음
- PHP와 HTML 코드를 석어서 사용해도 상관 없음

### 조건문과 반복문

- IF() 함수

```php+HTML
// 형식
if(조건식) {
	// 참일 때 실행
} else {
	// 거짓일 때 실행
}

// 예시
<?php
	$a=100;
	$b=200;

	if($a > $b) {
        echo "a가 b보다 큽니다.";
    } else {
        echo "a가 b보다 작습니다.";
    }
?>

// 여러 개의 조건 if~elseif~else
<?php
	$jumsu=83;

	if($jumsu >= 90) {
        echo "A학점";
    } elseif($jumsu >= 80) {
        echo "B학점";
    } elseif($jumsu >= 70) {
        echo "C학점";
    } elseif($jumsu >= 60) {
        echo "D학점";
    } else {
        echo "F학점"
    }
?>
```



- switch()함수
  - if~elseif와 비슷하게 switch~case로 여러 조건을 처리할 수 있음
    - default부분은 생략 가능

```php+HTML
// 형식
switch(변수) {
	case 값1:
	// 값1이면 이 부분을 처리
	break;
	case 값2:
	// 값2면 이 부분을 처리
	break;
	...
	default:
	// 아무 것도 해당 안되면 이 부분을 처리
}

// switch~case 사용 예시
<?php
	$jumsu=83;
	// intval 은 정수형으로 바꿔줌
	switch(intval($jumsu / 10)) {
        case 10:
        case 9:
            echo "A학점"; break;
        case 8:
            echo "B학점"; break;
        case 7:
            echo "C학점"; break;
        case 6:
            echo "D학점"; break;
        default:
            echo "F학점"''
    }
?>
```



- for() 함수
  - 지정된 수 만큼 반복하기 위해 사용되는 함수

```php+HTML
// 형식
for(초깃값 ; 조건식 ; 증감식) {
	//  이 부분을 반복함
}

// 1부터 10까지 출력하도록 활용 예시
<?php
	for( $i=1; $i<=10; $i=$i+1) {
        echo $i, " ";
    }
?>

// 123부터 456까지 홀수의 합계 활용 예시
<?php
	$hap = 0;	

	for( $i=123; $i<=456; $i=$i+2) {
        $hap = $hap+$i;
    }

	echo "123부터 456까지 홀수의 합계 : ", $hap;
?>

```



- while() 함수
  - 조건식만 있음

```php+HTML
// 형식
초깃값;
while(조건식) {
	// 이 부분을 반복함
	증감식;
}

<?php
	$hap=0;
	
	$i=123;
	while( $i<=456 ) {
        $hap = $hap +$i;
        $i=$i+2;
    }

	echo "123부터 456까지 홀수의 합계 : ", $hap;
?>
```



- 배열
  - 배열에는 형식이 따로 없음

```php+HTML
// 형식1 :
$배열명 = array(값1, 값2, 값3 ...);

// 형식2 :
$배열명 = range(시작값, 끝값, 증가값);

// 형식3 :
$배열명[0] = 값1;
$배열명[1] = 값2;
$배열명[2] = 값3;
...

// 배열 예시
<?php
  $myArray = array(100, 'MySQL', 123.123);
  echo $myArray[0], " ", $myArray[1], " ", $myArray[2], "<br>";
  
  $myArray = range(1,3);
  echo $myArray[0], " ", $myArray[1], " ", $myArray[2], "<br>";
  
  $myArray = range(1,10,2);
  echo $myArray[0], " ", $myArray[4], "<br>";
  
  $newArray[0] = 'This';
  $newArray[1] = 'is';
  $newArray[2] = 'MySQL';
  echo $newArray[0], " ", $newArray[1], " ", $newArray[2], "<br>";  
 ?>
```

![39](D:\workspace\00.TIL\SQL\IMAGE\39.png)

```php+HTML
// 배열 예시 2 : 1에서 10까지 더하기
<?php
  $hap = 0;
  $myArray = range(1,10); // 1부터 10 배열
 // (0부터, 10미만, 1씩증가)
  for($i=0; $i<10; $i++) {
     $hap = $hap + $myArray[$i];
  }
   echo "배열의 합계 : " , $hap;  
?>

// 배열 예시 3 :
<?php
  $myArray = range(1,10);
  
  echo "임의로 섞은 값 ==> ";
  shuffle($myArray); // shuffle 내용을 섞어줌
  foreach($myArray as $data) // foreach 배열의 데이터를 하나씩 빼서 돌려줌
	echo $data, " ";
	
  echo "<br>오름차순 정렬 ==> ";	
  sort($myArray);
  foreach($myArray as $data)
	echo $data, " ";

  echo "<br>내림차순 정렬 ==> ";	
  rsort($myArray);
  foreach($myArray as $data)
	echo $data, " ";
  
  echo "<br>순서를 반대로 ==> ";	
  $revArray = array_reverse($myArray); //array_reverse 순서 뒤집기
  foreach($revArray as $data)
	echo $data, " ";	
 ?>
```

![40](D:\workspace\00.TIL\SQL\IMAGE\40.png)



### PHP의 내장함수

![41](D:\workspace\00.TIL\SQL\IMAGE\41.png)

```php+HTML
// 다양한 함수의 활용
<?php
	// 현재 날짜 연-월-일 로 출력
   $today = "현재는 ".date("Y-m-j")." 입니다.";
   echo $today, "<br>";
   
	// ARRAY에 최대/최소값 출력 가능
   $ary = array(100, 50, 200, 7);
   echo "최대:", max($ary) ," 최소:", min(-123, 50, 999), "<br>";
   
	// PI값 출력가능, 반올림, 올림
   echo pi(), " ", round(M_PI), " ",ceil(M_PI), "<br>";
   
	// TRIM 문자열 앞 뒤 공백 제거
   $str = "   This is MySQL   "; // 앞뒤에 공백 3개씩.
   $str = trim($str);
   echo "#", $str, "#", "<br>";
   
	// 문자열 길이 반환
   echo "문자열 길이:", strlen($str), "<br>";
   
	//문자열 횟수 만큼 반복
   echo str_repeat("-", 30), "<br>";
   				  // OLD, NEW, TARGET : OLD 에서 NEW로 타겟 문자열의 값을 바꿔줌
   echo str_replace( "MySQL", "마이에스큐엘", "This is MySQL"), "<br>";
   
   $ary = str_split("This is MySQL", 3); // 문자열을 길이만큼 잘라 배열로 분리 함
   print_r($ary); echo "<br>"; // 배열을 출력한다.
   echo "<br>";
   
	// 배열을 만듬(구분자, 배열로 만들 값)
   $ary = explode(" ", "This is MySQL");
   print_r($ary); echo "<br>";// 배열을 출력한다.
   
	// 배열을 문자열로 만듬(ARRAY, 구분자)
   echo implode($ary, " "), "<br>";
   
   $myHTML = "<A href='www.hanbit.co.kr'> 한빛미디어 </A> <br>";
   echo $myHTML;
   echo htmlspecialchars($myHTML);  // HTML 을 그대로 그냥 출력하려고 할 경우
 ?>
```

![42](D:\workspace\00.TIL\SQL\IMAGE\42.png)

### [그 외 함수 참고 사이트](http://docs.php.net/manual/kr/)

- php 주요 MySQL 관련 함수

![43](D:\workspace\00.TIL\SQL\IMAGE\43.png)



## 13. PHP와 MySQL의 연동

1. db 접속

```php+HTML
<?php
   $db_host="localhost";
   $db_user="root";
   $db_password="0000";
   $db_name="";
   $con=mysqli_connect($db_host, $db_user, $db_password, $db_name);
   if ( mysqli_connect_error($con) ) {
	   echo "MySQL 접속 실패 !!", "<br>";
	   echo "오류 원인 : ", mysqli_connect_error();
	   exit();
   }
   echo "MySQL 접속 완전히 성공!!";
   mysqli_close($con);
?>

// 좀더 간단히 접속
<?php
   $con=mysqli_connect("localhost", "root", "0000", "") or die("MySQL 접속 실패 !!");
   echo "MySQL 접속 완전히 성공!!";
   mysqli_close($con);
?>

// 데이터 베이스 생성
<?php
   $con=mysqli_connect("localhost", "root", "0000", "") or die("MySQL 접속 실패 !!");
         
   $sql="CREATE DATABASE sqlDB"; // 쿼리문을 작성해서 변수에 넣고
   $ret = mysqli_query($con, $sql); // mysqli_query 로 쿼리문을 날려준뒤 ret으로 받아줌

	// ret은 정상적으로 실행되면 True, 실행되지 않으면 False   
   if($ret) {
	   echo "sqlDB가 성공적으로 생성됨.";
   }
   else {
	   echo "sqlDB 생성 실패!!!"."<br>";
	   echo "실패 원인 :".mysqli_error($con);
   }
   
   mysqli_close($con);
?>

// 테이블 생성
<?php
   $con=mysqli_connect("localhost", "root", "0000", "sqlDB") or die("MySQL 접속 실패 !!");

   $sql ="
	   CREATE TABLE userTbl 
		( userID  	CHAR(8) NOT NULL PRIMARY KEY,
		  name    	VARCHAR(10) NOT NULL,
		  birthYear   INT NOT NULL,
		  addr	  	CHAR(2) NOT NULL,
		  mobile1	CHAR(3),
		  mobile2	CHAR(8),
		  height    	SMALLINT,
		  mDate    	DATE
		)
   ";
 
   $ret = mysqli_query($con, $sql);
 
   if($ret) {
	   echo "userTBL이 성공적으로 생성됨..";
   }
   else {
	   echo "userTBL 생성 실패!!!"."<br>";
	   echo "실패 원인 :".mysqli_error($con);
   }
 
   mysqli_close($con);
?>

// insert 
<?php
   $con=mysqli_connect("localhost", "root", "0000", "sqlDB") or die("MySQL 접속 실패 !!");

   $sql ="
		INSERT INTO userTbl VALUES
		('LSG', '이승기', 1987, '서울', '011', '1111111', 182, '2008-8-8'),
		('KBS', '김범수', 1979, '경남', '011', '2222222', 173, '2012-4-4'),
		('KKH', '김경호', 1971, '전남', '019', '3333333', 177, '2007-7-7'),
		('JYP', '조용필', 1950, '경기', '011', '4444444', 166, '2009-4-4'),
		('SSK', '성시경', 1979, '서울', NULL  , NULL      , 186, '2013-12-12'),
		('LJB', '임재범', 1963, '서울', '016', '6666666', 182, '2009-9-9'),
		('YJS', '윤종신', 1969, '경남', NULL  , NULL      , 170, '2005-5-5'),
		('EJW', '은지원', 1972, '경북', '011', '8888888', 174, '2014-3-3'),
		('JKW', '조관우', 1965, '경기', '018', '9999999', 172, '2010-10-10'),
		('BBK', '바비킴', 1973, '서울', '010', '0000000', 176, '2013-5-5')
   ";
 
   $ret = mysqli_query($con, $sql);
 
   if($ret) {
	   echo "userTBL이 데이터가 성공적으로 입력됨.";
   }
   else {
	   echo "userTBL 데이터 입력 실패!!!"."<br>";
	   echo "실패 원인 :".mysqli_error($con);
   }
 
   mysqli_close($con);
?>

// select
<?php
   $con=mysqli_connect("localhost", "root", "0000", "sqlDB") or die("MySQL 접속 실패 !!");

   $sql ="
		SELECT * FROM userTBL
   ";
 
   $ret = mysqli_query($con, $sql);
 
   if($ret) {
	   echo mysqli_num_rows($ret), "건이 조회됨.<br><br>";
   }
   else {
	   echo "userTBL 데이터 조회 실패!!!"."<br>";
	   echo "실패 원인 :".mysqli_error($con);
	   exit();
   }
   
   while($row = mysqli_fetch_array($ret)) {
	   echo $row['userID'], " ", $row['name'], " ", $row['height'], " ", "<br>";
   }   
 
   mysqli_close($con);
?>

```



- 회원관리 시스템

![44](D:\workspace\00.TIL\SQL\IMAGE\44.png)



















