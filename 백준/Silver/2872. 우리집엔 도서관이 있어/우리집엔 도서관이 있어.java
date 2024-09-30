import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int N = Integer.parseInt(br.readLine());
    	
    	int maxVal = Integer.MIN_VALUE;
    	int maxIndex = -1;
    	int[] arr = new int[N];
    	    	
    	for (int i = 0; i < N; i++) {
    		int a = Integer.parseInt(br.readLine());
    		arr[i] = a;
    		if (maxVal < a) {
    			maxIndex = i;
    			maxVal = a;
    		}
    	}
    	
    	int cnt = N - maxIndex - 1;
    	for (int i = maxIndex - 1; i >= 0; i--) {
    		if (maxVal - arr[i] == 1) {
    			maxVal = arr[i];
    		} else {
    			cnt++;
    		}
    	}
    	System.out.println(cnt);
    }
}