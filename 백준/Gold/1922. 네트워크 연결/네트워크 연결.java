import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int start;
	int end;
	int cost;
	
	public Node(int start, int end, int cost) {
		super();
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.cost, o.cost);
	}
}
 
public class Main {
	
	static int[] parents;
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b) {
			return false;
		} else {
			parents[b] = find(a);
			return true;
		}
		
	}
	
	static int find(int a) {
		
		if (a == parents[a]) {
			return a;
		} else {
			parents[a] = find(parents[a]);
			return parents[a];
		}
		
	}
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 컴퓨터 수
        int N = Integer.parseInt(br.readLine());
        // 연결할 선의 수
        int M = Integer.parseInt(br.readLine());
        
        // 우선순위 큐
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        
        for (int i = 0; i < M; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	queue.add(new Node(s, e, c));
        }
        
        // 부모 담음
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
        	parents[i] = i;
        }
        
        int sum = 0;
        int cnt = 0;
        
        while (!queue.isEmpty()) {
        	Node no = queue.poll();
        	
        	int s = no.start;
        	int e = no.end;
        	int c = no.cost;
        	
        	if (union(s, e)) {
        		sum += c;
        		cnt++;
        		
        		if (cnt == N - 1) {
        			break;
        		}		
        	}
        }
        
        System.out.println(sum);
  
    }
}