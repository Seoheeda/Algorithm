import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception {
   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
        	dp[i] = 1;
        }
        
        for (int i = n - 1; i >= 0; i--) {
        	for (int j = i - 1; j >= 0; j--) {
        		if (nums[i] > nums[j] && dp[j] <= dp[i]) {
        			dp[j] = dp[i] + 1;
        		}
        	}
        }

        // 최댓값
        int ans = Arrays.stream(dp).max().getAsInt();
        System.out.println(ans);
        
    }
}