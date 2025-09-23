import java.util.*;

class Solution {
    
    static int N, M, answer;
    static boolean[] arrRow, arrCol;
    static int[][] initialArr, finishArr;
    
    // 뒤집을 행 경우의 수
    static void dfsRow(int depth) {
        
        if (depth == N) {
            dfsCol(0);
            return;
        }
        
        arrRow[depth] = true;
        dfsRow(depth + 1);
        arrRow[depth] = false;
        dfsRow(depth + 1);
        
    }
    
    // 뒤집을 열 경우의 수
    static void dfsCol(int depth) {
        
        if (depth == M) {
            // 도달 가능이면
            if(calc()) {
                // 뒤집은 횟수 계산
                int tempAns = 0;
                
                for (int i = 0; i < N; i++) {
                    if (arrRow[i]) {
                        tempAns++;
                    }
                }
                
                for (int i = 0; i < M; i++) {
                    if (arrCol[i]) {
                        tempAns++;
                    }
                }
                
                // 정답 갱신
                answer = Integer.min(answer, tempAns);
            }
            
            return;
        }
        
        arrCol[depth] = true;
        dfsCol(depth + 1);
        arrCol[depth] = false;
        dfsCol(depth + 1);
        
    }
    
    // 조합 따라 뒤집기 시행
    static boolean calc() {
        
        // 초기 배열 복사
        int[][] startArr = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                startArr[i][j] = initialArr[i][j];
            }
        }
        
        // 행 뒤집기
        for (int i = 0; i < N; i++) {
            if (arrRow[i]) {
                for (int j = 0; j < M; j++) {
                    if (startArr[i][j] == 0) {
                        startArr[i][j] = 1;
                    } else {
                        startArr[i][j] = 0;
                    }
                }
            }
        }
        
        // 열 뒤집기
        for (int i = 0; i < M; i++) {
            if (arrCol[i]) {
                for (int j = 0; j < N; j++) {
                    if (startArr[j][i] == 0) {
                        startArr[j][i] = 1;
                    } else {
                        startArr[j][i] = 0;
                    }
                }
            }
        }
           
        // 도달 여부 확인 및 리턴
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (startArr[i][j] != finishArr[i][j]) {
                    return false;
                }
            }
        }
        
        return true;        
    }
    
    public int solution(int[][] beginning, int[][] target) {
        
        // 초기 배열 
        initialArr = beginning;
        // 목표 배열
        finishArr = target;
        
        // 배열 크기
        N = beginning.length;
        M = beginning[0].length;
        // 뒤집을 행 정보
        arrRow = new boolean[N];
        // 뒤집을 열 정보
        arrCol = new boolean[M];
        
        // 정답 초기회 
        answer = Integer.MAX_VALUE;
        
        dfsRow(0);
        
        // 답이 없으면 -1
        if (answer == Integer.MAX_VALUE) {
            return -1;
        }
        
        return answer;
    }
}