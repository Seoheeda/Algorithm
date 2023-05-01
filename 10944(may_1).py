# n, m = list(input().split())
# m = int(m)

# answer = ""
# for _ in range(0, int(n)):
#     answer += n

# if len(answer) >= m:
#     print(answer[:m])
# else:
#     print(answer)


n, m = list(input().split())
m = int(m)

answer = n * int(n)
print(answer[:m])


