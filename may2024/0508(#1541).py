import sys
input = sys.stdin.readline

sick = input()
operations = []
plus = []
minus = []
ans = 0

for i in range(len(sick)):
    if sick[i] == '+' or sick[i] == '-':
        operations.append(i)
    if sick[i] == '+':
        plus.append(i)
    elif sick[i] == '-':
        minus.append(i)

if  len(minus) == 0:
    if len(plus) == 1:
            ans += int(sick[: plus[0]])
            ans += int(sick[plus[0] + 1 :])
    else:
        for i in range(len(plus)):
            if i == 0:
                ans += int(sick[: plus[i]])
            elif i == len(plus) - 1:
                ans += int(sick[plus[i - 1] : plus[i]])
                ans += int(sick[plus[i] :])
            else:
                ans += int(sick[plus[i - 1] : plus[i]])
elif len(plus) == 0:
    if len(minus) == 1:
            ans += int(sick[: minus[0]])
            ans -= int(sick[minus[0] + 1 :])
    else:
        for i in range(len(minus)):
            if i == 0:
                ans += int(sick[: minus[i]])
            elif i == len(minus) - 1:
                ans -= int(sick[minus[i - 1] + 1 : minus[i]])
                ans -= int(sick[minus[i] + 1 :])
            else:
                ans -= int(sick[minus[i - 1] + 1 : minus[i]])


print(ans)
