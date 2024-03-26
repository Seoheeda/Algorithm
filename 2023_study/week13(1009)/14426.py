import sys
input = sys.stdin.readline

N, M = map(int, input().strip().split())

words = [ input().strip() for _ in range(N) ]
prefixes = [ input().strip() for _ in range(M) ]

answer = 0

for prefix in prefixes:
    for word in words:
        if word.startswith(prefix):
            answer += 1
            break

print(answer)