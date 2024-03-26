# words = []
# dict_w = {}

# while True:
#     try:
#         word = input()
#         if word == "":
#             break
#         words.append(word)
#     except EOFError:
#         break

# for word in words:
#     sorted_w = "".join(sorted(word))
#     if sorted_w in dict_w.keys():
#         dict_w[sorted_w].append(word)
#     else:
#         dict_w[sorted_w] = [word]

# for key in list(dict_w.keys()):
#     dict_w[key, len(dict_w[key])] = dict_w[key]
#     del dict_w[key]

# for i in dict_w.keys():
#     dict_w[i].sort()

# new_dict = sorted(dict_w.items(), key=lambda x:(-x[0][1], x[1][0]))


# if len(new_dict) <= 5:
#     leng = len(new_dict)
# else:
#     leng = 5

# for i in range(leng):
#     print("Group of size {0}: {1} .".format(new_dict[i][0][1], " ".join(sorted(list(set(new_dict[i][1]))))))




# words = []
# dict_w = {}

# while True:
#     try:
#         word = input()
#         if word == "":
#             break
#         words.append(word)
#     except EOFError:
#         break

# words.sort()

# for word in words:
#     sorted_w = "".join(sorted(word))
#     if sorted_w in dict_w.keys():
#         dict_w[sorted_w].append(word)
#     else:
#         dict_w[sorted_w] = [word]

# for key in list(dict_w.keys()):
#     dict_w[key, len(dict_w[key])] = dict_w[key]
#     del dict_w[key]

# new_dict = sorted(dict_w.items(), key=lambda x:(-x[0][1], x[1][0]))


# if len(new_dict) <= 5:
#     leng = len(new_dict)
# else:
#     leng = 5

# for i in range(leng):
#     print("Group of size {0}: {1} .".format(new_dict[i][0][1], " ".join(sorted(list(set(new_dict[i][1]))))))





def ana_group(words):
    words.sort()
    dict_w = {}

    for word in words:
        sorted_w = "".join(sorted(word))
        if sorted_w in dict_w.keys():
            dict_w[sorted_w].append(word)
        else:
            dict_w[sorted_w] = [word]

    for key in list(dict_w.keys()):
        dict_w[key, len(dict_w[key])] = dict_w[key]
        del dict_w[key]

    new_dict = sorted(dict_w.items(), key=lambda x:(-x[0][1], x[1][0]))


    i = 0
    while i < len(new_dict) and i <= 4:
        print("Group of size {0}: {1} .".format(new_dict[i][0][1], " ".join(sorted(list(set(new_dict[i][1]))))))
        i += 1

words = []

while True:
    try:
        word = input()
        if word == "":
            break
        words.append(word)
    except EOFError:
        break

ana_group(words)