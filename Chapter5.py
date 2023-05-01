a = [1, 2, 3, 4, '안녕', True]

try:
    print(a[9])
except IndexError:
    print("존재하지 않는 인덱스")


b = {'key1':'value1', 'key2':'value2', 'key3':'value3'}

try:
    print(b['key4'])
except KeyError:
    print("존재하지 않는 키")

if 'key4' in b:
    print("존재하는 키")
else:
    print("존재하지 않는 키")


for k, v in b.items():
    print(k, v)

from collections import defaultdict

c = defaultdict(int)
c['A'] = 5
c['B'] = 4
print(c)

c['C'] += 1
print(c)

from collections import Counter

d = [1, 2, 3, 4, 5, 5, 5, 6, 6]
print(Counter(d))
