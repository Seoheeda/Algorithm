import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		ArrayList<String> list = new ArrayList<String>(Arrays.asList(br.readLine().split(" ")));
		ArrayList<Integer> NGE = new ArrayList<Integer>();
		
		for (int i = 0; i < n; i++) {
			NGE.add(-1);
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(0);
		
		for (int i = 1; i < n; i++) {
			while ((stack.size() != 0) && (Integer.parseInt(list.get(stack.peek())) < Integer.parseInt(list.get(i)))) {
				NGE.set(stack.pop(), Integer.parseInt(list.get(i)));
				}
			stack.add(i);				
			}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i : NGE) {
			sb.append(i + " ");
		}
		
		System.out.println(sb);
		}
		
		
	}
