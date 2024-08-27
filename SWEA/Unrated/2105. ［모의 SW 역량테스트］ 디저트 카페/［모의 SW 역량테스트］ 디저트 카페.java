import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static int[][] map;
	static int max;
	static int N;
	
	private static void check(int cx, int cy, int x, int y) {
		int temp = 0;
		boolean[] desert = new boolean[101];
		
		for (int k = 0; k < 4; k++) {
			if (k == 1 || k == 3) {
				for (int l = 0; l < x; l++) {
					cx += dx[k];
					cy += dy[k];
					if (cx < 0 || cx >= N || cy < 0 || cy >= N) {
						return;
					}
					
					if (desert[map[cx][cy]] == true) {
						return;
					}
					
					desert[map[cx][cy]] = true;
					
					temp++;
				}
			} else if (k == 0 || k == 2) {
				for (int l = 0; l < y; l++) {
					cx += dx[k];
					cy += dy[k];
					if (cx < 0 || cx >= N || cy < 0 || cy >= N) {
						return;
					}
					
					if (desert[map[cx][cy]] == true) {
						return;
					}
							
					desert[map[cx][cy]] = true;
					
					temp++;
				}
			}
		}
		
		if (max < temp) {
			max = temp;
		}
	
		return;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = -1;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int a = 1; a <= N - 1; a++) {
						for (int b = 1; b <= N - 1; b++) {
							check(i, j, a, b);
						}
					}
				}
			}
			
			System.out.println("#" + t + " " + max);
		}	
	}
}