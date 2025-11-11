import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 더해서 되는 수
		long T = Integer.parseInt(br.readLine());
		
		// 첫번째 배열
		int N = Integer.parseInt(br.readLine());
		int[] arr1 = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		
		// 두번째 배열
		int M = Integer.parseInt(br.readLine());
		int[] arr2 = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < M; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		
		 Map<Long, Integer> map = new HashMap<>();
		 
        for (int i = 0; i < N; i++) {
            long sum = 0;
            for (int j = i; j < N; j++) {
                sum += arr1[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        long answer = 0;
        for (int i = 0; i < M; i++) {
            long sum = 0;
            for (int j = i; j < M; j++) {
                sum += arr2[j];
                answer += map.getOrDefault(T - sum, 0);
            }
        }

	    System.out.println(answer);
	}
}