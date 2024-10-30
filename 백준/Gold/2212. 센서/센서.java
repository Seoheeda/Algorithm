import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 센서 개수
		int N = Integer.parseInt(br.readLine());
		// 집중국 개수
		int K = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int[] arr2 = new int[N - 1];
		for (int i = 0; i < N - 1; i++) {
			arr2[i] = arr[i + 1] - arr[i];
		}
		
		Arrays.sort(arr2);
		
		int ans = 0;
		for (int i = 0; i < N - K; i++) {
			ans += arr2[i];
		}
		
		System.out.println(ans);
	}
}