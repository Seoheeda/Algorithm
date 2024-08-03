from collections import deque

n = int(input())
graph = []
visited = [0] * n

for i in range(n):
    g = list(map(int, input().split()))
    graph.append(g)

def dfs(v):
   for i in range(n):
       if visited[i] == 0 and graph[v][i] == 1:
           visited[i] = 1
           dfs(i)
       
for i in range(n):
    dfs(i)
    print(*visited)
    visited = [0] * n

