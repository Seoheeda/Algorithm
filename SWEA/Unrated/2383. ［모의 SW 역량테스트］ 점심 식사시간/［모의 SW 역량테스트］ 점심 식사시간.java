import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {
	
	static int temp, ans;
	static int stairNum, peopleNum;
	static int[] arr, stairLen;
	static List<int[]> stairs, people;
	
	
	public static void dfs(int depth) {
		
		// 가능한 계단 조합 뽑기
		if (depth == peopleNum) {
			
			List<Integer> time0 = new ArrayList<>();
			List<Integer> time1 = new ArrayList<>();
			
			// 0번 계단으로 가는 사람들의 시간을 time0에 저장
			// 1번 계단으로 가는 사람들의 시간을 time1에 저장
			for (int i = 0; i < peopleNum; i++) {
				if (arr[i] == 0) {
					time0.add(Math.abs(stairs.get(0)[0] - people.get(i)[0]) + Math.abs(stairs.get(0)[1] - people.get(i)[1]));
				} else if (arr[i] == 1) {
					time1.add(Math.abs(stairs.get(1)[0] - people.get(i)[0]) + Math.abs(stairs.get(1)[1] - people.get(i)[1]));
				}
			}
			Collections.sort(time0);
			Collections.sort(time1);
			
			// 0번 계단 사람 이동
			if (time0.size() <= 3) {
				for (int i = 0; i < time0.size(); i++) {
					time0.set(i, time0.get(i) + stairLen[0] + 1);
				}
			} else {
				for (int i = 0; i < 3; i++) {
					time0.set(i, time0.get(i) + stairLen[0] + 1);
				}
				
				for (int i = 3; i < time0.size(); i++) {
					if (time0.get(i) >= (time0.get(i - 3))) {
						time0.set(i, time0.get(i) + stairLen[0] + 1);
					} else {
						time0.set(i, time0.get(i - 3) + stairLen[0]);
					}
				}
			}
			
			// 1번 계단 사람 이동
			if (time1.size() <= 3) {
				for (int i = 0; i < time1.size(); i++) {
					time1.set(i, time1.get(i) + stairLen[1] + 1);
				}
			} else {
				for (int i = 0; i < 3; i++) {
					time1.set(i, time1.get(i) + stairLen[1] + 1);
				}
				
				for (int i = 3; i < time1.size(); i++) {
					if (time1.get(i) >= (time1.get(i - 3))) {
						time1.set(i, time1.get(i) + stairLen[1] + 1);
					} else {
						time1.set(i, time1.get(i - 3) + stairLen[1]);
					}
				}
			}
			
			if (time0.size() > 0 && time1.size() > 0) {
				temp = Math.max(time0.get(time0.size() - 1), time1.get(time1.size() - 1));
			} else if (time0.size() == 0) {
				temp = time1.get(time1.size() - 1);
			} else {
				temp = time0.get(time0.size() - 1);

			}
			ans = Math.min(temp, ans);
				
			return;
		}
		
		arr[depth] = 0;
		dfs(depth + 1);
		
		arr[depth] = 1;
		dfs(depth + 1);
		
	}
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
        	
        	// 방의 한변 길이
        	int N = Integer.parseInt(br.readLine());
        	
        	// 방 정보
        	int[][] room = new int[N][N];
        	// 계단 정보
        	stairs = new ArrayList<>();
        	// 계단 수
        	stairNum = 0;
        	// 계단 길이
        	stairLen = new int[2];
        	// 사람 정보
        	people = new ArrayList<>();
        	// 사람 수
        	peopleNum = 0;
        	
        	for (int i = 0; i < N; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < N; j++) {
        			room[i][j] = Integer.parseInt(st.nextToken());
        			if (room[i][j] != 0 && room[i][j] != 1) {
        				int[] temp = {i, j};
        				stairs.add(temp);
        				stairLen[stairNum] = room[i][j];
        				stairNum++;
        			} else if (room[i][j] == 1) {
        				int[] temp = {i, j};
        				people.add(temp);
        				peopleNum++;
        			}
        		}
        	}
        	
        	arr = new int[peopleNum];
        	ans = Integer.MAX_VALUE;
        	dfs(0);
        	
        	System.out.println("#" + t + " " + ans);
        }
    }
}