## 유효한 괄호
## 1) 스택 일치 여부 판별

def isValid(s):
    stack = []
    table = {
        ')' : '(',
        '}' : '{',
        ']' : '['
    }

    for char in s:
        if char not in table:
            stack.append(char)
        elif not stack or table[char] != stack.pop():
            return False
    return len(stack) == 0

print(isValid("(){}[]"))


## 중복 문자 제거
## 1) 재귀를 이용한 분리

def removeDuplicateLetters(s):
    for char in sorted(set(s)):
        suffix = s[s.index(char):]
        if set(s) == set(suffix):
            return char + removeDuplicateLetters(suffix.replace(char, ''))
    return ''

print(removeDuplicateLetters("cbacdcbc"))


## 2) 스택을 이용한 문자 제거

import collections

def removeDuplicateLetters(s):
    counter, seen, stack = collections.Counter(s), set(), []

    for char in s:
        counter[char] -= 1
        if char in seen:
            continue

        while stack and char < stack[-1] and counter[stack[-1]] > 0:
            seen.remove(stack.pop())
        stack.append(char)
        seen.add(char)
    
    return ''.join(stack)

print(removeDuplicateLetters("cbacdcbc"))


## 일일 온도
## 1) 스택 값 비교

def dailyTemperatures(T):
    answer = [0] * len(T)
    stack = []
    for i, cur in enumerate(T):
        while stack and cur > T[stack[-1]]:
            last = stack.pop()
            answer[last] = i - last
        stack.append(i)

    return answer

T = [73, 74, 75, 71, 69, 72, 76, 73]
print(dailyTemperatures(T))


## 큐 이용한 스택 구현
## 1) push()할 때 큐를 이용해 재정렬

class MyStack:
    def __init__(self):
        self.q = collections.deque()

    def push(self, x):
        self.q.append(x)
        for _ in range(len(self.q) - 1):
            self.q.append(self.q.popleft())
    
    def pop(self):
        return self.q.popleft()
    
    def top(self):
        return self.q[0]
    
    def empty(self):
        return len(self.q) == 0
    

## 스택을 이용한 큐 구현
## 1) 스택 2개 사용    


class MyQueue:
    def __init__(self):
        self.input = []
        self.output = []

    def push(self, x):
        self.input.append(x)
    
    def pop(self):
        self.peek()
        return self.output.pop()
    
    def peek(self):
        if not self.output:
            while self.input:
                self.output.append(self.input.pop())
        return self.output[-1]
    
    def empty(self):
        return self.input == [] and self.output == []
    

## 원형 큐 디자인
## 1) 배열을 이용한 풀이

class MyCircularQueue:
    def __init__(self, k):
        self.q = [None] * k
        self.maxlen = k
        self.p1 = 0
        self.p2 = 0

    # enQueue() : rear 포인터 이동
    def enQueue(self, value):
        if self.q[self.p2] is None:
            self.q[self.p2] = Value 
            self.p2 = (self.p2 + 1) % self.maxlen
            return True
        else:
            return False
    
    # deQueue() : front 포인터 이동
    def deQueue(self):
        if self.q[self.p1] is None:
            return False
        else:
            self.q[self.p1] = None
            self.p1 = (self.p1 + 1) % self.maxlen
            return True
        
    def Front(self):
        return -1 if self.q[self.p1] is None else self.q[self.p1]
    
    def Rear(self):
        return -1 if self.q[self.p2 - 1] is None else self.q[self.p2 - 1]
    
    def isEmpty(self):
        return self.p1 == self.p2 and self.q[self.p1] is None
    
    def isFull(self):
        return self.p1 == self.p2 and self.q[self.p1] is not None
    
    

