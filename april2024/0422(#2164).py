import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
nums = []
for i in range(1, n + 1):
    nums.append(i)

q_num = deque(nums)

while len(q_num) > 1:
    q_num.popleft()
    q_num.append(q_num.popleft())

print(q_num[0])