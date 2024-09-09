import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	    
    public static void main(String[] args) throws Exception {
  
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 좌석 개수
    	int N = Integer.parseInt(br.readLine());
    	// 고정석 개수
    	int M = Integer.parseInt(br.readLine());
    	
    	// 피보나치 수열 담은 배열
    	int[] dp = new int[N + 1];
    	
    	dp[0] = 1;
		dp[1] = 1;
		
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
    	
		int now = 0;
		int ans = 1;
    	for (int i = 0; i < M; i++) {
    		int V = Integer.parseInt(br.readLine());
    		ans *= dp[V - now - 1];
    		now = V;
    	}
    	
    	ans *= dp[N - now];
    	System.out.println(ans);
    	
    	
     
    }
}