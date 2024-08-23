import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int[] arr;
	static int[][] cust;
	static boolean[] visited;
	static int comX, comY, homeX, homeY;
	static int min;
	
	public static void dfs(int depth) {
		
		if (depth == N) {
			int temp = calc(arr);
			if (temp < min) {
				min = temp;
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				arr[depth] = i;
				dfs(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static int calc(int[] arr) {
		int cx = comX;
		int cy = comY;
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			sum += Math.abs(cx - cust[arr[i]][0]);
			sum += Math.abs(cy - cust[arr[i]][1]);
			cx = cust[arr[i]][0];
			cy = cust[arr[i]][1];
		}
		
		sum += Math.abs(cx - homeX);
		sum += Math.abs(cy - homeY);
		
		return sum;
	}
 
    public static void main(String[] args) throws NumberFormatException, IOException {
     
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(br.readLine());
    	
    	for (int t = 1; t <= T; t++) {
    		
    		N = Integer.parseInt(br.readLine());
    		
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		
    		comX = Integer.parseInt(st.nextToken());
    		comY = Integer.parseInt(st.nextToken());
    		
    		homeX = Integer.parseInt(st.nextToken());
    		homeY = Integer.parseInt(st.nextToken());
    		
    		cust = new int[N][2];
    		
    		for (int i = 0; i < N; i++) {
    			cust[i][0] = Integer.parseInt(st.nextToken());
    			cust[i][1] = Integer.parseInt(st.nextToken());
    		}
    		
    		arr = new int[N];
    		visited = new boolean[N];
    		min = Integer.MAX_VALUE;
    		dfs(0);
    		
    		System.out.println("#" + t + " " + min);

    	}
    	
    }
 
}