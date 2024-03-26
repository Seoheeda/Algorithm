import sys
input = sys.stdin.readline
n, k = map(int, input().split())  # 1 2 3 4 5 6 7

def josephus(n, k):
    nums = [i for i in range(1, n + 1)]
    place = 0
    result = []

    for i in range(n):
        place = (place + k - 1) % len(nums)
        result.append(str(nums.pop(place)))
        print(place)

    print("<", end="")
    print(", ".join(result), end="")
    print(">")

josephus(n, k)

    


