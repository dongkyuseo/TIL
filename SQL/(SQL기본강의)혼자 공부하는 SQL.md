# 01-1. 데이터베이스 알아보기

- 데이터베이스(DB) : 데이터의 집합

- DBMS : 데이터베이스를 운영하고 관리하는 소프트웨어, 대용량의 데이터베이스를 다수의 사용자가 동시에 수정관리 가능

## DMBS의 종류

- MySQL, MaraiDB, Oracle 등

## DMBS의 발전 과정

1. 종이에 펜으로 기록

2. 컴퓨터에 파일로 저장

3. 대량의 데이터를 효율적으로 관리하기 위한 DBMS 등장

## RDBMS 관계형 DMBS

- 관계형 DBMS는 현재 많이 사용됨

- Relational DBMS는 줄여서 RDBMS라고 부름

- 관계형 DBMS의 가장 기본단위는 테이블

- 테이블은 하나이상의 열과 행으로 이루어져 있음

## DBMS에서 사용되는언어 : SQL

- SQL은 관계형 데이터베이스에서 사용되는 언어로, '에스큐엘' 또는 '시퀄'로 읽음

- 표준 SQL을 지정해서 언어의 통일성을 갖춤

# 02-1. 데이터베이스 모델링

- 데이터베이스 모델링 : 설계도를 그리는 과정과 같음

## 프로젝트 진행단계

- 프로젝트 : 현실세계에 일어나는 업무를 컴퓨터 시스템으로 옮겨놓는 과정, 소프트웨어를 작성하는 전체적인 과정

## 데이터베이스 모델링

- 시스템설계부분으로, 우리가 살고있는 세상에 사용되는 사물을 데이터베이스에 넣는 과정을 의미

## 전체 데이터 베이스 구성

- 기본키(PK) : 열지정, 중복불가, Null 불가(빈값이 될 수 없음)

# 02-2. 데이터베이스 시작부터 끝까지

- 데이터베이스 구축 절차
  
  - 데이터베이스 만들기 -> 테이블만들기 -> 데이터입력/수정/삭제하기 -> 데이터 조회/활용하기

- 데이터베이스 만들기
  
  - schemas = 데이터베이스 : 같은의미

# 02-03. 데이터베이스 개체

- 데이터베이스 개체란 데이터베이스 안에 들어있는 오브젝트들을 의미 (뷰, 인덱스, 스토어드 프로시저, 트리거 등)

- 인덱스(index) : 데이터 조회시 빠르게 조회하도록 도와주는 개념

- 뷰(view) : 가상의 테이블을 의미

## 스토어드 프로시저

- 명령어 저장해서 단축어 만들기.

# 03. SQL 기본문법

# 03-1. 기본 중에 기본, SELECT ~ FROM ~ WHERE

- SELECT 문은 테이블에서 데이터를 추출하는 기능

## 실습용 데이터베이스 구축

- -- 주석내용 : 주석 작성

- DROP DATABASE : 데이터베이스 삭제

- CREATE DATABASE name; : 데이터베이스 이름 생성

- USE DATABASENAME ; : 사용할 데이터베이스 지정

- CREATE TABLE member : 데이터베이스 안에 테이블 이름 지정하여 생성

- CHAR(개수) : 문자열로 지정(개수)가 있는 경우 해당 갯수만큼 작성 가능

- NOT NULL : 빈값 비허용

- PRIMARY KEY : 고유값 유일한값 지정

- VARCHAR(10) : 문자열로 지정(개수)가 있는경우 해당 갯수만큼 작성 가능

- INT : 정수 자료형태

- SMALLINT : 작은 정수?

- DATE : 날짜 자료형태

## USE문

- 데이터베이스가 많을때 데이터베이스를 지정하는 명령어

```sql
-- SELCET 절의 형식

SELECT 열_이름
      FROM 테이블_이름
      WHERE 조건식
      GROUP BY 열_이름
      HAVING 조건식
      ORDER BY 열_이름
      LIMIT 숫자
```

# 03.SQL 기본 문법

## 03-3 데이터변경을 위한 SQL문

## 테이블 생성: CREATE TALBE

