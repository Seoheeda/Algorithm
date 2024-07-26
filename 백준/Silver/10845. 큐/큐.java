import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> queue = new LinkedList<>();
		int n = Integer.parseInt(br.readLine());
		int back = 0;
		
		for (int i = 0; i < n; i++) {
			String x = br.readLine();
			String [] command = x.split(" ");
			
			switch (command[0]) {
			case "push" :
				int a = Integer.parseInt(command[1]);
				queue.add(a);
				back = a;
				break;
			case "pop" :
				if (queue.size() == 0) {
					System.out.println(-1);
				} else {
				System.out.println(queue.poll());
				}
				break;
			case "size" :
				System.out.println(queue.size());
				break;
			case "empty" :
				if (queue.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				break;
			case "front" :
				if (queue.size() == 0) {
					System.out.println(-1);
				} else {
				System.out.println(queue.peek());
				}
				break;
			case "back" :
				if (queue.size() == 0) {
					System.out.println(-1);
				} else {
				System.out.println(back);
				}
				break;
			}
			
		}
	}

}
