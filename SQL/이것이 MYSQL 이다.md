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



25 부터











