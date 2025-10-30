import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] red, blue, hole;
	static char[][] arr;
	
	static int[] dx = {0, 0, -1, 1}; 
	static int[] dy = {-1, 1, 0, 0};
	
	// 범위 내부인지
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M) {
			return true;
		}
		return false;
	}
	
	static int bfs() {
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {red[0], red[1], blue[0], blue[1], 0});
		
		while (!queue.isEmpty()) {
			
			int[] temp = queue.poll();
			int rx = temp[0];
			int ry = temp[1];
			int bx = temp[2];
			int by = temp[3];
			int cnt = temp[4];
						
			// 10번 넘었으면 그만
			if (cnt >= 10) {
				return -1;
			}
			
			for (int d = 0; d < 4; d++) {
				
				// 방향 기준으로 어떤 공을 먼저 굴릴지 결정
				boolean blueFirst = false;
				if (d == 0 && ry > by) {
					blueFirst = true;     
				} else if (d == 1 && ry < by) {
					blueFirst = true;
				} else if (d == 2 && rx > bx) {
					blueFirst = true;
				} else if (d == 3 && rx < bx) {
					blueFirst = true; 
				}
				
				int nrx = rx;
				int nry = ry;
				int nbx = bx;
				int nby = by;
				
				// 각 공이 들어갔는지 여부
				boolean redIn = false;
				boolean blueIn = false;
				
				// 파란공 먼저 이동
				if (blueFirst) {
					
					// 파란공
					while (true) {
						int nx = nbx + dx[d];
						int ny = nby + dy[d];
						
						// 범위 밖이면 그만
						if (!isIn(nx, ny) || arr[nx][ny] == '#') {
							break;
						}
						
						// 파란공 들어갔음 
						if (nx == hole[0] && ny == hole[1]) {
							blueIn = true;
							nbx = -1;
							nby = -1;
							break;
						}
						
						nbx = nx; 
						nby = ny;
					}
					
					// 빨간공
					while (true) {
						int nx = nrx + dx[d];
						int ny = nry + dy[d];
						
						// 범위 밖이면 그만
						if (!isIn(nx, ny) || arr[nx][ny] == '#') {
							break;
						}
												
						// 파란공과 겹치면 멈춤
						if (nx == nbx && ny == nby) {
							break;
						}
						
						// 빨간공 들어감 체크
						if (nx == hole[0] && ny == hole[1]) {
							redIn = true;
							break;
						}
						
						nrx = nx; 
						nry = ny;
					}
				// 빨간공 먼저 이동
				} else {
					// 빨간공
					while (true) {
						int nx = nrx + dx[d];
						int ny = nry + dy[d];
						
						// 범위 밖이라 그만
						if (!isIn(nx, ny) || arr[nx][ny] == '#') {
							break;
						}
						
						// 빨간공 들어감 체크
						if (nx == hole[0] && ny == hole[1]) {
							redIn = true;
							nrx = -1;
							nry = -1;
							break;
						}
						
						nrx = nx; 
						nry = ny;
					}
					
					// 파란공
					while (true) {
						int nx = nbx + dx[d];
						int ny = nby + dy[d];
						
						// 범위 밖이라 그만
						if (!isIn(nx, ny) || arr[nx][ny] == '#') {
							break;
						}
						
						// 파란공 들어감
						if (nx == hole[0] && ny == hole[1]) {
							blueIn = true;
							break;
						}
						// 빨간공과 겹치면 멈춤
						if (nx == nrx && ny == nry) {
							break;
						}
						
						nbx = nx; 
						nby = ny;
					}
				}
				
				// 파란공이 들어간 경우는 실패
				if (blueIn) {
					continue;
				}
				// 빨간공만 들어간 경우 성공
				if (redIn) {
					return cnt + 1;
				}
				// 같은 위치면 무효
				if (nrx == nbx && nry == nby) {
					continue;
				}
				
				// 큐에 추가
				queue.add(new int[] {nrx, nry, nbx, nby, cnt + 1});
			}
		}
		
		return -1;
	}
		
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 보드 크기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 보드
		arr = new char[N][M];
		
		// 빨강 구슬 위치
		red = new int[2];
		// 파란 구슬 위치
		blue = new int[2];
		// 구멍 위치
		hole = new int[2];
		
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = temp.charAt(j);
				if (arr[i][j] == 'R') {
					red[0] = i; red[1] = j;
				} else if (arr[i][j] == 'B') {
					blue[0] = i; blue[1] = j;
				} else if (arr[i][j] == 'O') {
					hole[0] = i; hole[1] = j;
				}
			}
		}
		
		System.out.println(bfs());
	}
}
