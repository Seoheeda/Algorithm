import java.io.*;
import java.util.*;

// 숫자 & 그 숫자 도달 시까지 걸린 연산
class numInfo {
	int num;
	String calc;
	public numInfo(int num, String calc) {
		super();
		this.num = num;
		this.calc = calc;
	}
}

public class Main {
	
	static int A, B, len;
	static Queue<numInfo> queue;
	static boolean[] visited;
		
	// D 연산
	static int calcD(int N) {
		return (N * 2) % 10000;
	}
	
	// S 연산
	static int calcS(int N) {
		if (N == 0) {
			return 9999;
		} else {
			return N - 1;
		}
	}
	
	// L 연산
	static int calcL(int N) {
		int d1 = 0;
		int d2 = 0;
		int d3 = 0;
		int d4 = 0;
		if (N > 999) {
			d1 = N / 1000;
			N %= 1000;
		} 
		if (N > 99) {
			d2 = N / 100;
			N %= 100;
		}
		if (N > 9) {
			d3 = N / 10;
			N %= 10;
		}
		d4 = N;
		
		return ((d2 * 10 + d3) * 10 + d4) * 10 + d1;
	}
	
	// N 연산
	static int calcR(int N) {
		int d1 = 0;
		int d2 = 0;
		int d3 = 0;
		int d4 = 0;
		if (N > 999) {
			d1 = N / 1000;
			N %= 1000;
		} 
		if (N > 99) {
			d2 = N / 100;
			N %= 100;
		}
		if (N > 9) {
			d3 = N / 10;
			N %= 10;
		}
		d4 = N;
		
		return ((d4 * 10 + d1) * 10 + d2) * 10 + d3;
	}
	
	static int calcLen(int N) {
		if (N > 999) {
			return 4;
		} else if (N > 99) {
			return 3;
		} else if (N > 9) {
			return 2;
		} else {
			return 1;
		}
	}
	
	static String bfs() {
			
		while (!queue.isEmpty()) {
			
			numInfo temp = queue.poll();
			
			if (temp.num == B) {
				return temp.calc;
			}
			
			if (!visited[calcD(temp.num)]) {
				queue.add(new numInfo(calcD(temp.num), temp.calc + "D"));
				visited[calcD(temp.num)] = true;
			}
			if (!visited[calcS(temp.num)]) {
				queue.add(new numInfo(calcS(temp.num), temp.calc + "S"));
				visited[calcS(temp.num)] = true;
			}
			if (!visited[calcL(temp.num)]) { 
				queue.add(new numInfo(calcL(temp.num), temp.calc + "L"));
				visited[calcL(temp.num)] = true;
			}
			if (!visited[calcR(temp.num)]) { 
				queue.add(new numInfo(calcR(temp.num), temp.calc + "R"));
				visited[calcR(temp.num)] = true;
			}
		}
		
		return "";
	}
	
	public static void main(String[] args) throws Exception {
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 개수
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			visited = new boolean[10000];
						
			queue = new ArrayDeque<>();
			queue.add(new numInfo(A, ""));
			visited[A] = true;
			
			System.out.println(bfs());
		}
	}
}	