import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N : 정수 개수
		int N = Integer.parseInt(st.nextToken());
		// K : 합
		int K = Integer.parseInt(st.nextToken());
		
		// 숫자들 (+ 누적합)
		int[] nums = new int[N + 1];
		
		// 부분합 개수
		long cnt = 0;
		
		// 누적합 숫자별 개수 저장해둘 map
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		map.put(nums[0], 1);
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			nums[i] = nums[i - 1] + Integer.parseInt(st.nextToken());
			
			
			// 그 이전에 nums[i] - K 있었다면 그 개수만큼 더하기
			if (map.containsKey(nums[i] - K)) {
				cnt += map.get(nums[i] - K);
			}
			
			// 누적합 map에 저장
			if (map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
			} else {
				map.put(nums[i], 1);
			}
		}
		
		System.out.println(cnt);
	}
}