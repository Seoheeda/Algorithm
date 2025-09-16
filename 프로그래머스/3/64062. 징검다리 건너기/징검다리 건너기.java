class Solution {
    
    // num 명 이동 가능한지
    static boolean canGo(int[] stones, int num, int k) {
        int blank = 0;
        
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] - num < 0) {
                blank++;
                if (blank >= k) {
                    return false;
                }
            } else {
                blank = 0;
            }
        }
        
        return true;
    }
    
    public int solution(int[] stones, int k) {
        
        int left = 0;
        int right = 0;
        
        int answer = 0;
        
        for (int stone: stones) {
            right = Integer.max(right, stone);
        }
                
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (canGo(stones, mid, k)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
}