import java.io.*;
import java.util.*;

public class Main {
	
	static int N, spreaded;
	static int[][] map;
	// 좌 하 우 상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	// 1 1 2 2 5 7 7 10 10 a
	// 좌 하 우 상
	static int[][] spreadX = {{-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
							  {-1, -1, 0, 0, 2, 0, 0, 1, 1, 1}, 
							  {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}, 
							  {1, 1, 0, 0, -2, 0, 0, -1, -1, -1}};
	static int[][] spreadY = {{1, 1, 0, 0, -2, 0, 0, -1, -1, -1},
			    			  {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}, 
			    			  {-1, -1, 0, 0, 2, 0, 0, 1, 1, 1},
			    			  {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}};
	
	// 밖인지
	static boolean isOut(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N) {
			return false;
		}
		return true;
	}
	
	// 흩어트리기
	static void spread(int x, int y, int d, int amount) {
		int leftover = amount;
		
		// 1퍼
		int per = amount / 100;
		for (int i = 0; i < 2; i++) {
			leftover -= per;
			if (isOut(x + spreadX[d][i], y + spreadY[d][i])) {
				spreaded += per;
			} else {
				map[x + spreadX[d][i]][y + spreadY[d][i]] += per;
			}
		}
		// 2퍼
		per = amount / 50;
		for (int i = 2; i < 4; i++) {
			leftover -= per;
			if (isOut(x + spreadX[d][i], y + spreadY[d][i])) {
				spreaded += per;
			} else {
				map[x + spreadX[d][i]][y + spreadY[d][i]] += per;
			}
		}
		// 5퍼
		per = amount / 20;
		for (int i = 4; i < 5; i++) {
			leftover -= per;
			if (isOut(x + spreadX[d][i], y + spreadY[d][i])) {
				spreaded += per;
			} else {
				map[x + spreadX[d][i]][y + spreadY[d][i]] += per;
			}
		}
		// 7퍼
		per = (amount * 7) / 100;
		for (int i = 5; i < 7; i++) {
			leftover -= per;
			if (isOut(x + spreadX[d][i], y + spreadY[d][i])) {
				spreaded += per;
			} else {
				map[x + spreadX[d][i]][y + spreadY[d][i]] += per;
			}
		}
		// 10퍼
		per = amount / 10;
		for (int i = 7; i < 9; i++) {
			leftover -= per;
			if (isOut(x + spreadX[d][i], y + spreadY[d][i])) {
				spreaded += per;
			} else {
				map[x + spreadX[d][i]][y + spreadY[d][i]] += per;
			}
		}
		// a
		if (isOut(x + spreadX[d][9], y + spreadY[d][9])) {
			spreaded += leftover;
		} else {
			map[x + spreadX[d][9]][y + spreadY[d][9]] += leftover;
		}
	}
	
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 격자 크기
        N = Integer.parseInt(br.readLine());
        
        // 격자 정보
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 중심 좌표
        int x = N / 2;
        int y = N / 2;
        
        // 직진 크기
        int size = 0;
        // 방향
        int d = 0;
        // 밖으로 나간 모래 양
        spreaded = 0;
        
        // 빙글뱅글
        while (x != 0 && y != N - 1) {
        	size++;
        	for (int i = 0; i < 2; i++) {
        		for (int j = 0; j < size; j++) {
        			x+= dx[d];
                	y += dy[d];
                	int amount = map[x][y];
        			map[x][y] = 0;
                	spread(x, y, d, amount);
        		}
            	d = (d + 1) % 4;
        	}
        }
        
        // 마지막 직진
        for (int i = 0; i < size; i++) {
        	x += dx[d];
        	y += dy[d];
        	int amount = map[x][y];
			map[x][y] = 0;
        	spread(x, y, d, amount);
        }
        
        System.out.println(spreaded);
    }
}