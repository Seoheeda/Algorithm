import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int x;
	int y;
	int power;
	
	public Edge(int x, int y, int power) {
		super();
		this.x = x;
		this.y = y;
		this.power = power;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.power, o.power);
	}	
}

public class Main {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int INF = Integer.MAX_VALUE;
	    
    public static void main(String[] args) throws Exception {
  
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
		int p = 1;
    	
    	while (true) {
    		
    		// 동굴 크기
    		int N = Integer.parseInt(br.readLine());
    		
    		if (N == 0) {
    			break;
    		}
    		
    		// 동굴 정보
    		int[][] arr = new int[N][N];
    		
    		for (int i = 0; i < N; i++) {
    			StringTokenizer st = new StringTokenizer(br.readLine());
    			for (int j = 0; j < N; j++) {
    				arr[i][j] = Integer.parseInt(st.nextToken());
    			}
    		}
    		
    		// 잃는 루피 초기화
    		int[][] dist = new int[N][N];
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < N; j++) {
    				dist[i][j] = INF;
    			}
    		}
    		
    		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
    		
    		// 시작점 처리
    		dist[0][0] = arr[0][0];
    		queue.add(new Edge(0, 0, arr[0][0]));
    		
    		while (!queue.isEmpty()) {
    			
    			Edge edge = queue.poll();
    			int x = edge.x;
    			int y = edge.y;
    			
    			// 범위 안에 들어오고, 최소면 큐에 추가
    			for (int d = 0; d < 4; d++) {
    				if (x + dx[d] >= 0 && x + dx[d] < N && y + dy[d] >= 0 && y + dy[d] < N) {
    					if (dist[x][y] + arr[x + dx[d]][y + dy[d]] < dist[x + dx[d]][y + dy[d]]) {
    						dist[x + dx[d]][y + dy[d]] = dist[x][y] + arr[x + dx[d]][y + dy[d]];
    						queue.add(new Edge(x + dx[d], y + dy[d], arr[x + dx[d]][y + dy[d]]));
    					}
    				}
    			}
    		}
    		
    		System.out.println("Problem " + p + ": " + dist[N - 1][N - 1]);
    		p++;
    	} 	
    }
}