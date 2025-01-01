import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 집 개수
		int N = Integer.parseInt(br.readLine());
		
		int INF = Integer.MAX_VALUE;
		
		// 비용 정보
		int[][] costs = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			costs[i][0] = r;
			costs[i][1] = g;
			costs[i][2] = b;
		}
		
		int[][] costsR = new int[N][3];
		int[][] costsG = new int[N][3];
		int[][] costsB = new int[N][3];
		
		// 첫번째가 빨간색임
		costsR[0][0] = costs[0][0];
		costsR[0][1] = INF;
		costsR[0][2] = INF;
		
		costsR[1][0] = INF;
		costsR[1][1] = costs[0][0] + costs[1][1]; 
		costsR[1][2] = costs[0][0] + costs[1][2]; 
		
		for (int i = 2; i < N; i++) {
			costsR[i][0] = costs[i][0] + Integer.min(costsR[i - 1][1], costsR[i - 1][2]);
			costsR[i][1] = costs[i][1] + Integer.min(costsR[i - 1][0], costsR[i - 1][2]);
			costsR[i][2] = costs[i][2] + Integer.min(costsR[i - 1][0], costsR[i - 1][1]);
		}
		int ansR = Integer.min(costsR[N - 1][1], costsR[N - 1][2]);
		
		// 첫번째가 초록색임
		costsG[0][0] = INF;
		costsG[0][1] = costs[0][1];
		costsG[0][2] = INF;
		
		costsG[1][0] = costs[0][1] + costs[1][0]; 
		costsG[1][1] = INF;
		costsG[1][2] = costs[0][1] + costs[1][2]; 
		
		for (int i = 2; i < N; i++) {
			costsG[i][0] = costs[i][0] + Integer.min(costsG[i - 1][1], costsG[i - 1][2]);
			costsG[i][1] = costs[i][1] + Integer.min(costsG[i - 1][0], costsG[i - 1][2]);
			costsG[i][2] = costs[i][2] + Integer.min(costsG[i - 1][0], costsG[i - 1][1]);
		}
		int ansG = Integer.min(costsG[N - 1][0], costsG[N - 1][2]);

		// 첫번째가 파란색임
		costsB[0][0] = INF;
		costsB[0][1] = INF;
		costsB[0][2] = costs[0][2];
		
		costsB[1][0] = costs[0][2] + costs[1][0]; 
		costsB[1][1] = costs[0][2] + costs[1][1]; 
		costsB[1][2] = INF;
		
		for (int i = 2; i < N; i++) {
			costsB[i][0] = costs[i][0] + Integer.min(costsB[i - 1][1], costsB[i - 1][2]);
			costsB[i][1] = costs[i][1] + Integer.min(costsB[i - 1][0], costsB[i - 1][2]);
			costsB[i][2] = costs[i][2] + Integer.min(costsB[i - 1][0], costsB[i - 1][1]);
		}
		int ansB = Integer.min(costsB[N - 1][0], costsB[N - 1][1]);

		// 답 도출
		int ans = Integer.min(ansR, ansG);
		ans = Integer.min(ans, ansB);
		
		System.out.println(ans);
		
	}
}