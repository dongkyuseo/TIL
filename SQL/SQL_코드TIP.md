# 1. 조회(SELECT)

```SQL
-- SELCET 절의 형식
SELECT 열_이름 			-- 출력할 열 조건, *은 전체 열선택, 이외 열이름 입력
      FROM 테이블_이름 	   -- 베이스가 될 테이블 이름 선택
      WHERE 조건식  		-- 특정 조건 부합 여부 # =, !=, AND, OR, NOT, LIKE, BETWEEN, IN
      JOIN 테이블_이름      -- 테이블 연결 ON조건에 맞춰서, INNER JOIN, LEFT JOIN, RIGHT JOIN, 
      					 					 --     LEFT OUTER JOIN, RIGHT OUTER JOIN
      ON A테이블.키 = B테이블.키 -- A테이블과 B테이블의 공통 키를 조건으로 연결
      GROUP BY 열_이름		-- 특정 열 선택
      HAVING 조건식		-- 그룹바이로 선택된 열에 특정 조건을 만족하는 데이터 출력
      ORDER BY 열_이름	  	 -- 정렬 내림차순(DESC) / 오름차순(ASC) 기본 셋팅되어 있음
      LIMIT 숫자      	 -- 출력 갯수 제한 # LIMIT 숫자 OFFSET 숫자 = 앞에 건너뛸 갯수
      
-- 모든 코드 조 회하기
SELECT * FROM 테이블명;
-- 특정 열만 선택해서 조회하기
SELECT 조회할 열1, 조회할 열2, ... FROM 테이블명;
	-- 조회시 특정 조건과 일치/제외 값만 조회
	WHERE 열이름 = '검색할값'
	WHERE 열이름 != '제외할값'
	-- 2가지 이상 검색
	WHERE IN('1조건', '2조건')
	
    -- 조회시 그룹바이로 지정한 열이 있을 경우 해당 열 기준 정렬을 해줌
	GROUP BY 열이름;
	-- 특정 개수 제한 / (DISTINCT 중복을 열 이름 제거) 1개 이상인 열 조회
	HAVING COUNT(DISTINCT 열이름)	> 1;
	-- 조회시 내림차순 정렬, 기본 오름차순 셋팅(ASC)	
	ORDER BY 열이름 DESC;
	-- 정렬 조회시 2가지 조건, 1가지가 겹칠 경우 다음 우선순위 설정 가능
	ORDER BY 열이름1 ASC, 열이름2 DESC;
	-- 조회시 출력 개수 제한
	LIMIT 제한수;

-- GROUP_CONCAT(열이름) 으로 조회시 각 데이터가,로 구분되어 한칸에 표시됨
SELECT GROUP_CONCAT(NAME) FROM city
	WHERE CountryCode='kor';
```



# 2. SUM, MAX, MIN

```SQL
-- 열 개수 조회 COUNT
SELECT COUNT(열이름) FROM 테이블명;
-- 중복 제거 DISTINCT
SELECT COUNT(DISTINCT 열이름) FROM 테이블명;
-- 특정 항목의 개수
SELECT DISTINCT 열이름, COUNT(*) FROM 테이블명
    GROUP BY 특정항목열  -- 특정항목으로 구분된 열일 경우
    ORDER BY 특정항목열
;
-- 특정 개수 이상 조회 GROUP BY ~ HAVING ~
SELECT 열이름, COUNT(열이름) FROM 테이블명
    WHERE 열이름 IS NOT NULL
    GROUP BY 열이름
    HAVING COUNT(열이름) > 1 -- 1개 초과의 데이터만 조회
    ORDER BY 열이름
;

-- 특정 시간대의 자료 개수 조회
SELECT HOUR(DATETIME), COUNT(HOUR(DATETIME)) FROM ANIMAL_OUTS
    WHERE HOUR(DATETIME) >= 9 AND HOUR(DATETIME) <20 -- 9시 이상 ~ 20시 미만 자료
    GROUP BY HOUR(DATETIME)
    ORDER BY HOUR(DATETIME)
;

-- RECURSIVE 사용하여 0부터 23 까지의 표 테이블 생성
WITH recursive CTE as( #재귀쿼리 세팅
    select 0 as HOUR #초기값 설정
    union all #위 쿼리와 아래 쿼리의 값을 연산
    select HOUR+1 from CTE #하나씩 불려 나감 
    where HOUR < 23 #반복을 멈추는 용도
)

-- HR 테이블 생성(0~23)
WITH RECURSIVE HR AS(
    SELECT 0 HOUR 
    UNION ALL
    SELECT HOUR +1
    FROM HR
    WHERE HOUR +1 <24
)
-- HR테이블을 베이스로 LEFT JOIN / IFNULL(열이름, 입력할값)
SELECT HR.HOUR AS HOUR, IFNULL(A.COUNT, 0) AS COUNT FROM HR
    LEFT JOIN (
        SELECT HOUR(DATETIME) AS 'HOUR',
        COUNT(DATETIME) AS 'COUNT'
        FROM ANIMAL_OUTS
        GROUP BY HOUR(DATETIME)
        ORDER BY 1
    ) AS A
    ON HR.HOUR = A.HOUR
    ORDER BY 1
;
```

