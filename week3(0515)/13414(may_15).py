# import sys

# K, L = map(int, sys.stdin.readline().split())
# students = [sys.stdin.readline().strip() for _ in range(L)]

# for i in range(len(students)):
#     if students[i] in students[i + 1 :]:
#         students.remove(students[i])

# for i in range(K):
#     print(students[i])



# import sys

# K, L = map(int, sys.stdin.readline().split())
# students = []

# for i in range(L):
#     student = sys.stdin.readline().rstrip()
#     if student in students:
#         students.remove(student)
#     students.append(student)

# for i in range(K):
#     print(students[i])


import sys 

K, L = map(int, sys.stdin.readline().split())

def register(K, L):
    registered = dict()

    for i in range(L):
        registered[sys.stdin.readline().strip()] = i + 1

    registered = sorted(registered.items(), key=lambda x:(x[1]))

    cnt = 0
    for key, value in registered:
        print(key)
        cnt += 1
        if cnt == K:
            break

register(K, L)
