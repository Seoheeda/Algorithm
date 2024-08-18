import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int white;
	static int blue;
	static int[][] paper;
	
	// 색종이 4분면으로 나누며 all_white, all_blue 여부 확인
	private static void divide(int row, int col, int size) {
		
		if (all_white(row, col, size)) {
			white++;
			return;
		} else if (all_blue(row, col, size)) {
			blue++;
			return;
		} 
		
		int n_size = size / 2;
		divide(row, col + n_size, n_size);  // 1사분면
		divide(row, col, n_size);  // 2사분면
		divide(row + n_size, col, n_size);  // 3사분면
		divide(row + n_size, col + n_size, n_size);  // 4사분면

	}
	
	// 모든 칸이 흰색인지 확인
	private static boolean all_white(int row, int col, int size) {
		
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (paper[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	// 모든 칸이 파란색인지 확인
	private static boolean all_blue(int row, int col, int size) {
		
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (paper[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 전체 종이 한 변의 길이
    	int N = Integer.parseInt(br.readLine());
    	
    	// 2차원 배열에 색종이 정보 저장
    	paper = new int[N][N];
    	
    	for (int i = 0; i < N; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < N; j++) {
    			paper[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// 흰 종이 개수, 파란 종이 개수 초기화
    	white = 0;
    	blue = 0;
    	
    	// 분할정복
    	divide(0, 0, N);
    	
    	System.out.println(white);
    	System.out.println(blue);
    	
    }
}