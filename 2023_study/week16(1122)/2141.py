import sys
input = sys.stdin.readline

N = int(input())
X = []
total_people = 0

for _ in range(N):
    x, a = map(int, input().split())
    X.append([x, a])
    total_people += a

X.sort(key = lambda x : x[0])
people = 0
cur = -1

while people < total_people / 2:
    cur += 1
    people += X[cur][1]

post = X[cur][0]
print(post)