- CREATE TALBE 테이블명 (열이름 속성(INT, CHAR 등..), ... 

- AUTO_INCREMENT : ID를 자동으로 생성하려면 AUTO_INCREMENT 명령어로 자동생성 가능하나, 항상 PRIMARY KEY로 지정해야 함,
  
  - CREATE TABLE 테이블명 (ID INT AUTO_INCREMENT PRIMARY KEY)

- PRIMARY KEY : 유일한 값으로 값이 단1개만 존재 할 수 있음

- ALTER TABLE 테이블명 AUTO_INCREMENT=100; : ID 자동생성 숫자를 임의로 변경하는 명령어

- SET @@auto_increment_increment=건너뛸갯수; : 자동생성 숫자를 1씩증가가 아닌 원하는 만큼 증가시키는 명령어

## 데이터 입력: INSERT

- 테이블에 값을 입력하는 방법
  
  - INSERT INTO 테이블명 VALUE (값); : 테이블 열에 맞춰 값을 맞춰 입력 해줘야 함
  
  - INSERT INTO 테이블명(열이름2, 열이름1) VALUE(열2값, 열1값); : 테이블의 특정 열을 지정해서 입력할수 있고, 열의 순서에 관계없이 지정한 열순서로 입력 가능

- SELECT LAST_INSERT_ID(); : 마지막으로 입력된 데이터 ID 확인명령어

```sql
-- SQL 샘플
CREATE TABLE hongong3 ( -- 테이블 생성
    toy_id INT AUTO_INCREMENT PRIMARY KEY,  -- ID 자동생성을 위한 PRIMARY KEY 지정
    toy_name CHAR(4),  -- 이름 문자열 4개로 지정
    age INT); -- 나이 숫자로 지정
ALTER TABLE hongong3 AUTO_INCREMENT=1000; -- 데이터 입력전 ID를 1000번부터 시작하도록 설정
SET @@auto_increment_increment=3; -- ID 증가를 3씩 1000 >  1003 > 1006 > ...
```

## DESC 테이블 정보 확인

- DESC 테이블명; : 테이블의 열이름, 타입, 널여부, 키 설정 등 테이블의 정보를 확인할 수 있음

## INSERT INTO ~ SELECT

- 새로운 데이터가 아닌 다른 테이블에 이미 입력되어 있는 많은 양의 데이터를 한번에 가져와서 다른 테이블에 입력하는 구문

- INSERT INTO 덮어쓸테이블명 SELECT 열이름 FROM 가져올테이블명

```sql
-- SQL 샘플
CREATE TABLE city_popul ( city_name CHAR(35), population INT); -- 빈 테이블 생성

INSERT INTO city_popul -- 빈테이블을 덮어쓸테이블로 지정
    SELECT Name, Population FROM world.city; -- world.city 테이블에서 name, population 열정보를 선택해 덮어씀

SELECT * FROM city_popul; -- 잘 복사 되었는지 확인
```

> update를 사용하려면 먼저 mysql의 Edit창에서 preferences를 선택후SQL Editor를 고른뒤 safe updates 체크를 풀어준 뒤 MYSQL을 껏다 켜주어야 함

![1.PNG](D:\workspace\00.TIL\SQL\IMAGE\1.PNG)

## UPDATE 문의 기본 문법

- UPDATE는 기존에 입력되어 있는 값을 수정하는 명령어

- UPDATE 업데이트할테이블명 SET 바꿀내용입력 WHERE 바뀔대상선택

- WHERE 없이 UPDATE SET 만 사용할 경우 모든 테이블의 내용이 동일하게 변경됨 따라서 잘 사용하지 않으나 가끔 특정한 상황에서 사용하는 경우가 있음

```sql
-- UPDATE SET WHERE 예시
UPDATE city_popul -- 업데이트할 테이블 선택
    SET city_name = '뉴욕', population = 0 -- 변경할 열선택후 변경내용 작성
    WHERE city_name = 'New York'; -- 변경 대상선택

SELECT * FROM city_popul WHERE city_name = '뉴욕'; -- 변경되었는지 조회시 변경한 내용을 입력하여 조회

-- UPDATE SET 예시 (WHERE를 사용하지 않는 경우)
UPDATE city_popul -- 업데이트할 테이블 선택
    SET population = population / 10000; -- 변경내용 입력, 예를 들어 인구수를 만명단위로 변경할 경우 모든 테이블의 인구를 10000으로 나누는 작업을 수행
SELECT * FROM city_popul LIMIT 5; 

-- UPDATE SET 주의 사항
UPDATE city_popul -- 업데이트할 테이블 선택
    SET city_name = '뉴욕', population = 0 -- 변경할 열선택후 변경내용 작성
-- 위와 같이 작성할 경우 테이블의 모든 city_name 이 뉴욕으로 변경되고, population 값이 0으로 바뀜
```

## DELETE 문의 기본 사용법

- DELETE는 테이블의 행 데이터를 삭제해야 하는 경우 사용

- DELETE FROM 테이블이름 WHRER 조건

- UPDATE와 마찬가지로 WHERE 없이 사용시 전체 행 데이터를 삭제하므로 주의!

```sql
-- DELETE FROM WHERE LIMIT 예시
SELECT * FROM city_popul WHERE city_name LIKE 'NEW%'; -- 삭제하기 전 자료 확인

DELETE FROM city_popul -- 삭제할 테이블명 설정
    WHERE city_name LIKE 'NEW%' -- 테이블내 조건 설정, city_name 열 중에서 new로 시작하는 조건의 도시이름 선택
    LIMIT 5; -- 그중 위에서부터 5개 삭제

SELECT * FROM city_popul WHERE city_name LIKE 'NEW%'; -- 삭제후 자료 확인

-- DELETE FROM WHERE 예시
DELETE FROM city_popul -- 삭제할 테이블 명 설정
    WHERE city_name LIKE 'NEW%'; -- 테이블내 조건 설정, city_name 열 중에서 new로 시작하는 조건의 도시이름 선택

SELECT * FROM city_popul WHERE city_name LIKE 'NEW%'; -- 모든 NEW로 시작하는 도시가 삭제됨을 확인
```

# 4장. SQL 고급 문법

## 04-1 MYSQL의 데이터 형식

- 테이블 생성시 데이터 형식을 지정해야 함(INT, CHAR, DATE 등..)

## 데이터 형식

### 정수형

- 정수형은 소수점이 없는 숫자를 의미함

- 정수형의 크기와 범위는 다음과 같음
  
  - INT / 크기: 4바이트, 숫자범위: 약 -21억 ~ +21억
  
  - SMALLINT / 크기 : 2바이트. 숫자범위: -32,768 ~ 32,767
  
  - TINYINT / 크기 : 1바이트, 숫자범위: -128 ~ 127
  
  - BIGINT / 크기 : 8바이트, 숫자범위: 약 -900경 ~ + 900경

- 나이와 같이 한정된 숫자를 입력하고 저장할때 TINYINT를 사용하면 4배의 용량을 저장할 수 있음. 

- 이와 같이 한정된 숫자를 사용 한다면 적절한 형식을 사용하면 데이터의 용량 및 속도를 개선할 수 있음

- UNSIGNED : 각각의 값을 -가 아닌 0부터 시작하는 양의 숫자로 구성할 수 있음
  
  - TINYINT UNSIGNED / 크기: 1바이트, 숫자범위: 0 ~ 255

```sql
-- 테이블 생성 비교
-- 1번 예시
CREATE TALBE memeber -- 회원 테이블
( mem_id      CHAR(8) NOT NULL PRIMARY KEY, -- 회원 아이디 (PK)
  mem_name    VARCHAR(10) NOT NULL, -- 이름
  mem_number  INT NOT NULL, -- 인원수
  addr        CHAR(2) NOT NULL, -- 주소(경기, 서울, 경남 식으로 2글자만 입력)
  phone1      CHAR(3), -- 연락처의 국번(02, 031, 055 등)
  phone2      CHAR(8), -- 연락처의 나머지 전화번호(하이픈 제외)
  height      SMALLINT, -- 평균 키
  debut_date  DATE -- 데뷔 일자
 );

-- 2번 예시
CREATE TALBE memeber -- 회원 테이블
( mem_id      CHAR(8) NOT NULL PRIMARY KEY, -- 회원 아이디 (PK)
  mem_name    VARCHAR(10) NOT NULL, -- 이름
  mem_number  TINYINT NOT NULL, -- 인원수
  addr        CHAR(2) NOT NULL, -- 주소(경기, 서울, 경남 식으로 2글자만 입력)
  phone1      CHAR(3), -- 연락처의 국번(02, 031, 055 등)
  phone2      CHAR(8), -- 연락처의 나머지 전화번호(하이픈 제외)
  height      TINYINT UNSIGNED, -- 평균 키
  debut_date  DATE -- 데뷔 일자
 );
```

- mem_number : INT -> TINYINT 변경하여 1/4 로 용량을 줄임

- height : SMALLINT -> TINYINT UNSIGNED 변경하여 1/2로 용량 줄임

## 문자형

- 글자를 저장하기 위해 사용함

- 데이터의 형식
  
  - CHAR(개수) : 바이트수 1 ~ 255
  
  - VARCHAR(개수)  : 바이트수 1 ~ 16383

- CHAR는 문자를 의미하는 Character의 약자로 고정길이 문자형을 의미함, 즉 자리수가 고정됨.

- CHAR(10)에 '가나다' 3글자만 입력해도 10의 자리를 모드 확보하게 되어 7자리를 낭비하게 됨

- VARCHAR는 가변 길이 문자형으로 VARCHAR(10)에 '가나다' 3글자를 저장할 경우 3자리만 사용됨

- 하지만, CHAR 가 VARCHAR를 사용할때 보다 속도가 더 빠름

- 따라서 용도에 맞게 CHAR, VARCHAR를 적절히 사용하는게 좋음

- INT 와 CHAR 입력 차이
  
  - INT : 더하기/빼기 등의 이미가 있는 경우, 크다/작다 또는 순서에 의미가 있는 경우 EX) 개수
  
  - CHAR : 연산과 관련된 어떠한것도 해당하지 않는 경우, EX) 전화번호

