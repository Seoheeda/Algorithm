n = int(input())
scores = input()
scores_list = list(map(int, scores.split(" ")))
max_score = max(scores_list)

new_scores = []
sum = 0
for score in scores_list:
    new_score = score/max_score * 100
    new_scores.append(new_score)
    sum += new_score

average = sum / n

print(average)





