import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
	
	static int N;
	static int[][] prices;
	static boolean[] visited;
	static int[] arr;
	static int ans;
	
	public static void dfs(int depth) {
		
		if (depth == N) {
			// 비용 더할 곳
			int temp = 0;
			
			// 순열에 따라 비용 더하기
			// 불가능하면 (길이 없어 비용이 0이면) return
			for (int i = 0; i < N - 1; i++) {
				if (prices[arr[i]][arr[i + 1]] == 0) {
					return;
				} else {
					temp += prices[arr[i]][arr[i + 1]];
				}
			}
			
			// 마지막 돌아오는 길
			if (prices[arr[N - 1]][arr[0]] == 0) {
				return;
			} else {
				temp += prices[arr[N - 1]][arr[0]];
			}
			
			// 정답 (최소값) 갱신
			ans = Integer.min(ans, temp);
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				dfs(depth + 1);
				visited[i] = false;
			}
		}
	}
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        // 도시 i에서 도시 j로 가기 위한 비용
        prices = new int[N][N];
        for (int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		prices[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 순열 생성을 위한 배열
        visited = new boolean[N];
        arr = new int[N];
        // 정답 : 최소값 초기화
        ans = Integer.MAX_VALUE;
        
        dfs(0);
        
        System.out.println(ans);
 
    }
 
}