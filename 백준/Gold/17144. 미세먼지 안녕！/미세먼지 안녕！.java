import java.io.*;
import java.util.*;
 
public class Main {
	
	static int R, C;
	static int[] machine1, machine2;
	static int[][] arr;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	// 범위 안인지 확인
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < R && y >= 0 && y < C) {
			return true;
		}
		return false;
	}
	
	// 미세먼지 확산
	static int[][] spread() {
		
		// arr 복사하기
		int[][] tempArr = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				tempArr[i][j] = arr[i][j];
			}
		}
		
		// 미세먼제 확산
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == -1) {
					continue;
				}
				int cnt = 0;
				int meonji = arr[i][j];
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (isIn(nx, ny)) {
						if (arr[nx][ny] == -1) {
							continue;
						}
						tempArr[nx][ny] += meonji / 5;
						cnt++;
					}
				}
				tempArr[i][j] -= (meonji / 5) * cnt;
			}
		}
		
		return tempArr;	
	}
	
	// 위쪽 공기청정기 작동
	static void cleanUp() {
		int sx = machine1[0];
		
		for (int i = sx - 1; i > 0; i--) {
	        arr[i][0] = arr[i - 1][0];
	    }
		
		for (int i = 0; i < C - 1; i++) {
			arr[0][i] = arr[0][i + 1];
		}
		
		for (int i = 0; i < sx; i++) {
			arr[i][C - 1] = arr[i + 1][C - 1];
		}
		
		for (int i = C - 1; i > 0; i--) {
			arr[sx][i] = arr[sx][i - 1];
		}	
		arr[sx][1] = 0;
	}
	
	// 아래쪽 공기청정기 작동
	static void cleanDown() {
		int sx = machine2[0];
		
		for (int i = sx + 1; i < R - 1; i++) {
			arr[i][0] = arr[i + 1][0];
		}
		
		for (int i = 0; i < C - 1; i++) {
			arr[R - 1][i] = arr[R - 1][i + 1];
		}
		
		for (int i = R - 1; i > sx; i--) {
			arr[i][C - 1] = arr[i - 1][C - 1];
		}
		
		for (int i = C - 1; i > 0; i--) {
			arr[sx][i] = arr[sx][i - 1];
		}
		
		arr[sx][1] = 0;
	}
	
    public static void main(String[] args) throws Exception{
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 격자판 크기
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // 시간
        int T = Integer.parseInt(st.nextToken());
        
        // 공기청정기 위치
        machine1 = new int[2];
        machine2 = new int[2];
        
        // 격자판
        arr = new int[R][C];
        
        for (int i = 0; i < R; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < C; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		if (arr[i][j] == -1) {
        			machine2[0] = i;
        			machine2[1] = j;
        			machine1[0] = i - 1;
        			machine1[1] = j;
        		}
        	}
        }
        
        // t초동안 반복
        for (int t = 0; t < T; t++) {
        	arr = spread();
            
        	cleanUp();
        	cleanDown();
        }
        
        // 미세먼지 총합
        int ans = 0;
        for (int i = 0; i < R; i++) {
        	for (int j = 0; j < C; j++) {
        		if (arr[i][j] != -1) {
        			ans += arr[i][j];
        		}
        	}
        }
        System.out.println(ans);
    }
}