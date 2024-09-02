import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {

     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
     int T = Integer.parseInt(br.readLine());
     
     for (int t = 1; t <= T; t++) {
    	 
    	 int N = Integer.parseInt(br.readLine());
    	 
    	 boolean[] check = new boolean[10];
    	 int checked = 0;
    	 int num = N;
    	 int multi = 0;
    	 
    	 while (checked < 10) {
    		 multi++;
    		 num = N * multi;
    		 
    		 int temp = num;
    		 
    		 while (temp > 0) {
    			 if (check[temp % 10] == false) {
        			 check[temp % 10] = true;
        			 checked++;
        		 }
    			 temp /= 10;
    		 }
    	    	 }
    	 
    	 System.out.println("#" + t + " " + num);
     }
     
    }
}