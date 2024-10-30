import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 2^^N차원 배열
		int N = Integer.parseInt(st.nextToken());
		// 목표 행
		int r = Integer.parseInt(st.nextToken()) + 1;
		// 목표 열
		int c = Integer.parseInt(st.nextToken()) + 1;
		
		int size = (int) Math.pow(2, N);
		int ans = 0;
		
		while (size > 1) {
			// 1사분면
			if (r <= size / 2 && c <= size / 2) {
			}
			// 2사분면
			if (r <= size / 2 && c > size / 2) {
				ans += (size / 2) * (size / 2);
				c -= (size / 2);
			}
			// 3사분면
			if (r > size / 2 && c <= size / 2) {
				ans += (size / 2) * (size / 2) * 2;
				r -= (size / 2);
			}
			// 4사분면
			if (r > size / 2 && c > size / 2) {
				ans += (size / 2) * (size / 2) * 3;
				c -= (size / 2);
				r -= (size / 2);
			}
			size /= 2;
		}
		System.out.println(ans);
	}
}