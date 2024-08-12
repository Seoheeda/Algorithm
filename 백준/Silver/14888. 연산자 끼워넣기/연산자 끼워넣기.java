import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	// N개의 수로 이루어진 수열을 담은 nums 배열
	static int N;
	static int[] nums;
	
	// (N - 1)개의 연산자 저장할 배열
	static String[] yeonsan;
	
	// dfs 탐색 위한 배열 (연산자 방문 여부)
	static boolean[] visited;
	
	// current 연산자 순열 저장할 배열
	static String[] arr;
	
	// 최댓값, 최솟값 초기화
	static int max = -1000000000;
	static int min = 1000000000;
	
	// 가능한 모든 연산자 순열 생성
	private static void dfs(int depth) {
		
		// 연산자 순열 완성 --> 그에 따라 값 계산
		if (depth == N - 1) {
			int temp = nums[0];
			for (int i = 0; i < N - 1; i++) {
				 switch (arr[i]) {
                 case "+":
                     temp += nums[i + 1];
                     break;
                 case "-":
                     temp -= nums[i + 1];
                     break;
                 case "*":
                     temp *= nums[i + 1];
                     break;
                 case "/":
                     temp /= nums[i + 1];
                     break;
             }
         }
			
			// 최댓값, 최솟값 업데이트
			if (temp > max) {
				max = temp;
			}
			if (temp < min) {
				min = temp;
			}
			
			return;
		}
		
		
		// 재귀 호출
		for (int i = 0; i < N - 1; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				arr[depth] = yeonsan[i];
				dfs(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		
		// N개의 수로 이루어진 순열
		N = Integer.parseInt(st1.nextToken());
		nums = new int[N];
		
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		// N개의 수로 이루어진 순열 nums
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st2.nextToken());
		}
		
		yeonsan = new String[N - 1];
		visited = new boolean[N - 1];
		arr = new String[N - 1];
		
		// 연산자별 주어진 개수에 따라 yeonsan 배열 생성
		StringTokenizer st3 = new StringTokenizer(br.readLine());
		String[] type = {"+", "-", "*", "/"};
		
		int a = 0;
		for (int i = 0; i < 4; i++) {
			int y = Integer.parseInt(st3.nextToken());
			for (int j = 0; j < y; j++) {
				yeonsan[a] = type[i];
				a++;
			}
		}
		
		//탐색 종료 후 최댓값, 최솟값 출력
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}


}
