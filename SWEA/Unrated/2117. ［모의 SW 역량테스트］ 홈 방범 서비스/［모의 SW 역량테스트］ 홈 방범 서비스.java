import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
    
public class Solution {
     
    static int N, M, max;
    static int[][] city;
     
    public static void calc(int len) {
         
    	// 운영 비용
        int K = len * len + (len - 1) * (len - 1);
        
        // 마름모 중심 기준으로
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
        
            	// 집 개수
                int cnt = 0;
                 
                // 마름모 탐색
                // 중간 (o)
                for (int a = j - len + 1; a <= j + len - 1; a++) {
                    if (a >= 0 && a < N && city[i][a] == 1) {
                        cnt++;
                    }
                }
                 
                // 위로
                for (int a = 1; a < len; a++) {
                    for (int b = j - len + 1 + a; b <= j + len - 1 - a; b++) {
                        int nx = i - a;
                        int ny = b;
                        if (nx >= 0 && nx < N && ny >= 0 && ny < N && city[nx][ny] == 1) {
                            cnt++;
                        }
                    }
                }
                 
                // 아래로
                for (int a = 1; a < len; a++) {
                    for (int b = j - len + 1 + a; b <= j + len - 1 - a; b++) {
                        int nx = i + a;
                        int ny = b;
                        if (nx >= 0 && nx < N && ny >= 0 && ny < N && city[nx][ny] == 1) {
                            cnt++;
                        }
                    }
                }
                
                // 흑자라면 개수 업데이트
                if (cnt * M >= K) {
                    max = Integer.max(max, cnt);
                }           
            }
        }
    }
     
      
    public static void main(String[] args) throws Exception {
    
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
       int T = Integer.parseInt(br.readLine());
        
       for (int t = 1; t <= T; t++) {
            
           StringTokenizer st = new StringTokenizer(br.readLine());
           // 도시의 크기
           N = Integer.parseInt(st.nextToken());
           // 하나의 집이 지불할 수 있는 비용
           M = Integer.parseInt(st.nextToken());
            
           city = new int[N][N];
            
           for (int i = 0; i < N; i++) {
               st = new StringTokenizer(br.readLine());
               for (int j = 0; j < N; j++) {
                   city[i][j] = Integer.parseInt(st.nextToken());
               }
           }
            
           // 최댓값 초기화
           max = 0;
           
           // 가능한 마름모 크기 모두 try
           for (int i = 1; i <= N + 1; i++) {
               calc(i);
           }
            
           System.out.println("#" + t + " " + max);
            
            
       }
        
        
       
    }
}