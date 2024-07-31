import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] visited1;
	private static int[] visited2;
	private static int[][] graph;
	static StringBuilder sb1 = new StringBuilder();
	static StringBuilder sb2 = new StringBuilder();
	static int n;
	
	public static void DFS(int v) {
		visited1[v] = 1;
		sb1.append(v + " ");
		for (int i = 1; i < n + 1; i++) {
		;	if (visited1[i] == 0 && graph[v][i] == 1) {
				DFS(i);
			}
		}
	}
	
	public static void BFS(int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		visited2[v] = 1;
		
		while (!queue.isEmpty()) {
			v = queue.poll();
			sb2.append(v + " ");
			
			for (int i = 1; i < n + 1; i++) {
				if (visited2[i] == 0 && graph[v][i] == 1) {
					queue.add(i);
					visited2[i] = 1;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		graph = new int[n + 1][n + 1];
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		
		visited1 = new int[n + 1];
		visited2 = new int[n + 1];

		DFS(v);
		bw.write(sb1.toString());
		
		bw.write("\n");
		
		BFS(v);
		bw.write(sb2.toString());
		
		bw.flush();
		bw.close();
		
	}

}
