import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {

     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
     int T = Integer.parseInt(br.readLine());
     
     for (int t = 1; t <= T; t++) {
    	 StringTokenizer st = new StringTokenizer(br.readLine());
    	 
    	 int N = Integer.parseInt(st.nextToken());
    	 int M = Integer.parseInt(st.nextToken());
    	 
    	 int mask = (1 << N) - 1;
    	 
    	 if ((M & mask) == mask) {
    		 System.out.println("#" + t + " " + "ON");
    	 } else {
    		 System.out.println("#" + t + " " + "OFF");
    	 }
       }
    }
}