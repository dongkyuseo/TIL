# Git



## 인터페이스

- GUI / graphic user interface
  - 그래픽으로 이루어진 인터페이스 ex) 윈도우즈
- CLI / command line interface
  - 텍스트로 이루어진 인터페이스 ex) DOS



## Git Bash

- 명령어
  - touch a.txt : 빈 파일 생성
  - ls : 파일 리스트 불러오기
  - mkdir test : 디렉토리만들기 디렉토리명
  - cd test : 디렉토리로 이동
  - cd .. : 상위 폴더로 이동
  - cd . : 현재 디렉토리
  - pwd : 현재 폴더 경로
  - ctrl + l : 화면 클리어
  - shift + insert : 붙여넣기
  - ctrl + u : 줄 삭제
  - git init : Git 저장소를 만드는 기본 명렁어
  - git add \<file> : working directory상 변경내용을 staging area에 추가하기 위해 사용 
    - . # 현재 디렉토리의 모든 변경사항
    - a.txt b.txt # 복수의 디렉토리
    - test_folder/ # 특정폴더
    - c.txt # 특정 파일
    - "*.txt" # 특정 확장자
  - git commit -m '<커밋메시지>' : 
    - \-commit
      - Git은 데이터를 파일 시스템의 스냅샷(사진을 찍음)으로 관리하고 크기가 매우 작음
      - 파일이 달라지지 않으면 성능을위해 새로 저장하지 않음
  - git status : 파일의 상태 확인 명령어(초록색 : staging area, 빨간색 : working directory)
    - Chages to be commited : 커밋이 될 변경사항들, staging area
    - Changes not staged for commit : staged가 아닌 변경사항들, wroking directory
    - Untracked file : 트래킹 X 파일들, 커밋이 된적 없는 파일, working directory
  - git log : 현재 저장소에 기록된 커밋 조회, 다양한 옵션을 통해 로그를 조회할 수 있음
    - git log -1 : 
    - git log --oneline : 로컬의 파일 목록
    - git log -2--oneline : 최근 2개만 한줄로
  - esc :wq 나가는기능
  - git restore --staged <file> : staged 에서 취소하는명령어
  - git restore : 복구 기능 단 취소 불가
  - remote -v : 원격 저장소 위치 확인
  - git commit --amend : 커밋메시지 수정
  - git reset --hard 버전 : 해당 버전으로 리셋, 이전기록 그냥 삭제 해버림, push 전 가능 , --mixed, --soft 
  
  - git revert 버전 : : 삭제할 버전 선택, 되돌리는 커밋을 남김, push 후 가능
- 기본흐름
  - modified : 파일이 수정된 상태
  - staged : 수정한 파일 곧 커밋할 것이라 표시한 상태
  - committed : 커밋이 된상태

### 원격저장소 조회

```bash
$ git remote -v # verbose

origin  https://github.com/dongkyuseo/test.git (fetch)
origin  https://github.com/dongkyuseo/test.git (push)
```

### 원격 저장소 추가

```bash
$ git remote -v : git 위치 확인
$ git remote add origin <주소> : 저장소 추가
$ git remote add origin https://github.com/<username>/<저장소 이름>.git : 저장소 추가
$ git remote rm : git 지우기
```

- 깃아, 원격 저장소(remote)를 추가해줘(add). origin이라는 이름으로, 주소를

### 원격저장소 삭제

```bash
$ git remote rm <원격 저장소 이름>
```

### 원격 저장소 push

```bash
$ git push <원격저장소이름> <브랜치이름>
$ git push origin master 
```

- 깃, psuh. origin에 master 브랜치를!!
- \-u 옵션 : upsteram 옵션
  - git push 라고 명령하더라도 설정된 원격저장소에 브랜치를 push

