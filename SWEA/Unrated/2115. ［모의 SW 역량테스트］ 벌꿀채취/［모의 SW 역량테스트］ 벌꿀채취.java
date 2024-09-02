import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
  
public class Solution {
     
    static int N, M, C;
    static int[] arr;
    static List<int[]> combs;
    static int[][] hive;
    static int[][] maxes;
    static int tempMax, combCnt;
    
    // M칸으로 가능한 모든 경우의 수 뽑기
    public static void comb(int depth, int i, int j) {
         
         
        if (depth == M) {
            int temp = 0;
            int add = 0;
   
            for (int a = 0; a < M; a++) {
                add += (arr[a] * hive[i][j + a]);
                if (add > C) {
                	return;
                }
            }
            // C 이하일 경우에만 계산하기
            if (add <= C) {
                for (int a = 0; a < M; a++) {
                    temp += (arr[a] * hive[i][j + a]) * (arr[a] * hive[i][j + a]);
                }
                tempMax = Math.max(tempMax, temp);
            }
            return;
        }
         
        arr[depth] = 1;
        comb(depth + 1, i, j);
         
        arr[depth] = 0;
        comb(depth + 1, i, j);
    }
     
     
    static int[] arr2;
    static boolean[] visited;
    static int max;
    static int x1, x2, y1, y2, c1, c2;
    
    // M칸으로 가능한 모든 경우의 수 중, 가능한 조합만 뽑아서 최댓값 갱신하기
    private static void comb2(int depth, int start) {
		if (depth == 2) {
			x1 = combs.get(arr2[0])[0];
			y1 = combs.get(arr2[0])[1];
			c1 = combs.get(arr2[0])[2];
			x2 = combs.get(arr2[1])[0];
			y2 = combs.get(arr2[1])[1];
			c2 = combs.get(arr2[1])[2];
			
			// 겹치는 경우 제외
			if (x1 == x2 && Math.abs(y1 - y2) < M) {
				return;
			} else {
				max = Integer.max(max, c1 + c2);
				return;
			}
		}
		
		for (int i = start; i < combCnt; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				arr2[depth] = i;
				comb2(depth + 1, i + 1);
				visited[i] = false;
			}
		}
	}
      
    
    public static void main(String[] args) throws Exception {
  
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       // 테스트케이스 개수
       int T = Integer.parseInt(br.readLine());
        
       for (int t = 1; t <= T; t++) {
            
           StringTokenizer st = new StringTokenizer(br.readLine());
           
           // 벌통 크기
           N = Integer.parseInt(st.nextToken());
           // 선택할 수 있는 벌통의 개수
           M = Integer.parseInt(st.nextToken());
           // 채취할 수 있는 꿀의 최대 양
           C = Integer.parseInt(st.nextToken());
            
           // hive 구성
           hive = new int[N][N];
            
           for (int i = 0; i < N; i++) {
               st = new StringTokenizer(br.readLine());
               for (int j = 0; j < N; j++) {
                   hive[i][j] = Integer.parseInt(st.nextToken());
               }
           }
            
           arr = new int[M];
           combs = new ArrayList<>();
           maxes = new int[2][3];
            
           for (int i = 0; i < N; i++) {
               for (int j = 0; j <= N - M; j++) {
                    tempMax = 0;
                    // M개로 가능한 모든 경우의 수 뽑아서 combs에 넣기
                    comb(0, i, j);
                    int[] c = {i, j, tempMax};
                    combs.add(c);
               }
           }
            
           combCnt = combs.size();
           arr2 = new int[2];
           visited = new boolean[combCnt];
           max = 0;
           
           // combs로 가능한 모든 조합 중 최댓값 구해서 출력하기
           comb2(0, 0);
           
           System.out.println("#" + t + " " + max);
         
       }
    }
}