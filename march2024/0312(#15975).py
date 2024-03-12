# 내가 시도해본 코드 --> 실행은 잘 되지만, n의 크기가 10,000을 넘길 시 런타임 에러

import sys
input = sys.stdin.readline

n = int(input())
dots = []
length = 0

for _ in range(n):
    dot = list(map(int, input().split()))
    dots.append(dot)

for i in range(n):
    add = 0
    for j in range(n):
        if i != j and dots[i][1] == dots[j][1]:
            if add == 0:
                add = abs(dots[i][0] - dots[j][0])
            else:
                add = min(add, abs(dots[i][0] - dots[j][0]))
    length += add

print(length)



# 점들의 정보를 일정 기준에 따라 정렬하니 훨씬 간단한 알고리즘 도출
# 시간복잡도 문제에도 걸리지 않음
n = int(input())
lst = [[] for _ in range(n)]
for i in range(n):
    a, b = map(int, input().split())
    lst[i] = [a, b]
lst.sort(key=lambda x: (x[1], x[0]))
res = 0
for i in range(n):
    s = 2 * 10**9 + 1
    if 0<= i < n - 1 and lst[i][1] == lst[i + 1][1]:
        s = min(s, abs(lst[i][0] - lst[i + 1][0]))
    if 0 < i < n and lst[i][1] == lst[i - 1][1]:
        s = min(s, abs(lst[i][0] - lst[i - 1][0]))
    if s == 2 * 10**9 + 1:
        s = 0
    res += s
print(res)