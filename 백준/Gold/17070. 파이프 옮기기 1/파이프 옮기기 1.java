import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 집의 크기
    	int N = Integer.parseInt(br.readLine());
    	
    	// 집 정보 저장하는 2차원 배열
    	int[][] house = new int[N][N];
    	
    	for (int i = 0; i < N; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < N; j++) {
    			house[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// dp 배열
    	// dp[0][][] : 가로 파이프 배열
    	// dp[1][][] : 세로 파이프 배열
    	// dp[2][][] : 대각선 파이프 배열
    	int [][][] dp = new int[3][N][N];
    	
    	// 첫번째 줄 값 초기화
    	dp[0][0][1] = 1;
    	for (int i = 2; i < N; i++) {
    		if (house[0][i] == 0) {
    			dp[0][0][i] = dp[0][0][i - 1];
    		}
    	}
    	
    	
    	// dp 수행
    	for (int i = 1; i < N; i++) {
    		for (int j = 1; j < N; j++) {
    			
    			if (house[i][j] == 0 && house[i - 1][j] == 0 && house[i][j - 1] == 0) {
    				dp[2][i][j] = dp[0][i - 1][j - 1] + dp[2][i - 1][j - 1] + dp[1][i - 1][j - 1];
    			}
    			
    			if (house[i][j] == 0) {
    				dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1];
    				dp[1][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j];
    			}
    		}
    	}
    	
    	// 정답 출력
		int ans = dp[0][N - 1][N - 1] + dp[1][N - 1][N - 1] + dp[2][N - 1][N - 1];
		
		System.out.println(ans);
    	
    }
}