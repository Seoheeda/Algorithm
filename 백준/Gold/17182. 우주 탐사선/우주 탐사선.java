import java.io.*;
import java.util.*;

public class Main {
	
	static int N, K, ans;
	static int[][] times;
	static boolean[] visited;

	// 이번에 방문할 행성, 방문한 행성 수, 지금 시간
	static void dfs(int planet, int visitedCnt, int time) {
		
		// N개 행성 다 탐사했으면 끝
		if (visitedCnt == N) {
			ans = Math.min(ans, time);
			return;
		}
		
		// 아직 다 탐사한건 아닌데 벌써 초과하면 끝
		if (time >= ans) {
			return;
		}
		
		for (int i = 0; i < N; i++) {	
			// 아직 시간 ans보다 적으면 킵고잉
			if (!visited[i]) {
				visited[i] = true;
				dfs(i, visitedCnt + 1, time + times[planet][i]);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 행성의 개수
		N = Integer.parseInt(st.nextToken());
		// 행성의 위치
		K = Integer.parseInt(st.nextToken());
		
		// 행성간 이동시간
		times = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				times[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 플로이드-워셜: 모든 행성 간 최단 시간 계산
		for (int k = 0; k < N; k++) {
		    for (int i = 0; i < N; i++) {
		        for (int j = 0; j < N; j++) {
		            if (times[i][j] > times[i][k] + times[k][j]) {
		                times[i][j] = times[i][k] + times[k][j];
		            }
		        }
		    }
		}

		// 방문 배열
		visited = new boolean[N];
		
		// 최소 시간
		ans = Integer.MAX_VALUE;
		
		// K번부터 시작
		visited[K] = true;
		dfs(K, 1, 0);
		
		System.out.println(ans);
	}
}