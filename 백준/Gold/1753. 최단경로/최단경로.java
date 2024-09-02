import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
class Node implements Comparable<Node> {
	
	int end;
	int weight;

	public Node(int end, int weight) {
		super();
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.weight, o.weight);
	}	
}


public class Main {
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 정점 개수
        int V = Integer.parseInt(st.nextToken());
        // 간선 개수
        int E = Integer.parseInt(st.nextToken());
        // 시작 정점 번호
        int K = Integer.parseInt(br.readLine());
        
        // 인접 리스트
        List<Node>[] list = new ArrayList[V + 1];
        
        for (int i = 1; i <= V; i++) {
        	list[i] = new ArrayList<>();
        }
        
        // 경로값 초기화
        int[] dist = new int[V + 1];
        
        for (int i = 0; i <= V; i++) {
        	dist[i] = Integer.MAX_VALUE;
        }
        
        // 시작점 초기화
        dist[K] = 0;
        
        for (int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	list[a].add(new Node(b, c));
        }
        
        // 방문 처리 배열
        boolean[] visited = new boolean[V + 1];
        
        // 큐에 첫번째 노트 넣기
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(K, 0));
        
        while (!queue.isEmpty()) {
        	Node no = queue.poll();
        	
        	// 방문처리 해주기
        	if (!visited[no.end]) {
        		visited[no.end] = true;
        	}
        	
        	// 현재 정점과 연결된 간선들에 대해서
        	for (int i = 0; i < list[no.end].size(); i++) {
        		// 현재 정점에서 이어진 다음 정점
        		Node ne = list[no.end].get(i);
        		
        		// 아직 방문 전인 정점이고
        		// 최단 경로 값이라면
        		if (!visited[ne.end] && no.weight + ne.weight < dist[ne.end]) {
        			// 최단 경로 갱신
        			dist[ne.end] = no.weight + ne.weight;
        			// queue에 삽입
        			queue.add(new Node(ne.end, dist[ne.end]));
        		}
        	}
        }
       
        for (int i = 1; i < V + 1; i++) {
    		if (dist[i] == Integer.MAX_VALUE) {
    			System.out.println("INF");
    		} else {
    			System.out.println(dist[i]);
    		}
    	}
    }
}