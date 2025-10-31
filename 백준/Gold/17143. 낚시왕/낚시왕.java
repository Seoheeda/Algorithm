import java.io.*;
import java.util.*;

public class Main {
	
	static int R, C, M, answer;
	// int[] : 속력, 이동방향, 크기
	static HashMap<Integer, int[]> sharks;
	static int[][] arr;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	// 격자 범위인지
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < R && y >= 0 && y < C) {
			return true;
		}
		return false;
	}
	
	// 방향 바꾸기
	static int changeDir(int d) {
		if (d == 0) {
			return 1;
		} else if (d == 1) {
			return 0;
		} else if (d == 2) {
			return 3;
		} else {
			return 2;
		}
	}
	
	// 땅과 가장 가까운 상어 잡기
	static void catchShark(int col) {
		
		for (int i = 0; i < R; i++) {
			if (arr[i][col] > 0) {
				// 잡은 상어 크기 누적
				answer += sharks.get(arr[i][col])[2];
				arr[i][col] = 0;
				
				return;
			}
		}
	}
	
	// 상어 이동하기
	static void moveShark() {
		
		// 이동 결과 임시 저장할 배열
		int[][] tempArr = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				
				// 상어 있으면
				if (arr[i][j] > 0) {
					int S = sharks.get(arr[i][j])[0];
					int D = sharks.get(arr[i][j])[1];
					int Z = sharks.get(arr[i][j])[2];
					
					int x = i;
					int y = j;
					
					// 속력만큼 이동
					int cycle;
					if (D == 0 || D == 1) {
						cycle = (R - 1) * 2;
					} else {
						cycle = (C - 1) * 2;
					}
					
					int dist = S % cycle;
					
					for (int s = 0; s < dist; s++) {
						// 해당 방향으로 이동 가능하면 그대로 이동
						if (isIn(x + dx[D], y + dy[D])) {
							x += dx[D];
							y += dy[D];
						// 해당 방향으로 이동 불가하면 방향 바꿔 이동
						} else {
							D = changeDir(D);
							x += dx[D];
							y += dy[D];
						}
					}
					
					// 상어 정보에 바뀐 방향 정보 수정
					sharks.replace(arr[i][j], new int[] {S, D, Z});
					
					// 만일 그 자리에 이미 상어 와있다면 크기 비교
					if (tempArr[x][y] > 0) {
						
						int originSize = sharks.get(tempArr[x][y])[2];
						
						if (originSize < Z) {
							tempArr[x][y] = arr[i][j];
						}
					} else {
						tempArr[x][y] = arr[i][j];
					}
				}
			}
		}
		
		// 기존 배열에 반영
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				arr[i][j] = tempArr[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 격자판 크기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 상어 수
		M = Integer.parseInt(st.nextToken());
		
		// 상어 정보
		// int: 상어 번호
		// int[] : 속력, 이동방향, 크기
		sharks = new HashMap<>();
		
		// 격자판
		arr = new int[R][C];
		
		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			
			arr[r][c] = i;
			sharks.put(i, new int[] {s, d, z});
		}
		
		// 낙시왕 이동하면서 작업 수행
		answer = 0;
		
		for (int i = 0; i < C; i++) {
			// 상어 잡기
			catchShark(i);
			// 상어 이동하기
			moveShark();
		}
		
		System.out.println(answer);
	}
}