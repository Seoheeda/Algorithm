import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static boolean[] visited;
	static int[] y_arr;
	static int[] gyu;
	static int[] young;
	static int gyu_j;
	static int young_j;
	static int win = 0;
	static int lose = 0;
	
	private static void dfs(int depth) {
		
		if (depth == 9) {
			gyu_j = 0;
			young_j = 0;
			for (int i = 0; i < 9; i++) {
				if (y_arr[i] > gyu[i]) {
					young_j += (y_arr[i] + gyu[i]);
				} else {
					gyu_j += (gyu[i] + y_arr[i]);
				}
			}
			
			if (gyu_j > young_j) {
				win++;
			} else {
				lose++;
			}
		}
		
		for (int i = 0; i < 9; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				y_arr[depth] = young[i];
				dfs(depth + 1);
				visited[i] = false;
			}
		}
		
	}

    public static void main(String[] args) throws NumberFormatException, IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(br.readLine());
    	
    	for (int t = 0; t < T; t++) {
    		
    		win = 0;
    		lose = 0;
    		
    		gyu = new int[9];
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		
    		for (int i = 0; i < 9; i++) {
    			gyu[i] = Integer.parseInt(st.nextToken());
    		}
    		
            int[] cards = Arrays.copyOf(gyu, 9);
    		Arrays.sort(cards);
    		young = new int[9];
    		
    		int c = 0;
    		int y = 0;
    		for (int i = 1; i <= 18; i++) {
    			if (c < 9 && cards[c] == i) {
    				c++;
    			} else {
    				young[y] = i;
    				y++;
    			}
  
    		}
    		
    		visited = new boolean[9];
    		y_arr = new int[9];
    		
//    		System.out.println(Arrays.toString(gyu));
//    		System.out.println(Arrays.toString(young));
    		
    		dfs(0);
    		System.out.println("#" + (t + 1) + " " + win + " " + lose);

    		
    	
    		
    	}
    }

}