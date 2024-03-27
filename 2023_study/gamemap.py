from collections import deque

def solution(maps):
    answer = 0
    n = len(maps) # 세로
    m = len(maps[0])  # 가로
    dx = [-1, 1, 0, 0] # 왼 오 아래 위
    dy = [0, 0, -1, 1]

    def bfs(x,y):
        q = deque()
        q.append((x,y))

        while q:
            x, y = q.popleft()
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]

                if nx < 0 or ny < 0 or nx >= n or ny >= m:  # 범위 벗어남
                    continue
                if maps[nx][ny] == 0:  # 벽이 있음
                    continue
                if maps[nx][ny] == 1:  # 벽이 없음
                    maps[nx][ny] = maps[x][y] + 1
                    q.append((nx, ny))
        return maps[n-1][m-1]

    if bfs(0,0) == 1:
        return -1
    else:
        answer = bfs(0,0)

    return answer