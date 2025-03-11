import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[][] arr;
	static int[][] students;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static boolean isIn(int x, int y) {
		if (x >= 1 && x <= N && y >= 1 && y <= N) {
			return true;
		}
		return false;
	}
	
	static int[] findSeat(int n, int[] around) {
		
		// 인접한 좋아하는 학생 수
		int cnt = 0;
		// 비어있는 칸 수
		int emptyCnt = 0;
		// 정답 x축
		int ax = N + 1;
		// 정답 y축
		int ay = N + 1;
		
		for (int a = 1; a <= N; a++) {
			for (int b = 1; b <= N; b++) {
				
				if (arr[a][b] != 0) {
					continue;
				}
				
				int tx = a;
				int ty = b;
				
				int aroundCnt = 0;
				int tempEmptyCnt = 0;
					
				for (int d = 0; d < 4; d++) {
					int nx = tx + dx[d];
					int ny = ty + dy[d];
					
					if (isIn(nx, ny)) {
						for (int i = 0; i < 4; i++) {
							if (arr[nx][ny] == around[i]) {
								aroundCnt++;
							}
						}
						if (arr[nx][ny] == 0) {
							tempEmptyCnt++;
						}
					}
					
					if (cnt < aroundCnt || (cnt == aroundCnt && emptyCnt < tempEmptyCnt)) {
						cnt = aroundCnt;
						emptyCnt = tempEmptyCnt;
						ax = tx;
						ay = ty;
					} else if (cnt == aroundCnt && emptyCnt == tempEmptyCnt) {
						if (ax > tx || ax == tx && ay > ty) {
							ax = tx;
							ay = ty;
						}
					}
				}
			}
		}
		return new int[] {ax, ay};
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 격자 크기
		N = Integer.parseInt(br.readLine());
		
		// 격자
		arr = new int[N + 1][N + 1];
		
		// 학생 정보
		students = new int[N * N + 1][2];
		
		// 좋아하는 학생 정보
		int[][] likes = new int[N * N + 1][4];

		// 첫번째 학생
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l1 = Integer.parseInt(st.nextToken());
		int l2 = Integer.parseInt(st.nextToken());
		int l3 = Integer.parseInt(st.nextToken());
		int l4 = Integer.parseInt(st.nextToken()); 
		
		likes[n] = new int[] {l1, l2, l3, l4};
		
		arr[2][2] = n;
		students[n][0] = 2;
		students[n][1] = 2;

		
		for (int i = 1; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			l1 = Integer.parseInt(st.nextToken());
			l2 = Integer.parseInt(st.nextToken());
			l3 = Integer.parseInt(st.nextToken());
			l4 = Integer.parseInt(st.nextToken());

			int[] temp = findSeat(n, new int[] {l1, l2, l3, l4});
			arr[temp[0]][temp[1]] = n;
			students[n][0] = temp[0];
			students[n][1] = temp[1];
			
			likes[n][0] = l1;
			likes[n][1] = l2;
			likes[n][2] = l3;
			likes[n][3] = l4;
		}
	
		// 정답 구하기
		int ans = 0;
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				int num = arr[i][j];
				int tempCnt = 0;
				
				for (int d = 0; d < 4; d++) {
					boolean isThere = false;
					for (int b = 0; b < 4; b++) {
						if (isIn(i + dx[d], j + dy[d]) && arr[i + dx[d]][j + dy[d]] == likes[num][b]) {
							isThere = true;
						}
					}
					
					if (isThere) {
						tempCnt++;
					}
				}
				
				if (tempCnt == 1) {
					ans += 1;
				} else if (tempCnt == 2) {
					ans += 10;
				} else if (tempCnt == 3) {
					ans += 100;
				} else if (tempCnt == 4) {
					ans += 1000;
				}
			}
		}
		System.out.println(ans);
	}
}