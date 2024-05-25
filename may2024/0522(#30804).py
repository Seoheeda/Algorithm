# import sys
# imput = sys.stdin.readline

# n = int(input())
# tang = list(map(int, input().split()))

# def count(tang):
#     info = [[] for _ in range(10)]

#     for i in range(len(tang)):
#         info[tang[i]].append(i + 1)

#     cnt = 0
#     for i in info:
#         if len(i) >= 1:
#             cnt += 1

#     return cnt

# start = 0
# end = n - 1

# def find(start, end, tang):
#     # print(start,end)
#     mini = tang[start : end + 1]
#     if count(mini) <= 2:
#         print(len(mini))
#         sys.exit()
#     else:
#         find(start + 1, end, tang)
#         find(start, end - 1, tang)

# print(find(start, end, tang))
        
# '''
# 5 1 1 2 1 3 3
# '''


def max_fruits(N, fruits):
    from collections import defaultdict
    
    # 포인터와 변수 초기화
    left_pointer = 0
    fruit_count = defaultdict(int)
    max_length = 0
    
    # 현재 과일을 윈도우에 추가하는 함수
    def add_fruit(fruit):
        fruit_count[fruit] += 1
    
    # 왼쪽 끝 과일을 윈도우에서 제거하는 함수
    def remove_fruit(fruit):
        if fruit_count[fruit] > 0:
            fruit_count[fruit] -= 1
        if fruit_count[fruit] == 0:
            del fruit_count[fruit]
    
    # 오른쪽 포인터를 이동시키면서 배열을 순회
    for right_pointer in range(N):
        # 현재 과일을 윈도우에 추가
        add_fruit(fruits[right_pointer])
        
        # 윈도우에 과일 종류가 두 종류를 초과할 때, 왼쪽 포인터를 이동시켜 윈도우 축소
        while len(fruit_count) > 2:
            remove_fruit(fruits[left_pointer])
            left_pointer += 1
        
        # 두 종류 이하의 과일로 이루어진 윈도우의 최대 길이 갱신
        current_length = right_pointer - left_pointer + 1
        max_length = max(max_length, current_length)
    
    return max_length

# 예제 사용법:
N = int(input())  # 첫 번째 줄에서 과일의 개수 N을 입력받음
fruits = list(map(int, input().split()))  # 두 번째 줄에서 과일 번호 리스트를 입력받음
print(max_fruits(N, fruits))  # 최대 길이를 출력

