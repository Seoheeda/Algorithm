import java.io.*;
import java.util.*;

public class Main {
	
	static int size;
	static int[][] arr, tempArr;
	
	static void moveSimple(int x, int y, int k, int l) {
		
		// 1번 연산
		if (k == 1) {
			for (int i = x; i < x + l; i++) {
				for (int j = y; j < y + l; j++) {
					tempArr[2 * x + l - i - 1][j] = arr[i][j];
				}
			}
		}
		
		// 2번 연산
		if (k == 2) {
			for (int i = x; i < x + l; i++) {
				for (int j = y; j < y + l; j++) {
					tempArr[i][2 * y + l - j - 1] = arr[i][j];
				}
			}
		}
		
		// 3번 연산
		if (k == 3) {
			for (int i = x; i < x + l; i++) {
				for (int j = y; j < y + l; j++) {
					int ni = x + (j - y);
		            int nj = y + (l - 1) - (i - x);
		            tempArr[ni][nj] = arr[i][j];
				}
			}
		}
		
		// 4번 연산
		if (k == 4) {
			for (int i = x; i < x + l; i++) {
				for (int j = y; j < y + l; j++) {
					int ni = x + (l - 1) - (j - y);
		            int nj = y + (i - x);
		            tempArr[ni][nj] = arr[i][j];
				}
			}
		}
	}
	
	static void hardMove(int k, int l) {
		
		// 5번 연산
		if (k == 5) {
			for (int i = 0; i < size - l + 1; i += l) {
				for (int j = 0; j < size - l + 1; j += l) {
					for(int a = 0; a < l; a++) {
						for (int b = 0; b < l; b++) {
							tempArr[(size - l) - i + a][j + b] = arr[i + a][j + b];
						}
					}
				}
			}
		}
		
		// 6번 연산
		if (k == 6) {
			for (int i = 0; i < size - l + 1; i += l) {
				for (int j = 0; j < size - l + 1; j += l) {
					for(int a = 0; a < l; a++) {
						for (int b = 0; b < l; b++) {
							tempArr[i + a][(size - l) - j + b] = arr[i + a][j + b];
						}
					}
				}
			}
		}
		
		// 7번 연산
		if (k == 7) {
			for (int i = 0; i < size - l + 1; i += l) {
				for (int j = 0; j < size - l + 1; j += l) {
					for (int a = 0; a < l; a++) {
						for (int b = 0; b < l; b++) {
							tempArr[j + a][size - i - l + b] = arr[i + a][j + b];
						}
					}
				}
			}
		}
		
		// 8번 연산
		if (k == 8) {
			for (int i = 0; i < size - l + 1; i += l) {
				for (int j = 0; j < size - l + 1; j += l) {
					for (int a = 0; a < l; a++) {
						for (int b = 0; b < l; b++) {
							tempArr[size - j - l + a][i + b] = arr[i + a][j + b];						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		size = (int) Math.pow(2, N);
		
		arr = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		tempArr = new int[size][size];
		
		// 연산들
		// k번 연산을 단계 l로 적용
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			int level = (int) Math.pow(2, l);
			
			if (k <= 4) {
				for (int a = 0; a < size; a += level) {
					for (int b = 0; b < size; b += level) {
						moveSimple(a, b, k, level);
					}
				}
			} else {
				hardMove(k, level);
			}
			
			int[][] t = arr;
			arr = tempArr;
			tempArr = t;
		}
		
		// 최종 출력
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
	}
}