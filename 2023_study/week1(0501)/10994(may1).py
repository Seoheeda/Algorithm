n = int(input())

if n == 1:
    print("*")
else:
    for i in range(0, 2 * (n - 1)):
        if i % 2 == 0:
            a = i // 2 
            print("* " * (a - 1 + 1) + "*" * (4 * (n - a - 1) + 1)  + (a - 1 + 1) * " *")
        else:
            a = (i + 1) // 2
            print("* " * (a) + " " * (4 * (n - a) - 3) + (a) * " *")
    print("* " * (2 * n - 1))
    for i in range(2 * (n - 1) - 1, -1, -1):
        if i % 2 == 0:
            a = i // 2 
            print("* " * (a - 1 + 1) + "*" * (4 * (n - a - 1) + 1)  + (a - 1 + 1) * " *")
        else:
            a = (i + 1) // 2
            print("* " * (a) + " " * (4 * (n - a) - 3) + (a) * " *")


# n = int(input())

# def stars(i, n):
#     print("* " * (i - 1) + "*" * (4 * (n - i) + 1)  + (i - 1) * " *")

# def blanks(i, n):
#     print("* " * i + " " * (4 * (n - i) - 3) + i * " *")

# if n == 1:
#     print("*")
# else:
#     for i in range(1, n):
#         stars(i, n)
#         blanks(i, n)
#     print("* " * (2 * n - 1))
#     for i in range(n-1, 0, -1):
#         blanks(i, n)
#         stars(i, n)


