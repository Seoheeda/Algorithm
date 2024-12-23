import sys
input = sys.stdin.readline

n, k = map(int, input().split()) 
coins = []   

for _ in range(n):
    a = int(input())
    coins.append(a)

cnt = 0
i = n - 1
while k > 0:
    if k >= coins[i]: 
        c = k // coins[i] 
        s = coins[i] * c  
        cnt += c
        k -= s
    i -= 1

print(cnt)
