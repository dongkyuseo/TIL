# 함수
class TreeNode() :
    def __init__(self) :
        self.left = None
        self.data = None
        self.right = None

# 전역변수
memory = []
root = None
nameAry = ['블랙핑크', '레드벨벳', '마마무', '에이핑크', '걸스데이', '트와이스']

# 메인
# 첫번째 데이터를 만들고 루트로 지정
node = TreeNode()
node.data = nameAry[0]
root = node
memory.append(node)

# 이후 이진 탐색 트리 이용
for name in nameAry[1:] :
    node = TreeNode()
    node.data = name
    current = root
    while True: # 이진탐색은 자리를 언제 잡을지 몰라 무한반복 해야 함, 한글은 가나다 순
        if name < current.data :
            if current.left == None :
                current.left == node
                break
            current == current.left
        else:
            if current.right == None :
                current.right == node
                break
            current = current.right

    memory.append(node)

print('이진 탐색 트리 구성 완료!')

findData = '마마무'
current = root

while True :
    if current.data == findData:
        print(findData, '찾았음!')
        break
    elif findData < current.data :
        if current.left == None :
            print(findData, '없음!')
            break
        current = current.left
    else :
        if current.right == None :
            print(findData, '없음!')
            break
        current = current.right

