import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int[][] grid;
	private static int K;
	private static int N;
	private static int M;

	private static int[] numbers;
	private static boolean[] isSelected;
	private static int[][] yeonsan;
	
	private static int min = 10000;
	
	// 왼쪽 윗 칸 (r - s, c - s)
	// 오른쪽 아랫 칸 (r + s, c + s)
	// 배열 회전하는 메서드
	private static void rotate(int r, int c, int s, int[][] grid2) {
		
		while (s >= 1) {
			int temp = grid2[r - s][c - s];
			for (int i = 0; i < (2 * s); i++) {
				grid2[r - s + i][c - s] = grid2[r - s + i + 1][c - s];
			}
			for (int i = 0; i < (2 * s); i++) {
				grid2[r + s][c - s + i] = grid2[r + s][c - s + i + 1];
			}
			for (int i = 0; i < (2 * s); i++) {
				grid2[r + s - i][c + s] = grid2[r + s - i - 1][c + s];
			}
			for (int i = 0; i < (2 * s - 1); i++) {
				grid2[r - s][c + s - i] = grid2[r - s][c + s - i - 1];
			}
			grid2[r - s][c - s + 1] = temp;
			s--;
		}
				
		
	
	}
	
	// 회전 연산의 조합에 따라 배열 A 값의 최솟값 갱신하는 메서드
	private static void dfs(int depth) {
		
		if (depth ==  K) {
			
			// 배열 연산을 위한 배열 복사
			int[][] grid2 = new int[grid.length][];
			for (int i = 0; i < grid.length; i++) {
			    grid2[i] = grid[i].clone();
			}
			
			// 조합의 각 연산마다 rotate 함수 호출
			for (int i = 0; i < K; i++) {
				rotate(yeonsan[numbers[i]][0] - 1, yeonsan[numbers[i]][1] - 1, yeonsan[numbers[i]][2], grid2);
			}
			
			// 부분 최솟값 갱신
			int sub_min = 10000;
			for (int i = 0; i < N; i++) {
				int cnt = 0;
				for (int j = 0; j < M; j++) {
					cnt += grid2[i][j];
				}
				if (cnt < sub_min) {
					sub_min = cnt;
				}
			}
			
			// 전체 최솟값 갱신
			if (sub_min < min) {
				min = sub_min;
			}
			
			return;
		}
		
		// 조합 생성
		for (int i = 0; i < K; i++) {
			if (isSelected[i]) {
				continue;
			}
			isSelected[i] = true;
			numbers[depth] = i;
			dfs(depth + 1);
			isSelected[i] = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		// 배열의 크기 N, M
		N = Integer.parseInt(st1.nextToken());
		M = Integer.parseInt(st1.nextToken());
		// 회전 연산 개수
		K = Integer.parseInt(st1.nextToken());
		
		// 배열 입력
		grid = new int[N][M];
	
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st2.nextToken());
			}
		}
		
		// 연산 입력
		yeonsan = new int[K][3];
		for (int i = 0; i < K; i++) {
			StringTokenizer st3 = new StringTokenizer(br.readLine());
			yeonsan[i][0] = Integer.parseInt(st3.nextToken());
			yeonsan[i][1] = Integer.parseInt(st3.nextToken());
			yeonsan[i][2] = Integer.parseInt(st3.nextToken());
			
		}
		
		numbers = new int[K];
		isSelected = new boolean[K];
		dfs(0);	
		System.out.println(min);
	}
}