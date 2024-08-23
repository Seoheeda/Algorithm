import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] userA, userB;
	static int xA, yA, xB, yB;
	static int M, A;
	static ArrayList<BC> battery;
	static int ans;
	static boolean[] visited;
	
	// 배터리 정보
	static class BC {
		// 좌표
		int X;
		int Y;
		// 충전 범위
		int C;
		// 처리량
		int P;
		
		public BC(int x, int y, int c, int p) {
			super();
			X = x;
			Y = y;
			C = c;
			P = p;
		}
	}
	
	// n번 충전기에서 충전 가능한 만큼 리턴
	public static int count(int n, int cx, int cy) {
	
		BC bc = battery.get(n);
		int x = bc.X;
		int y = bc.Y;
		int c = bc.C;
		int p = bc.P;
		
		if (Math.abs(cx - x) + Math.abs(cy - y) <= c) {
			return  p;
		}
		
		return 0;
	}
	
	// 해당 위치에서 충전 가능한 최댓값 리턴
	public static int find() {
		int max = 0;
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				int sum = 0;
				int cntA = count(i, xA, yA);
				int cntB = count(j, xB, yB);
				
				if (i != j) {
					sum += cntA;
					sum += cntB;
				} else {
					sum += Math.max(cntA, cntB);
				}
				
				if (max < sum) {
					max = sum;
				}
				
			}
		}
		
		return max;
	}
 
    public static void main(String[] args) throws NumberFormatException, IOException {
     
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(br.readLine());
    	
    	for (int t = 1; t <= T; t++) {
    		StringTokenizer st1 = new StringTokenizer(br.readLine());
    		
    		// 이동 시간
    		M = Integer.parseInt(st1.nextToken());
    		// BC 개수
    		A = Integer.parseInt(st1.nextToken());
    		
    		// 사용자 A의 이동 정보
    		userA = new int[M + 1];
    		StringTokenizer st2 = new StringTokenizer(br.readLine());
    		for (int i = 1; i <= M; i++) {
    			userA[i] = Integer.parseInt(st2.nextToken());
    		}
    		// 초기 위치
    		xA = 1;
    		yA = 1;
    		
    		// 사용자 B의 이동 정보
    		userB = new int[M + 1];
    		StringTokenizer st3 = new StringTokenizer(br.readLine());
    		for (int i = 1; i <= M; i++) {
    			userB[i] = Integer.parseInt(st3.nextToken());
    		}
    		// 초기 위치
    		xB = 10;
    		yB = 10;
    		
    		// BC 정보
    		battery = new ArrayList<>();
    		for (int i = 0; i < A; i++) {
    			StringTokenizer st4 = new StringTokenizer(br.readLine());
    			int x = Integer.parseInt(st4.nextToken());
    			int y = Integer.parseInt(st4.nextToken());
    			int c = Integer.parseInt(st4.nextToken());
    			int p = Integer.parseInt(st4.nextToken());

    			battery.add(new BC(y, x, c, p));
    		}
    		
    		ans = 0;
    		    		
    		for (int i = 0; i <= M; i++) {
    			// A 이동
    			if (userA[i] == 1) {
    				xA--;
    			} else if (userA[i] == 2) {
    				yA++;
    			} else if (userA[i] == 3) {
    				xA++;
    			} else if(userA[i] == 4) {
    				yA--;
    			}
    			
    			// B 이동
    			if (userB[i] == 1) {
    				xB--;
    			} else if (userB[i] == 2) {
    				yB++;
    			} else if (userB[i] == 3) {
    				xB++;
    			} else if(userB[i] == 4) {
    				yB--;
    			}
    			
    			ans += find();
    			
    		}
    		
    		System.out.println("#" + t + " " + ans);
    	}
    	
    }
 
}