import sys
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

n = int(input())
tree = dict()

for i in range(n):
    a, b, c = map(str, input().split())
    tree[a] = b, c

def preorder(node): # 전위순회
    if node != ".":
        print(node, end='')
        preorder(tree[node][0])
        preorder(tree[node][1])

def inorder(node): # 중위순회
    if node != ".":
        inorder(tree[node][0])
        print(node, end='')
        inorder(tree[node][1])

def postorder(node): # 후위순회
    if node != ".":
        postorder(tree[node][0])
        postorder(tree[node][1])
        print(node, end="")

preorder("A")
print()
inorder("A")
print()
postorder("A")