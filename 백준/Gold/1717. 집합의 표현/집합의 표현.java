import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	
	// 합집합
	private static void union(int x, int y) {
		x = sets(x);
		y = sets(y);
		if (x == y) {
			return;
		} else {
			arr[y] = x;
		}
	}
	
	// 원소가 속한 집합 찾기
	private static int sets(int target) {
		if (target == arr[target]) {
			return target;
		}
		arr[target] = sets(arr[target]);
		return arr[target];
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 1];
		
		for (int i = 0; i <= n; i++) {
			arr[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if (a == 0) {
				union(b, c);
			} else if (a == 1) {
				if (sets(b) == sets(c)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
        }	
	}
}