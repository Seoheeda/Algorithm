import java.io.*;
import java.util.*;

public class Main {
	
	static int[] parents;
	
	// 원소가 속한 집합 찾기
	static int find(int target) {
		
		if (target == parents[target]) {
			return target;
		} else {
			parents[target] = find(parents[target]);
			return parents[target];
		}
	}
	
	// 두 원소가 속한 집합 합치고 true 반환
	// 이미 같으면 false 반환
	static boolean union(int a, int b) {
		
		a = find(a);
		b = find(b);
		
		if (a == b) {
			return false;
		} else {
			parents[b] = a;
			return true;
		}
	}
	
	public static void main(String[] args) throws Exception {
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 점의 개수
		int n = Integer.parseInt(st.nextToken());
		// 진행된 차례의 수
		int m = Integer.parseInt(st.nextToken());
		
		// 부모 배열 초기화
		parents = new int[500001];
		
		for (int i = 0; i < 500001; i++) {
			parents[i] = i;
		}
		
		// 정답
		int ans = 0;
		
		for (int i = 1; i < m + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
						
			if (find(a) == find(b)) {
				ans = i;
				break;
			} else {
				union(a, b);
			}
		}
		
		System.out.println(ans);
	}
}	