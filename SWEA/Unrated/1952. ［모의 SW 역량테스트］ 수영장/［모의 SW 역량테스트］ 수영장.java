import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			// 가격 : 1일, 1달, 3달, 1년
			int[] prices = new int[4];
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 4; i++) {
				prices[i] = Integer.parseInt(st1.nextToken());
			}
			
			// 계획 : 1 ~ 12월
			int[] plans = new int[13];
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= 12; i++) {
				plans[i] = Integer.parseInt(st2.nextToken());
			}
			
			int[][] dp = new int[3][13];
			int min_temp;
			
			for (int i = 1; i <= 12; i++) {
			
				min_temp = Integer.min(dp[0][i - 1], dp[1][i - 1]);
				if (i - 3 > 0) {
					min_temp = Integer.min(min_temp, dp[2][i - 3]);
				}

				dp[0][i] = min_temp + plans[i] * prices[0];
				dp[1][i] = min_temp + prices[1];
				dp[2][i] = min_temp + prices[2];
			}
			
			// 최종 최소 비용
			int min;
			min = Integer.min(dp[0][12], dp[1][12]);
			min = Integer.min(min, dp[2][10]);			
			min = Integer.min(min, prices[3]);
			System.out.println("#" + t + " " + min);
			
		}
		
	}

}