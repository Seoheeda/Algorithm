import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
            Queue<Integer> nums = new LinkedList<>();
			int maxNum = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums.add(Integer.parseInt(st.nextToken()));
			}
			
			int ans = 1;
			
			while (!nums.isEmpty()) {
				if (nums.peek() < Collections.max(nums)) {
					nums.add(nums.poll());
				} else {
					if (M == 0) {
						break;
					}
					nums.poll();
					ans++;
				}
				if (M > 0) {
					M--;
				} else {
					M = nums.size() - 1;
				}
			}
			System.out.println(ans);
		}
	}
}