import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
     
    static int N;
    static int[][] grid;
    static int[][] grid2;
    static int[] isSelected;
    static int max;
    
    // 현재 보드에서 가장 큰 블록 찾기
    public static int find_max() {
    	
    	int temp_max = 0;
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			if (temp_max < grid[i][j]) {
    				temp_max = grid[i][j];
    			}
    		}
    	}
    	
    	return temp_max;
    }
    
    public static void dfs(int depth) {
    	
    	if (depth == 5) {
    		
    		grid = new int[grid2.length][];
 			for (int i = 0; i < grid2.length; i++) {
 				grid[i] = grid2[i].clone();
 			}
            
    		
    		// 조합에 맞게 움직이기
    		for (int i = 0; i < 5; i++) {
    			if (isSelected[i] == 1) {
    				up();
    			} else if (isSelected[i] == 2) {
    				down();
    			} else if (isSelected[i] == 3) {
    				right();
    			} else if (isSelected[i] == 4) {
    				left();
    			}
    		}
    		
    		// 최댓값 갱신하기
    		if (max < find_max()) {
    			max = find_max();
    		}
    		
    		return;
    	}
    	
    	// 움직이지 않는 경우
    	isSelected[depth] = 0;
    	dfs(depth + 1);
    	
    	// 위로 움직이는 경우
    	isSelected[depth] = 1;
    	dfs(depth + 1);
    	
    	// 아래로 움직이는 경우
    	isSelected[depth] = 2;
    	dfs(depth + 1);
    	
    	// 오른쪽으로 움직이는 경우
    	isSelected[depth] = 3;
    	dfs(depth + 1);
    	
    	// 왼쪽으로 움직이는 경우
    	isSelected[depth] = 4;
    	dfs(depth + 1);
    }
     
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
    	
        boolean[][] check = new boolean[N][N];
         
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
    	
        boolean[][] check = new boolean[N][N];
         
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
    	
        boolean[][] check = new boolean[N][N];
         
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
    	
        boolean[][] check = new boolean[N][N];
         
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
 
    public static void main(String[] args) throws Exception {
 
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
                        
           N = Integer.parseInt(br.readLine());
            
           grid = new int[N][N];
            
           // 이미 합쳐진 위치인지 확인하는 2차원 배열
            
           for (int i = 0; i < N; i++) {
               StringTokenizer st2 = new StringTokenizer(br.readLine());
               for (int j = 0; j < N; j++) {
                   grid[i][j] = Integer.parseInt(st2.nextToken());
               }
           }
           
           grid2 = new int[grid.length][];
 			for (int i = 0; i < grid.length; i++) {
 				grid2[i] = grid[i].clone();
 			}
            
         isSelected = new int[5];
         max = 0;         
         dfs(0);
         
         System.out.println(max);
       }
}