# 문제

###### 문제 설명

조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA

조이스틱을 각 방향으로 움직이면 아래와 같습니다.

```
▲ - 다음 알파벳
▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
▶ - 커서를 오른쪽으로 이동 (마지막 위치에서 오른쪽으로 이동하면 첫 번째 문자에 커서)
```

예를 들어 아래의 방법으로 "JAZ"를 만들 수 있습니다.

```
- 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
- 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
- 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.
```

만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.

##### 제한 사항

- name은 알파벳 대문자로만 이루어져 있습니다.
- name의 길이는 1 이상 20 이하입니다.

##### 입출력 예

| name     | return |
| -------- | ------ |
| "JEROEN" | 56     |
| "JAN"    | 23     |



# 풀이

```python
def solution(name):
    answer = 0
    min_left_right = len(name) # 왼쪽에서 오른쪽으로만 이동할 때 좌,우 조작 횟수
    next_idx = 0
    for idx, char in enumerate(name):
        # 위, 아래 조작 횟수의 최솟값 구하기
        answer += min(ord(char) - ord('A'), ord('Z') - ord(char) + 1)
        
        # 좌, 우 조작 횟수의 최솟값 구하기
        next_idx = idx + 1
        while next_idx < len(name) and name[next_idx] == 'A':
            next_idx += 1 # 현재 위치 이후 연속된 A 다음의 문자를 가리킴
        
        # 한 방향으로만 이동하는 경우와, 오른쪽으로 이동했다가 왼쪽으로 이동하는 경우를 비교
        min_left_right = min(min_left_right, idx + idx + len(name) - next_idx)
    answer += min_left_right
    return answer

solution("JEROEN")
```



[(아직 이해가 안됨)참고링크](https://velog.io/@dhelee/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A1%B0%EC%9D%B4%EC%8A%A4%ED%8B%B1-Python-%ED%83%90%EC%9A%95%EB%B2%95Greedy)