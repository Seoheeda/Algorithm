import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R;
	static int C;
	static char[][] arr;
	static int cnt;
	static int[] dx = {-1, 0, 1};
	
	public static boolean dfs(int x, int y) {
				
		if (y == C - 1) {
			cnt++;
			return true;
		}
		
		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + 1;
			
			if (nx >= 0 && nx < R && ny >= 0 && ny < C && arr[nx][ny] != 'x') {
				arr[nx][ny] = 'x';
				 if (dfs(nx, ny)) {
		                return true; 
				 }
			}
		}
		
		return false;
		
	}
 
    public static void main(String[] args) throws NumberFormatException, IOException {
     
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st1 = new StringTokenizer(br.readLine());
    	
    	R = Integer.parseInt(st1.nextToken());
    	C = Integer.parseInt(st1.nextToken());
    	
    	arr = new char[R][C];
    	
    	for (int i = 0; i < R; i++) {
    		String line = br.readLine();
    		for (int j = 0; j < C; j++) {
    			arr[i][j] = line.charAt(j);
    		}
    	}
    	
    	cnt = 0;
    	for (int i = 0; i < R; i++) {
        	dfs(i, 0);
    	}
    	System.out.println(cnt);
    	
    }
 
}