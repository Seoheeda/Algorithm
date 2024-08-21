import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] isSelected;
	static int[][] film2;
	static int D;
	static int W;
	static int K;
	static int min;
	
	public static void dfs(int cnt, int[][] film, int ans) {
		
    	if (check(film, W, D, K) == true && min > ans) {
    		min = ans;
        	return;
    	}
    	
		if (ans >= min) {
			return;
		}
       
		if (cnt == D) {
			return;
		}
		
		film = change0(cnt, film);
        dfs(cnt + 1, film, ans + 1);
		
        for (int i = 0; i < W; i++) {
        	film[cnt][i] = film2[cnt][i];
        }
        
         
		film = change1(cnt, film);
        dfs(cnt + 1, film, ans + 1);
		
        for (int i = 0; i < W; i++) {
        	film[cnt][i] = film2[cnt][i];
        }
        
        dfs(cnt + 1, film, ans);
    }
	
	public static int[][] change0(int row, int[][] film2) {
		
		for (int i = 0; i < W; i++) {
			film2[row][i] = 0;
		}
		
		return film2;
	}
	
	public static int[][] change1(int row, int[][] film2) {
		
		for (int i = 0; i < W; i++) {
			film2[row][i] = 1;
		}
		
		return film2;
	}
	
	public static boolean check(int[][] film, int W, int D, int K) {
		
		boolean[] checked = new boolean[W];
		
		// 가로 순회
		for (int i = 0; i < W; i++) {
			
			int recent = 2;
			int cnt = 1;
			
			// 세로 순회
			for (int j = 0; j < D; j++) {
				if (film[j][i] == recent) {
					cnt++;
				} else {
					cnt = 1;
					recent = film[j][i];
				}
				
				if (cnt == K) {
					checked[i] = true;
				}
			}	
			
			if (checked[i] == false) {
				return false;
			}
		}
		
		return true;
		
	}

    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(br.readLine());
    	
    	for (int t = 1; t <= T; t++) {
    		
    		StringTokenizer st1 = new StringTokenizer(br.readLine());
    		
    		// 보호필름 두께
    		D = Integer.parseInt(st1.nextToken());
    		// 가로 크기
    		W = Integer.parseInt(st1.nextToken());
    		// 합격 기준
    		K = Integer.parseInt(st1.nextToken());
    		
    		int[][] film = new int[D][W];
    		
    		for (int i = 0; i < D; i++) {
    			StringTokenizer st2 = new StringTokenizer(br.readLine());
    			for (int j = 0; j < W; j++) {
    				film[i][j] = Integer.parseInt(st2.nextToken());
    			}
    		}
    		
         	film2 = new int[film.length][];
    			for (int i = 0; i < film.length; i++) {
    				film2[i] = film[i].clone();
    			}
    		
    		isSelected = new int[D];
    		min = D;
    		
    		if (K == 1) {
        		System.out.println("#" + t + " " + 0);
    		} else {
	    		dfs(0, film, 0);
	    		System.out.println("#" + t + " " + min);
    		}
    		
    	}
    	
    }
}