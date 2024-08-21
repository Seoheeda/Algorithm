import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int[][] grid;
	static boolean[][] check;
	
	// 숫자판 출력
	public static void print() {
 	   for (int i = 0; i < N; i++) {
		   for (int j = 0; j < N; j++) {
			   System.out.print(grid[i][j] + " ");
		   }
		   System.out.println();
	   }
	}
	
	// 위로 이동
	public static void up() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				
				int flag = 0;
				
				int a = 1;
				
				while (true) {
									
					if (flag == 1) {
						break;
					}
					
					if (j - a >= 0 && check[j - a][i] == false && j - a >= 0 && grid[j - a + 1][i] != 0 && grid[j - a + 1][i] == grid[j - a][i]) {
						grid[j - a][i] *= 2;
						grid[j - a + 1][i] = 0;
						check[j - a][i] = true;
						flag = 1;
					} else if (grid[j - 1][i] == 0 && grid[j][i] != 0) {
						while (true) {
							grid[j - a][i] = grid[j - a + 1][i];
							grid[j - a + 1][i] = 0;
							a++;
							
							if (j - a < 0 || grid[j - a][i] != 0) {
								break;
							}
						}
					} else {
						flag = 1;
					}						
				}
			}
		}
	}
	
	// 아래로 이동
	public static void down() {
		
		for (int i = 0; i < N; i++) {
			for (int j = N - 2; j >= 0; j--) {
				
				int flag = 0;
				
				int a = 1;
				
				while (true) {
										
					if (flag == 1) {
						break;
					}
					
					if (j + a < N && check[j + a][i] == false  && j + a < N && grid[j + a - 1][i] != 0 && grid[j + a - 1][i] == grid[j + a][i]) {
						grid[j + a][i] *= 2;
						grid[j + a - 1][i] = 0;
						check[j + a][i] = true;
						flag = 1;
					} else if (grid[j + 1][i] == 0 && grid[j][i] != 0) {
						while (true) {
							grid[j + a][i] = grid[j + a - 1][i];
							grid[j + a - 1][i] = 0;
							a++;
							
							if (j + a >= N || grid[j + a][i] != 0) {
								break;
							}
						}
					} else {
						flag = 1;
					}					
				}
			}
		}
	}
	
	// 왼쪽으로 이동
	public static void left() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				
				int flag = 0;
				
				int a = 1;
				
				while (true) {
									
					if (flag == 1) {
						break;
					}
					
					if (j - a >= 0 && check[i][j - a] == false && j - a >= 0 && grid[i][j - a + 1] != 0 && grid[i][j - a + 1] == grid[i][j - a]) {
						grid[i][j - a] *= 2;
						grid[i][j - a + 1] = 0;
						check[i][j - a] = true;
						flag = 1;
					} else if (grid[i][j - 1] == 0 && grid[i][j] != 0) {
						while (true) {
							grid[i][j - a] = grid[i][j - a + 1];
							grid[i][j - a + 1] = 0;
							a++;
							
							if (j - a < 0 || grid[i][j - a] != 0) {
								break;
							}
						}
					} else {
						flag = 1;
					}				
				}
			}
		}
	}
	
	// 오른쪽으로 이동
	public static void right() {
		
		for (int i = 0; i < N; i++) {
			for (int j = N - 2; j >= 0; j--) {
				
				int flag = 0;
				
				int a = 1;
				
				while (true) {
										
					if (flag == 1) {
						break;
					}
					
					if (j + a < N && check[i][j + a] == false  && j + a < N && grid[i][j + a - 1] != 0 && grid[i][j + a - 1] == grid[i][j + a]) {
						grid[i][j + a] *= 2;
						grid[i][j + a - 1] = 0;
						check[i][j + a] = true;
						flag = 1;
					} else if (grid[i][j + 1] == 0 && grid[i][j] != 0) {
						while (true) {
							grid[i][j + a] = grid[i][j + a - 1];
							grid[i][j + a - 1] = 0;
							a++;
							
							if (j + a >= N || grid[i][j + a] != 0) {
								break;
							}
						}
					} else {
						flag = 1;
					}				
				}
			}
		}
	}

    public static void main(String[] args) throws Exception{

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       int T = Integer.parseInt(br.readLine());
       
       for (int t = 1; t <= T; t++) {
    	   
    	   StringTokenizer st1 = new StringTokenizer(br.readLine());
    	   
    	   N = Integer.parseInt(st1.nextToken());
    	   String S = st1.nextToken();
    	   
    	   grid = new int[N][N];
    	   
    	   // 이미 합쳐진 위치인지 확인하는 2차원 배열
    	   check = new boolean[N][N];
    	   
    	   for (int i = 0; i < N; i++) {
    		   StringTokenizer st2 = new StringTokenizer(br.readLine());
    		   for (int j = 0; j < N; j++) {
    			   grid[i][j] = Integer.parseInt(st2.nextToken());
    		   }
    	   }
    	   
    	   // 방향 조건에 따라 이동하기
    	   if (S.equals("up")) {
    		   up();
    	   } else if (S.equals("down")) {
    		   down();
    	   } else if (S.equals("right")) {
    		   right();
    	   } else if (S.equals("left")) {
    		   left();
    	   }
    	   
    	   // 정답 출력
    	   System.out.println("#" + t);
    	   print();
       }
    	
    }
}