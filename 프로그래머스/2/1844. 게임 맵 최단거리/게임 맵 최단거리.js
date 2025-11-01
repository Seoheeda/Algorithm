function solution(maps) {
    var answer = 0;
    
    const dx = [0, 0, -1, 1];
    const dy = [-1, 1, 0, 0];
    
    const n = maps.length;
    const m = maps[0].length;
    
    function bfs() {
        const queue = [];
        queue.push([0, 0, 1]);
        
        const visited = Array.from({length:n}, () => Array(m).fill(false));
        visited[0][0] = true;
        
        while (queue.length > 0) {
            var temp = queue.shift();
            var x = temp[0];
            var y = temp[1];
            var cnt = temp[2];
            
            if (x === n - 1 & y === m - 1) {
                return cnt;
            }
            
            for (var d = 0; d < 4; d++) {
                var nx = x + dx[d];
                var ny = y + dy[d];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && visited[nx][ny] === false && maps[nx][ny] === 1) {
                    queue.push([nx, ny, cnt + 1]);
                    visited[nx][ny] = true;
                }
            }
        }
        return -1;
    }
    
    
    
    return bfs();
}