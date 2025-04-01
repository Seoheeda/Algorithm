import java.util.*;
import java.io.*;

public class Main {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static char[][] arr;
	static boolean[][] visited;
	
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < 12 && y >= 0 && y < 6) {
			return true;
		}
		return false;
	}
	
	static int bfs(int x, int y) {
		
		// bfs 위한 큐
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(new int[] {x, y});
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
		
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				
				if (isIn(nx, ny) && arr[x][y] == arr[nx][ny] && !visited[nx][ny]) {
					queue.add(new int[] {nx, ny});
					visited[nx][ny] = true;
					list.add(new int[] {nx, ny});
				}
			}
		}
		
		if (list.size() >= 4) {
			for (int i = 0; i < list.size(); i++) {
				int[] temp = list.get(i);
				arr[temp[0]][temp[1]] = '.';
			}
		}
		
		return list.size();
	}
	
	static void goDown() {
		for (int j = 0; j < 6; j++) {
			ArrayList<Character> list = new ArrayList<Character>();
			for (int i = 11; i >= 0; i--) {
				if (arr[i][j] != '.') {
					list.add(arr[i][j]);
				}
			}
			
			int cnt = 0;
			for (int i = 11; i >= 0; i--) {
				if (cnt >= list.size()) {
					arr[i][j] = '.';
				} else {
					arr[i][j] = list.get(cnt);
					cnt++;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new char[12][6];
		
		for (int i = 0; i < 12; i++) {
			String temp = br.readLine();
			for (int j = 0; j < 6; j++) {
				arr[i][j] = temp.charAt(j);
			}
		}
		
		int ans = 0;
		
		while (true) {
			visited = new boolean[12][6];
			boolean puyoPuyo = false;
			
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (arr[i][j] != '.') {
						int cnt = bfs(i, j);
						if (cnt >= 4) {
							puyoPuyo = true;
						}
					}
				}
			}
			
			if (!puyoPuyo ) {
				break;
			} else {
				goDown();
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}