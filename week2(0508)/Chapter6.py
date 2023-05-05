# 01 유효한 팰린드롬 - 리스트로 변환
def isPalindrome(s:str):
    strs = []
    for char in s:
        if char.isalnum():
            strs.append(char.lower())

    while len(strs) > 1:
        if strs.pop(0) != strs.pop():
            return False
    return True

print(isPalindrome("A man, a plan, a canal: Panama"))
print(isPalindrome("hello!"))


# 02 유효한 팰린드롬 - 데크 자료형 이용하기
import collections

def isPalindrome(s:str):
    strs: Deque = collections.deque()
    for char in s:
        if char.isalnum():
            strs.append(char.lower())

    while len(strs) > 1:
        if strs.popleft() != strs.pop():
            return False
    return True

print(isPalindrome("A man, a plan, a canal: Panama"))
print(isPalindrome("hello!"))


# 03 유효한 팰린드롬 - 슬라이싱 사용
import re

def isPalindrome(s:str):
    s = s.lower()
    s = re.sub('[^a-z0-9]', '', s)
    return s == s[::-1]

print(isPalindrome("A man, a plan, a canal: Panama"))
print(isPalindrome("hello!"))


# 01 문자열 뒤집기 - 투 포인터를 이용한 스왑
from typing import List

def reverseString(s):
    left, right = 0, len(s) - 1
    while left < right:
        s[left], s[right] = s[right], s[left]
        left += 1
        right -= 1
    return s

print(reverseString(["h", "e", "l", "l", "o"]))

# 02 문자열 뒤집기 - 파이썬다운 방식
def reverseString(s):
    s.reverse()
    return s

print(reverseString(["h", "e", "l", "l", "o"]))


# 01 로그 파일 재정렬
def reorderLogFiles(logs):
    letters, digits = [], []
    for log in logs:
        if log.split()[1].isdigit():
            digits.append(log)
        else:
            letters.append(log)
    
    letters.sort(key=lambda x: (x.split()[1:], x.split()[0]))
    return letters + digits

logs = ["dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"]
print(reorderLogFiles(logs))


# 01 가장 흔한 단어
def mostCommonWord(paragraph, banned):
    words = [word for word in re.sub('[\W]', ' ', paragraph).lower().split() if word not in banned]
    
    counts=collections.Counter(words)

    return counts.most_common(1)[0][0]

paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]

print(mostCommonWord(paragraph, banned))


# 01 그룹 애너그램
def groupAnagrams(strs):
    anagrams = collections.defaultdict(list)

    for word in strs:
        anagrams[''.join(sorted(word))].append(word)

    return list(anagrams.values())

strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
print(groupAnagrams(strs))


# 01 가장 긴 팰린드롬 부분 문자열
def longestPalindrome(s):
    def expand(left, right):
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1
        return s[left + 1 : right]
    
    if len(s) < 2 or s == s[::-1]:
        return s
    result = ''

    for i in range(len(s) - 1):
        result = max(result, expand(i, i + 1), expand(i, i + 2), key=len)

    return result

print(longestPalindrome("babad"))
print(longestPalindrome("cbbd"))