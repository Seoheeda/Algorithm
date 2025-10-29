import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static ArrayList<Integer>[] graph;
	static int[] degree;
	static List<Integer> list;
	
	// 위상정렬
	static void sort() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		// 확정된 순서
		list = new ArrayList<Integer>();
		
		for (int i = 1; i < N + 1; i++) {
			if (degree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int singer = queue.poll();
			list.add(singer);
			
			for (int i = 0; i < graph[singer].size(); i++) {
				int nextSinger = graph[singer].get(i);
				degree[nextSinger]--;
				
				if (degree[nextSinger] == 0) {
					queue.add(nextSinger);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 가수 수
		N = Integer.parseInt(st.nextToken());
		// 보조 PD 수
		M = Integer.parseInt(st.nextToken());
		
		// 인접 리스트
		graph = new ArrayList[N + 1];
		
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		// 차수 정보
		degree = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cnt = Integer.parseInt(st.nextToken());
			
			int prev = Integer.parseInt(st.nextToken());
			for (int j = 1; j < cnt; j++) {
				int singer = Integer.parseInt(st.nextToken());
				
				graph[prev].add(singer);
				degree[singer]++;
				
				prev = singer;
			}
		}
		
		// 위상정렬 수행
		sort();
		
		// 정답 출력
		StringBuilder sb = new StringBuilder();
		
		if (list.size() == N) {
			for (int i = 0; i < N; i++) {
				sb.append(list.get(i)).append("\n");;
			}
		} else {
			System.out.println(0);
		}
		
		System.out.println(sb);
	}
}
