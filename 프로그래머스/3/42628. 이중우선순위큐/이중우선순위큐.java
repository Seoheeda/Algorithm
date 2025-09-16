import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        
        StringTokenizer st;
        List<Integer> list = new ArrayList<>();
        
        int N = operations.length;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(operations[i]);
            String order = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            
            if (order.equals("I")) {
                list.add(num);
            } else if (order.equals("D") && list.size() >= 1) {
                
                Collections.sort(list);
                
                if (num == 1) {
                    list.remove(list.size() - 1);
                } else {
                    list.remove(0);
                }
            }
        }
        
        if (list.size() == 0) {
            return new int[] {0, 0};
        }       
        
        int[] answer = new int[2];
        
        Collections.sort(list);
        answer[0] = list.get(list.size() - 1);
        answer[1] = list.get(0);
        
        return answer;
    }
}