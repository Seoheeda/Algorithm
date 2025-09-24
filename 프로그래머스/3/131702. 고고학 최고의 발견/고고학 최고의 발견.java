import java.util.*;

class Solution {
    
    static int N, answer;
    static int[][] arr;
    
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    // 범위 내인지 여부
    static boolean isIn(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N) {
            return true;
        }
        return false;
    }
    
    static void dfs(int cnt, int y) {
        
        // 현재까지 돌린 횟수가 정답보다 크거나 같으면 종료
        if (cnt >= answer) {
            return;
        }
        
        // 맨 윗줄 다 정해졌으면
        if (y == N) {
            
            // 배열 복사
            int[][] tempArr = new int[N][N];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tempArr[i][j] = arr[i][j];
                }
            }
            
            // 두번째줄부터
            for (int i = 1; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 바로 위 칸 0 만들려면 돌려야 하는 횟수
                    int t = (4 - tempArr[i - 1][j]) % 4;
                    // 해당 칸에 적용
                    tempArr[i][j] = (tempArr[i][j] + t) % 4;
                    
                    // 상하좌우 칸에 적용
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        
                        if (isIn(nx, ny)) {
                            tempArr[nx][ny] = (tempArr[nx][ny] + t) % 4;
                        }
                    }
                    // 돌린 횟수 증가
                    cnt += t;
                }
            }
            
            // 만일 전부 0이라면 정답 갱신
            if (check(tempArr)) {
                answer = Integer.min(answer, cnt);
            }
            
            return;
        }
        
        // 해당 지점 0~3번 돌리기
        for (int i = 0; i < 4; i++) {
            // 돌리기
            for (int j = 0; j < i; j++) {
                turn(0, y);
            }
            
            // 다음 지점
            int nx = 0;
            int ny = y + 1;
            
            // 다음 지점 탐색
            dfs(cnt + i, ny);
            
            // 원상복귀
            for (int j = 0; j < i; j++) {
                reverse(0, y);
            }
        }
    }
    
    // 시계방향으로 돌리기
    static void turn(int x, int y) {
        arr[x][y] = (arr[x][y] + 1) % 4;
        
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (isIn(nx, ny)) {
                arr[nx][ny] = (arr[nx][ny] + 1) % 4;
            }
        }
    }
    
    // 시계 반대 방향으로 돌려놓기
    static void reverse(int x, int y) {
        arr[x][y] = (arr[x][y] + 3) % 4;
        
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (isIn(nx, ny)) {
                arr[nx][ny] = (arr[nx][ny] + 3) % 4;
            }
        }
    }
    
    // 모두 12시 방향인지 체크하기
    static boolean check(int[][] checkArr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (checkArr[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public int solution(int[][] clockHands) {
        
        // 정답 초기화
        answer = Integer.MAX_VALUE;
        
        // 배열 전역화
        arr = clockHands;
        // 배열 크기
        N = clockHands.length;
            
        dfs(0, 0);
        
        return answer;
    }
}