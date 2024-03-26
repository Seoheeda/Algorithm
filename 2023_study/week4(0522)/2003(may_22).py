import sys
input = sys.stdin.readline

n, m = map(int, input().split())
nums = list(map(int, input().split()))  # 1 2 1 3 1 2 

def find_sum(n, m, nums):
    count = 0
    left = 0
    right = 1

    while right <= n and left <= right:
        sums = sum(nums[left:right])

        if sums == m:
            count += 1
            left += 1
        elif sums > m:
            left += 1
        else:
            right += 1
    return count

print(find_sum(n, m, nums))


        
