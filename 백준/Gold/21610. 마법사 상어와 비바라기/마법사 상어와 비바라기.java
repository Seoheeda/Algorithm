import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st1 = new StringTokenizer(br.readLine());
    // N : 행열 개수, M : 명령 개수
    int N = Integer.parseInt(st1.nextToken());
    int M = Integer.parseInt(st1.nextToken());
    
    // 바구니 정보 2차원 배열로 입력받기
    int[][] arr = new int[N][N];
    for (int i = 0; i < N; i++) {
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
            arr[i][j] = Integer.parseInt(st2.nextToken());
        }
    }
    
    // 비구름 위치
    int[][] clouds = new int[N * N][2];
    clouds[0][0] = N - 1;
    clouds[0][1] = 0;
    clouds[1][0] = N - 1;
    clouds[1][1] = 1;
    clouds[2][0] = N - 2;
    clouds[2][1] = 0;
    clouds[3][0] = N - 2;
    clouds[3][1] = 1;
    // 구름 개수
    int clouds_cnt = 4;
        
        
    // 방향들
    int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};


    for (int i = 0; i < M; i++) {
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        // d: 방향, s: 거리
        int d = Integer.parseInt(st3.nextToken());
        int s = Integer.parseInt(st3.nextToken());
        
        // 물이 증가한 칸 저장 (r, c)
        int[][] increased = new int[N * N][2];
        int inc_cnt = 0;

        
        for (int j = 0; j < clouds_cnt; j++) {
            
            // 1. 모든 구름이 d 방향으로 s칸 이동한다
        	clouds[j][0] = (N + clouds[j][0] + dx[d - 1] * s % N) % N;
        	clouds[j][1] = (N + clouds[j][1] + dy[d - 1] * s % N) % N;

            
            // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다
            arr[clouds[j][0]][clouds[j][1]] += 1;
            
            // 물이 증가한 칸 저장
            increased[j][0] = clouds[j][0];
            increased[j][1] = clouds[j][1];
            inc_cnt++;
        }
        
	     // 3. 구름이 모두 사라진다.
	     boolean[][] isCloud = new boolean[N][N];
	     for (int j = 0; j < clouds_cnt; j++) {
	         isCloud[clouds[j][0]][clouds[j][1]] = true;
	         clouds[j][0] = 0;
	         clouds[j][1] = 0;
	     }
	
	     clouds_cnt = 0;
        
        // 4. 2에서 물이 증가한 칸에 물복사버그 마법을 시전한다.
        // 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니 수만큼 증가한다.
        for(int j = 0; j < inc_cnt; j++) {
        	int r = increased[j][0];
        	int c = increased[j][1];
        	int cnt = 0;
        	
        	if (r - 1 >= 0 && c - 1 >= 0 && arr[r - 1][c - 1] >= 1) {
        		cnt++;
        	} 
        	if (r - 1 >= 0 && c + 1 < N && arr[r - 1][c + 1] >= 1) {
        		cnt++;
        	} 
        	if (r + 1 < N && c - 1 >= 0 && arr[r + 1][c - 1] >= 1) {
        		cnt++;
        	}
        	if (r + 1 < N && c + 1 < N && arr[r + 1][c + 1] >= 1) {
        		cnt++;
        	}
        	
        	arr[r][c] += cnt;
        }
        
        // 5. 물의 양이 2 줄어든다
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                if (arr[k][l] >= 2 && !isCloud[k][l]) {
                    arr[k][l] -= 2;
                    clouds[clouds_cnt][0] = k;
                    clouds[clouds_cnt][1] = l;
                    clouds_cnt++;
                }
            }
        }    
        
    }

   
    
    int sum = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            sum += arr[i][j];
        }
    }
    System.out.println(sum);
}
}