# import sys
# input = sys.stdin.readline

# n, m = map(int, input().split())
# trees = list(map(int, input().split()))

# start = max(trees)
# while start > 0:
#     sums = 0
#     start -= 1
#     for tree in trees:
#         if int(tree) > start:
#             len = int(tree) - start
#             sums += len
#     if sums >= m:
#         print(start)
#         break

import sys
input = sys.stdin.readline

n, m = map(int, input().split())
trees = list(map(int, input().split()))
start, end = 1, max(trees)

while start <= end:
    mid = (start + end) // 2
    sums = 0
    for tree in trees:
        if tree > mid:
            sums += (tree - mid)
    if sums >= m:
        start = mid + 1
    else:
        end = mid - 1
print(end)
    