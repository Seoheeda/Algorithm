import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int start;
	int end;
	int cost;
	
	public Edge(int start, int end, int cost) {
		super();
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.cost, o.cost);
	}	
}

public class Main {
	
	static int[] parents;
	
	static int find(int target) {
		
		if (target == parents[target]) {
			return target;
		} else {
			parents[target] = find(parents[target]);
			return parents[target];
		}
	}
	
	static boolean union(int a, int b) {
		
		a = find(a);
		b = find(b);
		
		if (a == b) {
			return false;
		} else {
			parents[b] = a;
			return true;
		}
		
	}
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	// 집의 개수
    	int N = Integer.parseInt(st.nextToken());
    	// 길의 개수
    	int M = Integer.parseInt(st.nextToken());
    	
    	PriorityQueue<Edge> queue = new PriorityQueue<>();
    	
    	// 길 유지비 정보 저장
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int s = Integer.parseInt(st.nextToken());
    		int e = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		queue.add(new Edge(s, e, c));
       	}
    	
    	// 부모 정보 초기화
    	parents = new int[N + 1];
    	for (int i = 0; i < N + 1; i++) {
    		parents[i] = i;
    	}
    	
    	// 연결된 정점 개수
    	int cnt = 1;
    	// 연결된 유지비
    	int connectSum = 0;
    	
    	// 연결한 간선 중 가장 비싼 유지비
    	int maxCost = 0;
    	
    	while (true) {
    		
    		// 큐가 비었거나, 모든 정점이 연결되었다면 종료
    		if (queue.isEmpty() || cnt == N) {
    			break;
    		}
    		
    		Edge edge = queue.poll();
    		
    		if (union(edge.start, edge.end)) {
    			connectSum += edge.cost;
    			cnt++;
    			
    			// 연결된 간선 중 가장 큰 유지비
    			maxCost = Integer.max(maxCost, edge.cost);
    		}
    	}
    	
    	System.out.println(connectSum - maxCost);
    	
    }
}