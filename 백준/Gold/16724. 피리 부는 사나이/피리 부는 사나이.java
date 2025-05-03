import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int[][] arr;
	static boolean[][] visited, cycle;
	static int safeZone;
	
	static void dfs(int x, int y) {
		
		// 방문처리
		visited[x][y] = true;
		
		int nx = x + dx[arr[x][y]];
		int ny = y + dy[arr[x][y]];
		
		// 방문 전이라면
		if (!visited[nx][ny]) {
			dfs(nx, ny);
		// 이미 방문헀다면 사이클 체크
		} else {
			if (!cycle[nx][ny]) {
				safeZone++;
			}
		}
		
		cycle[x][y] = true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 지도의 행 수
		int N = Integer.parseInt(st.nextToken());
		// 지도의 열 수
		int M = Integer.parseInt(st.nextToken());
		
		// 지도
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				char t = temp.charAt(j);
				if (t == 'U') {
					arr[i][j] = 2;
				} else if (t == 'D') {
					arr[i][j] = 3;
				} else if (t == 'L') {
					arr[i][j] = 0;
				} else {
					arr[i][j] = 1;
				}
			}
		}
		
		// 방문 여부
		visited = new boolean[N][M];
		// 사이클 여부
		cycle = new boolean[N][M];
		
		// SAFE ZONE 개수
		safeZone = 0;
		
		// 방문 전인 지점들만 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
				}
			}
		}
		
		System.out.println(safeZone);
	}
}