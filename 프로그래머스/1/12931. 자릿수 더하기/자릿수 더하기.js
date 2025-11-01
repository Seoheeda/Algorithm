function solution(n)
{
    var answer = 0;

    const stringN = n.toString();
    const splitted = stringN.split("");
    
    console.log(splitted);
    
    const len = splitted.length;
    
    for (var i = 0; i < len; i++) {
        answer += Number(splitted[i])
    }

    return answer;
}