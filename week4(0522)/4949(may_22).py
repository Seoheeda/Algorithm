def is_bracket(sent):
    for char in sent:
            if char in start:
                stack.append(char)
            elif char == ")":
                if stack and stack[-1] == "(":
                    stack.pop()
                else:
                    return "no"
            elif char == "]":
                if stack and stack[-1] == "[":
                    stack.pop()      
                else:
                    return "no"
    if not stack:
        return "yes"
    else:
        return "no"
        
        
while 1:
    sent = input()
    stack = []

    start = ["(", "["]
    end = [")", "]"]

    if sent == ".":
        break

    print(is_bracket(sent))
