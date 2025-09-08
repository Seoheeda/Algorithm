import java.util.*;

class Solution {
    
    static int N, M;
    
    // 범위 내인가
    static boolean isIn(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N) {
            return true;
        }
        return false;
    }
    
    // 키 넣어보기
    static boolean keyTry(int x, int y, int[][] key, int[][] lock) {
        
        // 빈틈없이 키로 채워졌는지 확인
        int[][] test = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                test[i][j] = lock[i][j];
            }
        }
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                int cx = x + i;
                int cy = y + j;
                
                if (isIn(cx, cy)) {
                    // 키가 있는데 자물쇠 차있다면 실패
                    if (key[i][j] == 1 && lock[cx][cy] == 1) {
                        return false;
                    }
                    // 딱 맞는다면 키 채우기
                    if (key[i][j] == 1 && lock[cx][cy] == 0) {
                        test[cx][cy] = 1;
                    }
                }
            }
        }
        
        // 빈틈 있으면 실패
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (test[i][j] == 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    
    static boolean keyCheck(int x, int y, int[][] key, int[][] lock) {
    
        // 기본 상태
        if (keyTry(x, y, key, lock)) {
            return true;
        }
        
        int[][] secKey = new int[M][M];
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                secKey[i][j] = key[j][M - 1 - i];
            }
        }
        
        // 90도 회전
        if (keyTry(x, y,secKey, lock)) {
            return true;
        }
        
        int[][] thirdKey = new int[M][M];
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                thirdKey[i][j] = secKey[j][M - 1 - i];
            }
        }
        
        // 180도 회전
        if (keyTry(x, y, thirdKey, lock)) {
            return true;
        }
        
        int[][] fourKey = new int[M][M];
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                fourKey[i][j] = thirdKey[j][M - 1 - i];
            }
        }
        
        // 270도 회전
        if (keyTry(x, y, fourKey, lock)) {
            return true;
        }
        
        return false;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        
        M = key.length;
        N = lock.length;
        
        // 칸별로 실험
        for (int i = -M + 1; i < N; i++) {
            for (int j = -M + 1; j < N; j++) {
                if (keyCheck(i, j, key, lock)) {
                    return true;
                }
            }
        }
        return false;
    }
}