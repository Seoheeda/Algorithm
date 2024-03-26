import sys
input = sys.stdin.readline

def isBingo(board):
    isbingo = 0

    for x in board:
        if x.count(0) == 5:
            isbingo += 1
    
    for i in range(5):
        cnt = 0
        for j in range(5):
            if board[j][i] == 0:
                cnt += 1
        if cnt == 5:
            isbingo += 1
    
    cnt = 0    
    for i in range(5):
        if board[i][i] == 0:
            cnt += 1
    if cnt == 5:
        isbingo += 1

    cnt = 0
    for i in range(5):
        if board[i][4 - i] == 0:
            cnt += 1
    if cnt == 5:
        isbingo += 1
    
    return isbingo

board = [
    list(map(int, input().split()))
    for _ in range(5)
]

called = [
    list(map(int, input().split()))
    for _ in range(5)
]

def whenBingo(board, called):
    result = 0
    for i in range(5):
        for j in range(5):
            result += 1
            for k in range(5):
                for l in range(5):
                    if called[i][j] == board[k][l]:
                        board[k][l] = 0
            if isBingo(board) >= 3:
                return result

print(whenBingo(board, called))






