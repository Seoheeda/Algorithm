import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 노드 클래스
class Node implements Comparable<Node> {
	
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

public class Solution {
	
	static int[] parents;
	
	// 부모 찾기
	public static int find(int target) {
		if (parents[target] == target) {
			return target;
		} else {
			parents[target] = find(parents[target]);
			return parents[target];
		}
	}
	
	// 부모 같으면 true, 다르면 false
	public static boolean union(int a, int b) {
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 정점 개수
			int V = Integer.parseInt(st.nextToken());
			// 간선 개수
			int E = Integer.parseInt(st.nextToken());
			
			// 1 ~ V 노드 정보 담기
			parents = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				parents[i] = i;
			}
			
			// 간선 리스트
			List<Node> list = new ArrayList<>();
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				Node node = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				list.add(node);
			}
			// 가중치 기준으로 오름차순 정렬
			Collections.sort(list);
			
			int cnt = 0;
			long sum = 0;
			
			for (Node node : list) {
				if (union(node.a, node.b )) {
					sum += node.c;
					cnt++;
					
					if (cnt == E) {
						break;
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(sum).append("\n");
		}
		
		bw.append(sb);
		bw.flush();
		bw.close();
		
	}
}