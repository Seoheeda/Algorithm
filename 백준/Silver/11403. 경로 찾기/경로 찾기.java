import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] visited;
	static int[][] graph;
	
	public static void dfs(int v) {
		for (int i = 0; i < n; i++) {
			if (visited[i] == 0 && graph[v][i] == 1) {
				visited[i] = 1;
				dfs(i);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new int[n];
		
		for (int i = 0; i < n; i++) {
			dfs(i);
			StringBuilder sb = new StringBuilder();
			for (int num : visited) {
				sb.append(num + " ");
			}
			bw.append(sb + "\n");
			
			visited = new int[n];
		}
		
		bw.flush();
		bw.close();
		
	}
}