import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int[][] graph;
	static int[] visited;
	static int len, start;
	static int maxCnt, max;
	static Queue<int[]> queue;
	
	public static void check() {
		
		// 큐가 비면 : 더이상 갈 곳이 없음
		while (!queue.isEmpty()) {
			// 현재 큐 길이만큼 반복
			int n = queue.size();
			for (int j = 0; j < n; j++) {
				int[] item = queue.poll();
				int t = item[0];
				int c = item[1];
//				// 방문 처리
//				visited[t] = 1;

				// 최대 이동 거리라면 -> 최댓값 판단
				if (maxCnt < c) {
					max = t;
					maxCnt = c;
				} else if (maxCnt == c) {
					if (max < t) {
						max = t;
					}
				}
				
				// 이동 가능한 정점 큐에 넣기
				for (int i = 1; i < 101; i++) {
					if (visited[i] == 0 && graph[t][i] == 1) {
						queue.add(new int[] {i, c + 1});
						// 방문 처리
						visited[i] = 1;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 총 10개
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 데이터 길이
			len = Integer.parseInt(st.nextToken());
			// 시작점
			start = Integer.parseInt(st.nextToken());
			
			// 인접행렬
			graph = new int[101][101];
			// 방문여부
			visited = new int[101];
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < len / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from][to] = 1;
			}
			
			// 최대 이동 횟수
			maxCnt = -1;
			// 최대 이동 횟수에서의 최댓값
			max = -1;
			// 큐에 초기값 저장
			queue = new ArrayDeque<int[]>();
			queue.add(new int[] {start, 0});
			
			check();
			
			System.out.println("#" + t + " " + max);
		}

	}

}