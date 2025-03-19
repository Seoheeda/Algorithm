import java.util.*;
import java.io.*;

public class Main {
	
	static int N, L, R;
	static int[][] arr;
	static boolean[][] visited;
	static ArrayList<int[]> countries;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	// 범위 안인지
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}
	
	// 연합 찾기 대작전
	static int bfs(int x, int y) {
		
		// 열린 나라 저장
		countries = new ArrayList<int[]>();
		
		// bfs이니깐용
		Queue<int[]> queue = new ArrayDeque<int[]>();
		
		countries.add(new int[] {x, y});
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		
		// 합
		int sum = arr[x][y];
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int cx = temp[0];
			int cy = temp[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if (isIn(nx, ny) && !visited[nx][ny] && Math.abs(arr[cx][cy] - arr[nx][ny]) >= L && Math.abs(arr[cx][cy] - arr[nx][ny]) <= R) {
					queue.add(new int[] {nx, ny});
					countries.add(new int[] {nx, ny});
					sum += arr[nx][ny];
					visited[nx][ny] = true;
				}
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 땅 크기
		N = Integer.parseInt(st.nextToken());
		// 인구 차이 L명 이상, R명 이하
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 연합 정보
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 인구 이동 수
		int ans = 0;
		
		// 방문 여부
		visited = new boolean[N][N];
		
		while (true) {
			
			// 인구 이동 했나요?
			boolean moved = false;
			
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						
						// 연합 인구수
						int groupPop = bfs(i, j);
						
						// 연합 크기가 1보다 크면!!
						if (countries.size() > 1) {
							int avgPop = groupPop / countries.size();
							
							for (int[] country : countries) {
								int cx = country[0];
								int cy = country[1];
								arr[cx][cy] = avgPop;
							}
							
							moved = true;
						}
					}
				}
			}
						
			if (moved) {
				ans++;
			} else {
				break;
			}
		}

		System.out.println(ans);
	}
}