import java.io.*;
import java.util.*;

public class Main {
	
	static boolean[] cannotLie;
	static int[] parents, truth;
	
	static boolean union(int a, int b) {
		
		a = find(a);
		b = find(b);
		
		if (a == b) {
			return true;
		} else {
			parents[find(b)] = a;
			return false;
		}
		
	}
	
	static int find(int target) {
		
		if (parents[target] == target) {
			return target;
		} else {
			parents[target] = find(parents[target]);
			return parents[target];
		}
	}
	
	// 거짓말 가능한 파티 여부 확인
	static boolean canLie(List<Integer> party) {
		
		for (int i = 0; i < party.size(); i++) {
			for (int j = 0; j < truth.length; j++) {
				if (find(party.get(i)) == find(truth[j])) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 사람 수
		int N = Integer.parseInt(st.nextToken());
		// 파티 수
		int M = Integer.parseInt(st.nextToken());
		
		// 이야기 진실 아는 사람 수
		st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		
		// 진실 아는 사람들 번호
		truth = new int[C];
		for (int i = 0; i < C; i++) {
			truth[i] = Integer.parseInt(st.nextToken());
		}
		
		// 부모 정보
		parents = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			parents[i] = i;
		}
		
		// 파티 정보
		List<Integer>[] parties = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			parties[i] = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			for (int j = 0; j < P; j++) {
				parties[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 같은 파티 참석한 사람들끼리 유니온
		for (int i = 0; i < M; i++) {
			if (parties[i].size() >= 2) {
				for (int a = 0; a < parties[i].size(); a++) {
					for (int b = 1; b < parties[i].size(); b++) {
						union(parties[i].get(0), parties[i].get(b));
					}
				}
			}
		}
				
		// 정답
		int cnt = 0;
		
		for (int i = 0; i < M; i++) {
			if (canLie(parties[i])) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}