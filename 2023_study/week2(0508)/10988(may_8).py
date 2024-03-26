word = input()

def palin_check(word):
    if word == word[::-1]:
        return 1 
    else:
        return 0

print(palin_check(word))

