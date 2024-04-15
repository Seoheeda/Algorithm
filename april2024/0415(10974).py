import sys
import itertools
input = sys.stdin.readline

n = int(input())
arr = []

for i in range(1, n + 1):
    arr.append(i)

comb = itertools.permutations(arr, n)
comb_list = list(comb)

for i in comb_list:
    one_comb = str(i)
    one_comb = one_comb.replace("(", "").replace(",", "").replace(")", "")
    print(one_comb)
