import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int[] arr;
	static int[][] ingred;
	static int max = 0;
	static int L;
	
	private static void dfs(int depth, int start, int goal) {
		
		if (depth == goal) {
			int mat = 0;
			int cal = 0;
			
			for (int a : arr) {
				mat += ingred[a][0];
				cal += ingred[a][1];
				
			}
	
			
			if (cal <= L) {
				if (max < mat) {
					max = mat;
				}
			}
			
			return;
		}
		
		for (int i = start; i < N; i++) {
				arr[depth] = i;
				dfs(depth + 1, i + 1, goal);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			
			StringTokenizer st1 = new StringTokenizer(br.readLine());
						
			// 재료의 수
			N = Integer.parseInt(st1.nextToken());
			// 제한 칼로리
			L = Integer.parseInt(st1.nextToken());
			// 재료 정보
			ingred = new int[N][2];
			max = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				for (int j = 0; j < 2; j++) {
					ingred[i][j] = Integer.parseInt(st2.nextToken());
				}
			}
			
			
			
			for (int i = 1; i < N + 1; i++) {
				arr = new int[i];
				dfs(0, 0, i);
			}
			
			System.out.println("#" + (t + 1) + " " + max);
			
		}

	}

}