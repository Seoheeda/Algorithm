class Solution {
    public int solution(int sticker[]) {
        
        // 스티커 개수
        int n = sticker.length;
        
        if (n == 1) {
            return sticker[0];
        } else if (n == 2) {
            return Integer.max(sticker[0], sticker[1]);
        }
 
        // 0번째 인덱스를 고르면서 시작한 경우
        int[] dp1 = new int[n - 1];
        dp1[0] = sticker[0];
        dp1[1] = sticker[0];
 
        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Integer.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }
 
        // 1번째 인덱스를 고르면서 시작한 경우
        int[] dp2 = new int[n];
        dp2[0] = 0;
        dp2[1] = sticker[1];
 
        for (int i = 2; i < n; i++) {
            dp2[i] = Integer.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }
 
        return Integer.max(dp1[n - 2], dp2[n - 1]);
    }
}