import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[K];
		for (int i = 0; i < K; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(nums);
		long start = 1;
		long end = nums[K - 1];
		long ans = 0;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			int cnt = 0;
			for (int num : nums) {
				cnt += (num / mid);
			}
			
//			System.out.println(cnt);
//			System.out.println(mid);
//			System.out.println();
			
			if (cnt >= N) {
				start = mid + 1;
				ans = mid;
			} else {
				end = mid - 1;
			}
		}
		
		System.out.println(ans);
	}
}