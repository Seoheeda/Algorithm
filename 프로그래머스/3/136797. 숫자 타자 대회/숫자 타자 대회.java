import java.util.*;

class Solution {   
    
    static String nums;
    static int len;
    static int[][][] dp;
    static  int[][] dist = {
        { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
        { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
        { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
        { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
        { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
        { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
        { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
        { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
        { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
        { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };
    
    static int dfs(int index, int L, int R) {
        
        // 다 눌렀으면
        if (index == len) {
            return 0;
        }
        
        // 이미 계산 됐으면
        if (dp[index][L][R] != -1) {
            return dp[index][L][R];
        }
        
        // 누를 숫자
        int num = nums.charAt(index) - '0';
        int result = Integer.MAX_VALUE;
        
        // 왼손으로 누르기
        if (num != R)
            result = Math.min(dfs(index + 1, num, R) + dist[L][num], result);
        
        // 오른손으로 누르기
        if (num != L)
            result = Math.min(dfs(index + 1, L, num) + dist[R][num], result);

        // 최소 비용 저장
        return dp[index][L][R] = result;
    }
    
    public int solution(String numbers) {
        
        nums = numbers;

        len = nums.length();
        dp = new int[len + 1][10][10];
        
        // -1이면 아직 계산 안됨
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        
        int answer = 0;
       
        // 왼손 4, 오른손 6,
        answer = dfs(0, 4, 6);
        
        return answer;
    }
}
