import sys
input = sys.stdin.readline

n, m = map(int, input().split())

under = 1
for i in range(1, m + 1):
    under = under * i

top = 1
for j in range(n, n - m, -1):
    top = top * j

answer = top // under
print(answer)