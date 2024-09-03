import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C;
	static char[][] arr;
	static int X, Y;
	static boolean top, left, down, right;

	private static void find() {
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				char now = arr[i][j];
				
				if (now == '|' && i - 1 >= 0 && i + 1 < R) {
					if (arr[i - 1][j] == '.') {
						X = i - 1;
						Y = j;
						return;
					} else if (arr[i + 1][j] == '.') {
						X = i + 1;
						Y = j;
						return;
					}
				}
				
				if (now == '-' && j - 1 >= 0 && j + 1 < C) {
					if (arr[i][j - 1] == '.') {
						X = i;
						Y = j - 1;
						return;
					} else if (arr[i][j + 1] == '.') {
						X = i;
						Y = j + 1;
						return;
					}
				}
				
				if (now == '+' && i - 1 >= 0 && i + 1 < R && j - 1 >= 0 && j + 1 < C) {
					if (arr[i - 1][j] == '.') {
						X = i - 1;
						Y = j;
						return;
					} else if (arr[i + 1][j] == '.') {
						X = i + 1;
						Y = j;
						return;
					} else if (arr[i][j - 1] == '.') {
						X = i;
						Y = j - 1;
						return;
					} else if (arr[i][j + 1] == '.') {
						X = i;
						Y = j + 1;
						return;
					}
				}
				
				if (now == '1' && i + 1 < R && j + 1 < C) {
					if (arr[i + 1][j] == '.') {
						X = i + 1;
						Y = j;
						return;
					} else if (arr[i][j + 1] == '.') {
						X = i;
						Y = j + 1;
						return;
					}
				}
				
				if (now == '2' && i - 1 >= 0 && j + 1 < C) {
					if (arr[i - 1][j] == '.') {
						X = i - 1;
						Y = j;
						return;
					} else if (arr[i][j + 1] == '.') {
						X = i;
						Y = j + 1;
						return;
					}
				}
				
				if (now == '3' && i - 1 >= 0 && j - 1 >= 0) {
					if (arr[i - 1][j] == '.') {
						X = i - 1;
						Y = j;
						return;
					} else if (arr[i][j - 1] == '.') {
						X = i;
						Y = j - 1;
						return;
					}
				}
				
				if (now == '4' && i + 1 < R && j - 1 >= 0) {
					if (arr[i + 1][j] == '.') {
						X = i + 1;
						Y = j;
						return;
					} else if (arr[i][j - 1] == '.') {
						X = i;
						Y = j - 1;
						return;
					}
				}
				
				
			}
		}
	}
	
	private static void what() {
		
		// 위아래 가능하지만, 양옆은 MZ
		if (X - 1 >= 0 && X + 1 < R && Y - 1 >= 0 && Y + 1 < C) {
			if (arr[X - 1][Y] == '|' || arr[X - 1][Y] == '+' || arr[X - 1][Y] == '1' || arr[X - 1][Y] == '4') {
				if (arr[X + 1][Y] == '|' || arr[X + 1][Y] == '+' || arr[X + 1][Y] == '2' || arr[X + 1][Y] == '3') {
					if (arr[X][Y - 1] == 'M' || arr[X][Y - 1] == 'Z') {
						if (arr[X][Y + 1] == 'M' || arr[X][Y + 1] == 'Z') {
							top = true;
							down = true;
							return;
						}
					}
				}
			}
		}
		
		// 양옆 가능하지만, 위아래 MZ
		if (X - 1 >= 0 && X + 1 < R && Y - 1 >= 0 && Y + 1 < C) {
			if (arr[X][Y - 1] == '-' || arr[X][Y - 1] == '+' || arr[X][Y - 1] == '1' || arr[X][Y - 1] == '2') { 
				if (arr[X][Y + 1] == '-' || arr[X][Y + 1] == '+' || arr[X][Y + 1] == '3' || arr[X][Y + 1] == '4') {
					if (arr[X - 1][Y] == 'M' || arr[X - 1][Y] == 'Z') {
						if (arr[X + 1][Y] == 'M' || arr[X + 1][Y] == 'Z') {
							left = true;
							right = true;
							return;
						}
					}
				}
			}
		}

		// 위
		if (X - 1 >= 0) {
			if (arr[X - 1][Y] == '|' || arr[X - 1][Y] == '+' || arr[X - 1][Y] == '1' || arr[X - 1][Y] == '4' || arr[X - 1][Y] == 'M' || arr[X - 1][Y] == 'Z') {
				top = true;
			}
		}
		
		// 왼쪽
		if (Y - 1 >= 0) {
			if (arr[X][Y - 1] == '-' || arr[X][Y - 1] == '+' || arr[X][Y - 1] == '1' || arr[X][Y - 1] == '2' || arr[X][Y - 1] == 'M' || arr[X][Y - 1] == 'Z') {
				left = true;
			}
		}
		
		// 아래
		if (X + 1 < R) {
			if (arr[X + 1][Y] == '|' || arr[X + 1][Y] == '+' || arr[X + 1][Y] == '2' || arr[X + 1][Y] == '3' || arr[X + 1][Y] == 'M' || arr[X + 1][Y] == 'Z') {
				down = true;
			}
		}
		
		// 오른쪽
		if (Y + 1 < C) {
			if (arr[X][Y + 1] == '-' || arr[X][Y + 1] == '+' || arr[X][Y + 1] == '3' || arr[X][Y + 1] == '4' || arr[X][Y + 1] == 'M' || arr[X][Y + 1] == 'Z') {
				right = true;
			}
		}
		
		return;
				
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = line.charAt(j);
				if (arr[i][j] == 'M') {
				} else if (arr[i][j] == 'Z') {
				}
			}
		}
		
		X = 0;
		Y = 0;
		
		find();
		
		what();
		
		char ans = '.';
		
		if (top && down && !left && !right) {
			ans = '|';
		}
		
		if (!top && !down && left && right) {
			ans = '-';
		}
		
		if (top && down && left && right) {
			ans = '+';
		}
		
		if (!top && down && !left && right) {
			ans = '1';
		}
		
		if (top && !down && !left && right) {
			ans = '2';
		}
		
		if (top && !down && left && !right) {
			ans = '3';
		}
		
		if (!top && down && left && !right) {
			ans = '4';
		}
		
		System.out.println((X + 1) + " " + (Y + 1) + " " + ans);

		
	}

}