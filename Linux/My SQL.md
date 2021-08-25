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



### 테이블 생성

- use mcdb;
  create table 테이블명(조건설정) auto_increment=시작번호설정;
- show tables; 테이블 조회
- desc 테이블명; 테이블 정보 확인
- drop table 테이블명; 테이블 삭제
- 테이블 변경
  - alter table
    - add : 칼럼추가
    - drop : 컬럼 삭제
    - change : 컬럼명, 자로형 변경
    - modify : 컬럼 순서 바꾸기

### Key의 종류

- 후보키(candidate key) : 테이블 구성하는 열 중에서 유일하게 식별할 수 있는 열
- 기본키(primary key) : 테이블에서 유일하게 식별하기 위해 사용하는 키
- 대체키(alternate key) : 후보키중 기본키를 제외한 나머지 후보키
- 외래키(foreign key) : 테이블 내의 열 중 다른 테이블의 기본키를 참조하는 열
- 슈퍼키(super key) : 2개 이상의 열이 합쳐져서 기본키로 사용하는 것

# colab에서 MySQL 사용하기

## 초기 연결 설정방법

```python
!pip install pymysql > /dev/null #mysql 설치

# mysql.json 파일 업로드
from google.colab import files
uploaded = files.upload()
filename = list(uploaded.keys())[0]

# json 파일 읽기
import json
with open(filename) as fp:
  config_str = fp.read()
config = json.loads(config_str)

# mysql 로그인 및 접속
import pymysql 
conn = pymysql.connect( 
    host = config['host'], 
    user = config['user'], 
    password = config['password'], 
    database = config['database'], 
    port = config['port'] 
  )

# mysql 연결 접속
cur = conn.cursor()

# mysql 접속 종료
cur.close()
conn.close()
```

## 테이블

### 생성

```python

# 커서를 지정해서 연결후 
cur = conn.cursor() 

# 테이블을 생성하는데 ''' 내용에 SQL 생성문을 적음'''
sql_create_table = ''' 
  create table if not exists users ( 
    uid varchar(20) not null primary key, 
    pwd char(44) not null, 
    uname varchar(20) not null, 
    email varchar(40),
    reg_date datetime default current_timestamp, 
    is_deleted int default 0 
  ); 
  ''' 
# EXECUTE 로 생성해주면 SQL에 가서 조회시 생성되어 있음
cur.execute(sql_create_table)
```

### 추가

```python
cur = conn.cursor()

sql_insert = "INSERT INTO users(uid, pwd, uname) VALUES('admin', '1234', '관리자');"

cur.execute(sql_insert)

# 단건 조회시 cur.fetchone()
row = cur.fetchone()
row
```

- 프로그램에서 데이터 변경시 데이터베이스에 바로 적용되는게 아닌, MySQL의 cach에 저장되어 데이터엔 늦게 반영됨

```python
# 데이터를 하드 디스크에 쓰게 하는 명령
conn.commit()
```

- **placeholder** : 데이터 입력 및 저장을 프로그램화 함

```python
# 여러 건의 데이터 추가하는 방법
# 1
sql_insert_ph = "INSERT INTO users(uid, pwd, uname) VALUES(%s, '1234', %s);" # 가변인수를 %s 로 받고
uid, uname = 'djy', '대조영'
# 각각의 변수에 저장해 준뒤

cur.execute(sql_insert_ph, (uid, uname)) #튜플로 묶어 적용시킴
conn.commit()

# 2 여러건의 리스트, 튜플을 튜플로 묶기
users = (('gdhong', '홍길동'), ('jbpark', '박재범'))
cur.executemany(sql_insert_ph, users)
conn.commit()

# 3 반복문 사용
users = (('gdhong2', '홍길동'), ('jbpark2', '박재범'))

for user in users:
  sql_insert_ph = "INSERT INTO users(uid, pwd, uname) VALUES(%s, '1234', %s);"
  cur.execute(sql_insert_ph, user)
conn.commit()

```

### 조회

```python
sql_select = """
  SELECT uid, uname, email, 
    date_format(reg_date, '%Y-%m-%d %H:%i') AS reg_date
    FROM users WHERE is_Deleted=0 ORDER BY reg_date; 
"""

cur = conn.cursor()
cur.execute(sql_select)
row = cur.fetchone() # 단건 조회
row

rows = cur.fetchmany(3) # 다건 조회
rows

rows = cur.fetchall() # 전체 조회
rows

for row in cur: # 반복문을 사용한 전체 조회
  print(row)
```



### 검색

```python
uid = 'eskim'
sql_search = """
  SELECT uid, uname, email, date_format(reg_date, '%%Y-%%m-%%d %%H:%%i') AS reg_date
    FROM users WHERE is_deleted = 0 AND uid=%s; 
 """
# %s 를 사용하면서 다른 %Y와 같은걸 사용하려면 %%두번 사용해야 함

cur = conn.cursor()
cur.execute(sql_search, (uid,)) # 튜플로 입력해야 해서 (단어,) 로 넣어줌

result = cur.fetchone() # 단건 검색
result

# 사용자가 없을 경우
uid = 'park'
cur = conn.cursor()
cur.execute(sql_search, (uid,)) # 튜플로 입력해야 해서 (단어,) 로 넣어줌
result = cur.fetchone()
result
>>>
# 결과값이 출력이 안됨

if result:
  print(result)
else:
  print(f'uid={uid}인 사용자는 없습니다.')
```

## password

### 패스워드 넣기

- user table에 패스워드 넣기
- password
  - 단방향 : 평문 > 비문 (비문 > 평문 은 불가능)
  - 양방향 : 평문 <> 비문
- Secure Hash Algorithm(SHA-256) : 암호화 알고리즘으로 바이너리화 함

```python
import hashlib
pwd = '1234'
pwd_sha256 = hashlib.sha256(pwd.encode()) #pwd 1234을 sha256으로 인코딩
pwd_sha256.digest() # 인코딩한 값을 읽기, 바이너리값으로 나옴
>>>b'\x03\xacgB\x16\xf3\xe1\\v\x1e\xe1\xa5\xe2U\xf0g\x956#\xc8\xb3\x88\xb4E\x9e\x13\xf9x\xd7\xc8F\xf4'
```

- Base64로 인코딩

```python
import base64

base64.b64encode(pwd_sha256.digest())
# sha256으로 인코딩 된 값을 넣어주면 base64로 인코딩 됨
>>> b'A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ='
```

```python
# 암호화 된 내용 사용하기 위한 디코드
hashed_pwd = base64.b64encode(pwd_sha256.digest()).decode('utf-8')
hashed_pwd
>>> A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=
```



