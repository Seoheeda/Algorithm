# import sys
# input = sys.stdin.readline

# n = int(input())  # 소인수분해 해야하는 수

# for i in range(n):
#     num = int(input())
#     num2 = num
#     ans = [0] * 100001  # 소인수분해 해야하는 수의 범위가 100000 이하임
#     for j in range(2, num2 + 1):  # 2부터 num까지
#         while num2 % j == 0:  # 만일 num의 인수라면
#             num2 = num2 // j 
#             ans[j] += 1  # 리스트에 갯수 세기
#     for k in range(2, num + 1): 
#         if ans[k]:  # 갯수 센 리스트에서 정답 출력하기
#             print(k,'',ans[k])

import sys
input = sys.stdin.readline

n = int(input())  # 소인수분해 해야하는 수 개수

for _ in range(n) :
  num = int(input())  # 소인수분해 해야하는 수
  factor = 2  # 인수

  data = {}  # 인수의 개수 저장할 딕셔너리
  for i in range(num + 1) :
    data[i] = 0  # 모든 인수의 개수 0으로 초기화

  while num > 1 :
    if num % factor != 0 :  # 인수가 아니면
      factor += 1  # 1 더하기
    else :  # 맞으면
      num /= factor  # 소인수분해 해야하는 수인 num을 인수로 나누고
      data[factor] += 1  # 딕셔너리에 인수 개수 저장하기

  for i in data.items() :  # 딕셔너리의 인수 개수 형식대로 출력하기
    if i[1] != 0 :
      print(i[0], i[1])