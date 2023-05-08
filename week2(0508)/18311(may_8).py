n, k = list(map(int, input().split()))
course = list(map(int, input().split()))

def running(k, course):
    run = 0
    place = 0
    while run <= k and place <= (n - 1):
        place += 1
        run += course[place - 1]

    if run >= k:
        return place
    else:
        place = n
        while run <= k and place >= 0:
            place -= 1
            run += course[place]
        return place + 1

print(running(k, course))


    