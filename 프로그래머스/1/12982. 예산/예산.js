function solution(d, budget) {
    
    d.sort((a, b) => a - b);
    
    var answer = 0;
    var sum = 0;
    
    for (var i = 0; i < d.length; i++) {
        sum += d[i];
        if (sum > budget) {
            return answer;
        }
        answer++;
    }
    
    
    return answer;
}