- 재귀함수

```SQL
-- 테이블 재귀적으로 생성 하는 코드
WITH RECURSIVE HR AS(	-- WITH RECURSIVE 테이블명 AS()
    SELECT 0 NUM		-- 0부터 시작
    UNION ALL			-- 
    SELECT NUM +1		-- NUM을 1씩 더함
    FROM HR				-- HR테이블
    WHERE NUM +1 <24	-- 24미만까지 제한
)
```



# 3. NULL

```SQL
-- NULL 값만 조회를 위한 코드
SELECT ANIMAL_ID FROM ANIMAL_INS
    WHERE NAME IS NULL -- WHERE 열이름 IS NULL 로 널값만 조회하도록 셋팅
    ORDER BY ANIMAL_ID
;

-- NULL 값을 제외한 조회 코드
SELECT ANIMAL_ID FROM ANIMAL_INS
    WHERE NAME IS NOT NULL
    ORDER BY ANIMAL_ID
;

-- NULL값 바꿔서 출력하기 IFNULL(열이름, 빈값일경우 출력할 내용)
SELECT ANIMAL_TYPE, IFNULL(NAME, 'No name'), SEX_UPON_INTAKE FROM ANIMAL_INS
    ORDER BY ANIMAL_ID
;
```



# 4. JOIN

```SQL
-- LEFT JOIN (왼쪽 테이블 기준 JOIN)
SELECT O.ANIMAL_ID, O.NAME FROM ANIMAL_OUTS AS O
    LEFT JOIN ANIMAL_INS AS A
    ON A.ANIMAL_ID = O.ANIMAL_ID
    WHERE A.ANIMAL_ID IS NULL -- 값이 NULL 인 것만 출력
;
-- RIGHT JOIN (오른쪽 테이블 기준 JOIN)
SELECT O.ANIMAL_ID, O.NAME FROM ANIMAL_INS AS A
    RIGHT JOIN ANIMAL_OUTS AS O
    ON A.ANIMAL_ID = O.ANIMAL_ID
    WHERE A.ANIMAL_ID IS NULL -- 값이 NULL 인 것만 출력
;

-- LEFT OUTER JOIN (전체 조인)
SELECT O.ANIMAL_ID, O.NAME FROM ANIMAL_OUTS AS O
    LEFT OUTER JOIN ANIMAL_INS AS A
    ON A.ANIMAL_ID = O.ANIMAL_ID
    WHERE A.ANIMAL_ID IS NULL -- 값이 NULL 인 것만 출력
;

-- 날짜 비교를 통해 날짜순으로 정렬하여 출력
SELECT O.ANIMAL_ID, O.NAME FROM ANIMAL_OUTS AS O
    LEFT OUTER JOIN ANIMAL_INS AS A
    ON A.ANIMAL_ID = O.ANIMAL_ID
    WHERE A.DATETIME > O.DATETIME
    ORDER BY A.DATETIME
;

-- 날짜 값이 없는 열을 개수 제한하여 조회
SELECT A.NAME, A.DATETIME FROM ANIMAL_OUTS AS O
    RIGHT OUTER JOIN ANIMAL_INS AS A
    ON A.ANIMAL_ID = O.ANIMAL_ID
    WHERE O.DATETIME IS NULL
    ORDER BY A.DATETIME
    LIMIT 3
;

-- 조인한 테이블의 I테이블의 특정 단어로 시작하는 열과 I와O가 다른 단어인 테이블을 조회
SELECT I.ANIMAL_ID, I.ANIMAL_TYPE, I.NAME FROM ANIMAL_INS AS I
    LEFT JOIN ANIMAL_OUTS AS O
    ON I.ANIMAL_ID = O.ANIMAL_ID
    WHERE I.SEX_UPON_INTAKE LIKE 'INTACT%' 
    AND O.SEX_UPON_OUTCOME != I.SEX_UPON_INTAKE
    ORDER BY I.ANIMAL_ID
;
```



