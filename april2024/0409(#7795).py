import sys 
input = sys.stdin.readline 

t = int(input()) # 테스트케이스 개수 

for _ in range(t): 
    n, m = map(int, input().split()) # a의 수, b의 수 
    a = list(map(int, input().split())) # a의 수 
    b = list(map(int, input().split())) # b의 수 
    a.sort() 
    b.sort() 
    
    start = 0 
    cnt = 0 
    
    for i in range(n):
        while start < m: 
            if a[i] > b[start]: 
                start += 1 
            else: 
                cnt += start 
                break 
        else: 
            cnt += start 

print(cnt)