### VARCHAR의 바이트 수보다 큰 텍스트를 입력해야 할 경우 (

- TEXT : 바이트 수 1 ~ 65535

- LONGTEXT : 바이트 수 1 ~ 4294967295 / ex)영화 자막의 경우

- BLOB : 바이트 수 1 ~ 65535

- LONGBLOB : 바이트 수 1 ~ 4294967295 / ex)영화 파일의 경우

```sql
-- CHAR, VARCHAR 예시
CREATE DATABASE netflix_db;
USE netflix_db;
CREATE TABLE movie (
    movie_id        INT,
    movie_title      VARCHAR(20),
    movie_director     VARCHAR(20),
    movie_star      VARCHAR(20),
    movie_script    LONGTEXT,
    movie_file        LONGBLOB
);
```

## 실수형

- 실수형은 소수점이 있는 숫자 저장시 사용

- FLOAT / 바이트 : 4, 소수점 아래 7자리 까지 표현

- DOUBLE / 바이트 : 8, 소수점 아래 15자리 까지 표현

## 날짜형

- 날짜형은 날짜 및 시간을 저장할 때 사용함

- DATE / 바이트: 3, 날짜만 저장, YYYY-MM-DD 형식

- TIME / 바이트: 3, 시간만 저장, HH:MM:SS 형식