# 5. STRING, DATE

```SQL
-- 특정 열의 단어 찾기
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE FROM ANIMAL_INS
    WHERE NAME IN ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
;

-- 특정 타입의 특정 단어가 들어간 열 검색
SELECT ANIMAL_ID, NAME FROM ANIMAL_INS
    WHERE NAME LIKE '%EL%'
    AND ANIMAL_TYPE = 'DOG'
    ORDER BY NAME
;

-- 출력 조건을 설정해서 O,X로 출력하기 / IF (열이름 LIKE 조건, 참수행, 거짓수행)
SELECT ANIMAL_ID, NAME, IF(SEX_UPON_INTAKE LIKE 'INTACT%', 'X', 'O') AS '중성화' FROM ANIMAL_INS
    ORDER BY ANIMAL_ID
;

-- 기간을 계산해 출력
SELECT I.ANIMAL_ID, I.NAME FROM ANIMAL_INS AS I 
    LEFT JOIN ANIMAL_OUTS AS O
    ON I.ANIMAL_ID = O.ANIMAL_ID
    ORDER BY O.DATETIME - I.DATETIME DESC
    LIMIT 2
;

-- 날짜형식으로 출력하기
-- DATE_FORMAT(날짜열, %Y-%M-%D:연, 문자형월, 문자형일 / %Y-%m-%d:연, 숫자형월, 숫자형일)
SELECT ANIMAL_ID, NAME, DATE_FORMAT(DATETIME, '%Y-%m-%d') FROM ANIMAL_INS
    ORDER BY ANIMAL_ID
;
```



# 6. 이 외

