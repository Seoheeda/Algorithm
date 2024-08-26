import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int I;
	static int tx, ty;
	static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
	static boolean[][] visited;
	static int ans;
			
	private static void bfs(int sx, int sy) {
		
		// 큐에 시작값 넣어주기
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {sx, sy});
		visited[sx][sy] = true;
		
		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			
			int q = queue.size();
			
			// 큐에 담긴 지점만큼 반복
			for (int j = 0; j < q; j++) {
				int[] out = queue.poll();
				
				for (int i = 0; i < 8; i++) {
					int nx = out[0] + dx[i];
					int ny = out[1] + dy[i];
					
					if (nx >= 0 && nx < I && ny >= 0 && ny < I && visited[nx][ny] == false) {
						if (nx == tx && ny == ty) {
							ans++;
							return;
						}
						
						if (visited[nx][ny] == false) {
							queue.add(new int[] {nx, ny});
							visited[nx][ny] = true;
						}
					}
				}
			}
			ans++;

		}
		
		return;
		
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			I = Integer.parseInt(br.readLine());
			
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st1.nextToken());
			int sy = Integer.parseInt(st1.nextToken());
			
			StringTokenizer st2 =  new StringTokenizer(br.readLine());
			tx = Integer.parseInt(st2.nextToken());
			ty = Integer.parseInt(st2.nextToken());
			
			visited = new boolean[I][I];
			ans = 0;
			
			if (sx != tx || sy != ty) {
				bfs(sx, sy);
			}
			
			System.out.println(ans);
			
		}
	}

}