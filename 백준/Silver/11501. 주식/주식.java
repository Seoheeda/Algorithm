import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
   
    public static void main(String[] args) throws NumberFormatException, IOException {
         
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 테스트케이스 개수
    	int T = Integer.parseInt(br.readLine());
    	
    	for (int t = 1; t <= T; t++) {
    		
    		// 날짜 수
    		int N = Integer.parseInt(br.readLine());
    		
    		// price 배열에 모든 값 담기
    		int[] price = new int[N];
    		
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		
    		for (int i = 0; i < N; i++) {
    			price[i] = Integer.parseInt(st.nextToken());
    		}
    		
    		// 이윤
    		long profit = 0;
    		// 최대 주가
    		int max_price = price[N - 1];
    		
    		for (int i = N - 1; i >= 0; i--) {
    			if (price[i] > max_price) {
    				max_price = price[i];
    			} else {
    				profit += (max_price - price[i]);
    			}
    		}
    		
    		System.out.println(profit);
    	}

    }
}