- DATETIME / 바이트: 8, 날짜 및 시간 저장, YYYY-MM-DD HH:MM:SS 형식

## 변수의 사용

- 변수란 무엇을 담을수 있는 임시 저장소의 개념

- SET @변수이름 = 변수의값; : 변수선언 및 값 대입

- SELECT @변수이름 : 변수의 값 출력

```sql
-- 변수 사용 예시 1
USE market_db; -- 테이블 선택
SET @myVar1 = 5; -- @myVar1 변수에 5 할당
SET @myVar2 = 4.25; -- @myVar2 변수에 4.25 할당

SELECT @myVar1; -- SELECT 변수를 통해 변수 호출 가능
SELECT @myVar1 + @myvar2; -- SELECT 변수가 숫자형이라면 변수간의 연산 가능

-- 변수 사용 예시 2
SET @txt = '가수이름==>'; -- @txt 변수에 문자 할당
SET @height = 166; -- @height 변수에 숫자 할당

SELECT @txt, mem_name FROM member WHERE height > @height;
-- @txt변수와 member테이블의 member_name 을 가져오되, height열이 @height 변수값보다 큰 열만 가져옴
SELECT '가수이름==>', mem_name FROM member WHERE height > 166;
-- 변수 없이 할 경우 위와 같음
```

## 데이터 형 변환

- 데이터 형식을 바꿈

- 문자형을 정수형으로 혹은 정수형을 문자형으로 바꾸는 것

- 명시적인 변환 : 직접적으로 명시해서 바꾸는 것
  
  - CAST ( 값 AS 데이터_형식 [ (길이) ] )
  
  - CONVERT ( 값, 데이터_형식 [ (길이) ] )

