import sys
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

n = int(input())

graph = [[] for _ in range(n + 1)]
parent = [0] * (n + 1)

for i in range(n -1):
    start, end = map(int, input().split())
    graph[start].append(end)
    graph[end].append(start)

def dfs(node):
    for i in graph[node]:
        if parent[i] == 0:
            parent[i] = node
            dfs(i)

dfs(1)

for i in range(2, n + 1):
    print(parent[i])