import java.util.*;
import java.io.*;

class Problem implements Comparable<Problem> {
	int num;
	int level;
	
	public Problem(int num, int level) {
		this.num = num;
		this.level = level;
	}

	@Override
	public int compareTo(Problem p) {
		// 레벨 내림차순
		if (this.level != p.level) {
			return Integer.compare(p.level, this.level);
		}
		// 레벨이 같으면 num 오름차순
		return Integer.compare(p.num, this.num);
	}
}

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 문제 개수
		int N = Integer.parseInt(br.readLine());
		
		// 문제들
		PriorityQueue<Problem> problemsHard = new PriorityQueue<Problem>();
		PriorityQueue<Problem> problemsEasy = new PriorityQueue<Problem>(Comparator.reverseOrder());
		
		// 문제 존재 여부
		int[] isThere = new int[100001];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int tempNum = Integer.parseInt(st.nextToken());
			int tempLevel = Integer.parseInt(st.nextToken());
			
			isThere[tempNum] = tempLevel;
			problemsHard.add(new Problem(tempNum, tempLevel));
			problemsEasy.add(new Problem(tempNum, tempLevel));
		}

		// 명령문 개수
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String order = st.nextToken();
			
			if (order.equals("recommend")) {
				int x = Integer.parseInt(st.nextToken());
								
				// 가장 어려운 문제
				if (x == 1) {
					while (true) {
						Problem p = problemsHard.peek();
						if (isThere[p.num] == p.level) {
							System.out.println(p.num);
							break;
						}
						problemsHard.poll();
					}
					
				// 가장 쉬운 문제
				} else if (x == -1) {
					while (true) {
						Problem p = problemsEasy.peek();
						if (isThere[p.num] == p.level) {
							System.out.println(p.num);
							break;
						}
						problemsEasy.poll();
					}
				}
				
			} else if (order.equals("add")) {
				int tempNum = Integer.parseInt(st.nextToken());
				int tempLevel = Integer.parseInt(st.nextToken());
				
				isThere[tempNum] = tempLevel;
				problemsHard.add(new Problem(tempNum, tempLevel));
				problemsEasy.add(new Problem(tempNum, tempLevel));
			} else if (order.equals("solved")) {
				int tempNum = Integer.parseInt(st.nextToken());
				
				isThere[tempNum] = 0;
			}
		}
	}
}