### 문자열 연결하기 CONCAT

- CONCAT('문자1', '문자2') = 문자1문자2

```sql
-- 명시적 형변환 예시
SELECT AVG(price) '평균 가격' FROM buy; -- 해당 명령어로 호출시 142.9167 소숫점으로 출력됨

-- 아래의 CAST 혹은 CONVERT 문을 사용하여 같은 결과를 도출할 수 있음
SELECT CAST(AVG(price) AS SIGNED) '평균 가격' FROM buy; -- SIGNED 는 실수형을 정수형으로 변환해줌
-- 또는
SELECT CONVERT(AVG(price), SIGNED) '평균 가격' FROM buy;

-- 문자를 날짜 형식으로 변환 예시
SELECT CAST('2022$12$12' AS DATE);
SELECT CAST('2022/12/12' AS DATE);
SELECT CAST('2022%12%12' AS DATE);
SELECT CAST('2022@12@12' AS DATE);
-- 모두 2022-12-12 형식으로 바꿔서 표현하게 됨

-- CONCAT 을 활용한 숫자형을 문자형으로 변경 예시
SELECT num, CONCAT(CAST(price AS CHAR), 'X', CAST(amount AS CHAR) , '=') '가격X수량',
-- 가져올 변수선택, concat으로 문자열연결, cast로 숫자형을 문자열로 변경
        price*amount '구매액'
    -- 각열의 행끼리 곱하기 수행
        FROM buy;
        -- 가져올 테이블 선택
```

- 암시적인 변환 : 직접적으려 명시하지 않아도 자연스럽게 바뀌는 것

```sql
-- 암시적 형변환 예시
SELECT '100' + '200'; -- 문자와 문자를 더함(정수로 변환되어 연산됨)
SELECT CONCAT('100', '200') ; -- 문자와 문자를 연결(문자로 처리)
SELECT CONCAT('100' + '200') ; -- 문자와 문자를 콤마가 아닌 + 연결하면 (숫자로 처리)
SELECT CONCAT(100, '200') ; -- 정수와 문자를 연결(정수가 문자로 변환되어 처리)

-- 비교연산자 사용시 가장 앞의 글자를 기준으로 변환되어 비교됨
SELECT 1 > '2mega'; -- 정수인 2로 변환되어 비교
SELECT 1 > '0mega'; -- 정수인 0으로 변환되어 비교
SELECT 3 > '2mega'; -- 정수인 2로 변환되어 비교
SELECT 0 = 'mega2'; -- 문자는 0으로 변환됨
```

## 04-2 두 테이블을 묶는 조인

- 두 개의 테이블을 서로 묶어서 하나의 결과를 만들어내는 것을 의미함
  
  - 두 테이블을 엮어야 원하는 형태가 나오는 경우도 많음

### 내부조인

- 두 테이블 연결시 가장 많이 사용되는 것

- 일대다 관계의 이해
  
  - 두 테이블의 조인은 테이블이 일대다(one to many) 관계로 연결되어야 함
  
  - 데이터베이스의 테이블은 하나로 구성되는 것보다는 여러 정보를 주제에 따라 분리해서 저장하는 것이 효율적임
  
  - 이렇게 분리된 테이블은 서로 관계를 맺고 있음
  
  - 일대다 관계란 한쪽 테이블에는 하나의 값만 존재해야 하지만, 연결된 다른 테이블에는 여러개의 값이 존재할 수 있는 관계를 의미함

![2.PNG](D:\workspace\00.TIL\SQL\IMAGE\2.PNG)

## 내부 조인의 기본

- 일반적으로 조인이라 부르는 것은 내부 조인(inner join)을 의미

- 조인은 3개 이상의 테이블로도 가능하지만, 대부분 2개로 조인함

```sql
-- 내부조인의 형식
SELECT <열 목록>
FROM <첫 번째 테이블>
  INNER JOIN <두 번째 테이블>
  ON <조인될 조건>
[WHERE 검색 조건]
```

```sql
-- 내부 조인 예시
USE market_db; -- 테이블 선택
SELECT *       -- 출력은 모든 자료형태 선택
    FROM buy   -- 기준 테이블은 buy테이블
        INNER JOIN member    -- 내부조인 할 테이블은 member
        ON buy.mem_id = member.mem_id    -- 내부조인시 기준점은 각 테이블의 mem_id
    WHERE buy.mem_id = 'MMU';    -- 특정조건 mem_id 가 MMU인 경우만(있어도 되고 없어도 
```

