import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
    static int[][] arr;      
      
    public static void main(String[] args) throws Exception {
   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          
        int T = Integer.parseInt(br.readLine());
          
        for (int t = 1; t <= T; t++) {
              
            // 학생 수
            N = Integer.parseInt(br.readLine());
            // 키 비교 횟수
            int M = Integer.parseInt(br.readLine());
              
            arr = new int[N + 1][N + 1];
              
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a][b] = 1;              
            }
            
            for (int i = 0; i < N + 1; i++) {
            	for (int j = 0; j < N + 1; j++) {
            		if (arr[i][j] != 1) {
            			arr[i][j] = 1000;
            		}
            	}
            }
              
            for (int k = 1; k <= N; k++) {
            	for (int i = 1; i <= N; i++) {
            		for (int j = 1; j <= N; j++) {
            			if (arr[i][j] > arr[i][k] + arr[k][j]) {
            				arr[i][j] = arr[i][k] + arr[k][j];
            			}
            		}
            	}
            }

            int ans = 0;
            for (int i = 1; i < N + 1; i++) {
            	int top = 0;
            	int down = 0;
            	for (int a = 0; a < N + 1; a++) {
            		if (arr[i][a] < 1000) {
            			top++;
            		}
            		if (arr[a][i] < 1000) {
            			down++;
            		}
            	}
            	if (top + down == N - 1) {
            		ans++;
            	}
            }
            
            System.out.println("#" + t + " " + ans);
        }
	}
}