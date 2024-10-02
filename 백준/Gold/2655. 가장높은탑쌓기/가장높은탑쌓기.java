import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 벽돌 정보
class Rock implements Comparable<Rock>{
	
	int n; // 벽돌 번호
	int a; // 벽돌 밑면 넓이
	int b; // 벽돌 높이
	int w; // 벽돌 무게
	
	public Rock(int n, int a, int b, int w) {
		super();
		this.n = n;
		this.a = a;
		this.b = b;
		this.w = w;
	}

	// 무게 기준으로 오름차순
	@Override
	public int compareTo(Rock o) {
		return Integer.compare(this.w, o.w);
	}	
}

public class Main {
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 입력될 벽돌 수
    	int N = Integer.parseInt(br.readLine());
    	
    	// 벽돌 정보
    	// 번호, 밑면, 높이, 무게
    	ArrayList<Rock> list = new ArrayList<Rock>();
    	list.add(new Rock(0, 0, 0, 0));
    	for (int i = 1; i < N + 1; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		list.add(new Rock(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    	}
    	
    	// 무게 기준 오름차순 정렬
    	Collections.sort(list);
    	
    	// dp[i] : i번째 벽돌이 맨 위에 있을 때 탑의 최대 높이
    	int[] dp = new int[N + 1];
    	
    	for (int i = 1; i < N + 1; i++) {
    		for (int j = 0; j < i; j++) {
    			// i번째 벽돌을 j번째 벽돌 위에 쌓을 수 있나?
    			if (list.get(i).a > list.get(j).a) {
    				dp[i] = Math.max(dp[i], dp[j] + list.get(i).b);
    			}
    		}
    	}
    	
    	int max = Integer.MIN_VALUE;
    	
    	// dp 배열에서 최댓값이 탑 쌓는 최대 높이
    	for (int i = 0; i < N + 1; i++) {
    		if (max < dp[i]) {
    			max = dp[i];
    		}
    	}
    	
    	int index = N;
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	
    	// 탑에 사용된 벽돌 추적
    	while (index != 0) {
    		if (max == dp[index]) {
    			res.add(list.get(index).n);
    			max -= list.get(index).b;
    		}
    		index--;
    	}
    	
    	// 높이 출력
    	System.out.println(res.size());
    	
    	// 벽돌 출력
    	for (int i = res.size() - 1; i >= 0; i--) {
    		System.out.println(res.get(i));
    	}  	
    }
}