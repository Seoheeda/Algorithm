import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 학생 수
    	int N = Integer.parseInt(br.readLine());
    	
    	// 학생들 점수 (나이순)
    	int[] grades = new int[N + 1];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	for (int i = 1; i <= N; i++) {
    		grades[i] = Integer.parseInt(st.nextToken());
    	}
    	    	
    	// dp 채우기
    	int[] dp = new int[N + 1];
    	for (int i = 1; i <= N; i++) {
    		int max = 0;
    		int min = 10000;
    		for (int j = i; j <= N; j++) {
    			max = Integer.max(max, grades[j]);
    			min = Integer.min(min, grades[j]);
    			dp[j] = Integer.max(dp[j], dp[i - 1] + max - min);
    		}
    	}
    	
    	System.out.println(dp[N]);  	
    }
}