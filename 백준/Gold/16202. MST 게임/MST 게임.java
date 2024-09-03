import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
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
		if (this.cost == o.cost) {
			if (this.end == o.end) {
				return Integer.compare(this.start, o.start);
			}
			return Integer.compare(this.end, o.end);
		}
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	
	// 부모 정보 저장
	static int[] parents;
	
	// union-find
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
		
		if (parents[a] == a) {
			return a;
		} else {
			parents[a] = find(parents[a]);
			return parents[a];
		}
	}
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st =  new StringTokenizer(br.readLine());
        // 정점 개수
        int N = Integer.parseInt(st.nextToken());
        // 간선 개수
        int M = Integer.parseInt(st.nextToken());
        // 턴의 수
        int K = Integer.parseInt(st.nextToken());
        
        // 간선 정보
        List<Node> queue = new ArrayList<>();
        
        for (int i = 1; i <= M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	queue.add(new Node(s, e, i));
        }
        
        // 간선 가중치 기준 정렬
        Collections.sort(queue);
        
        parents = new int[N + 1];
        
        // 총 턴 만큼 반복
        for (int i = 0; i < K; i++) {
        	
        	// 큐 복사하기
        	List<Node> tempQ = new ArrayList<>();
            for (int k = 0; k < queue.size(); k++) {
            	tempQ.add(queue.get(k));
            }
            
            // parents 배열 초기화하기
            for (int l = 1; l <= N; l++) {
            	parents[l] = l;
            }
        	
            // 가중치 합, 방문 노드 수 초기화
        	int sum = 0;
            int cnt = 0;
            
            int len = tempQ.size();

            for (int j = 0; j < len; j++) {
            	Node no = tempQ.get(j);
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
            
            // MST 없으면 0, 있으면 sum
            if (cnt != N - 1) {
            	sb.append(0).append(" ");
            } else {
                sb.append(sum).append(" ");
            }
            
            // 가중치 가장 작은 노드 제거
            queue.remove(0);
        }
        
        System.out.println(sb);
         
    }
}