import sys
input = sys.stdin.readline
from collections import defaultdict

n, k = list(map(int, input().split()))

people = []
for _ in range(n):
    people.append(len(input().strip()))

check = defaultdict(int)
count = 0

for i in range(n):
    if i > k:
        check[people[i-k-1]] -= 1
    count += check[people[i]]
    check[people[i]] += 1

print(count)
