import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 숫자 개수
		int N = Integer.parseInt(br.readLine());
		
		// 숫자 입력 후 오름차순 정렬
		int[] nums = new int[N];
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(nums);
		
		for (int i = N - 1; i >= 0; i--) {
			// 합
			int a = nums[i];
			
			// 세 수 중 하나 고정 후 이분탐색
			for (int j = 0; j < N - 1; j++) {
				int b = nums[j];
				int target = a - b;
				int left = j;
				int right = N - 1;
				
				while (left <= right) {
					int sum = nums[left] + nums[right];
					
					if (sum == target) {
						System.out.println(a);
						return;
					} else if (sum < target) {
						left++;
					} else {
						right--;
					}
				}
			}
		}
	}
}