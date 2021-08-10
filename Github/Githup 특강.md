# Githup 특강

## 코딩 스타일 가이드

- 함수이름/변수이름 = snake_case i_have_an_apple
- 클래스이름 = CamelCase             IHaveAnApple
- [좋은 git commit  메시지를 위한 영어사전 사이트](https://blog.ull.im/engineering/2019/03/10/logs-on-git.html)
- [파이썬 스타일 참조 사이트](https://www.python.org/dev/peps/pep-0008/)
- [gitignore 각 환경별 쉽게 만드는 사이트](https://www.toptal.com/developers/gitignore)

## Githup 브랜치 branch

- 독립된 작업 흐름을 만듬
- HEAD : 정보를 담아주고 포인팅 해줌
- git merge [다른head] 와 합침

### 1. branch 생성

```bash
$ git branch <브랜치이름>
```

### 2. branch 이동

```bash
$ git checkout 브랜치 이름
```

### 3. branch 생성 및 이동

```bash
$ git checkout -b 브랜치 이름
```

### 4. branch 조회

```bash
$ git branch
```

### 5. branch merge

```bash
(master) $ git merge 브랜치 이름
```

- master로 병합하려는 경우 master 브랜치에서 명령어를 작성해야함
- '브랜치이름' 을 해당 브랜치로 병합하는 명령어이기 때문

### 6. branch 삭제

```bash
$ git branch -d 브랜치이름
```



## Github Flow

 ## 끝말잇기

### 조장

- 로컬 저장소
  - 바탕화면에 끝말잇기 폴더 만들고, README.md 파일 만들어 커밋
  - `파`로 시작하는 단어 입력하고 커밋
- 원격저장소
  - end 라는 원격 저장소 만들고 push
- 조원 초대

### 조원

- 이메일에 저장소 초대 => 수락

- 원격저장소를 컴퓨터로 가져옴

  ```bash
  $ git clone _____________주소
  ```

  - clone 하면 폴더가 만들어짐

- 끝말잇기 단어 입력하고, push 하고 조장님께 pull 받으라고 이야기

### 이후 일

- pull을 받고
- 끝말잇기 단어 입력
- 커밋
- push
- 상대방에게 pull 받아주세요

## stash - 되돌리기 기능

$ git stash : 임시보관

$ git stash pop : 임시보관 해지

모든 커밋 시점은 복원 가능

로컬 작업중 온라인 변경발생시

stash로 임시 저장후

git pull 로 최신화 해준뒤

stash pop으로 변경사항 적용 가능











