import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 노드 정보 : 가중치 기준으로 오름차순 정렬
class Node implements Comparable<Node>{
	int a;
	int b;
	int c;
	
	public Node(int a, int b, int c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public int compareTo(Node o) {
		if (this.c == o.c) {
			if (this.b == o.b) {
				return Integer.compare(this.a, o.a);
			}
			return Integer.compare(this.b, o.b);
		}
		return Integer.compare(this.c, o.c);
	}
}

public class Main {
	
	static int[] parents;
	
	// 부모 찾기
	private static int find(int target) {
		
		if (parents[target] == target) {
			return target;
		} else {
			parents[target] = find(parents[target]);
			return parents[target];
		}
	}
	
	// 부모가 같으면 true, 다르면 false
	private static boolean union(int x, int y) {
		
		x = find(x);
		y = find(y);
		
		if (x == y) {
			return false;
		} else {
			parents[y] = x;
			return true;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 정점 개수
		int V = Integer.parseInt(st.nextToken());
		// 간선 개수
		int E = Integer.parseInt(st.nextToken());
		
		// 1 ~ V 노드 정보 담아둠
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
		
		// 노드 정보 담는 리스트
		List<Node> list = new ArrayList<>();
	
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			list.add(new Node(A, B, C));
		}
		
		// 정렬하기 : 가중치 기준 오름차순
		Collections.sort(list);
		
		int sum = 0;
		int cnt = 0;
		
		for (Node node : list) {
			// 부모가 같으면 : 이 간선이 추가되어도 사이클 만들지 않음
			if (union(node.a, node.b)) {
				// 간선 더하기 (가중치 더하기, 간선 개수 더하기)
				sum += node.c;
				cnt++;
				
				// 모든 간선이 포함됨 --> 탐색 종료
				if (cnt == V - 1) {
					break;
				}
			}
		}
		
		System.out.println(sum);
		
	}
}