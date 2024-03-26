### < 보석과 돌 >
### 1)

def numJewelInStones(J, S):
    freqs = {}
    count = 0

    for char in S:
        if char not in freqs:
            freqs[char] = 1
        else:
            freqs[char] += 1

    for char in J:
        if char in freqs:
            count += freqs[char]

    return count

print(numJewelInStones("aA", "aAAbbbb"))


### 2)
import collections

def numJewelInStones(J, S):
    freqs = collections.defaultdict(int)
    count = 0

    for char in S:
        freqs[char] += 1

    for char in J:
        count += freqs[char]

    return count

print(numJewelInStones("aA", "aAAbbbb"))


### 3)
def numJewelInStones(J, S):
    freqs = collections.Counter(S)
    count = 0

    for char in J:
        count += freqs[char]

    return count

print(numJewelInStones("aA", "aAAbbbb"))


### 4)
def numJewelInStones(J, S):
    return sum(s in J for s in S)

print(numJewelInStones("aA", "aAAbbbb"))


### < 중복 문자 없는 가장 긴 부분 문자열 >
### 1)

def lengthOfLongestSubstring(s):
    used = {}
    max_length = start = 0
    for index, char in enumerate(s):
        if char in used and start <= used[char]:
            start = used[char] + 1
        else:
            max_length = max(max_length, index - start + 1)
        
        used[char] = index
    
    return max_length

print(lengthOfLongestSubstring("abcabcbb"))



### < 상위 K 빈도 요소 >
### 1) Counter를 이용한 음수 순 추출

def topKFrequent(nums, k):
    freqs = collections.Counter(nums)
    freqs_heap = []

    for f in freqs:
        heapq.heappush(freqs_heap, (-freqs[f], f))

    topk = list()

    for _ in range(k):
        topk.append(heapq.heappop(freqs_heap)[1])

    return topk

print(topKFrequent([1, 1, 1, 2, 2, 3], 2))


### 2) 파이썬다운 방식

def topKFrequent(nums, k):
    return list(zip(*collections.Counter(nums).most_common(k)))[0]

print(topKFrequent([1, 1, 1, 2, 2, 3], 2))
