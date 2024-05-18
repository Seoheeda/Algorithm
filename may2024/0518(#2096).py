import sys
input = sys.stdin.readline

n = int(input())
nums = [list(map(int, input().split())) for _ in range(n)]
max_num = [[0, 0, 0] for _ in range(n)]
min_num = [[0, 0, 0] for _ in range(n)]
max_num[0] = nums[0]
min_num[0] = nums[0]

for i in range(1, n):
    for j in range(3):
        if j == 0:
            max_num[i][j] = max(max_num[i - 1][j] + nums[i][j], max_num[i - 1][j + 1] + nums[i][j]) 
            min_num[i][j] = min(min_num[i - 1][j] + nums[i][j], min_num[i - 1][j + 1] + nums[i][j]) 
        elif j == 1:
            max_num[i][j] = max(max_num[i - 1][j - 1] + nums[i][j], max_num[i - 1][j] + nums[i][j], max_num[i - 1][j + 1] + nums[i][j]) 
            min_num[i][j] = min(min_num[i - 1][j - 1] + nums[i][j], min_num[i - 1][j] + nums[i][j], min_num[i - 1][j + 1] + nums[i][j]) 
        else:
            max_num[i][j] = max(max_num[i - 1][j] + nums[i][j], max_num[i - 1][j - 1] + nums[i][j]) 
            min_num[i][j] = min(min_num[i - 1][j] + nums[i][j], min_num[i - 1][j +-1] + nums[i][j]) 


print(max(max_num[n - 1]), min(min_num[n - 1]))
