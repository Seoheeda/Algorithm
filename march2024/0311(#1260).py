from collections import deque
import sys
input = sys.stdin.readline

n, m, v = map(int, input().split()) # 정점의 개수, 간선의 개수, 시작 정점 번호

graph = [[0] * (n + 1) for _ in range(n + 1)] 
list = [0] * (n + 1)  # bfs
list2 = [0] * (n + 1)  # dfs

for _ in range(m):
  a, b = map(int, input().split())
  graph[a][b] = graph[b][a] = 1

def bfs(v):
  q = deque()
  q.append(v)       
  list[v] = 1   
  while q:
    v = q.popleft()
    print(v, end = " ")
    for i in range(1, n + 1):
      if list[i] == 0 and graph[v][i] == 1:
        q.append(i)
        list[i] = 1

def dfs(v):
  list2[v] = 1        
  print(v, end = " ")
  for i in range(1, n + 1):
    if list2[i] == 0 and graph[v][i] == 1:
      dfs(i)

dfs(v)
print()
bfs(v)