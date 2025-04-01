import java.util.*;
import java.io.*;

class QueueProps {
	int[] queueArr;
	int cnt;
	
	public QueueProps(int[] queueArr, int cnt) {
		super();
		this.queueArr = queueArr;
		this.cnt = cnt;
	}
}

// 54321 넣어    q1 - > 54321  

// q2 -> 45321
// q3 -> 53421

//


public class Main {
	
	static int N, K;
	static int[] nums;
	static boolean[] visited;
	
	// 수 뒤집기
	static int[] flip(int[] numArr, int t) {
		int[] tempArr = new int[K];
		
		int index = 0;
		for (int i = t - 1; i < t + K - 1; i++) {  
			tempArr[index] = numArr[i];
			index++;
		}
				
		index = K - 1;
		for (int i = t - 1; i < t + K - 1; i++) {  
			numArr[i] = tempArr[index];
			index--;
		}
		return numArr;
	}
	
	// 오름차순 여부
	static boolean isAsc(int[] numArr) {
		int tempNum = numArr[0];
		for (int i = 1; i < N; i++) {
			if (tempNum > numArr[i]) {
				return false;
			}
			tempNum = numArr[i];
		}
		
		return true;
	}
	
	// 방문 여부 판단 위해 int로
	static int toVisited(int[] numArr) {
		
		int calced = 0;
		int times = N - 1;
		
		for (int i = 0; i < N - 1; i++) {
			calced += (numArr[i] * Math.pow(10, times));
			times--;
		}
		calced += numArr[N - 1];
		
		return calced;
	}
	
	static int bfs() {
		Queue<QueueProps> queue = new ArrayDeque<QueueProps>();
		
		queue.add(new QueueProps(nums, 0));
		int initialVisited = toVisited(nums);
		visited[initialVisited] = true;
				
		while (!queue.isEmpty()) {
			QueueProps pollArr = queue.poll();
			
			if (isAsc(pollArr.queueArr)) {
				return pollArr.cnt;
			}
			
			for (int i = 1; i < N - K + 2; i++) {
				int[] tempArr = new int[N];
				
				for (int j = 0; j < N; j++) {
					tempArr[j] = pollArr.queueArr[j];
				}
				int[] flippedArr = flip(tempArr, i);
				int forVisited = toVisited(flippedArr);
				
				if (!visited[forVisited]) {
					queue.add(new QueueProps(flippedArr, pollArr.cnt + 1));
					visited[forVisited] = true;
				}
			}
		}
		
		return -1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		nums = new int[N];
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		visited = new boolean[100000000];
	
		System.out.println(bfs());
		
	}
}