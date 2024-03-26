import sys
input = sys.stdin.readline

n = int(input())
n_nums = list(map(int, input().split()))
n_nums.sort()
m = int(input())
m_nums = list(map(int, input().split()))

for num in m_nums:
    start = 0
    end = n - 1
    inlist = False
    while start <= end:
        mid = (start + end) // 2
        if num == n_nums[mid]:
            inlist = True
            print(1)
            break
        elif num < n_nums[mid]:
            end = mid - 1
        elif num > n_nums[mid]:
            start = mid + 1
    if inlist == False:
        print(0)

