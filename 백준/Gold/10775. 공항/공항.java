import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 게이트 개수
		int G = Integer.parseInt(br.readLine());
		// 비행기 개수
		int P = Integer.parseInt(br.readLine());
		
		// 비행기 정보
		int[] planes = new int[P];
		
		for (int i = 0; i < P; i++) {
			planes[i] = Integer.parseInt(br.readLine());
		}
		
		// 도킹한 비행기 대수
		int cnt = 0;
		
		// 게이트별 도킹 여부
		boolean[] docking = new boolean[G + 1];
		// 비행기 번호별 마지막으로 도킹한 위치
		int[] lastDockingPlace = new int[G + 1];
		
		for (int plane: planes) {
			int idx;
			// 처음 마주하는 비행기 번호라면
			if (lastDockingPlace[plane] == 0) {
				idx = plane;
			// 마주한 적 있는 번호라면
			} else {
				idx = lastDockingPlace[plane];
			}
			
			while (idx > 0) {
				if (!docking[idx]) {
					docking[idx] = true;
					lastDockingPlace[plane] = idx;
					cnt++;
					break;
				} else {
					idx--;
				}
				
				if (idx == 0) {
					System.out.println(cnt);
					return;
				}
			}
		}
		System.out.println(cnt);
	}
}
