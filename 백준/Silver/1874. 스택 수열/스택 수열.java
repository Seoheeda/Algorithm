import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int t = 1;
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(br.readLine());
			while (t <= k) {
				stack.add(t);
				t++;
				sb.append("+\n");
			}
			
			if (stack.get(stack.size() - 1) == k) {
				stack.pop();
				sb.append("-\n");
			}
			
		}
		
		if (!stack.isEmpty()) {
			bw.write("NO");
		} else {
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
		br.close();
		
	}

}
