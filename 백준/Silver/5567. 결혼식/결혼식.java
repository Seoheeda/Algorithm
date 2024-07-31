import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] graph;
	static boolean[] visited;
	static int cnt = 0;

	
	public static void BFS() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		visited[1] = true;
		
		int depth = 0;
		int[] level = new int[n + 1];
		level[1] = 0;
		
		while (!queue.isEmpty() ) {
			int size = queue.size();
			if (depth == 2) {
				break;
			}
			depth++;
						
			for (int i = 0; i < size; i++) {
				int v = queue.poll();

				for (int j = 1; j <= n; j++) {
					if (graph[v][j] == 1 && !visited[j]) {
						visited[j] = true;
						queue.add(j);
						level[j] = depth;
						cnt++;
					}
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		graph = new int[n + 1][n + 1];
		visited = new boolean[n + 1];
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			
			graph[a][b] = 1;
			graph[b][a] = 1;
			}
		
		BFS();
		System.out.println(cnt);
	}

}
