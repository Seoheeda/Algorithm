import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static int[] arr;
	
	private static void dfs(int depth) {
		if (depth == M) {
			for (int a : arr) {
				sb.append(a);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
				arr[depth] = i + 1;
				dfs(depth + 1);
		}
	}
	

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 찾기
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	arr = new int[M];
    	dfs(0);
    	System.out.println(sb);
    }
}
