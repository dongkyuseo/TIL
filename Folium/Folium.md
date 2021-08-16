# [Folium](http://python-visualization.github.io/folium/)

## REST API

### HTTP METHOD

- POST - 요청에 보안을 적용하여 서버로 전달함
- GET - 주소창을 통해 정보를 조회하는 것
- PUT - 
- DELETE - 
- XML - (eXtensible Markup Langeuage)
- JSON - (JavaScript Object Notation) 웹에서 제일 많이 사용됨

## [Folium 공부 자료](https://github.com/dongkyuseo/DataAnalysis/blob/main/04.%EA%B3%B5%EA%B3%B5API_%EB%B0%8F_%EC%A7%80%EB%8F%84%EC%8B%9C%EA%B0%81%ED%99%94/03_%EC%B9%B4%EC%B9%B4%EC%98%A4_%EB%A1%9C%EC%BB%AC.ipynb)

## [공부 사이트링크](https://dailyheumsi.tistory.com/144)

```python
# 폴리움 지도 시각화 툴 불러오기
import folium

location = [37.541, 126.986]						# 위도, 경도
map = folium.Map(location=location, tiles='Stamen Toner', zoom_start=12
    # folium.map(location=위도경도자료, tiles=지도의 모양, zoom_start=지도 확대 배율)
map.save('seoulmap.html') 							# 맵파일 저장
```

```python
map2 = folium.Map(location=[37.566317, 126.977829], zoom_start=13)
folium.Circle(						  # Circle-원, Marker-마커
    radius=200,						  # 지도에 표시하는 원의 크기
    location=[37.566317, 126.977829],   # 위도, 경도
    popup='서울 시청, 팝업',			  # 위치 클릭시 팝업창에 내용
    tooltip='서울특별시청, 툴팁',		 # 마우스 on일시 뜨는 툴팁
    color='crimson',				  # 지도에 표시된 원의 색깔
    fill=True						 # 원을 채울 경우 True, 빈원
).add_to(map2)
folium.CircleMarker(				  # 써클마커
    radius=50,
    location=[37.5799317, 126.979829],   # 위도, 경도
    popup='팝업',
    tooltip='툴팁',
    color="#3186cc",
    fill=False,
).add_to(map2)
map2
```

### 로컬파일 Colab에 업로드하기

```python
from google.colab import files
uploaded = files.upload()
uploaded.keys()
```

### 건물명 명소로부터 도로명주소 구하기

```python
import requests
from urllib.parse import quote
```

- 에이스64 인코딩을 하기 위한 request 불러오기
- urllib.parse 의 quote 함수 적용

```python
with open(filename) as f:
  api_key = f.read()
len(api_key)
```

- api데이터를 가져오기 위해 api_key인증정보를 파일화하여 인증받기 위한 업로딩

```python
# 각 구청의 도로명주소 가져오기

bldgs = ['종로구청','노원구청','송파구청','마포구청','양천구청']
quote(bldg)   #에이스64 인코딩, 컴퓨터가 읽기위한 인코딩작업

addr_list = []
for bldg in bldgs:
  option = f'confmKey={api_key}&currentPage=1&countPerPage=10&keyword={quote(bldg)}'
# api 정보를 받기위한 f'키 인증&currentPage=1&countPerPage=10(가져올데이터양)&keyword=인코딩한  필요 데이터값 '
  road_url = 'https://www.juso.go.kr/addrlink/addrLinkApi.do'
  url = f'{road_url}?{option}&resultType=json'	# url값을 주소를 받을url?인증 및 데이터정보를 제이슨정보로 받아옴
  result = requests.get(url).json()			   # 결과를 get을 써서 읽어옴 json으로
  addr = result['results']['juso'][0]['roadAddr'] # 필요한 주소값을 딕셔너리를 이용해 받아옴
  addr_list.append(addr)						# 주소리스트에 각 구청별 도로명주소를 받아옴
    
addr_list
['서울특별시 종로구 종로1길 36(수송동)',
 '서울특별시 노원구 노해로 437(상계동)',
 '서울특별시 송파구 올림픽로 326(신천동)',
 '서울특별시 마포구 월드컵로 지하190(성산동)',
 '서울특별시 양천구 목동동로 105(신정동)']
```

```python
import pandas as pd

df = pd.DataFrame({				
    '공공기관' : bldgs,
    '도로명주소' : addr_list
})
df 		# 판다스를 이용해 공공기관을 도로명주소와 연결하는 데이터프레임 만듬
df.to_csv('공공기관.csv', index=False)	# csv로 저장하는 방법 to_csv, index=Fasle 인덱스 제외 저장
```

### 카카오 로컬 활용하기

```python
from google.colab import files    		# 코랩에 파일 업로드 기능 활성화
uploaded = files.upload()			    # 파일 업로드
filename = list(uploaded.keys())[0]		# 카카오api키 파일 업로드
filename								# 업로드 확인
[kakaoapikey.txt]

import requests							# 에이스 64로 변환을 위한 REQUESTS 활성화
from urllib.parse import quote			# QUOTE 활성화

with open(filename) as f:				# 카카오 개발 API키 업로드
  api_key = f.read()					# API_KEY 변수에 할당
len(api_key)							# 키 이상여부 확인
```

- ㄴ 카카오 api 활용을 위한 Colab 셋팅
- [카카오 개발api 활용사이트](https://developers.kakao.com/docs/latest/ko/local/dev-guide)

```python
addr = '서울특별시 중구 태평로1가 31 서울특별시청'					# 도로명주소
serch_url = 'https://dapi.kakao.com/v2/local/search/address.json'  # 카카오api
url = f'{serch_url}?query={quote(addr)}'						# api를 쓰기위한 url 변수 설정

# Authorization: KakaoAK {REST_API_KEY}
result = requests.get(url,
            headers={"Authorization": f'KakaoAK {api_key}'}).json()
		# 결과를 받기 위한 get함수 이용, 카카오 키 인증, 방식은 카카오의 규칙에 따름

    
lng = float(result['documents'][0]['x'])
lat = float(result['documents'][0]['y'])
lat, lng
# 경도, 위도를 각각 받음
(37.5663174209601, 126.977829174031)
```

### 공공기관.csv파일에 위도 경도 추가하기

- 