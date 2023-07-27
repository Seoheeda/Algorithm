from collections import deque
import sys
input = sys.stdin.readline

n, m, v = map(int, input().split()) # 정점의 개수, 간선의 개수, 시작 정점 번호

graph = [[0] * (n + 1) for _ in range(n + 1)] 
# 각 노드가 방문된 정보 list
list = [0] * (n + 1)  # bfs
list2 = [0] * (n + 1)  # dfs

for _ in range(m):
  a, b = map(int, input().split())
  graph[a][b] = graph[b][a] = 1

def bfs(v):
  q = deque()
  q.append(v)       
  list[v] = 1  # 현재 노드 방문
  while q:  # 큐가 빌 때까지
    v = q.popleft()
    print(v, end = " ")
    # 아직 방문하지 않은 인접한 노드를 모두 큐에 삽입
    for i in range(1, n + 1):
      if list[i] == 0 and graph[v][i] == 1:
        q.append(i)
        list[i] = 1

def dfs(v):
  list2[v] = 1  # 현재 노드 방문      
  print(v, end = " ")
  for i in range(1, n + 1):
    if list2[i] == 0 and graph[v][i] == 1: # 아직 방문 전이고, 인접한 노드이면 재귀 호출
      dfs(i)



dfs(v)
print() # 줄바꿈 필요
bfs(v)