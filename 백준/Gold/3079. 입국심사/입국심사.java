import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 입국심사대 개수
    	int N = Integer.parseInt(st.nextToken());
    	// 사람 수
    	int M = Integer.parseInt(st.nextToken());
    	
    	// 시간들
    	int[] times = new int[N];
    	for (int i = 0; i < N; i++) {
    		times[i] = Integer.parseInt(br.readLine());
    	}
    	
    	// 이분탐색 위한 값 설정
    	long start = 0;
    	long end = (long) times[0] * M;
    	long ans = Long.MAX_VALUE;
    	
    	while (start <= end) {
    		long mid = (start + end) / 2;
    		
    		long sum = 0;
    		for (int t : times) {
    			sum += (mid / t);
    			if (sum > M) {
    				break;
    			}
    		}
    		
    		// 심사를 다 못하면 시간 늘려야 함.
    		if (sum < M) {
    			start = mid + 1;
    		// 삼사 다 가능하면 정답 갱신
    		} else {
    			ans = Long.min(ans, mid);
    			end = mid - 1;
    		}
    	}
    	
    	System.out.println(ans);
    	
    }
}