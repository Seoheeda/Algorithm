import java.util.*;

class Solution {
    
    static int N, M;
    static char[][] arr;
    static boolean[][] visited;
    static boolean[][] isEmpty;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    // 범위 내부인지 여부
    static boolean isIn(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < M) {
            return true;
        }
        return false;
    }
    
    // 지게차
    static void check(int x, int y, String request) {
        
        // 비어있지 않으면 탐색 종료
        if (!isEmpty[x][y]) {
            if (arr[x][y] == request.charAt(0)) {
                isEmpty[x][y] = true;
            }
            visited[x][y] = true;
            return;
        }
        
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.add(new int[] {x, y});
        visited[x][y] = true;
        
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            
            int cx = temp[0];
            int cy = temp[1];
            
             for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                 // 비어있으면 큐에 넣기
                if (isIn(nx, ny) && !visited[nx][ny] && isEmpty[nx][ny]) {
                    queue.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                // 비어있지 않으면 큐에 넣지 않음
                } else if (isIn(nx, ny) && !visited[nx][ny] && !isEmpty[nx][ny]) {
                    // 출고 요청과 같으면 비우기
                    if (arr[nx][ny] == request.charAt(0)) {
                        isEmpty[nx][ny] = true;
                    }
                    visited[nx][ny] = true;
                }
            }
        }    
        return;
    }
    
    public int solution(String[] storage, String[] requests) {
        
        N = storage.length;
        M = storage[0].length();
        
        // 물류창고
        arr = new char[N][M];
        
        // 물류창고 비었는지 여부
        isEmpty = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = storage[i].charAt(j);
            }
        }
        
        for (int i = 0; i < requests.length; i++) {
            String request = requests[i];
            
            // 지게차
            if (request.length() == 1) {
                
                visited = new boolean[N][M];
                
                // 세로 테두리 돌기
                for (int a = 0; a < N; a++) {
                    if (!visited[a][0]) {
                        check(a, 0, request);
                    }
                    
                    if (!visited[a][M - 1]) {
                        check(a, M - 1, request);
                    }
                }
                
                // 가로 테두리 돌기
                for (int a = 1; a < M - 1; a++) {
                    System.out.println(0 + " " + a);
                    if (!visited[0][a]) {
                        check(0, a, request);
                    }
                    
                    if (!visited[N - 1][a]) {
                        check(N - 1, a, request);
                    }
                }
                
            // 크레인
            } else {
                for (int a = 0; a < N; a++) {
                    for (int b = 0; b < M; b++) {
                        if (arr[a][b] == request.charAt(0)) {
                            isEmpty[a][b] = true;
                        }
                    }
                }
            }
        }
        
        int answer = 0;
        
        // 개수 세기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isEmpty[i][j]) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
