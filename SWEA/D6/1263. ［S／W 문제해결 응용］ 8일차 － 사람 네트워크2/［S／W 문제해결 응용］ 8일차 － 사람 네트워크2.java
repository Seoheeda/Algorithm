import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 사람 수
			int N = Integer.parseInt(st.nextToken());
			
			// 인접 행렬
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 0) {
						arr[i][j] = 10000;
					}
				}
			}
					
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (arr[i][j] > arr[i][k] + arr[k][j]) {
							arr[i][j] = arr[i][k] + arr[k][j];
						}
					}
				}
			}
			
			int min = Integer.MAX_VALUE;
			int[] ansArr = new int[N];
			for (int i = 0; i < N; i++) {
				int tempCnt = 0;
				for (int j = 0; j < N; j++) {
					if (i != j) {
						tempCnt += arr[i][j];
					}
				}
				if (tempCnt < min) {
					min = tempCnt;
				}
			}
			
			System.out.println("#" + t + " " + min);
		}
	}
}