# import sys
# input = sys.stdin.readline

# n, m = map(int, input().split())
# nums = list(map(int, input().split()))

# for i in range(m):
#     start, end = map(int, input().split())
#     total = sum(nums[start - 1 : end])
#     print(total)


import sys
input = sys.stdin.readline

n, m = map(int, input().split())
nums = list(map(int, input().split()))

sums = [0]
total = 0

for i in nums:
    total += i
    sums.append(total)

for i in range(m):
    start, end = map(int, input().split())
    print(sums[end] - sums[start - 1])
