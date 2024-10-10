import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, X;
	
	static boolean test(int[] testArr) {
		
		int curH = testArr[0];
		int cnt = 1;
		
		for (int i = 1; i < N; i++) {
			if (curH - testArr[i] == 1) {
				int tempCnt = 1;
				int tempIndex = i;
				while (true) {
					tempIndex++;
					if (tempIndex < N && testArr[tempIndex] == testArr[i]) {
						tempCnt++;
					} else {
						break;
					}
				}
				if (tempCnt < X) {
					return false;
				}
				cnt = X * -1 + 1;
				curH = testArr[i];
			} else if (curH - testArr[i] == -1) {
				if (cnt < X) {
					return false;
				}
				cnt = 1;
				curH = testArr[i];
			} else if(curH - testArr[i] == 0) {
				cnt++;
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 지도의 한 변 크기
			N = Integer.parseInt(st.nextToken());
			// 경사로의 길이
			X = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = 0;
			
			// 가로 방향 체크
			for (int i = 0; i < N; i++) {
				boolean tempAns = test(arr[i]);
//				System.out.println(tempAns);
				if (tempAns) {
					ans++;
				}
			}
			// 세로 방향 체크
			int[] tempArr = new int[N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tempArr[j] = arr[j][i];
				}
//				System.out.println(Arrays.toString(tempArr));
				boolean tempAns = test(tempArr);
//				System.out.println(tempAns);
				if (tempAns) {
					ans++;
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}
}