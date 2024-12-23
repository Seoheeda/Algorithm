import sys
input = sys.stdin.readline

sick = input().split('-')

nums = []

for i in sick:
    sum = 0
    plus = i.split('+')
    for j in plus:
        sum += int(j)
    nums.append(sum)

ans = nums[0]

for i in range(1, len(nums)):
    ans -= nums[i]

print(ans)