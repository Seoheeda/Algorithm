import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int M;
	static int N;
	static int K;
	static int[][] farm;
	
	public static void bfs(int[][] farm, int x, int y) {
		// 큐로 활용할 리스트
		List<int[]> baechu = new ArrayList<int[]>();
		int[] a = {x, y};
		baechu.add(a);
		farm[x][y] = 0;
		
		// baechu가 비었다면, 인접한 배추가 모두 체크되었을 것
		while (baechu.size() > 0) {
			int[] b = baechu.get(0);
			baechu.remove(0);
			int nx = b[0];
			int ny = b[1];
			
			// 상하좌우에 배추가 있다면 baechu에 추가하기
			if (nx - 1 >= 0) {
				if (farm[nx - 1][ny] == 1) {
					int[] c = {nx - 1, ny};
					baechu.add(c);
					farm[nx - 1][ny] = 0;
				}
			}
			if (nx + 1 < N) {
				if (farm[nx + 1][ny] == 1) {
					int[] c = {nx + 1, ny};
					baechu.add(c);
					farm[nx + 1][ny] = 0;
				}
			}
			if (ny - 1 >= 0) {
				if (farm[nx][ny - 1] == 1) {
					int[] c = {nx, ny - 1};
					baechu.add(c);
					farm[nx][ny - 1] = 0;
				}
			}
			if (ny + 1 < M) {
				if (farm[nx][ny + 1] == 1) {
					int[] c = {nx, ny + 1};
					baechu.add(c);
					farm[nx][ny + 1] = 0;
				}
			}
		}
		return;
	}
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 테스트 케이스 개수 T
    	int T = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
    	
    	// 세로 N, 가로 M, 배추 개수 K
    	for (int t = 0; t < T; t++) {
	    	StringTokenizer st1 = new StringTokenizer(br.readLine());
	    	M = Integer.parseInt(st1.nextToken());
	    	N = Integer.parseInt(st1.nextToken());
	    	K = Integer.parseInt(st1.nextToken());
	    	
	    	// farm 배열에 배추 위치 1로 저장하기
	    	farm = new int[N][M];
	    	for (int i = 0; i < K; i++) {
	    		StringTokenizer st2 = new StringTokenizer(br.readLine());
	    		int a = Integer.parseInt(st2.nextToken());
	    		int b = Integer.parseInt(st2.nextToken());
	    		farm[b][a] = 1;
	    	}
	    	
	    	// farm에서 1일 경우에만 bfs 함수 호출 --> 호출 횟수가 곧 정답
	    	int ans = 0;
	    	for (int i = 0; i < N; i++) {
	    		for (int j = 0; j < M; j++) {
	    			if (farm[i][j] == 1) {
	    				bfs(farm, i, j);
	    				ans++;
	    			}
	    		}
	    	}
	    	System.out.println(ans);
    	}
    }
}
