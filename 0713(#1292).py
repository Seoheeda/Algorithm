import sys
input = sys.stdin.readline

a, b = map(int, input().split())

data = [0]
for i in range(1, b + 1):
    for j in range(i):
        data.append(i)

ans = 0
for k in range(a, b + 1):
    ans += data[k]

print(ans)
