import sys
input = sys.stdin.readline

left_stack = []
right_stack = []

text = input().strip()
for char in text:
    left_stack.append(char)

m = int(input())
for _ in range(m):
    rule = input().split()
    if rule[0] == 'L' and left_stack:
        right_stack.append(left_stack.pop())
    elif rule[0] == 'D' and right_stack:
        left_stack.append(right_stack.pop())
    elif rule[0] == 'B' and left_stack:
        left_stack.pop()
    elif rule[0] == 'P':
        left_stack.append(rule[1])

print(''.join(left_stack + right_stack[::-1]))