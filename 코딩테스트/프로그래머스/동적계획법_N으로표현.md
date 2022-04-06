# 문제

###### 문제 설명

아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

##### 제한사항

- N은 1 이상 9 이하입니다.
- number는 1 이상 32,000 이하입니다.
- 수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
- 최솟값이 8보다 크면 -1을 return 합니다.

##### 입출력 예

| N    | number | return |
| ---- | ------ | ------ |
| 5    | 12     | 4      |
| 2    | 11     | 3      |

##### 입출력 예 설명

예제 #1
문제에 나온 예와 같습니다.

예제 #2
`11 = 22 / 2`와 같이 2를 3번만 사용하여 표현할 수 있습니다.

# 풀이

```python
def solution(N, number):
    dp = []
    
    for i in range (1,9) : # 8번 반복 안에 가능한 경우의수 생성
        # i = N의 개수
        all_case = set() # set에 나오는 값을 모아 중복 제거
        check_number = int(str(N)* i) # 단순 반복으로 만들수 있는 숫자 생성
        # {N}, {NN} , {NNN}...
        all_case.add(check_number)
        if number in all_case: # 반복으로 만들수 있는지 체크
            return i
        for j in range(0,i-1): # N개를 i개 사용해서 만든 값들
            for op1 in dp[j]: # dp[]
                for op2 in dp[-j-1] :
                    all_case.add(op1 - op2)
                    all_case.add(op1 + op2)
                    all_case.add(op1 * op2)
                    if op1 != 0:
                        if op2 != 0:
                          all_case.add(op1 // op2)
                    if number in all_case: # 만든 케이스 안에 답이 있는지 확인
                        return i                    
          
        dp.append(all_case) 

    return -1
```

[참고풀이](https://velog.io/@j_user0719/N%EC%9C%BC%EB%A1%9C-%ED%91%9C%ED%98%84-PYTHON)

### 문제 풀이

개인적으로 DP문제임을 몰랐으면 풀기 쉽지 않았을 것 같다. 문제를 보고 어떤 문제 유형인지를 유추해야하는데 프로그래머스의 DP 문제 중 하나로 분류 되어 있어서 😥
개인적으로 DP 문제라는 것은 이전에 실행의 결과 값이 다음 실행에 영향을 주는 것으로 인식해서 이전 값들의 조합을 살펴보게 된다.
문제 지문을 보고 연습장에 한번 직접 적어서 해보면 조금 감을 빨리 잡는 듯 하다.
그리고 약간 꼼수? 이지만 제한 사항에 8번이라는 비교적 작은 숫자가 걸려 있으면 시간 복잡도가 엥간하면 다 돌기 때문에 거꾸로 생각하면 시간 복잡도가 복잡한 문제로 볼 수 있다🤦‍♂️
(N = 5 일 경우)

- N이 i 개 만큼 있는 set을 만들어 준다.
- dp[1] 일 경우, {5} , dp[2] 일 경우 {5+5, 5-5, 5//5, 5*5}이기 때문에 **이전(dp[1])의 구성요소의 사칙 연산 결과로 구성 되어있다.**
- 이처럼 dp[3]을 해보면, 555 , (dp[1] 연산 dp[2]) , (dp[2] 연산 dp[1])이 되는것을 볼 수 있다.
- 만들어진 dp[i] set 에서 number이 존재하면 i를 반환.
- 끝까지 발견 못하면 -1을 출력

```python
def solution(N, number):
    answer = -1
    dp = []
    
    for i in range (1,9) : 
    # i = N의 개수
    	all_case = set()
        check_number = int(str(N)* i)
        # {N}, {NN} , {NNN}...
        all_case.add(check_number)
        
        for j in range(0,i-1):
        # j 개를 사용해서 만든 값들
            for op1 in dp[j]:
                for op2 in dp[-j-1] :
                    all_case.add(op1 - op2)
                    all_case.add(op1 + op2)
                    all_case.add(op1 * op2)
                    if op2 != 0:
                        all_case.add(op1 // op2)
                        
        if number in all_case:
            answer = i
            break
            
        dp.append(all_case) 
        

    return answer
```