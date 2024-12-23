import sys
input = sys.stdin.readline

n = int(input())

d = [0] * (n + 1)

for i in range(2, n + 1):
    d[i] = d[i - 1] + 1
    if i % 3 == 0:
        d[i] = min(d[i], d[i // 3]+1)
    if i % 2 == 0:
        d[i] = min(d[i], d[i // 2]+1)
    
print(d[n])

'''
d[2] = 1 (d[1] + 1)
d[3] = 1 (d[1] + 1)
d[4] = 2 (d[2] + 1)
d[5] = 3 (d[4] + 1)
d[6] = 2 (d[2] + 1)
d[7] = 3 (d[6] + 1)
'''
