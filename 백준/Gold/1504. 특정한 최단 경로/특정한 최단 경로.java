import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 

class Node implements Comparable<Node>{
	int end;
	int dist;
	
	public Node(int end, int dist) {
		super();
		this.end = end;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.dist, o.dist);
	}	
}

public class Main {
	
	static int N;
	static List<Node>[] nodes;
	
	// 출발, 도착 넣으면 최소 거리 반환
	static int dij (int start, int end) {
		// 거리 초기화
    	int[] dist = new int[N + 1];
    	for (int i = 0; i <= N; i++) {
    		dist[i] = 1000 * N;
    	}
    	
    	dist[start] = 0;
    	
    	// 방문 여부
    	boolean[] visited = new boolean[N + 1];
    	
    	PriorityQueue<Node> queue = new PriorityQueue<>();
    	queue.add(new Node(start, 0));
    	
    	while (!queue.isEmpty()) {
    		Node node = queue.poll();
    		
    		if (!visited[node.end]) {
    			visited[node.end] = true;
    		}

    		
    		for (int i = 0; i < nodes[node.end].size(); i++) {
    			Node no = nodes[node.end].get(i);
    			
    			if (!visited[no.end] && node.dist + no.dist < dist[no.end]) {
    				dist[no.end] = node.dist + no.dist;
    				queue.add(new Node(no.end, dist[no.end]));
    			}
    		}
    	}
    	
    	return dist[end];
	}
	    
    public static void main(String[] args) throws Exception {
  
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	// 정점 개수
    	N = Integer.parseInt(st.nextToken());
    	// 간선 개수
    	int E = Integer.parseInt(st.nextToken());
    	
    	// 길 저장
    	nodes = new ArrayList[N + 1];
    	for (int i = 1; i <= N; i++) {
    		nodes[i] = new ArrayList<Node>();
    	}
    	
    	for (int i = 0; i < E; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		nodes[a].add(new Node(b, c));
    		nodes[b].add(new Node(a, c));
    	}
    	
    	// 필수 정점들
    	st = new StringTokenizer(br.readLine());
    	int V1 = Integer.parseInt(st.nextToken());
    	int V2 = Integer.parseInt(st.nextToken());
    	
    	// 1 -> V1 -> V2 -> N
    	int r1 = dij(1, V1);
    	int r2 = dij(V1, V2);
    	int r3 = dij(V2, N);
    	int route1 = r1 + r2 + r3;
    	
    	// 1 -> V2 -> V1 -> N\
    	int r4 = dij(1, V2);
    	int r5 = dij(V2, V1);
    	int r6 = dij(V1, N);
    	int route2 = r4 + r5 + r6;
    	
    	// 경로 없으면 -1 출력
    	if (r1 >= 1000 * N || r2 >= 1000 * N || r3 >= 1000 * N) {
    		System.out.println(-1);
       	// 두 경우의수 중 작은 값 출력
    	} else if (r4 >= 1000 * N || r5 >= 1000 * N || r6 >= 1000 * N) {
        	System.out.println(-1);
    	} else {
        	System.out.println(Integer.min(route1, route2));
    	}

  	
    	
    }
}