import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
	
	// 말이 이동 가능한 곳
	static int[] dxH = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dyH = {-2, -1, 1, 2, 2, 1, -1, -2};
	// 원숭이가 이동 가능한 곳
	static int[] dxM = {-1, 0, 1, 0};
	static int[] dyM = {0, 1, 0, -1};
	// 말로 이동한 횟수
	static int horseCnt;
	
	static int[][] arr;
	static boolean[][][] visited;
	static Queue<int[]> queue;
	static int K, W, H;
	static int cnt;
	static int xEnd, yEnd;
	
	static void bfs() {
		
		int len = queue.size();
		
		// 도착점에서 갈 수 없다면 -1
		if (len == 0) {
			cnt = -1;
			return;
		}
				
		for (int i = 0; i < len; i++) {
			int[] q = queue.poll();
	        // x좌표, y좌표, 말로 이동한 횟수
			int x = q[0];
			int y = q[1];
			int c = q[2];
			
			// 도착했으면 return
			if (x == xEnd && y == yEnd) {
				return;
			}
			
			// 아직 말로 이동 가능하면
			if (c < K) {
				for (int a = 0; a < 8; a++) {
					int nx = x + dxH[a];
					int ny = y + dyH[a];
					
					if (nx >= 0 && nx < H && ny >= 0 && ny < W && arr[nx][ny] == 0 && !visited[c + 1][nx][ny]) {
						queue.add(new int[] {nx, ny, c + 1});
						visited[c + 1][nx][ny] = true;
					}
				}
			}
			
			// 원숭이로 이동
			for (int a = 0; a < 4; a++) {
				int nx = x + dxM[a];
				int ny = y + dyM[a];
				
				if (nx >= 0 && nx < H && ny >= 0 && ny < W && arr[nx][ny] == 0 && !visited[c][nx][ny]) {
					queue.add(new int[] {nx, ny, c});
					visited[c][nx][ny] = true;
				}
			}
		}
		
		// 이동 횟수 
		cnt++;
		
		bfs();
	}
     
    public static void main(String[] args) throws Exception {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 말처럼 움직이는 횟수
        K = Integer.parseInt(br.readLine());
       
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        // 격자판
        arr = new int[H][W];
        for (int i = 0; i < H; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < W; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 도착 지점
        xEnd = H - 1;
        yEnd = W - 1;
        
        horseCnt = 0;
        queue = new ArrayDeque<>();
        // x좌표, y좌표, 말로 이동한 횟수
        queue.add(new int[] {0, 0, 0});
        
        // 동작 수
        cnt = 0;
        
        // 방문 확인
        // 말로 이동 횟수별로 3차원 방문처리
        visited = new boolean[K + 1][H][W];
        visited[0][0][0] = true;

        
        bfs();
        
        System.out.println(cnt);           
    }
}