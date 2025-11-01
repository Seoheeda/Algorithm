function solution(s)
{
    var answer = -1;

    const stack = [];
    
    const splitted = s.split("");
    
    for (var i = 0; i < s.length; i++) {
        var now = s[i];
        var prev = stack[stack.length - 1];
        
        if (now == prev) {
            stack.pop();
        } else {
            stack.push(now);
        }
    }
    
    if (stack.length == 0) {
        return 1;
    } else {
        return 0;
    }

    return answer;
}