![3.PNG](D:\workspace\00.TIL\SQL\IMAGE\3.PNG)

```sql
-- 내부조인 결과중 원하는 열로 이루어진 테이블 출력
SELECT buy.mem_id, mem_name, prod_name, addr, CONCAT(phone1, phone2) AS '연락처'
    FROM member
        INNER JOIN buy
        ON member.mem_id = buy.mem_id 
```

![4.PNG](D:\workspace\00.TIL\SQL\IMAGE\4.PNG)

```sql
-- 더 나은 SQL 표현식
SELECT B.mem_id, M.mem_name, B.prod_name, M.addr, CONCAT(M.phone1, M.phone2) AS '연락처'
    FROM buy B -- 테이블 명을 알리안스로 B로 지정
        INNER JOIN member M -- 테이블 명을 알리안스로 M으로 지정
        ON M.mem_id = B.mem_id;
-- 이후 각각 해당되는 열을 어떤 테이블에서 참조했는지 표시해주면 알아보기 쉬움
```

## 외부 조인(outer join)

- 내부 조인은 두 테이블에 모두 데이터가 있어야 결과가 나옴

- 외부 조인은 한쪽에만 데이터가 있어도 결과가 나옴

### 외부조인의 기본

- 자주 사용되지는 않지만 가끔 사용되는 방식으로, 필요한 내용이 한쪽에만 있어도 결과를 추출할 수 있음

```sql
SELECT <열 목록>
FROM <첫 번째 테이블(LEFT 테이블)>
    <LEFT | RIGHT | FULL> OUTER JOIN <두 번째 테이블(RIGHT 테이블)>
    ON <조인될 조건>
[WHERE 검색 조건];
```

- 외부 조인 예시

```sql
SELECT M.mem_id, M.mem_name, B.prod_name, M.addr
    FROM member M -- 기준이 되는 테이블 지정
        LEFT OUTER JOIN buy B -- 아우터 조인할 테이블 지정
        ON M.mem_id = B.mem_id -- 조인할 기준열 지정
    ORDER BY M.mem_id; -- 정렬 기준을 member 테이블의 mem_id 기준 오름차순정렬
```

![5.PNG](D:\workspace\00.TIL\SQL\IMAGE\5.PNG)

## 기타 조인

- 내부 조인이나 외부 조인처럼 자주 사용되지는 않지만 가끔 유용하게 사용되는 조인으로 **상호 조인**과 **자체 조인**이 있음

### 상호 조인

- 한쪽의 테이블의 모든 행과 다른 쪽 테이블의 모든 행을 조인 시키는 기능
  
  - 대용량의 데이터를 만들어야 할때 사용, 의미있는 데이터는 아님

```sql
SELECT *
    FROM buy
        CROSS JOIN member;
```

![6.PNG](D:\workspace\00.TIL\SQL\IMAGE\6.PNG)

- 상호 조인의 특징
  
  - ON 구문을 사용할 수 없음
  
  - 결과의 내용은 의미가 없음(랜덤으로 조인하기 때문)
  
  - 상호 조인의 주 용도는 테스트하기 위해 대용량의 데이터를 생성할 때

```sql
-- CROSS JOIN 을 사용하여 대용량 테이블을 만드는 코드
CREATE TABLE cross_table -- 빈 테이블 생성
    SELECT * -- 모든 자료 선택
        FROM sakila.actor -- 기준 자료
            CROSS JOIN world.country; -- cross join 할 자료

SELECT * FROM cross_table LIMIT 5; -- 조회시 갯수 제한을 두어 조회
```

## 자체 조인(self join)

- 자신이 자신과 조인한다는 의미

- 자체 조인은 1개의 테이블로 사용됨

```sql
SELECT <열 목록>
FROM <테이블> 별칭A -- 같은 테이블의 별칭을 A
    INNER JOIN <테이블> 별칭B -- 같은 테이블의 별칭을 B
    ON <조인될 조건>
[WHERE 검색 조건]
```

- 자체 조인은 같은테이블에 별칭을 다르게 주어 적용할 수 있음

