import java.util.*;

class Solution {
    
    static int[][] arr;
    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    // 범위 내부인지
    static boolean isIn(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }
    
    static int bfs() {
        
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, 0, 1});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];
            int cnt = temp[2];
            
            // 도착 지점이면 리턴
            if (x == n - 1 && y == m - 1) {
                return cnt;
            }
            
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if (isIn(nx, ny) && !visited[nx][ny] && arr[nx][ny] == 1) {
                    queue.add(new int[] {nx, ny, cnt + 1});
                    visited[nx][ny] = true;
                }
            }
        }
        
        return -1;
    }
    
    public int solution(int[][] maps) {
        
        // 행열 크기
        n = maps.length;
        m = maps[0].length;
        
        arr = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = maps[i][j];
            }
        }
             
        int answer = bfs();
        return answer;
    }
}