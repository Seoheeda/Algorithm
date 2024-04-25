import sys
input = sys.stdin.readline

'''
0으로 시작하지 않고, 1이 두 번 연속으로 나타나지 않음
n = 1  --> 1개 (1)
n = 2  --> 1개 (10)
n = 3  --> 2개 (100, 101)
n = 4  --> 3개 (1000, 1001, 1010)
n = 5  --> 5개 (10000, 10001, 10010, 10100, 10101)
'''

n = int(input()) 
if n == 1:
    print(1)
else:
    dp = [0] * (n + 1)

    dp[0] = 0
    dp[1] = 1

    for i in range(2, n + 1):
        dp[i] = dp[i - 1] + dp[i - 2]

    print(dp[n])
