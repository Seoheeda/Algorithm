import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] arr;
	static int size;
	static int[] levels;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	// 범위 여부
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < size && y >= 0 && y < size) {
			return true;
		}
		return false;
	}
	
	// 배열 돌리기
	static void turn(int q) {
		int tempSize = (int) Math.pow(2, levels[q]);
		
		// 회전된 버전
		int[][] tempArr = new int[size][size];
		
		for (int i = 0; i < size; i+=tempSize) {
			for (int j = 0; j < size; j+=tempSize) {
				for (int r = 0; r < tempSize; r++) {
					for (int c = 0; c < tempSize; c++) {
						tempArr[i + c][j + tempSize - 1 - r] = arr[i + r][j + c];
					}
				}
			}
		}

		// 회전된 버전 기존 arr에 복사하기
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				arr[i][j] = tempArr[i][j];
			}
		}
	}
	
	// 얼음 줄어들기
	static void decrease() {
		List<int[]> list = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int cx = i + dx[d];
					int cy = j + dy[d];
					
					if (isIn(cx, cy) && arr[cx][cy] > 0) {
						cnt++;
					}
				}
				
				if (cnt < 3) {
					list.add(new int[] {i, j});
				}
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			int[] temp = list.get(i);
			
			if (arr[temp[0]][temp[1]] > 0) {
				arr[temp[0]][temp[1]]--;
			}
		}
	}
	
	// 남은 얼음 합
	static int total() {
		int sum = 0;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sum += arr[i][j];
			}
		}
		
		return sum;
	}
	
	// 해당 덩어리 칸 크기
	static int getSize(int x, int y) {
		
		int size = 0;
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {x, y});
		arr[x][y] = 0;
		size++;
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int cx = temp[0] + dx[d];
				int cy = temp[1] + dy[d];
				
				if (isIn(cx, cy) && arr[cx][cy] > 0) {
					queue.add(new int[] {cx, cy});
					arr[cx][cy] = 0;
					size++;
				}
			}
		}
		
		return size;
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 2^^N 만큼 격자 크기
		int N = Integer.parseInt(st.nextToken());
		// 파이어스톰 시전 횟수
		int Q = Integer.parseInt(st.nextToken());
		
		size = (int) Math.pow(2, N);
		
		arr = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 시전한 단계 정보
		levels = new int[Q];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			levels[i] = Integer.parseInt(st.nextToken());
		}
				
		for (int q = 0; q < Q; q++) {
			// 부분 격자로 나눠서 시계 방향으로 90도 회전시키기
			turn(q);
			
			// 얼음이 있는 칸 3개 또는 그 이상과 인접하지 않으면 얼음 양 1 줄어들음
			decrease();			
		}
		
		System.out.println(total());
		
		// 가장 큰 덩어리
		int maxArea = 0;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (arr[i][j] > 0) {
					maxArea = Integer.max(maxArea, getSize(i, j));
				}
			}
		}
		
		System.out.println(maxArea);
	}
}