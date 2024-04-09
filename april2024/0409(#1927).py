import sys
import heapq

input = sys.stdin.readline

n = int(input())
heap = []

for _ in range(n):
    num = int(input())
    if num != 0:
        heapq.heappush(heap, num)
    elif len(heap) == 0:
        print(0)
    else:
        print(heapq.heappop(heap))
