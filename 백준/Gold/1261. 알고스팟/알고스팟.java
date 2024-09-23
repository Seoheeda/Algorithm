import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// x좌표, y좌표, 부신 벽 수
class Point implements Comparable<Point>{
	int x;
	int y;
	int cnt;
	
	public Point(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Point o) {
		return Integer.compare(this.cnt, o.cnt);
	}
}

public class Main {
	
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	static int N, M;
	static char[][] arr;
	static boolean[][] visited;
	
	static int bfs() {
		
		// 부신 벽 수가 가장 적은 애부터 나옴
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		
		queue.add(new Point(0, 0, 0));
		
		while (!queue.isEmpty()) {
			
			Point point = queue.poll();
			
			// 방문처리
			if (!visited[point.x][point.y]) {
				
				visited[point.x][point.y] = true; 
			
				// 도착하면 리턴
				if (point.x == N - 1 && point.y == M - 1) {
					return point.cnt;
				}
				
				for (int i = 0; i < 4; i++) {
					int nx = point.x + dx[i];
					int ny = point.y + dy[i];
					if (nx >= 0 &&  nx < N && ny >= 0 && ny < M) {
						// 벽 부수지 않아도 됨
						if (arr[nx][ny] == '0') {
							queue.add(new Point(nx, ny, point.cnt));
						// 벽 부셔야 하므로 cnt + 1
						} else {
							queue.add(new Point(nx, ny, point.cnt + 1));
						}
					}
				}
			}
		}
		
		return 0;
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] =  temp.charAt(j);
			}
		}
		
		// 방문 여부
		visited = new boolean[N][M];
		
		System.out.println(bfs());

	}

}