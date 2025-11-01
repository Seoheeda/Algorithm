function solution(elements) {
    var answer = 0;
    const set = new Set();
    
    for (var i = 0; i < elements.length; i++) {
        var sum = 0;
        for (var j = 0; j < elements.length; j++) {
            if (i + j > elements.length - 1) {
                var num = (i + j) % (elements.length);
                sum += elements[num];
                set.add(sum);
            } else {
                sum += elements[i + j];
                set.add(sum);
            }
        }
    }
    return set.size;
}