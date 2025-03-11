import java.util.*;
import java.io.*;

public class Main {
	
	static int N, S;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 수열 길이
		N = Integer.parseInt(st.nextToken());
		// 합
		S = Integer.parseInt(st.nextToken());
				
		// 부분합
		nums = new int[N + 2];
		
		int INF = Integer.MAX_VALUE;
		int ans = INF;
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 1;
		int sum = nums[0];
		
		while (start < N + 1 && end < N + 1) {
			if (sum >= S) {
				ans = Integer.min(ans, end - start);
				sum -= nums[start];
				start++;
			} else {
				sum += nums[end];
				end++;
			}	
		}
		
		if (ans == INF) {
			System.out.println(0);
		} else {
			System.out.println(ans);
		}
	}
}