import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] cards;
	
	static int find(int target) {
		
		// lower bound
		int s1 = 0;
		int e1 = N;
		
		while (s1 < e1) {
			int mid = (s1 + e1) / 2;
			
			if (cards[mid] < target) {
				s1 = mid + 1;
			} else {
				e1 = mid;
			}
		}
		
		// upper bound
		int s2 = 0;
		int e2 = N;
		
		while (s2 < e2) {
			int mid = (s2 + e2) / 2;
			
			if (cards[mid] <= target) {
				s2 = mid + 1;
			} else {
				e2 = mid;
			}
		}
		
		return (s2 - s1);
	}
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 숫자 카드 개수
    	N = Integer.parseInt(br.readLine());
    	
    	// 숫자 카드 정수
    	cards = new int[N];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < N; i++) {
    		cards[i] = Integer.parseInt(st.nextToken());
    	}
    	Arrays.sort(cards);
    	    	
    	// 몇개인지 맞출 정수 개수
    	int M = Integer.parseInt(br.readLine());
    	
    	// 정수들 몇개인지 맞추기
    	StringBuilder sb = new StringBuilder();
    	
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < M; i++) {
    		int t = Integer.parseInt(st.nextToken());
    		sb.append(find(t)).append(" ");
    	}    
    	
    	System.out.println(sb);
    }
}