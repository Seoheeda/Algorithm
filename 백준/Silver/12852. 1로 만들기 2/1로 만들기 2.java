import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N + 1];
		
		for (int i = 2; i < N + 1; i++) {
			dp[i] = dp[i - 1] + 1;
			
			if (i % 3 == 0) {
				dp[i] = Integer.min(dp[i], dp[i / 3] + 1);
			}
			
			if (i % 2 == 0) {
				dp[i] = Integer.min(dp[i], dp[i / 2] + 1);
			}
		}
		
		System.out.println(dp[N]);
						
		StringBuilder sb = new StringBuilder();
		
		// 역추적해서 경로 찾기
		while (true) {
			sb.append(N).append(" ");
			
			if (N == 1) {
				break;
			}
			
			if (N % 3 == 0 && dp[N / 3] == dp[N] - 1) {
				N /= 3;
			} else if (N % 2 == 0 && dp[N / 2] == dp[N] - 1) {
				N /= 2;
			} else {
				N -= 1;
			}
		}
		
		System.out.println(sb);
	}
}