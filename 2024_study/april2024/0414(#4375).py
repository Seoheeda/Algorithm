import sys
input = sys.stdin.readline

while True:  # 입력 개수 무한대 가능. 빈 입력 시 입력 종료
    try:
        n = int(input()) 
    except:
        break

    num = 1
    cnt = 1
    while True:
        if num % n != 0:
            num = num * 10 + 1
            cnt += 1
        else:
            break
    print(cnt)
