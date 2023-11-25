import sys
input = sys.stdin.readline

T = int(input())

for _ in range(T):
    N = int(input())
    prices = list(map(int, input().split()))
    max_price = prices[-1]
    total = 0
    for i in range(len(prices) - 1, -1, -1):
        if prices[i] < max_price:
            total += (max_price - prices[i])
        else:
            max_price = prices[i]
    print(total)


