import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] arr;
	
	public static int[] find(int x, int y) {
		
		int nx = x;
		int ny = y;
		int original = arr[nx][ny];
		int target = arr[nx][ny];
		int cnt = 1;
		
		int[] value = new int[2];
//		System.out.println("new");
		
		while (true) {
//			System.out.println(target);
			boolean flag = false;

			for (int i = 0; i < 4; i++) {
				if (flag == false && nx + dx[i] < N && nx + dx[i] >= 0 && ny + dy[i] < N && ny + dy[i] >= 0 && arr[nx + dx[i]][ny + dy[i]] - target == 1) {
					target = arr[nx + dx[i]][ny + dy[i]];
					nx += dx[i];
					ny += dy[i];
					cnt++;
					flag = true;
				}
			}
			
			if (!flag) {
				value[0] = original;
				value[1] = cnt;
				return value;
			}
		}
		
	}
   
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(br.readLine());
    	
    	for (int t = 1; t <= T; t++) {
    		
    		N = Integer.parseInt(br.readLine());
    		
    		arr = new int[N][N];
    		
    		for (int i = 0; i < N; i++) {
    			StringTokenizer st = new StringTokenizer(br.readLine());
    			for (int j = 0; j < N; j++) {
    				arr[i][j] = Integer.parseInt(st.nextToken());
    			}
    		}
    		
    		int max = -1;
    		int num = N * N;
    		
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < N; j++) {
    				int ans = find(i, j)[1];
    				if (ans > max) {
    					max = ans;
    					num = find(i, j)[0];
    				} else if (ans == max && num > find(i, j)[0]) {
    						num = find(i, j)[0];
    					}
    				}
    			}
    		
    		System.out.println("#" + t + " " + num + " " + max);
    	}
    }
}