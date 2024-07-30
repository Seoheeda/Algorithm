import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ArrayList<Integer> tops = new ArrayList<>();
		Stack<int[]> stack = new Stack<>();
		ArrayList<Integer> ans = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			ans.add(0);
		}
		
		while (st.hasMoreTokens()) {
			tops.add(Integer.parseInt(st.nextToken()));
		}
		
		int a = 0;
		for (int top : tops) {
			while (!stack.isEmpty() && (stack.peek()[1]) <= top) {
				stack.pop();
			}
			
			if (!stack.isEmpty()) {
				ans.set(a, stack.peek()[0] + 1);
			}
			
			stack.add(new int[] {a, top});
			a++;
		}
		
		for (int an : ans) {
			sb.append(an + " ");
		}
		
		bw.append(sb);
		bw.flush();
		bw.close();
		
		
		
	}

}
