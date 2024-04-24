import sys
input = sys.stdin.readline

a, b = map(int, input().split())
c, d = map(int, input().split())

down = b * d
up = (a * d) + (c * b)

def gcd(x, y):  # 최대공약수 구하는 함수
    while y > 0:
        x, y = y, x % y
    return x

gcdnum = gcd(up, down)

print(int(up / gcdnum), int(down / gcdnum))