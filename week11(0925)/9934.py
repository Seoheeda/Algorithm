import sys
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

level = int(input())
inorder = list(map(int, input().split()))
tree = [[] for _ in range(level)]

def building(start, end, level):
    if start > end:
        return
    root = (start + end) // 2
    tree[level].append(inorder[root])
    building(start, root-1, level+1)
    building(root+1, end, level+1)



building(0, len(inorder) - 1, 0)
for i in range(level):
    for j in tree[i]:
        print(j, end=' ')
    print()