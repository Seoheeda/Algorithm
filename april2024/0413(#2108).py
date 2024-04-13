import sys
input = sys.stdin.readline

nums = []

n = int(input())
for i in range(n):
    num = int(input())
    nums.append(num)
nums.sort()

# 산술평균
average = sum(nums) / n
print(round(average))

# 중앙값
mid = nums[n // 2]
print(mid)

# 최빈값
cnt = dict()
for num in nums:
    if num in cnt:
        cnt[num] += 1
    else:
        cnt[num] = 1

often = max(cnt.values())
often_nums = []
print("often", often)

for i in cnt:
    if cnt[i] == often:
        often_nums.append(i)

if len(often_nums) == 1:
    print(often_nums[0])
else:
    print(often_nums[1])

# 범위
ranges = nums[n - 1] - nums[0]
print(ranges)