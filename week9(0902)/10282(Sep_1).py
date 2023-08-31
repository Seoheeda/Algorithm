import sys
INF = sys.maxsize 
tests = int(input())

for _ in range(tests):
    n, d, c = map(int ,input().split())
    graph = [[INF for _ in range(n + 1)] for _ in range(n + 1)]
    for i in range(n + 1):
        graph[i][c] = 0
    for _ in range(d):
        a, b, s = map(int, input().split())
        graph[b][a] = min(graph[b][a], s)

    for k in range(1, n + 1):  # 거쳐가기
        for i in range(1, n + 1):  # 시작
            for j in range(1, n + 1):  # 끝
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])
    cnt = 0
    time = 0

    for i in range(1, n + 1):
        gone = False
        for j in range(1, n + 1):
            if graph[i][j] < INF:
                gone = True
                time = max(graph[i][j], time)
        if gone == True:
            cnt += 1
    print(cnt, time)
    