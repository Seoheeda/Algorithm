import sys
from collections import deque  # deque 자료구조 사용하기 --> 수행시간 단축 가능
input = sys.stdin.readline

n = int(input())
nums = []
for i in range(1, n + 1):
    nums.append(i)

q_num = deque(nums)

while len(q_num) > 1:
    q_num.popleft()  # 왼쪽 요소 하나를 pop
    q_num.append(q_num.popleft())  # 오른쪽에 요소를 append 가능

print(q_num[0])