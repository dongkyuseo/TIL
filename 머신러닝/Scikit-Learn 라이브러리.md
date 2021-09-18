# Scikit-Learn 라이브러리

1. 머신러닝 알고리즘을 구현한 오픈소스 라이브러리 중 가장 유명한 라이브러리

   tensorflow, pytorch : deepLearning

   spark : ML 내장됨, JAVA, Scalar, python언어로 결과를 볼 수 잇음

2. python으로 만들어져 일관되고 API가 강점이며, 문서화가 잘되어 있음

3. 알고리즘은파이썬 클래스로 구현, 데이터 셋은 Numpy배열, Pandas DataFrame, SciPy 희소행렬을 사용할 수 있



## 특징 행렬(Feature Matrix)

1. 표본(sample) : 데이터셋이 설명하는 개별 객체를 나타냄
2. 특징(feature) : 각표본을 연속적인 수치값, 부울값, 이산값으로 표현하는 개별 관측치를 의미
   - 표본 : 행렬의 행
   - 행의 개수 : n_samples
   - 특징(feature) : 행렬의 열
   - 열의 개수 : n_features
3. 관례적으로 특징행렬은 변수 X에 저장
4. [n_samples, n_features] 형태의 2차원 배열 구조를 사용

(주로 Numpy배열, Pandas DataFrame, SciPy 희소행렬을 사용)



## 대상 벡터(Target Vector)

1. 연속적인 수치값, 이산 클래스/레이블을 가짐
2. 길이 -> n_samples
3. 관례적으로 대상벡터는 변수 y에 저장
4. 1차원 배열 구조를 사용 (주로 Numpy 배열, Pandas Series를 사용)
5. 특징 행렬로 부터 예측하고자 하는 값의 벡터
6. 종속변수, 출력 변수, 결과 변수, 반응 변수 라고도 함



## 특징행렬과 대셩벡터의 데이터 레이아웃

1. 길이가 같아야 함(행의 갯수가 같아야 함)



## Numpy 배열을 이용한 특징 행렬(X), 대상벡터(y)의 생성

```python
import numpy as np

rs = np.random.RandomState(10)
x = 10 * rs.rand(5)
y = 2 * x - 1 * rs.rand(5)

x.shape, y.shape
>>>
((5,), (5,))

# shape을 변경해야 함
X = x.reshape(-1, 1) # (행, 열) 열고정 1열
X.shape
>>>
(5, 1)
```



## Pandas DataFrame을 이용한 특징 행렬(X), 대상벡터(y)의 생성

```python
import seaborn as sns
iris = sns.load_dataset("iris")
iris.info()
>>>
<class 'pandas.core.frame.DataFrame'>
RangeIndex: 150 entries, 0 to 149
Data columns (total 5 columns):
 #   Column        Non-Null Count  Dtype  
---  ------        --------------  -----  
 0   sepal_length  150 non-null    float64
 1   sepal_width   150 non-null    float64
 2   petal_length  150 non-null    float64
 3   petal_width   150 non-null    float64
 4   species       150 non-null    object 
dtypes: float64(4), object(1)
memory usage: 6.0+ KB
```

```python
iris.head()
>>>
```

![1](D:/Workspace/00.TIL/%EB%A8%B8%EC%8B%A0%EB%9F%AC%EB%8B%9D/image/1.jpg)

```python
X = iris.drop("species", axis = 1)
X.shape	# 2차원 배열 구조
>>>
(150, 4)

y = iris["species"]
y.shape # 1차원 배열 구조
>>>
(150,)
```

## Scikit-Learn의 데이터 표현 방식

- bunch객체를 이용한 특징 행렬(X), 대상 벡터(y)의 생성

