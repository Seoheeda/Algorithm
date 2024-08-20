import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 회의 개수
		int N = Integer.parseInt(br.readLine());
		
		// 회의 저장하는 2차원 배열
		long[][] meetings = new long[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meetings[i][0] = Integer.parseInt(st.nextToken());
			meetings[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 끝나는 시간이 빠른 순으로 정렬
		// 끝나는 시간이 같다면, 시작 시간이 빠른 순으로 정렬
		Arrays.sort(meetings, (o1, o2) -> {
			if (o1[1] == o2[1]) {
				return Long.compare(o1[0], o2[0]);
			} else {
				return Long.compare(o1[1], o2[1]);
			}
		});
		
		// 첫번째 회의 선택 후, 끝나는 시간 초기화
		long end = meetings[0][1];
		// 회의 개수 1로 초기화
		int cnt = 1;
		
		// 그리디하게 개수 세기
		for (int i = 1; i < N; i++) {
			if (meetings[i][0] >= end) {
				cnt++;
				end = meetings[i][1];
			}
		}
		
		System.out.println(cnt);

	}

}