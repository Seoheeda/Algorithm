import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 수열의 크기
		int N = Integer.parseInt(br.readLine());
		
		// 숫자들
		int[] nums = new int[N + 1];
		
		// dp 배열
		int[][] dp = new int[N + 1][N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int j = 1; j < N + 1; j++) {
			nums[j] = Integer.parseInt(st.nextToken());
			
			for (int i = 1; i < j + 1; i++) {
				if (i == j) {
					dp[i][j] = 1;
				} else if (j - i == 1) {
					if (nums[i] == nums[j]) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = 0;
					}
				} else {
					if (nums[i] == nums[j] && dp[i + 1][j - 1] == 1) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = 0;
					}
				}
			}
		}
		
		// 질문 개수
		int M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			sb.append(dp[S][E]).append("\n");
		}
		
		System.out.println(sb);
	}
}