```sql
-- 자체조인 예시 코드
USE market_db;

CREATE TABLE emp_table (emp CHAR(4), manager CHAR(4), phone VARCHAR(8)); -- 빈 테이블 생성

-- 각각의 정보 입력 직위, 직속상관, 연락처
INSERT INTO emp_table VALUES('대표', NULL, '0000'); 
INSERT INTO emp_table VALUES('영업이사', '대표', '1111');
INSERT INTO emp_table VALUES('관리이사', '대표', '2222');
INSERT INTO emp_table VALUES('정보이사', '대표', '3333');
INSERT INTO emp_table VALUES('영업과장', '영업이사', '1111-1');
INSERT INTO emp_table VALUES('경리부장', '관리이사', '2222-1');
INSERT INTO emp_table VALUES('인사부장', '관리이사', '2222-2');
INSERT INTO emp_table VALUES('개발팀장', '정보이사', '3333-1');
INSERT INTO emp_table VALUES('개발주임', '정보이사', '3333-1-1');

-- 직원의 직속상관과 직속상관의 연락처를 뽑는 코드
SELECT A.emp "직원", B.emp "직속상관", B.phone "직속상관연락처"
    FROM emp_table A -- 똑같은 테이블을 A와 B로 각각 지정해 두개의 테이블인 것 처럼 사용
        INNER JOIN emp_table B
            ON A.manager = B.emp
    WHERE A.emp = '경리부장'; -- 경리부장의 직속상관과 직속상관 연락처 호출
```

![](C:\Users\서동규\AppData\Roaming\marktext\images\2022-03-03-16-43-03-image.png)

## 04-3 SQL 프로그래밍

- SQL에서도 기본적인 조건문, 반복문은 프로그래밍 가능함



### IF문

- IF문은 조건문으로 참이면 무엇을 실행하고 거짓이면 실행하지 않음

- 기본 IF문 형식

```sql
IF <조건식> THEN
	SQL 문장들
END IF
```

- IF문 예시

```sql
USE market_db;
DROP PROCEDURE IF EXISTS ifProc1; -- 기존에 만들어진게 있다면 삭제
DELIMITER $$
CREATE PROCEDURE ifProc1()
BEGIN
	IF 100 = 100 THEN
		SELECT '100은 100과 같습니다.';
	END IF;
END $$
DELIMITER ;
CALL ifProc1();
```

### IF ~ ELSE 문

- 조건에 따라 다른 부분을 수행함

```sql
USE market_db;
DROP PROCEDURE IF EXISTS ifProc2; -- 기존에 만들어진게 있다면 삭제
DELIMITER $$
CREATE PROCEDURE ifProc2()
BEGIN
	DECLARE myNum INT; -- myNum 변수 선언
    SET myNum = 200; -- 변수에 값 대입
    IF myNum = 100 THEN
		SELECT '100입니다.';
	ELSE
		SELECT '100이 아닙니다.';
    END IF;
END $$
DELIMITER ;
CALL ifProc2();
```

- 응용문

```sql
USE market_db;
DROP PROCEDURE IF EXISTS ifProc3; -- 기존에 만들어진게 있다면 삭제
DELIMITER $$
CREATE PROCEDURE ifProc3()
BEGIN
	DECLARE debutDate DATE; -- 데뷔일
    DECLARE curDate DATE; -- 오늘날짜
    DECLARE days INT; -- 활동일수
    
    SELECT debut_date INTO debutDate -- debut_date결과를 debutDate 변수에 할당
		FROM market_db.member
        WHERE mem_id = 'APN';
        
	SET curDate = CURRENT_DATE(); -- 오늘날짜를 curDate 변수에 할당
    SET days = DATEDIFF(curDate, debutDate); 
    -- 데뷔일 부터 오늘날짜 까지의 일수 계산하여 days 변수에 할당
    
    IF (days/365) >= 5 THEN -- 데뷔한지 5년이 지났다면
		SELECT CONCAT('데뷔한지', days, '일이나 지났습니다.');
        
	ELSE
		SELECT '데뷔한지'+ days+ '일 밖에 안됐습니다.';
    END IF;
END $$
DELIMITER ;
CALL ifProc3();
```



## CASE문

- CASE문은 여러가지 조건을 설정 가능(다중분기)

```sql
CASE
	WHEN 조건1 THEN
		SQL문장들1
	WHEN 조건2 THEN
		SQL문장들2
	WHEN 조건3 THEN
		SQL문장들3
	ELSE
		SQL문장들4
END CASE;
```




















































