import sys
input = sys.stdin.readline

n, m = list(map(int, input().split()))
nums = [int(input()) for _ in range(n)]
nums.sort()

left = 0
right = 0

min_diff = 2e9

while left <= right and right < n:
    if nums[right] - nums[left] < m:
        right += 1
    else:
        min_diff = min(min_diff, nums[right] - nums[left])
        left += 1

print(min_diff)
