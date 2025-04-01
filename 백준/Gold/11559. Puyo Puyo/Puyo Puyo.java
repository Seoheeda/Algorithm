import java.util.*;
import java.io.*;

public class Main {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static char[][] arr;
	static boolean[][] visited;
	
	// 배열 범위 안에 있는가
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < 12 && y >= 0 && y < 6) {
			return true;
		}
		return false;
	}
	
	// 연결된 같은 색 뿌요들 처리
	static int bfs(int x, int y) {
		
		// bfs 위한 큐
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		
		// 연결 정보 저장 리스트
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(new int[] {x, y});
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
		
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				
				// 범위 안에 있고, 최초 뿌요와 같은 색이고, 방문 전이면
				if (isIn(nx, ny) && arr[x][y] == arr[nx][ny] && !visited[nx][ny]) {
					queue.add(new int[] {nx, ny});
					visited[nx][ny] = true;
					list.add(new int[] {nx, ny});
				}
			}
		}
		
		// 연결 정보가 4 이상이면 => 연쇄 가능
		if (list.size() >= 4) {
			for (int i = 0; i < list.size(); i++) {
				int[] temp = list.get(i);
				arr[temp[0]][temp[1]] = '.';
			}
		}
		
		// 연결 정보 리턴 (4 이상이면 연쇄 일어났다는 뜻)
		return list.size();
	}
	
	// 중력 작용
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
		
		// 정답 (연쇄 횟수)
		int ans = 0;
		
		while (true) {
			visited = new boolean[12][6];
			// 연쇄 일어났는지 여부
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
			
			// 연쇄 없었으면 그만, 있었으면 연쇄 횟수 증가
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