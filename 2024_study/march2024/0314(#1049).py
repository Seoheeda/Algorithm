import sys
input = sys.stdin.readline

n, m = map(int, input().split())
sets = []
indiv = []
money = 0

for _ in range(m):
    a, b = list(map(int, input().split()))
    sets.append(a)
    indiv.append(b)

while n != 0:
    if n >= 6 and min(sets) <= min(indiv) * 6:
        money += min(sets)
        n -= 6
    elif n < 6 and min(indiv) * n >= min(sets):
        money += min(sets)
        n = 0
    else:
        money += min(indiv) * n
        n = 0

print(money)

