# 1. 조회(SELECT)

```SQL
-- SELCET 절의 형식
SELECT 열_이름 			-- 출력할 열 조건, *은 전체 열선택, 이외 열이름 입력
      FROM 테이블_이름 	   -- 베이스가 될 테이블 이름 선택
      WHERE 조건식  		-- 특정 조건 부합 여부
      GROUP BY 열_이름		-- 특정 열 선택
      HAVING 조건식		-- 그룹바이로 선택된 열에 특정 조건을 만족하는 데이터 출력
      ORDER BY 열_이름	  	 -- 정렬 내림차순(DESC) / 오름차순(ASC) 기본 셋팅되어 있음
      LIMIT 숫자      	 -- 출력 갯수 제한
      
-- 모든 코드 조 회하기
SELECT * FROM 테이블명;
-- 특정 열만 선택해서 조회하기
SELECT 조회할 열1, 조회할 열2, ... FROM 테이블명;
	-- 조회시 특정 조건과 일치/제외 값만 조회
	WHERE 열이름 = '검색할값'
	WHERE 열이름 != '제외할값'
    -- 조회시 그룹바이로 지정한 열이 있을 경우 해당 열 기준 정렬을 해줌
	GROUP BY 열이름;
	-- 조회시 내림차순 정렬, 기본 오름차순 셋팅(ASC)	
	ORDER BY 열이름 DESC;
	-- 정렬 조회시 2가지 조건, 1가지가 겹칠 경우 다음 우선순위 설정 가능
	ORDER BY 열이름1 ASC, 열이름2 DESC;
	-- 조회시 출력 개수 제한
	LIMIT 제한수;
	
```



# 2. SUM, MAX, MIN

```SQL
-- 열 개수 조회 COUNT
SELECT COUNT(열이름) FROM 테이블명;
-- 중복 제거 DISTINCT
SELECT COUNT(DISTINCT 열이름) FROM 테이블명;
-- 특정 항목의 개수
-- 코드를 입력하세요
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

