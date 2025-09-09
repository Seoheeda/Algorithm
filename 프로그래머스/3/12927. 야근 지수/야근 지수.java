import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int size = works.length;
        
        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }
             
        for (int i = 0; i < n; i++) {
            int num = pq.poll();
            if (num == 0) {
                pq.add(0);
                continue;
            }
            pq.add(num - 1);
        }
        
        long answer = 0;
                
        for (int i = 0; i < size; i++) {
            answer += Math.pow(pq.poll(), 2);
        }
        
        return answer;
    }
}