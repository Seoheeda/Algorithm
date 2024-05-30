import sys
input = sys.stdin.readline

n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]

# dp 테이블 (초기값 설정)
dp = [[0] * n for _ in range(n)]
dp[0][0] = 1

for i in range(n): # 세로
    for j in range(n): # 가로

        # 오른쪽 이래 칸이라면, 정답 출력하고 마무리
        if i == n - 1 and j == n - 1:
            print(dp[i][j])
            break
        
        # 오른쪽으로 이동 가능하면 이동하고, dp 테이블 업데이트
        if j + board[i][j] < n:
            dp[i][j + board[i][j]] += dp[i][j]

        # 아래로 이동 가능하면 이동하고, dp 테이블 업데이트
        if i + board[i][j] < n:
            dp[i + board[i][j]][j] += dp[i][j]