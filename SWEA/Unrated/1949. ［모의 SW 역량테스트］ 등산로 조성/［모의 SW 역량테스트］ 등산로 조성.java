import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
   
public class Solution {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, K;
	static List<int[]> maxBongs;
	static int[][] map;
	static int ans;
	
	static void kkak() {
		// 깎을 곳
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 깎을 깊이
				for (int k = 1; k <= K; k++) {
					map[i][j] -= k;
					// 해당 좌표를 해당 길이만큼 깎고,
					// 봉우리마다 최대 길이 구해서 갱신하기
					for (int[] bong : maxBongs) {
			    		   ans = Integer.max(ans, find(bong[0], bong[1]));
			    	}
					map[i][j] += k;
				}
			}
		}
	}
	
	// 해당 좌표에서 가능한 최대 등산로 길이 구하는 메서드
	static int find(int x, int y) {
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {x, y, 1});
		int maxLen = 0;
		
		while (!queue.isEmpty()) {
			int[] q = queue.poll();
			int cx = q[0];
			int cy = q[1];
			int len = q[2];
			
			maxLen = Integer.max(maxLen, len);
			
			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (map[nx][ny] < map[cx][cy]) {
						queue.add(new int[] {nx, ny, len + 1});
					}
				}
			}
		}
		return maxLen;
	}
	
	
    public static void main(String[] args) throws Exception {
   
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
       int T = Integer.parseInt(br.readLine());
       
       for (int t = 1; t <= T; t++) {
    	   
    	   StringTokenizer st = new StringTokenizer(br.readLine());
    	   // 지도 한 변의 길이
    	   N = Integer.parseInt(st.nextToken());
    	   // 최대 공사 가능 깊이
    	   K = Integer.parseInt(st.nextToken());
    	   
    	   // 가장 높은 봉우리의 높이
    	   int maxBong = -1;
    	   // 가장 높은 봉우리 좌표
    	   maxBongs = new ArrayList<>();
    	   
    	   // 지도 정보
    	   map = new int[N][N];
    	   for (int i = 0; i < N; i++) {
    		   st = new StringTokenizer(br.readLine());
    		   for (int j = 0; j < N; j++) {
    			   map[i][j] = Integer.parseInt(st.nextToken());
    			   
    			   // 가장 높은 봉우리들 찾기
    			   if (map[i][j] > maxBong) {
    				   maxBong = map[i][j];
    				   maxBongs.clear();
    				   maxBongs.add(new int[] {i, j});
    			   } else if (map[i][j] == maxBong) {
    				   maxBongs.add(new int[] {i, j});
    			   }
    		   }
    	   }
    	   
    	   ans = -1;
    	   kkak();
    	   
    	   System.out.println("#" + t + " " + ans);
    	   
       }
    }
}