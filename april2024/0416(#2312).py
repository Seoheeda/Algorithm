import sys
input = sys.stdin.readline

n = int(input())  # 소인수분해 해야하는 수

for i in range(n):
    num = int(input())
    num2 = num
    ans = [0] * 100000  # 소인수분해 해야하는 수의 범위가 100000 이하임
    for j in range(2, num2 + 1):  # 2부터 num까지
        while num2 % j == 0:  # 만일 num의 인수라면
            num2 = num2 // j 
            ans[j] += 1  # 리스트에 갯수 세기
    for k in range(2, num + 1): 
        if ans[k]:  # 갯수 센 리스트에서 정답 출력하기
            print(k,'',ans[k])