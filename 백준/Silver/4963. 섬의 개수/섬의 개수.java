import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int[][] map;
	static int ans;
	static int w, h;
	static int[] temp = new int[2];
	
	private static void bfs(int x, int y) {
	    Queue<int[]> queue = new LinkedList<>();
	    queue.add(new int[]{x, y});
	    map[x][y] = 0;
	    
	    while (!queue.isEmpty()) { 
	        int[] elem = queue.poll();
	        int tx = elem[0];
	        int ty = elem[1];
	        
	        for (int i = 0; i < 8; i++) {
	            int nx = tx + dx[i];
	            int ny = ty + dy[i];
	            if (nx >= 0 && nx < h && ny >= 0 && ny < w && map[nx][ny] == 1) {
	                queue.add(new int[]{nx, ny}); 
	                map[nx][ny] = 0;
	            }
	        }
	    }
	}


	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st1.nextToken());
			h = Integer.parseInt(st1.nextToken());
			
			if (w == 0 && h == 0) {
				break;
			}
			
			// 지도 입력받기
			map = new int[h][w];
			
			for (int i = 0; i < h; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st2.nextToken());
				}
			}
			
			ans = 0;
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1) {
						bfs(i, j);
						ans++;
					}
				}
			}
			
			System.out.println(ans);
		}

	}

}