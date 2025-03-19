import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M, cctvCnt, ans;
	static int[][] arr;
	static ArrayList<int[]> cctvInfos;
	static int[] oneDx = {0, 1, 0, -1};
	static int[] oneDy = {1, 0, -1, 0};
	static int[][] twoDx = {{0, 0}, {-1, 1}};
	static int[][] twoDy = {{-1, 1}, {0, 0}};
	static int[][] threeDx = {{-1, 0}, {0, 1}, {0, 1}, {-1, 0}};
	static int[][] threeDy = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	static int[][] fourDx = {{1, 0, -1}, {0, 0, -1}, {0, 1, -1}, {0, 1, 0,}};
	static int[][] fourDy = {{0, -1, 0}, {1, -1, 0}, {1, 0,  0}, {1, 0, -1}};
	static int[] fiveDx = {0, 1, 0, -1};
	static int[] fiveDy = {1, 0, -1, 0};
	static int[][] sideInfos;
	
	// 범위 안인가
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M) {
			return true;
		}
		return false;
	}
	
	static void dfs(int turn) {
		
		// 모든 cctv들 방향 정함
		if (turn >= cctvCnt) {
				
			// 사각지대 표시
			int[][] check = new int[N][M];
			
			for (int i = 0; i < cctvCnt; i++) {
				int x = cctvInfos.get(i)[1];
				int y = cctvInfos.get(i)[2];
				int cctvNum = sideInfos[i][0];
				int cctvSide = sideInfos[i][1];
								
				check[x][y] = 1;
				
				// cctv가 1번
				if (cctvNum == 1) {
				    int nx = x;
				    int ny = y;
				    while (true) {
				        nx += oneDx[cctvSide];
				        ny += oneDy[cctvSide];

				        // 범위를 벗어나거나 벽을 만나면 종료
				        if (!isIn(nx, ny) || arr[nx][ny] == 6) {
				        	break;
				        };

				        check[nx][ny] = 1;
				    }
				}
				
				// cctv가 2번
				if (cctvNum == 2) {
				    for (int d = 0; d < 2; d++) { 
				        int nx = x;
				        int ny = y;
				        while (true) {
				            nx += twoDx[cctvSide][d];
				            ny += twoDy[cctvSide][d];

				            // 범위를 벗어나거나 벽을 만나면 종료
				            if (!isIn(nx, ny) || arr[nx][ny] == 6) {
				            	break;
				            };

				            check[nx][ny] = 1;
				        }
				    }
				}

				// cctv가 3번
				if (cctvNum == 3) {
				    for (int d = 0; d < 2; d++) { 
				        int nx = x;
				        int ny = y;
				        while (true) {
				            nx += threeDx[cctvSide][d];
				            ny += threeDy[cctvSide][d];

				            // 범위를 벗어나거나 벽을 만나면 종료
				            if (!isIn(nx, ny) || arr[nx][ny] == 6) {
				            	break;
				            };

				            check[nx][ny] = 1;
				        }
				    }
				}

				// cctv가 4번
				if (cctvNum == 4) {
				    for (int d = 0; d < 3; d++) { 
				        int nx = x;
				        int ny = y;
				        while (true) {
				            nx += fourDx[cctvSide][d];
				            ny += fourDy[cctvSide][d];

				            // 범위를 벗어나거나 벽을 만나면 종료
				            if (!isIn(nx, ny) || arr[nx][ny] == 6) {
				            	break;
				            };

				            check[nx][ny] = 1;
				        }
				    }
				}
				
				// cctv가 5번
				if (cctvNum == 5) {
				    for (int d = 0; d < 4; d++) { 
				        int nx = x;
				        int ny = y;
				        while (true) {
				            nx += fiveDx[d];
				            ny += fiveDy[d];

				            // 범위를 벗어나거나 벽을 만나면 종료
				            if (!isIn(nx, ny) || arr[nx][ny] == 6) {
				            	break;
				            }

				            check[nx][ny] = 1;
				        }
				    }
				}		
			}
			
			int sagakCnt = 0;
			
			// 사각지대 개수 세기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (check[i][j] == 0 && arr[i][j] != 6) {
						sagakCnt++;
					}
				}
			}
			
			ans = Integer.min(ans, sagakCnt);
			
			return;
		}
		
		// cctv 순서대로 방향 정해서 dfs 돌리기
		int cctvNow = cctvInfos.get(turn)[0];
		
		if (cctvNow == 5) {
			sideInfos[turn][0] = 5;
			sideInfos[turn][1] = 0;
			dfs(turn + 1);
		}
		if (cctvNow == 4 || cctvNow == 3 || cctvNow == 1) {
			sideInfos[turn][0] = cctvNow;
			for (int i = 0; i < 4; i++) {
				sideInfos[turn][1] = i;
				dfs(turn + 1);
			}
		}
		if (cctvNow == 2) {
			sideInfos[turn][0] = cctvNow;
			for (int i = 0; i < 2; i++) {
				sideInfos[turn][1] = i;
				dfs(turn + 1);
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 사무실 크기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 사무실 정보
		arr = new int[N][M];
		
		// CCTV 개수
		cctvCnt = 0;
		
		// CCTV 정보
		cctvInfos = new ArrayList<int[]>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] >= 1 && arr[i][j] <= 5) {
					cctvInfos.add(new int[] {arr[i][j], i, j});
					cctvCnt++;
				}
			}
		}
		
		// 0 : 빈 칸
		// 1 : 한쪽 방향
		// 2 : 서로 반대인 두 방향
		// 3 : 직각인 두 방향
		// 4 : 세 방향
		// 5 : 네 방향
		// 6 : 벽
		// 쭈우우우우우욱 감시 가능, 근데 벽은 통과 불가능, CCTV는 통과 가능
		
		// 사각지대 최소 크기
		ans = Integer.MAX_VALUE;
		
		// 방향 정보
		sideInfos = new int[cctvCnt][2];
		
		dfs(0);
		
		System.out.println(ans);
	}
}