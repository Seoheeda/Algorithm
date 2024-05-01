import sys
input = sys.stdin.readline

n = int(input())  # 지방 수
areas = list(map(int, input().split()))  # 예산 요청들
m = int(input()) # 총 예산

max_num = max(areas)
min_num = 1
num = (max_num + min_num) // 2

def count(yesan):
    sums = 0
    for i in areas:
        if i <= yesan:
            sums += i
        elif i > yesan:
            sums += yesan
    return sums

if sum(areas) <= m:
    print(max(areas))
else:
    while min_num <= max_num:
        print(num, max_num, min_num)
        if count(num) > m :  # 만일 이 예산이 오바라면
            # print("y")
            max_num = num - 1
            num = (max_num + min_num) // 2
        elif count(num) <= m: # 만일 이 예산이 오바가 아니라면
            # print('n')
            min_num = num + 1
            num = (max_num + min_num) // 2

    print(num)
    
