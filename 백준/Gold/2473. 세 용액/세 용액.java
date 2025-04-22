import java.util.*;
import java.io.*;

public class Main {
    
	static int N;
    static long min;
    static long[] ans, liquids;
    
    // 알맞은 용액 조합 찾기
    static void find() {
        
        // 용액 하나는 고정
        for (int i = 0; i < N - 2; i++) {
            // 각 용액에 대해 이분탐색
            for (int j = i + 1; j < N - 1; j++) {
            	// 처음에 고정한 용액이랑 겹치면 안됨
            	if (j != i) {
            		                    
                    // 이분탐색 위해 left, right
            		int left = j + 1;
            		int right = N - 1;
                    
                    while (left <= right) {
                    	int mid = (left + right) / 2;
                        
                        // mid가 고정한 i, j와 겹치면 넘어감
                        if (mid == i || mid == j) {
                            if (mid == left) left++;
                            else right--;
                            continue;
                        }

                    	long temp = liquids[i] + liquids[j] + liquids[mid];
                        
                        // 기존 최솟 절대값과 이번 값 비교
                    	if (Math.abs(temp) < Math.abs(min)) {
                            ans[0] = liquids[i];
                            ans[1] = liquids[j];
                            ans[2] = liquids[mid];
                            min = temp;
                        }
                              
                        // 이분탐색
                        if (temp < 0) {
                            left = mid + 1;
                        } else if (temp > 0) {
                            right = mid - 1;
                        // 0이면 바로 최소니까 종료
                        } else {
                            return;
                        }
                    }
                }    
            }
        }
        return;
    }
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 용액 수
        N = Integer.parseInt(br.readLine());
        
        // 용액들
        liquids = new long[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            liquids[i] = Long.parseLong(st.nextToken());
        }
        
        // 정렬
        Arrays.sort(liquids);
        
        // 찾은 용액
        ans = new long[3];    
        
        min = Long.MAX_VALUE;
        
        find();
        
        // 정답 정렬 후 출력
        Arrays.sort(ans);
        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }
}