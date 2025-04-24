import java.io.*;
import java.util.*;

public class Main {

	static int[] steps;
	static int len;
	static int[][][] dp; // dp[step][left][right] = 최소 힘

	// 이동 시 드는 힘 구하기
	static int getStrength(int from, int to) {
		if (from == to) {
			return 1;
		} else if (from == 0) {
			return 2;
		} else if (Math.abs(from - to) == 2) {
			return 4;
		}
		return 3;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 저장
		List<Integer> orders = new ArrayList<>();
		while (st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			
			if (num == 0) {
				break;
			}
			
			orders.add(num);
		}

		// 입력 길이
		len = orders.size();
		steps = new int[len];
		for (int i = 0; i < len; i++) {
			steps[i] = orders.get(i);
		}

		// dp 초기화
		dp = new int[len + 1][5][5];
		
		for (int i = 0; i < len + 1; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					dp[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}

		// 시작 상태: 왼발 0, 오른발 0
		dp[0][0][0] = 0;

		// 발판 순서대로 밟기
		for (int i = 0; i < len; i++) {
			// 다음으로 밟을 발판 번호
			int next = steps[i];

			// 왼발 위치, 오른발 위치
			for (int l = 0; l < 5; l++) {
				for (int r = 0; r < 5; r++) {
					
					// 현재 순서에 이 왼발 오른발 위치 없음
					if (dp[i][l][r] == Integer.MAX_VALUE) {
						continue;
					}

					// 현재까지 누적 힘
					int sumPower = dp[i][l][r];

					// 다음 움직여야 하는 위치가 오른발 위치가 아니라면 왼발 옮기기
					if (next != r) {
						int movePower = getStrength(l, next);
						dp[i + 1][next][r] = Math.min(dp[i + 1][next][r], sumPower + movePower);
					}

					// 다음 움직여야 하는 위치가 왼발 위치가 아니라면 오른발 옮기기
					if (next != l) {
						int movePower = getStrength(r, next);
						dp[i + 1][l][next] = Math.min(dp[i + 1][l][next], sumPower + movePower);
					}
				}
			}
		}

		// 정답: 마지막 단계에서 최소 힘
		int result = Integer.MAX_VALUE;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				result = Math.min(result, dp[len][i][j]);
			}
		}

		System.out.println(result);
	}
}