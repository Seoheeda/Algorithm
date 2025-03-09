import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 공사 시작하기 전 역의 개수
		int N = Integer.parseInt(st.nextToken());
		// 공사 횟수
		int M = Integer.parseInt(st.nextToken());
		
		// 이전 역 정보
		int[] prevStation = new int[1000001];
		// 다음 역 정보
		int[] nextStation = new int[1000001];
		
		st = new StringTokenizer(br.readLine());
		// 첫번째 역
		int firstStation = Integer.parseInt(st.nextToken());
		int now = firstStation;
		
		for (int i = 0; i < N - 1; i++) {
			int next = Integer.parseInt(st.nextToken());
			prevStation[next] = now;
			nextStation[now] = next;
			now = next;
		}
		
		nextStation[now] = firstStation;
		prevStation[firstStation] = now;
		
		StringBuilder sb = new StringBuilder();
		
		// 공사 정보
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			String info = st.nextToken();
			
			if (info.equals("BN")) {
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				int nextA = nextStation[a];
				sb.append(nextA).append("\n");
				
				nextStation[a] = b;
				prevStation[b] = a;
				nextStation[b] = nextA;
				prevStation[nextA] = b;
				
			} else if (info.equals("BP")) {
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				int prevA = prevStation[a];
				sb.append(prevA).append("\n");
				
				nextStation[prevA] = b;
				prevStation[b] = prevA;
				nextStation[b] = a;
				prevStation[a] = b;
				
			} else if (info.equals("CN")) {
				
				int a = Integer.parseInt(st.nextToken());
				
				int nextA = nextStation[a];
				int nextnextA = nextStation[nextA];
				
				sb.append(nextA).append("\n");
				
				nextStation[a] = nextnextA;
				prevStation[nextnextA] = a;
				
				prevStation[nextA] = 0;
				nextStation[nextA] = 0;
			
			} else {

				int a = Integer.parseInt(st.nextToken());
				
				int prevA = prevStation[a];
				int prevprevA = prevStation[prevA];
				
				sb.append(prevA).append("\n");
				
				prevStation[a] = prevprevA;
				nextStation[prevprevA] = a;
				
				prevStation[prevA] = 0;
				nextStation[prevA] = 0;
			}
		}	
		
		System.out.println(sb);
	}
}	