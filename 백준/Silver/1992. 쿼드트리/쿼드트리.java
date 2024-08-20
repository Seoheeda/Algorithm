import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int[][] screen;
	public static StringBuilder sb = new StringBuilder();
	
	// 같은 숫자의 점들 구성인지 확인
	private static int check(int row, int col, int size) {
		
		int start = screen[row][col];
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (screen[i][j] != start) {
					// 다른 숫자가 존재한다면 2 리턴
					return 2;
				}
			}
		}
		// 1로만 구성되면 1 리턴
		// 2로만 구성되면 2 리턴
		return start;
	}
	
	// 분할정복 메서드
	private static void divide(int row, int col, int size) {
		
		// 만일 같은 숫자로만 구성되었다면, 구성 숫자 (1 또는 0)를 sb에 추가
		if (check(row, col, size) != 2) {
			sb.append(check(row, col, size));
			return;
		}
		
		// 분할할 때마다 괄호로 감싸주기
		sb.append("(");
		
		// 1사분면
		divide(row, col, size / 2);
		// 2사분면
		divide(row, col + size / 2, size / 2);
		// 3사분면
		divide(row + size / 2, col, size / 2);
		// 4사분면
		divide(row + size / 2, col + size / 2, size / 2);
		
		sb.append(")");
		
	}
   
    public static void main(String[] args) throws NumberFormatException, IOException {
         
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 영상의 크기
    	int N = Integer.parseInt(br.readLine());
    	
    	// 영상 배열 저장
    	screen = new int[N][N];
    	
    	for (int i = 0; i < N; i++) {
    		String line = br.readLine();
    		for (int j = 0; j < N; j++) {
    			screen[i][j] = line.charAt(j) - '0';
    		}
    	}
    	
    	// 분할정복 시작
    	divide(0, 0, N);
    	
    	// 정답 출력
    	System.out.println(sb);
    	
    }
}