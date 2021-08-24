# My SQL

## 데이터 타입

- 정수

- 실수

  - 정확한값 : numeric, decimal
  - 근사치 : flaot, double

- Date and Time Data Type

  - date  Type : yyyy-mm-dd

  - datatime type : yyyy-mm-dd hh:mm:ss

  - timestamp type

    1970-01-01 00:00:01 UTC ~ 2038-01-19 03:14:07 UTC

  - Time Zone

    2020-01-01 10:10:10-08:00

  - Default value

    dt DATETIME DFAULT CURRENT_TIMESTAMP

- Stiring Data Type

  - CHAR and VARCHAR
    - CHAR : 1개의 캐릭터를 표현하면 4개의 저장공간 사용
    - VARCHAR : 사용하는 만큼 저장공간 사용
  - BLOB(Binary Large OBject)
  - DDL : DATA DFINITION LANGUAGE
  - DML : DATA MANIPULATION LANGUAGE - SQL 이 속해있음
  - DCL : DATA CONTROL LANGUAGE

## 데이터 조작

### SELECT

- \# : 주석기능 가능
- /* 주석 */ : 주석 가능

- select : 테이블을 조회할 때 사용 함, 찾고자하는 filed, colum 명 마지막엔 세미콜론(;) 붙여야 함
  - where : select 할때 조건을 주기 위함, 필드명에는 대소문자 구분이 없음
  - \` \` : 백코트 사용시 특정 기능으로 잡힌 영어단어를 사용할 수 있음 ex) \`name\`
  - AS : 사용시 필드명을 원하는 네이밍으로 변경할 수 있음, (프로그램상에서 변수명이 바뀔 수 있음)
  - AND OR NOT : 조건을 부여하기 위한 함수, > 크기 비교 등
  - distinct : 중복을 제외한 유니크한 값 조회
  - between a and b : a 부터 b 사이의 값 조회
  - x like ' %' : x필드의 특정 단어가 있는 값 조회, A_c _사용시A로시작해 C로 끝나는 단어 한글자만 적용해 조회 가능
  - IN ('a', 'b', 'c') : 특정 값 여러개 조회
  - ORDER BY 컬럼명 : 특정 컬럼의 숫자나 알파벳 순서로 정렬, 컬럼명을 2개 줄경우 1차로 1번째 컬럼정렬후 컬럼내에서 2번째 컬럼을 정렬을 하게 됨
- SELECT [DISTINCT] filed`1`, ..., field`n`
  - 함수사용가능
    - count(컬럼명 or * :전체) : 갯수 조회
    - sum(컬럼명 or * :전체) : 숫자 합
    - avg(컬럼명 or * :전체) : 평균 값
    - min(컬럼명 or * :전체) : 최소값
    - max(컬럼명 or * :전체) : 최대값
    - GROUP_CONCAT(컬럼명) : 해당 컬럼 내용을 리스트화 시킴
- FROM table명
- WHERE 조건  # =, AND, OR, NOT, LIKE, BETWEEN, IN 등
- ORDER BY field명1, ... filed명n [DESC] : 필드 정렬   # DESC:내림차순 desc 가없으면 오름차순 조회
- GROUP BY field명 : 그룹으로 묶을 필드 선택
- HAVING : GROUP BY에 거는 조건
- LIMIT 숫자 : 보고싶은 데이터 갯수 조절
- LIMIT 숫자 OFFSET 숫자 : OFFSET 의 갯수만큼 제외하고 리미트된 숫자를 조회해줌
- (INNER) JOIN TALBE명 : 테이블 두개를 합쳐 하나의 테이블로 만듬, INNER를 넣을 경우 앞의 테이블 기준
- ON : JOIN 할때 조건

### UPDATE & INSERT

- UPDATE FILED SET 대상='변경하려는단어' WHERE 대상='변경시킬단어'
- INSERT INTO 대상(각 열의 해당하는 테이블명) VALEUS(각 열에 테이블별 값할당)
- DELETE FROM 테이블 WHERE id=아이디값; #id>값 도 가능
- CREATE TABLE 테이블명 LIKE 복사할테이블;
- CREATE VIEW 테이블명 FROM AS SELECT * FROM 복사할테이블 
  	WHERE 조건을걸열과 조건설정<>=숫자;
  - 테이블은 물리적 공간을 차지함, 뷰는 가상의 공간에서 존재

### 날짜 형식

- CREATE TABLE date_table ( id int auto_increment primary key, dt datetime default current_timestamp );
- INSERT INTO date_table (dt) VALUES ('2017-08-28 17:22:21'), ('2017-02-15 10:22:24'), ('2017-12-09 22:13:24'), ('2018-07-06 20:15:18'); INSERT INTO date_table VALUES (default, default); # 특정 날짜시간등록
- INSET INTO date_table VALUES (DEFAULT, DEFAULT); # 현재날짜시각으로 등록
- SELECT date_format(dt, '%Y-%m-%d') AS my_date, # 열을 데이터테이블에서 연(%Y) 월(%m) 일(%d)을 가져와서 만듬
  	DATE_FORMAT(dt, '%h:%i:%s %p') AS my_time  # 열을 시(%h):분(%i):초(%s) 오전오후(%p) *시간을 대문자 H 사용시 24시간 표시 형식으로 바뀜
  	DATE_FORMAT(dt, '%r') AS my_time2 # %r 은 복사
      FROM date_table;
- SELECT NOW(), CURDATE(), CURTIME(); # 년월일시분초, 년월일, 시분초
- SELECT DATE_ADD(NOW(), INTERVAL 2 MONTH); # 현재부터 2달 후 년월일시분초 # 5day 로 입력시 5일뒤
- SELECT TO_DAYS(CURDATE()); # 서기 0년0월0일부터~오늘까지 from AD
- SELECT TO_DAYS(NOW()); # 위와 같은 값이 나옴
- SELECT to_days('2021-11-18') - to_days(now()); # 특정일을 기준으로 - 오늘을 해 일정계산가능
- SELECT DAYOFWEEK(dt) FROM date_table; # 요일 확인 1-일, 2-월, ..., 7-토