```python
from sklearn.datasets import load_iris

iris = load_iris()
type(iris)
>>>
sklearn.utils.Bunch

iris.keys()
>>>
dict_keys(['data', 'target', 'frame', 'target_names', 'DESCR', 'feature_names', 'filename'])

iris.feature_names # iris 열이름
>>>
['sepal length (cm)',
 'sepal width (cm)',
 'petal length (cm)',
 'petal width (cm)']

iris.data[:5]
>>>
array([[5.1, 3.5, 1.4, 0.2],
       [4.9, 3. , 1.4, 0.2],
       [4.7, 3.2, 1.3, 0.2],
       [4.6, 3.1, 1.5, 0.2],
       [5. , 3.6, 1.4, 0.2]])

iris.data.shape	# 150행, 4열
>>>
(150, 4)

iris.target # 1차원 배열, 수치형 데이터로 구성됨 확인
>>>
array([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
       0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
       1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
       1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
       2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
       2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2])

iris.target.shape
>>>
(150,)

iris.target_names
>>>
array(['setosa', 'versicolor', 'virginica'], dtype='<U10')
	#   0              1             2
    

X = iris.data # X : 특징행렬
y = iris.target # y: 대상행렬
```



# Scikit-Learn Estimator API 기본활용 절차

1. 데이터 준비
2. 모델 클래스 선택
3. 모델 인스턴스 생성과 하이퍼파라미터 선택
4. 특징 행렬과 대상 벡터 준비 # 지도학습
5. 모델을 데이터에 적합 # Fit
6. 새로운 데이터를 이용해 예측
7. 모델 평가 # 정확도



## 데이터 준비

```python
import numpy as np
import matplotlib.pyplot as plt
rs = np.random.RandomState(10)
x = 10* rs.rand(100) # 0~10 사이 100개의 랜덤값
y = 3 * x + 2 * rs.rand(100)
plt.scatter(x,y,s=10)	# 산점도
plt.show()
```

![output](D:/Workspace/00.TIL/%EB%A8%B8%EC%8B%A0%EB%9F%AC%EB%8B%9D/image/output.png)

- 선형적인 데이터 생성됨

## 모델클래스 선택, 모델 인스턴스 생성과 하이퍼 파라미터 선택

- x : 연속형 수치 데이터 
- y : 연속형 수치 데이터
- 선형 회귀모델 : 수치값을 예상할 때 사용

```python
from sklearn.linear_model import  LinearRegression
# 인스턴스 만들기
regr = LinearRegression(fit_intercept=True) # fit_intercept=True 옵션값 설정 가능
```



## 특징 행렬과 대상 벡터 준비

```python
# 1차원인 X를 2차원으로 변경
X = x.reshape(-1, 1)
X.shape, y.shape
>>>
((100, 1), (100,))
```



## 모델을 데이터에 적합

```python
regr.fit(X,y)
>>> 
regr.fit(X,y)LinearRegression(copy_X=True, fit_intercept=True, n_jobs=1, normalize=False)
				  # X 복사 여부, 절편에 적합시킬지 여부, job의 갯수, 정규화기능 사용여부 값이 셋팅되어 있음
    
regr.coef_ # 기울기
>>>
array([2.9855087])

regr.intercept_ # y절편
>>>
0.9878534341975662
```



## 새로운 데이터를 이용해 예측

```python
x_new = np.linspace(-1, 11, num=100) # 새로운 데이터 -1~ 11 사이의 값 100개
X_new = x_new.reshape(-1, 1) # 2차원 구조로 변경
X_new.shape
>>>
(100, 1)

y_pred = regr.predict(X_new) # y_pred(y예측값): 회귀객체(regr)를 넣어 predict(특징행렬 값)을 넣어줌

plt.plot(x_new, y_pred, c='red') # 예측선
plt.scatter(x, y, s=10) # 실제 데이터
plt.show()

# 예측값과 실제값의 오차는 잔차라고 부름
```

![output2](D:/Workspace/00.TIL/%EB%A8%B8%EC%8B%A0%EB%9F%AC%EB%8B%9D/image/output2.png)



## 모델 평가

- 회귀를 평가할땐 RMSE(Root Mean Squared Error)라는 평가 값을 사용함, (실제값과 오차값의)평균제곱 오차가 존재함

```python
from sklearn.metrics import  mean_squared_error #mean_squared_error:평균 제곱까지 구해주는 함수

rmse = np.sqrt(mean_squared_error(y, y_pred))
							# y: 실제값 , y_pred: 예측값
rmse
>>>
13.70823712248633
```

- rmse 값이 0에 가까울수록 훌륭한 모델





