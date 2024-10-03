import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m, r1, c1, r2, c2;
	static boolean arrived, flag;
	static char[][] arr;
	static boolean[][] visited;
	
	// 범위 내에 있나 확인
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < n && y >= 0 && y < m) {
			return true;
		}
		return false;
	}
	
	static void dfs(int x, int y) {
		
		// 빙판길 크기가 1*1이라면 바로 return
		if (n == 1 && m == 1) {
			return;
		}
		
		// 탈출 가능하면 바로 return;
		if (flag) {
			return;
		}
		
		// 목적지에 도착했다면, 4방향 보며 탈출 가능한지 봄
		if (x == r2 && y == c2) {
			arrived = true;
			if (arr[x][y] == 'X') {
				flag = true;
				return;
			}
			if (isIn(x - 1, y) && arr[x - 1][y] != 'X') {
				flag = true;
				return;
			}
			if (isIn(x + 1, y) && arr[x + 1][y] != 'X') {
				flag = true;
				return;
			}
			if (isIn(x, y - 1) && arr[x][y - 1] != 'X') {
				flag = true;
				return;
			}
			if (isIn(x, y + 1) && arr[x][y + 1] != 'X') {
				flag = true;
				return;
			}
		}
		
		visited[x][y] = true;
		arr[x][y] = 'X';
		
		// 4방향 탐색
		if (isIn(x - 1, y) && !visited[x - 1][y] && (arr[x - 1][y] != 'X') || (x - 1 == r2 && y == c2)) {
			dfs(x - 1, y);
		}
		
		if (isIn(x + 1, y) && !visited[x + 1][y] && (arr[x + 1][y] != 'X') || (x + 1 == r2 && y == c2)) {
			dfs(x + 1, y);
		}
		
		if (isIn(x, y - 1) && !visited[x][y - 1] && (arr[x][y - 1] != 'X') || (x == r2 && y - 1 == c2)) {
			dfs(x, y - 1);
		}
		
		if (isIn(x, y + 1) && !visited[x][y + 1] && (arr[x][y + 1] != 'X') || (x == r2 && y + 1 == c2)) {
			dfs(x, y + 1);
		}
	}
	
    public static void main(String[] args) throws Exception {
    
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	// 행열 개수
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	// 빙판길 초기 상태
    	// X : 손상된 얼음
    	// . : 손상되지 않은 얼음
    	arr = new char[n][m];
    	for (int i = 0; i < n; i++) {
    		String temp = br.readLine();
    		for (int j = 0; j < m; j++) {
    			arr[i][j] = temp.charAt(j);
    		}
    	}
    	
    	// 초기 위치
    	st = new StringTokenizer(br.readLine());
    	r1 = Integer.parseInt(st.nextToken()) - 1;
    	c1 = Integer.parseInt(st.nextToken()) - 1;
    	
    	// 탈출구 위치
    	st = new StringTokenizer(br.readLine());
    	r2 = Integer.parseInt(st.nextToken()) - 1;
    	c2 = Integer.parseInt(st.nextToken()) - 1;
    	
    	visited = new boolean[n][m];
    	arrived = false;
    	flag = false;
    	
    	// 시작점과 도착점이 같을 경우 따로 처리
    	if (r1 == r2 && c1 == c2) {
    		if (isIn(r1 + 1, c1) && arr[r1 + 1][c1] != 'X') {
    			flag = true;
    		} else if (isIn(r1 - 1, c1) && arr[r1 - 1][c1] != 'X') {
    			flag = true;
    		} else if (isIn(r1, c1 + 1) && arr[r1][c1 + 1] != 'X') {
    			flag = true;
    		} else if (isIn(r1, c1 - 1) && arr[r1][c1 - 1] != 'X') {
    			flag = true;
    		}
    	} else {
        	dfs(r1, c1);
    	}
    	
    	if (flag) {
    		System.out.println("YES");
    	} else {
    		System.out.println("NO");
    	}	
    }
}