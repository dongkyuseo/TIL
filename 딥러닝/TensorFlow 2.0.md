# [TensorFlow 2.0](https://www.youtube.com/playlist?list=PLSlDi2AkDv82pFZvTKP10oUAXenBZwpl7)

## Sequential 모델

- 하나의 입력과 하나의 출력이 있는 레이어를 쌓을때 적합함
- 다중 입력 또는 다중 출력이 있는 경우 적합하지 않음(함수형 모델에서는 가능)

```python
# 시퀀셜 모델 만드는 방법 1
model = Sequential([
    Flatten(input_shape=(28,28), name='A')
    Dense(units=128, activation='relu', input_shape=(784,)),
    Dense(3, activation='softmax')
])

```

- name : layer의 이름을 지정해 줄수 있음
- Flatten : 2차원 함수를 1줄로 자동으로 펼쳐서 넣어줌
- units : 생략 가능 그냥 숫자만 써도 됨
- input_shape : 입력 값의 모양
- activation : 활성화 함수
  - ReLU : 



```python
# 모델 요약
model.summery()
```



## 컴파일(compile)이란?

- 해석기, 번역기라는 뜻으로 특정 프로그래밍 언어로 쓰여있는 문서를 프로그래밍 언어로 옮기는 언어 번역 프로그램을 의미한다



```python
model.compile(optimizer='adam',
             loss='sparse_categorical_crossentropy',
             metrics=['accuracy'])
```

- loss : 손실함수, 목적함수 라고하며, 훈련하는 동안 오차를 측정함(예측값, 실제값의 차이) 훈련하는 동안 최소화 시키기 위해 옵티마이저를 사용함

  - 다중분류 : 'categorical_crossentropy' : 원핫 인코딩 시켜줌

    ​		   'sparse_categorical_crossentropy' : 정수형 그대로 사용 가능

  - 이진분류 : 'binary_crossentropy'

  - 회귀 : 'mse' or 'mae'

- optimizer : 손실함수를 바탕으로 모델의 업데이트 방법을 결정함.

  ​			경사하강법을 이용함, 기울기가 0이 되는 지점을 찾음

- metrics : 평가지표

  - 분류 : accuracy
  - 회귀 : MAE, MSE 등



## 훈련, 학습

```python
model.fit(x_train, y_train, epochs=3, batch_size=32, verbose=1)
		# f     ,  label , 학습횟수(기본값1), 학습할 데이터수(기본값 32) , 출력값을 표시해주는 단계 0:미출력 

```

- model.fit을 계속 할 경우 가중치가 중첩됨!, 새로이 훈련을 하려면 초기화 해야함
- verbose : 출력값 표시여부
  - 0 :  출력값 미표시
  - 1 : (기본값) 출력값 표시, loss, acc, 진행표시가 보임
  - 2 : 진행표시 없이 loss, acc 를 표시

```python
history = model.fit(x_train, y_train, epochs=5, batch_size=64)
```

- history : 그림을 그리기 위한 함수값

```python
# 학습 정확도 라인플롯
plt.plot(history.history['accuracy'])
plt.xlabel('epochs')
plt.ylabel('accuracy')
```



## train/validation, 훈련, 검증

- train : 훈련용 데이터
- validation : 검증용 데이터

```python
from sklearn.model_selection import train_test_split
x_train, x_val, y_train, y_val = trian_test_split(X, y, test_size=0.2)
model.fit(x_train, y_train, validation_data=(x_val,y_val), epochs=5)

history = mdoel.fit(X, y, validation_split=0.2, epochs=5, batch_size=64, verbose=1)
```

- train_test_split 을 이용해 x, y데이터를 넣고 test_size를 균일하게 자동으로 분리해주도록 설정할 수 잇음



## 평가

```python
loss, accuracy = model.evaluate(x_test, y_test)
print("test 데이터 정확도:", accuracy)
```

- Accuracy(정확도) : 맞춘 예측 데이터 / 전체 데이터



```python
#예측
predictions = model.predict(x_test)
# 단일 값의 예측 결과를 받아옴

#첫번째 데이터의 예측 결과값
predictions[0]
>>> array([1.90902139102390, 0.0000000e+00 ...])

# 표현방식을 두자리고 고정하면 보기 편함
# 표현방식 변경(소수점 2자리로 제한)
import numpy as np
np.set_printoptions(formatter={'float_kind':lambda x:"{0:0.2f}".format(x)})

predictions[0]
>>> array([0.00, 0.00, 0.00, 1.00, 0.00 ...])
						# 예측값!
```

























