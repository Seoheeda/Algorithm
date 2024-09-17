import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.concurrent.PriorityBlockingQueue;

class Edge implements Comparable<Edge>{
	int b;
	int l;
	
	public Edge(int b, int l) {
		super();
		this.b = b;
		this.l = l;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.l, o.l);
	}
}

public class Main {
	
	static int n, m;
	static int[] items; 
	static ArrayList<Edge>[] edges;
	
	static int dij(int start) {
		
		// 거리 초기화
    	int[] dist = new int[n + 1];
    	for (int i = 0; i < n + 1; i++) {
    		dist[i] = Integer.MAX_VALUE;
    	}
    	
    	dist[start] = 0;
    	
    	// 방문 여부
    	boolean[] visited = new boolean[n + 1];
    	
    	PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
    	queue.add(new Edge(start, 0));
    	
    	while (!queue.isEmpty()) {
    		Edge e = queue.poll();
    		
    		if (!visited[e.b]) {
    			visited[e.b] = true;
    		}
    		
    		for (int i = 0; i < edges[e.b].size(); i++) {
    			Edge ed = edges[e.b].get(i);
    			
    			if (!visited[ed.b] && e.l + ed.l < dist[ed.b]) {
    				dist[ed.b]= e.l + ed.l;
    				queue.add(new Edge(ed.b, dist[ed.b]));
    			}
    		}
    	}
    	
    	int sum = 0;
    	for (int i = 1; i < n + 1; i++) {
    		if (dist[i] <= m) {
    			sum += items[i];
    		}
    	}
    	
    	return sum;
	}
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	// 지역 개수
    	n = Integer.parseInt(st.nextToken());
    	// 예은이의 수색 범위
    	m = Integer.parseInt(st.nextToken());
    	// 길의 개수
    	int r = Integer.parseInt(st.nextToken());
    	
    	// 각 구역의 아이템 수
    	items = new int[n + 1];
    	st = new StringTokenizer(br.readLine());
    	for (int i = 1; i < n + 1; i++) {
    		items[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	edges = new ArrayList[n + 1];
    	for (int i = 1; i < n + 1; i++) {
    		edges[i] = new ArrayList<Edge>();
    	}
    	
    	for (int i = 0; i < r; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int l = Integer.parseInt(st.nextToken());
    		edges[a].add(new Edge(b, l));
    		edges[b].add(new Edge(a, l));
    	}
    	
    	int max = Integer.MIN_VALUE;
    	for (int i = 1; i < n + 1; i++) {
    		int temp = dij(i);
    		max = Integer.max(max, temp);
    	}
    	
    	System.out.println(max);
    }
}