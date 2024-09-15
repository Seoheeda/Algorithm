import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	// 아이들의 수
    	int N = Integer.parseInt(st.nextToken());
    	
    	// 색상 수
    	int M = Integer.parseInt(st.nextToken());
    	
    	// 보석 개수들
    	int[] colors = new int[M];
    	int max = Integer.MIN_VALUE;
    	for (int i = 0; i < M; i++) {
    		colors[i] = Integer.parseInt(br.readLine());
    		max = Integer.max(max, colors[i]);
    	}
    	
    	int start = 1;
    	int end = max;
    	
    	while (start < end) {
    		int mid = (start + end) / 2;
    		
    		int sum = 0;
    		for (int c : colors) {
    			sum += (c / mid);
    			if (c % mid > 0) {
    				sum++;
    			}
    		}
    		
    		if (sum <= N) {
    			end = mid;
    		} else {
    			start = mid + 1;
    		}
    	}
    	
    	System.out.println(start);
    }
}