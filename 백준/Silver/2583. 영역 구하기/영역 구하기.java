import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int M, N;
	static int[][] arr;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static int bfs(int x, int y) {
		int area = 1;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {x, y});
		arr[x][y] = 1; // 방문 표시
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int cx = temp[0];
			int cy = temp[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if (nx >= 0 && ny >= 0 && nx < M && ny < N && arr[nx][ny] == 0) {
					arr[nx][ny] = 1;
					queue.add(new int[] {nx, ny});
					area++;
				}
			}
		}
		
		return area;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 지도 크기
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		// 얼어붙은 영역의 개수
		int K = Integer.parseInt(st.nextToken());
		
		// 격자판
		arr = new int[M][N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for (int a = y1; a < y2; a++) {
				for (int b = x1; b < x2; b++) {
					arr[a][b] = 1;
				}
			}
		}
		
		int cnt = 0;
		ArrayList<Integer> areas = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0) {
					areas.add(bfs(i, j));
					cnt++;
				}
			}
		}
		
		Collections.sort(areas);
		System.out.println(cnt);
		for (int a : areas) {
			System.out.print(a + " ");
		}
	}
}