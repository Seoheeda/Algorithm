import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int[][] arr;
	
	static int bfsSmall(int target) {
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(target);

		boolean[] visited = new boolean[N + 1];
		visited[target] = true;
		
		int cntSmall = 0;
		
		while (!queue.isEmpty()) {
			int stu = queue.poll();
			
			for (int i = 1; i < N + 1; i++) {
				if (arr[i][stu] == 1 && visited[i] == false) {
					queue.add(i);
					visited[i] = true;
					cntSmall++;
				}
			}
		}
		
		return cntSmall;
	}
	
	static int bfsBig(int target) {
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(target);

		boolean[] visited = new boolean[N + 1];
		visited[target] = true;
		
		int cntBig = 0;
		
		while (!queue.isEmpty()) {
			int stu = queue.poll();
			
			for (int i = 1; i < N + 1; i++) {
				if (arr[stu][i] == 1 && visited[i] == false) {
					queue.add(i);
					visited[i] = true;
					cntBig++;
				}
			}
		}
		
		return cntBig;
	}
	
	
    public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
        	
        	// 학생 수
        	N = Integer.parseInt(br.readLine());
        	// 키 비교 횟수
        	int M = Integer.parseInt(br.readLine());
        	
        	arr = new int[N + 1][N + 1];
        	
        	for (int i = 0; i < M; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		arr[a][b] = 1;        		
        	}
        	
        	int cnt = 0;
        	
        	for (int i = 1; i < N + 1; i++) {
        		if (bfsBig(i) + bfsSmall(i) == N - 1) {
        			cnt++;
        		}
        	}
        	
        	System.out.println("#" + t + " " + cnt);
        }
    }
}