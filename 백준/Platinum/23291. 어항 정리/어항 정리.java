import java.io.*;
import java.util.*;

public class Main {
	
	static int N, K;
	static int[][] arr;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	// 범위 여부
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}
	
	// 물고기 수가 가장 적은 어항에 물고기 한 마리 넣기
	static void putFish() {
		
		int[] tempArr = Arrays.copyOf(arr[0], N);
		Arrays.sort(tempArr);
		
		int min = tempArr[0];
		
		for (int i = 0; i < N; i++) {
			if (arr[0][i] == min) {
				arr[0][i]++;
			}
		}
	}
	
	// 가장 왼쪽에 있는 어항 쌓기
	static void stackFish() {
		int stackValue = arr[0][0];
		
		arr[0][0] = 0;
		arr[1][1] = stackValue;
	}
	
	// 2개 이상 쌓여있는 어항 공중 부양하기
	static boolean stackMultiple() {
		// 오른쪽에 놓을 수 있는 공간 크기
		int rightSize = 0;
		
		for (int i = N - 1; i >= 0; i--) {
			if (arr[0][i] > 0 && arr[1][i] == 0) {
				rightSize++;
			}
		}
		
		// 1층 길이
		int length = 0;
		
		for (int i = 0; i < N; i++) {
			if (arr[0][i] > 0) {
				length = N - i;
				break;
			}
		}
				
		// 왼쪽 높이
		int leftHeight = 0;

		for (int i = 0; i < N; i++) {
			if (arr[i][N - length] == 0) {
				leftHeight = i;
				break;
			}
		}
		
		if (leftHeight > rightSize) {
			return false;
		}
				
		// 지금 쌓고있는 높이
		int curHeight = 1;
		
		for (int i = N - rightSize; i >= N - length + 1; i--) {
			for (int j = 0; j < leftHeight; j++) {
				arr[curHeight][N - rightSize + j] = arr[j][i - 1];
				arr[j][i - 1] = 0;
			}
			curHeight++;
		}
		
		return true;
	}
	
	// 물고기 수 조절
	static void changeFishCnt() {
		
		int[][] changeNeedCnt = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] > 0) {
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						if (isIn(nx, ny) && arr[nx][ny] > 0) {
							int diff = Math.abs(arr[i][j] - arr[nx][ny]);
							int div = diff / 5;
							
							if (div > 0) {
								if (arr[i][j] > arr[nx][ny]) {
									changeNeedCnt[i][j] -= div;
									changeNeedCnt[nx][ny] += div;
								}
							}
						}
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] += changeNeedCnt[i][j];
			}
		}
	}
	
	// 어항 일렬로
	static void oneLine() {
		
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[j][i] > 0) {
					list.add(arr[j][i]);
				} else {
					continue;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = 0;
			}
		}
		
		for (int i = 0; i < N; i++) {
			arr[0][i] = list.get(i);
		}
	}
	
	// 두번째 공중부양
	static void secondGongjoong() {
		int half = N / 2;
		
		for (int i = half - 1; i >= 0; i--) {
			arr[1][N - i - 1] = arr[0][i];
			arr[0][i] =  0;
		}
		
		int four = N / 4;
		
		int idx = N - four;
		for (int i = N - four - 1; i >= half; i--) {
			arr[2][idx] = arr[1][i];
			arr[1][i] =  0;
			idx++;
		}
		
		idx = N - four;
		for (int i = N - four - 1; i >= half; i--) {
			arr[3][idx] = arr[0][i];
			arr[0][i] =  0;
			idx++;
		}
	}
	
	// K 이하인가
	static boolean isUnderK() {
		
		int[] tempArr = Arrays.copyOf(arr[0], N);
		Arrays.sort(tempArr);
		
		int min = tempArr[0];
		int max = tempArr[tempArr.length - 1];
		
		if (max - min <= K) {
			return true;
		}
		
		return false;		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 어항의 수
		N = Integer.parseInt(st.nextToken());
		// 물고기 수 차이가 K 이하
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[0][i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;

		while (true) {
			
			// K 이하인가
			if (isUnderK()) {
				System.out.println(answer);
				return;
			}
			
			answer++;
						
			// 가장 물고기 적은 곳에 한마리 추가
			putFish();
			
			// 왼쪽 하나 쌓기
			stackFish();

			// 가능할 때까지 공중부양
			while (true) {
				boolean available = stackMultiple();
				
				if (!available ) {
					break;
				}
			}
			
			// 물고기 수 조절
			changeFishCnt();
			
			// 어항 일렬로
			oneLine();
			
			// 두번째 공중 부양
			secondGongjoong();

			// 물고기 수 조절
			changeFishCnt();
			
			// 어항 일렬로
			oneLine();
		}	
	}
}