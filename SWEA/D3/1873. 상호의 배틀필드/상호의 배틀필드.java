import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static void print(int H, int W, char[][] arr) {
		// 출력하기
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	   
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T= Integer.parseInt(br.readLine());
    	
    	for (int t = 1; t <= T; t++) {
    		
    		StringTokenizer st1 = new StringTokenizer(br.readLine());
    		
    		// 높이, 너비
    		int H = Integer.parseInt(st1.nextToken());
    		int W = Integer.parseInt(st1.nextToken());
    		
    		char[][] arr = new char[H][W];
    		// 전차의 위치
    		int row = 0;
    		int col = 0;
    		
    		for (int i = 0; i < H; i++) {
    			String line = br.readLine();
    			for (int j = 0; j < W; j++) {
    				arr[i][j] = line.charAt(j);
    				if (line.charAt(j) == '^' || line.charAt(j) == 'v' || line.charAt(j) == '>' || line.charAt(j) == '<') {
    					row = i;
    					col = j;
    				}
    			}
    		}
    		
    		// 입력 개수
    		int N = Integer.parseInt(br.readLine());
    		String line = br.readLine();
    		char[] user = new char[N];
    		
    		for (int i = 0; i < N; i++) {
    			user[i] = line.charAt(i);
    		}
    		    		
    		// 이동하기
    		for (char input : user) {
    			if (input == 'U') {
    				arr[row][col] = '^';
    				if (row - 1 >= 0 && arr[row - 1][col] == '.') {
    					arr[row - 1][col] = '^';
    					arr[row][col] = '.';
    					row--;
    				}
    			} else if (input == 'D') {
    				arr[row][col] = 'v';
    				if (row + 1 < H && arr[row + 1][col] == '.') {
    					arr[row + 1][col] = 'v';
    					arr[row][col] = '.';
    					row++;
    				}
    			} else if (input == 'L') {
    				arr[row][col] = '<';
    				if (col - 1 >= 0 && arr[row][col - 1] == '.') {
    					arr[row][col - 1] = '<';
    					arr[row][col] = '.';
    					col--;
    				}
    			} else if (input == 'R') {
    				arr[row][col] = '>';
    				if (col + 1 < W && arr[row][col + 1] == '.') {
    					arr[row][col + 1] = '>';
    					arr[row][col] = '.';
    					col++;
    				}
    			} else if (input == 'S') {
    				int x = row;
    				int y = col;
    				if (arr[row][col] == '^') {
    					while (true) {
    						x--;
    						if (x < 0) {
    							break;
    						}
    						
    						if (arr[x][y] == '*') {
    							arr[x][y] = '.';
    							break;
    						} else if (arr[x][y] == '#') {
    							break;
    						}
    					}
    				} else if (arr[row][col] == 'v') {
    					while (true) {
    						x++;
    						if (x >= H) {
    							break;
    						}
    						
    						if (arr[x][y] == '*') {
    							arr[x][y] = '.';
    							break;
    						} else if (arr[x][y] == '#') {
    							break;
    						}
    					}
    				} else if (arr[row][col] == '<') {
    					while (true) {
    						y--;
    						if (y < 0) {
    							break;
    						}
    						
    						if (arr[x][y] == '*') {
    							arr[x][y] = '.';
    							break;
    						} else if (arr[x][y] == '#') {
    							break;
    						}
    					}
    				} else if (arr[row][col] == '>') {
    					while (true) {
    						y++;
    						if (y >= W) {
    							break;
    						}
    						
    						if (arr[x][y] == '*') {
    							arr[x][y] = '.';
    							break;
    						} else if (arr[x][y] == '#') {
    							break;
    						}
    					}
    				}
    			}
    		}
    		System.out.print("#" + t + " ");
			print(H, W, arr);
    		
    	}
    }
}