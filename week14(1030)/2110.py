import sys
input = sys.stdin.readline

n, c = map(int,input().split())
houses = [int(input()) for i in range(n)]
houses.sort()  # [1, 2, 4, 8, 9]
start = 1  # 집 사이의 최소 거리
end = houses[n-1] - houses[0]  # 집 사이의 최대 거리
result = 0

if c == 2:
    print(houses[n-1] - houses[0])
else:
    while start <= end:
        mid = (start + end) //2
        count = 1
        cur = houses[0]
        for house in houses:  # 공유기 설치 몇개 가능한가
            if house - cur >= mid:
                count += 1
                cur = house
        if count >= c:  # 설치 가능한 공유기 수가 c보다 크면 거리를 늘려야 하고
            result = mid
            start = mid + 1
        elif count < c:  # 설치 가능한 공유기 수가 c보다 작으면 거리를 좁혀야 함
            end = mid - 1
    print(result)