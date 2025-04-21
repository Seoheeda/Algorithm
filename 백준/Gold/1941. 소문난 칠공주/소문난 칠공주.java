import java.util.*;
import java.io.*;

public class Main {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static char[][] arr;
	static boolean[] visited;
	static boolean[][] queueVisited;
	static int cnt;
	static int[] picked;
	static boolean[][] test;
	static Queue<int[]> queue;
	
	// 범위 안에 있나
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < 5 && y >= 0 && y < 5) {
			return true;
		}
		return false;
	}
	
	// 7명이 이어져있나 보기
	static boolean bfs() {
		
		queue = new ArrayDeque<>();
		queue.add(new int[] {picked[0] / 5, picked[0] % 5});
		
		queueVisited = new boolean[5][5];
		queueVisited[picked[0] / 5][picked[0] % 5] = true;
		
		int tempCnt = 1;
		
		// 이어져있는 모든 개수 세기
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int cx = temp[0];
			int cy = temp[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (isIn(nx, ny) && !queueVisited[nx][ny] && test[nx][ny]) {
					queue.add(new int[] {nx, ny});
					tempCnt++;
					queueVisited[nx][ny] = true;
				}
			}
		}
			
		// 7명이 이어져있으면 참
		if (tempCnt == 7) {
			return true;
		} 
		return false;
	}
	
	// 7명 조합 짜기
	static void dfs(int depth, int start) {
		
		if (depth == 7) {
			int tempCnt = 0;
			
			test = new boolean[5][5];
			
			// 7명 자리 표시
			for (int i = 0; i < 7; i++) {
				int target = picked[i];
				int x = target / 5;
				int y = target % 5;
				test[x][y] = true;
				if (arr[x][y] == 'S') {
					tempCnt++;
				}
			}
		
			// 7명이 이어져있고, 이다솜파가 4명 이상이면 개수 세기
			if (bfs() && tempCnt >= 4) {
				cnt++;
			}
			return;
		}
		
		// 조합
		for (int i = start; i < 25; i++) {
			if (!visited[i]) {
				picked[depth] = i;
				visited[i] = true;
				dfs(depth + 1, i + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 학생들 자리
		arr = new char[5][5];
		
		for (int i = 0; i < 5; i++) {
			String temp = br.readLine();
			for (int j = 0; j < 5; j++) {
				arr[i][j] = temp.charAt(j);
			}
		}
		
		// 정답
		cnt = 0;
		
		// 조합 위한 방문 배열
		visited = new boolean[25];
		
		// 조합 저장할 배열
		picked = new int[7];
		
		
		dfs(0, 0);
		
		System.out.println(cnt);

	}
}