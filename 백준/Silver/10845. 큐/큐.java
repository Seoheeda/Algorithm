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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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
					bw.write("-1\n");
				} else {
				bw.write(queue.poll() + "\n");
				}
				break;
			case "size" :
				bw.write(queue.size() + "\n");
				break;
			case "empty" :
				if (queue.isEmpty()) {
					bw.write("1\n");
				} else {
					bw.write("0\n");
				}
				break;
			case "front" :
				if (queue.size() == 0) {
					bw.write("-1\n");
				} else {
					bw.write(queue.peek() + "\n");
				}
				break;
			case "back" :
				if (queue.size() == 0) {
					bw.write("-1\n");
				} else {
					bw.write(back + "\n");
				}
				break;
			}
			
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
