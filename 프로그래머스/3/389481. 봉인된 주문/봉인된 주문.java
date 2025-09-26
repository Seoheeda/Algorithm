import java.util.*;

class Solution {
    
    // 문자열의 순서 구하기
    static long stringOrder(String str) {
        if (str.length() == 1) {
            return str.charAt(0) - '0' - 48;
        } else {
            long order = 0;
            for (int i = 0; i < str.length() - 1; i++) {
                int ord = str.charAt(i) - '0' - 48;
                
                long add = (long)Math.pow(26, str.length() - 1 - i) * ord; 
                order += add;
            }
            
            order += (str.charAt(str.length() - 1) - '0' - 48);
            
            return order;
        }
    }
    
    // 순서에 해당하는 문자열 구하기
    static String orderString(long order) {
        String str = "";
        
        while (order > 0) {
            order--;                       
            int r = (int)(order % 26);     
            str += (char)('a' + r); 
            order /= 26;
        }
                
        String returnStr = "";
        
        for (int i = str.length() - 1; i >= 0; i--) {
            returnStr += str.charAt(i);
        }
        return returnStr;
    }
    
    public String solution(long n, String[] bans) {
        
        // 삭제된 주문 순서로 변경
        long[] orders = new long[bans.length];
        
        for (int i = 0; i < orders.length; i++) {
            orders[i] = stringOrder(bans[i]);
        }
        
        // 삭제된 주문 순서 오름차순으로 변경
        Arrays.sort(orders);      
                            
        // 삭제에 따른 n 값 미루기
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] <= n) {
                n++;
            }
        }
        
        return orderString(n);
    }
}