import sys
input = sys.stdin.readline

# 시험 n개, 시험 총 m분, s분까지 사용 가능
n, m, s = map(int, input().split())

# 시험 정보 담기
tests = []
for _ in range(n):
    test = list(map(int, input().split()))
    tests.append(test)

# 시험 정보 오름차순
tests.sort()

def find_time():
    # 0분부터 시험 진행이 가능할 때
    if tests[0][0] >= m:
       return 0

    # 중간 순서로 시험 진행이 가능할 때
    for i in range(n - 1):
        end_time = tests[i][0] + tests[i][1]
        start_time = tests[i + 1][0]
        if start_time - end_time >= m:
            return end_time

    # 마지막 순서로 시험 진행이 가능할 때
    if tests[-1][0] + tests[-1][1] + m <= s:
        return tests[-1][0] + tests[-1][1]
    
    # 가능한 시간이 없을 때
    return -1

print(find_time())


# import sys
# input = sys.stdin.readline

# # 시험 n개, 시험 총 m분, s분까지 사용 가능
# n, m, s = map(int, input().split())
# time = [0] * (s + 1)

# tests = []
# for _ in range(n):
#     a, b = list(map(int, input().split()))
#     for i in range(a, a + b):
#         time[i] = 1

# ans = s + 1
# for i in range(s + 1):
#     if time[i:i + m] == [0] * m:
#         ans = min(ans, i)

# if ans > s:
#     print(-1)
# else:
#     print(ans)
    