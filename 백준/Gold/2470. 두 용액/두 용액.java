import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	
	static int N, min;
	static int[] liquids, mins;
	
	static void find() {
		
		// 각 용액에 대해 이분탐색
		for (int i = 0; i < N; i++) {
			
			int li = liquids[i];    		
    		int left = i + 1;
    		int right = N - 1;
			int mid = (left + right) / 2;
    		
    		while (left <= right) {
    			
    			mid = (left + right) / 2;
    			int temp = li + liquids[mid];
    			
    			int tempMin = Integer.min(Math.abs(min), Math.abs(li + liquids[mid]));

        		if (Math.abs(tempMin) != Math.abs(min)) {
        			mins[0] = li;
            		mins[1] = liquids[mid];
            		min = tempMin;
        		}
    			    			
    			if (temp <= 0) {
    				left = mid + 1;
    			} else if (temp > 0) {
    				right = mid - 1;
    			// 0이면 바로 최소니까 종료
    			} else {
    				return;
    			}
    		}
    	}
		
		return;
	}
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 용액의 수
    	N = Integer.parseInt(br.readLine());
    	
    	// 용액 특성값들
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	liquids = new int[N];
    	for (int i = 0; i < N; i++) {
    		liquids[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 정렬
    	Arrays.sort(liquids);
    	    	    	
    	// 최솟값 초기화
    	min = Integer.MAX_VALUE;
    	
    	mins = new int[2];
    	
    	// 용액 찾는 메서드
    	find();
    	
    	// 찾은 두 용액 오름차순으로
    	Arrays.sort(mins);
    	
    	System.out.println(mins[0] + " " + mins[1]);
    	
    }
}