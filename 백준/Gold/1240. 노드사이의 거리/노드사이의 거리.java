import java.io.*;
import java.util.*;

public class Main {
	
	// 노드 정보
	static class node {
		int next, len;

		public node(int next, int len) {
		    this.next = next;
			this.len = len;
		}
	}
	
	static int N, M;
	static ArrayList<node>[] tree;
	static Queue<node> queue;
	
	// 두 노드 사이의 거리 계산하는 함수
	static int calc(int start, int end) {
		
		queue.add(new node(start, 0));
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			node next = queue.poll();
			if (next.next == end) {
				return next.len;
			}
			
			for (node no : tree[next.next]) {
				if (!visited[no.next]) {
					queue.add(new node(no.next, next.len + no.len));
					visited[no.next] = true;
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 노드 개수
		N = Integer.parseInt(st.nextToken());
		// 거리 알고싶은 노트 쌍 개수
		M = Integer.parseInt(st.nextToken());
		
		// 인접리스트
		tree = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<node>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			tree[a].add(new node(b, c));
			tree[b].add(new node(a, c));
		}
		
		queue = new ArrayDeque<node>();
				
		// 정답 GET
		for (int i = 0; i < M; i++) {
			queue.clear();
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			System.out.println(calc(s, e));
		}	
	}
}