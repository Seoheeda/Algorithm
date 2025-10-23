import java.io.*;
import java.util.*;

public class Main {
	
	static boolean[] visited;
	static int[] candyCnt;
	static List<Integer>[] friends;
	
	// 친구 그룹화
	static int[] findGroup(int start) {
		
		List<Integer> group = new ArrayList<Integer>();
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(start);
		visited[start] = true;
		group.add(start);
		
		while (!queue.isEmpty()) {
			int temp = queue.poll();
			
			for (int tempFriend : friends[temp]) {
				if (!visited[tempFriend]) {
					group.add(tempFriend);
					visited[tempFriend] = true;
					queue.add(tempFriend);
				}
			}
		}
		
		int candySum = 0;
		int cntSum = 0;
		
		for (int num : group) {
			candySum += candyCnt[num];
			cntSum++;
		}
		
		return new int[] {cntSum, candySum};
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 아이들 수
		int N = Integer.parseInt(st.nextToken());
		// 친구 관계 수
		int M = Integer.parseInt(st.nextToken());
		// 울음 소리 공명 위한 최소 아이 수
		int K = Integer.parseInt(st.nextToken());
		
		// 아이들이 받은 사탕 수
		candyCnt = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			candyCnt[i + 1] = Integer.parseInt(st.nextToken());
		}
				
		// 친구 정보
		friends = new List[N + 1];
		
		for (int i = 0; i < N + 1; i++) {
			friends[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			friends[a].add(b);
			friends[b].add(a);
		}
		
		// 친구 그룹화
		
		visited = new boolean[N + 1];
		List<int[]> groups = new ArrayList<int[]>();
		
		for (int i = 1; i < N + 1; i++) {
			if (!visited[i]) {
				int[] group = findGroup(i);
				groups.add(group);
			}
		}
		
		// 냅색 알고리즘
		int[] dp = new int[K];  

		for (int[] g : groups) {
			
			// 그룹 인원수
		    int cnt = g[0];
		    // 그룹 사탕수
		    int candy = g[1];

		    for (int i = K - 1; i >= cnt; i--) { 
		        dp[i] = Math.max(dp[i], dp[i - cnt] + candy);
		    }
		}

		System.out.println(dp[K - 1]);
	}
}