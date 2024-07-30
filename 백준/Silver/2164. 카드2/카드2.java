import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> queue= new LinkedList<Integer>();
		
		for (int i = 1; i <= n; i++) {
			queue.add(i);
		}
		
		while (queue.size() > 1) {
			queue.poll();
			queue.add(queue.poll());
		}
		for (int q : queue) {
			System.out.println(q);
		}
	}

}