```SQL
# IF
	# SELECT, WHERE 절에서 사용 가능
SELECT IF(10 > 5, '크다', '작다') AS result;

# LIKE
	# WHERE 절과 함께 특정 패턴을 검색할 때 사용
SELECT *
FROM Student
# % 의 사용법
WHERE Student_ID like 'a%'; -- %는 a로 시작하는 단어 전체 조회
# _ 의 사용법 : 단어 한칸 랜덤의 의미
WHERE Student_ID like 'a_c%' -- a?c 3글자중 a와c사이에 한글자는 랜덤, 해당단어 포함하는 단어 전체 조회

LIKE 'a%' -- a로 시작되는 모든 것
LIKE 'a_%_%' -- a로 시작되고 최소 3이상의 길이를 가진 것
LIKE '_a%' -- 두번째 자리에 a가 들어가는 모든 것

# IN
	# WHERE 절 내 여러 값을 설정하고자 할 때 사용
	# 연산속도가 상대적으로 빠름
	# OR 연산과 유사한 효과
select *
from Customers
where country in ('UK', 'Korea') -- Customers 중 country가 UK이거나 KOREA인 것 다 뽑기

# Between
	# where 절과 내 검색 조건으로 범위를 지정하고자 할 때 사용
	# between 이상 and 이하;

select *
from products
where price between 10 and 20;

select *
from products
where price not between 10 and 20;

select *
from products
where (price between 10 and 20) and not  id in(2,3); -- 이렇게 쓸 수도 있다

# CASE 문
CASE
	WHEN 조건1 THEN '조건1 반환값'
	WHEN 조건2 THEN '조건2 반환값'
	ELSE '충족되는 조건 없을때 반환값'
END

# WHEN ~ TEHN 세트
	# SELECT, WHERE, ORDER BY 에서 사용 가능하며, 보통 SLELECT 절에서 많이 사용 함
	# 주의 : ELSE를 생략하면 결과 값이 NULL이 나옴
SELECT 
    seq, 
    CASE
        WHEN (u.seq BETWEEN 1 AND 3) THEN 
            CASE 
                WHEN (u.enabled IS TRUE) THEN 'A+'
                ELSE 'A0'
            END 
        WHEN (u.seq BETWEEN 4 AND 6) THEN
            CASE 
                WHEN (u.enabled IS TRUE) THEN 'B+'
                ELSE 'B0'
            END
        ELSE 'C+'
    END AS case_result
FROM `user` u

# Limit
-- 조건식이 있는 경우
SELECT -- 열명 
FROM -- 테이블명 
WHERE -- 조건식 
LIMIT -- 행수     

-- 조건식이 없는 경우
SELECT -- 열명
FROM -- 테이블명
LIMIT -- 행수                  


-- 지정한 숫자 개수 리턴
SELECT *
FROM
LIMIT 0,3; -- 0번부터 3개

# GROUP BY
	# 집계함수와 함게 사용되며 결과를 지정한 컬럼에 따라 그룹으로 묶고자 할때 사용
select count(id)
from customers
group by country -- 각 도시에 사는 사람이 몇명인지

# MIN(), MAX(), COUNT(), AVG(), SUM()
	# 집계함수
	# SELECT 에서 사용
	# COUNT : NULL은 숫자로 세지 않는다
select count(id) from products; -- ID 갯수 
select avg(price) from products; 
select sum(price) from products;

	# 문제 : 평균 잔고(balance) 가 700이상인 지점의 이름과 평균 잔고를 구하라
select branch_name, avg(balance)
from account
group by branch_name 
having avg(balance) >= 700;

# UNION - 예제 찾아보기
# SELECT의 칼럼 리스트를 기준으로 두 개 이상의 결과를 하나의 테이블로 합칠때 사용
	# 기본적으로 중복값을 제거함
	# 중복값을 포함하고 싶은 경우 UNION ALL을 사용함
select *
from customers
union
select city from orders
order by city;

# JOIN
# INNER JOIN : 교집합
select test1.number from test1 
join test2 
on test1.number = test2.number;
	# test1 과 test2 의 number 컬럼을 서로 비교하여 중첩되는 값이 존재하면 test1.number 컬럼의 중첩 값만 출력한다.

	# 동일 테이블의 조인 결과를 구할 때도 쓴다.
	# 문제 : 우유와 요거트를 동시에 구입한 장바구니가 있는지 알아보려 합니다. 우유와 요거트를 동시에 구입한 장바구니의 아이디를 조회하는 SQL 문을 작성해주세요. 이때 결과는 장바구니의 아이디 순으로 나와야 합니다.
select distinct c.cart_id from cart_products c 
inner join cart_products p 
on(c.cart_id=p.cart_id) 
where (c.name='우유' and p.name='요거트') 
or (c.name='요거트'and p.name='우유') 
order by c.cart_id
;
	# 문제 : 지갑과 맥북프로를 동시에 구매한 ID를 조회하시오. 단, 아이디는 1개만 출력되게 하시오. 출력은 멤버아이디 순서로 출력해야 합니다.
SELECT B.MEM_ID FROM BUY B
	INNER JOIN BUY C
    ON B.MEM_ID = C.MEM_ID
    WHERE (B.PROD_NAME = '지갑' AND C.PROD_NAME = '맥북프로')
    OR (B.PROD_NAME = '맥북프로' AND C.PROD_NAME = '지갑')
    GROUP BY B.MEM_ID
    HAVING COUNT(B.PROD_NAME) > 1
    ORDER BY B.MEM_ID
;

# LEFT JOIN : 왼쪽 기준
select test1.number from test1 
left join test2 
on test1.number = test2.number;

# RIGHT JOIN : 오른쪽 기준

	# test1.number 컬럼을 출력하는 경우
select test1.number from test1 
right join test2 
on test1.number = test2.number;

	# test2.number 컬럼을 출력하는 경우
select test2.number from test1 
right join test2 
on test1.number = test2.number;

# OUTER JOIN : 매칭되는 값이 없어도 출력

select test1.*, test2.number from test1 
left outer join test2 
on test1.number = test2.number;

# 날짜 관련 코드
SELECT NOW(), CURDATE(), CURTIME(); -- NOW 현재 날짜 시간, CURDATE 현재 날짜, CURTIME 현재 시간

SELECT NOW(), CURDATE(), CURTIME(); -- NOW 현재 날짜 시간, CURDATE 현재 날짜, CURTIME 현재 시간

SELECT DATE_ADD(NOW(), INTERVAL 2 MONTH); -- 2달후 계산
SELECT DATE_ADD(NOW(), INTERVAL 5 DAY); -- 5일후 계산

SELECT TO_DAYS(CURDATE()); -- 0년 0월 0일 부터 오늘까지의 일수 계산
SELECT TO_DAYS(NOW());
SELECT TO_DAYS('2022-11-18') - TO_DAYS(NOW()); -- D-DAY 계산
SELECT DAYOFWEEK(dt) FROM DATE_TABLE; -- 요일을 숫자로 표시, 일요일:1, 월요일:2, 화:3, 수:4, 목:5, 금:6, 토:7

```



















