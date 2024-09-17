import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int start;
	int end;
	int dist;
	
	public Edge(int start, int end, int dist) {
		super();
		this.start = start;
		this.end = end;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.dist, o.dist);
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
    	
    	while (true) {
    		
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		// 집의 수
    		int m = Integer.parseInt(st.nextToken());
    		// 길의 수
    		int n = Integer.parseInt(st.nextToken());
    		
    		if (m == 0 && n == 0) {
    			break;
    		}
    		
    		// 우선순위 큐에 간선 정보 저장
    		PriorityQueue<Edge> queue = new PriorityQueue<>();
    		int sum = 0;
    		for (int i = 0; i < n; i++) {
    			st =  new StringTokenizer(br.readLine());
    			int x = Integer.parseInt(st.nextToken());
    			int y = Integer.parseInt(st.nextToken());
    			int z = Integer.parseInt(st.nextToken());
    			Edge e = new Edge(x, y, z);
    			queue.add(e);
    			// 모든 거리의 합
    			sum += z;
    		}
    		
    		// 부모 정보 저장
    		parents = new int[m + 1]; 
    		for (int i = 0; i < m + 1; i++) {
    			parents[i] = i;
    		}
    		
    		// 연결된 거리
    		int conSum = 0;
    		while (!queue.isEmpty()) {
    			Edge e = queue.poll();
    			int x = e.start;
    			int y = e.end;
    			int z = e.dist;
    			
    			// 사이클이 없는 간선이라면 추가하기
    			if (union(x, y)) {
    				union(x, y);
    				conSum += z;
    			}
    		}
    		
    		System.out.println(sum - conSum);
    		
    	}
    }
}