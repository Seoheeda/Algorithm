import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 지도 크기
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        // 조사 대상 영역 개수
        int K = Integer.parseInt(br.readLine());
        
        // 정글 수
        int[][] jungle = new int[M + 1][N + 1];
        
        // 바다 수
        int[][] ocean = new int[M + 1][N + 1];
        
        // 얼음 수
        int[][] ice = new int[M + 1][N + 1];
        
        // 지도 정보
        char[][] map = new char[M + 1][N + 1];
        for (int m = 1; m < M + 1; m++) {
        	String line = br.readLine();
        	for (int n = 0; n < N; n++) {
        		map[m][n + 1] = line.charAt(n);
        		if (map[m][n + 1] == 'J') {
        			jungle[m][n + 1] = 1;
        		} else if (map[m][n + 1] == 'O') {
        			ocean[m][n + 1] = 1;
        		} else {
        			ice[m][n + 1] = 1;
        		}
        	}
        }
        
        // 누적합 배열 (가로)
        for (int i = 0; i < M + 1; i++) {
        	for (int j = 1; j < N + 1; j++) {
        		jungle[i][j] += jungle[i][j - 1];
        		ocean[i][j] += ocean[i][j - 1];
        		ice[i][j] += ice[i][j - 1];

        	}
        }
        
        // 누적합 배열 (세로)
        for (int j = 0; j < N + 1; j++) {
        	for (int i = 1; i < M + 1; i++) {
        		jungle[i][j] += jungle[i - 1][j];
        		ocean[i][j] += ocean[i - 1][j];
        		ice[i][j] += ice[i - 1][j];

        	}
        }
        
        // 조사
        for (int k = 0; k < K; k++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());

        	int jungleCnt = 0;
        	int oceanCnt = 0;
        	int iceCnt = 0;
        	
        	jungleCnt += (jungle[c][d] - jungle[a - 1][d] - jungle[c][b - 1] + jungle[a - 1][b - 1]);
        	oceanCnt += (ocean[c][d] - ocean[a - 1][d] - ocean[c][b - 1] + ocean[a - 1][b - 1]);
        	iceCnt += (ice[c][d] - ice[a - 1][d] - ice[c][b - 1] + ice[a - 1][b - 1]);

        	// 정답 출력
        	System.out.println(jungleCnt + " " + oceanCnt + " " + iceCnt);
        }
    }
}