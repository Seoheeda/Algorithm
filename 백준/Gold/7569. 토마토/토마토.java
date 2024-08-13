import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	
	// 토마토
	static class Tomato {
		int x, y, z, a;
		
		public Tomato(int x, int y, int z, int a) {
			this.x = x; // 높이
			this.y = y; // 세로
			this.z = z; // 가로
			this.a = a; // 횟수
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 =  new StringTokenizer(br.readLine());
		
		// 상자 가로 칸
		int M = Integer.parseInt(st1.nextToken());
		// 상자 세로 칸
		int N = Integer.parseInt(st1.nextToken());
		// 상자 높이
		int H = Integer.parseInt(st1.nextToken());
		
		int[][][] boxes = new int[H][N][M];
		
        Queue<Tomato> good = new LinkedList<>();
		
		// 토마토 정보 저장 : boxes
		// 익은 토마토 정보 저장 : good		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					int temp = Integer.parseInt(st2.nextToken());
					if (temp == 1) {
						good.add(new Tomato(i, j, k, 0));
					}
					boxes[i][j][k] = temp;
				}
			}
		}
		
		int cnt = 0;
		// good이 비었다면, 가능한 모든 토마토가 익었음
		while (good.size() != 0) {
			Tomato tomato = good.poll();
			int x = tomato.x;
			int y = tomato.y;
			int z = tomato.z;
			int a = tomato.a;
			
			// 6방 탐색
			// 익지 않은 토마토 익히고, 횟수 1 증가
			if (x - 1 >= 0) {
				if (boxes[x - 1][y][z] == 0) {
					good.add(new Tomato(x - 1, y, z, a + 1));
					boxes[x - 1][y][z] = 1;
					cnt = a + 1;
				}
			}
			if (x + 1 < H) {
				if (boxes[x + 1][y][z] == 0) {
					good.add(new Tomato(x + 1, y, z, a + 1));
					boxes[x + 1][y][z] = 1;
					cnt = a + 1;
				}
			}
			if (y - 1 >= 0) {
				if (boxes[x][y - 1][z] == 0) {
					good.add(new Tomato(x, y - 1, z, a + 1));
					boxes[x][y - 1][z] = 1;
					cnt = a + 1;
				}
			}
			if (y + 1 < N) {
				if (boxes[x][y + 1][z] == 0) {
					good.add(new Tomato(x, y + 1, z, a + 1));
					boxes[x][y + 1][z] = 1;
					cnt = a + 1;
				}
			}
			if (z - 1 >= 0) {
				if (boxes[x][y][z - 1] == 0) {
					good.add(new Tomato(x, y, z - 1, a + 1));
					boxes[x][y][z - 1] = 1;
					cnt = a + 1;
				}
			}
			if (z + 1 < M) {
				if (boxes[x][y][z + 1] == 0) {
					good.add(new Tomato(x, y, z + 1, a + 1));
					boxes[x][y][z + 1] = 1;
					cnt = a + 1;
				}
			}
			
		}
		
		// 아직 익지 않은 토마토가 있다면 -1
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (boxes[i][j][k] == 0) {
						cnt = -1;
					}
				}
			}
		}
		
		System.out.println(cnt);
		
	
	

	}
}