import sys
input = sys.stdin.readline

n, m = list(map(int, input().split()))  # 달걀 수 n, 사람 수 m

customers = []  # 손님별 희망 가격 저장한 리스트
for _ in range(m):
    x = int(input())
    customers.append(x)

customers.sort()  # 오름차순 정렬하기
profit = 0  # 수익
price = 0  # 설정 가격

for i in range(m):  # 사람 수만큼 돌기
    egg = min(m - i, n)   # 팔 계란의 개수

    if profit < customers[i] * egg:  # 만일 현재 수익이 새로운 수익보다 적다면
        profit = customers[i] * egg  # 수익 갱신하기
        price = customers[i]  # 가격도 재설정하기

print(price, profit)


