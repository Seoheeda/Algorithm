import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            // 접수 창구 개수
            int N = Integer.parseInt(st.nextToken());
            // 정비 창구 개수
            int M = Integer.parseInt(st.nextToken());
            // 방문 고객 수
            int K = Integer.parseInt(st.nextToken());
            // 지갑 두고 간 고객이 이용한 접수 창구번호
            int A = Integer.parseInt(st.nextToken()) - 1;
            // 지갑 두고 간 고객이 이용한 정비 창구번호
            int B = Integer.parseInt(st.nextToken()) - 1;

            // 접수 창구 소요시간
            int[] timeN = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                timeN[i] = Integer.parseInt(st.nextToken());
            }

            // 정비 창구 소요시간
            int[] timeM = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                timeM[i] = Integer.parseInt(st.nextToken());
            }

            // 고객 도착시간, 고객번호, 이용 창구
            int[][] timeC = new int[K][3];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                timeC[i][0] = Integer.parseInt(st.nextToken()); // 도착 시간
                timeC[i][1] = i + 1; // 고객 번호
            }

            int ans = 0;

            // 접수 창구 대기시간
            int[] time = new int[N];

            // 고객 번호마다
            for (int i = 0; i < K; i++) {
                // 입장할 창구
                int whereN = -1;
                for (int a = 0; a < N; a++) {
                    // 고객 도착 시간보다 창구가 먼저 비면 -> 그 창구 선택
                    if (time[a] <= timeC[i][0]) {
                        whereN = a;
                        break;
                    }
                }

                if (whereN == -1) { // 빈 창구가 없으면 가장 빨리 비는 창구 선택
                    for (int a = 0; a < N; a++) {
                        if (whereN == -1 || time[a] < time[whereN]) {
                            whereN = a;
                        }
                    }
                }

                // 고객이 이용한 접수 창구 번호 기록
                timeC[i][2] = whereN;

                // 도착 시간 이후에 창구 비면 -> 접수 처리
                if (time[whereN] < timeC[i][0]) {
                    time[whereN] = timeC[i][0] + timeN[whereN];
                // 아직 안 비면 -> 기다렸다가 처리
                } else {
                    time[whereN] = time[whereN] + timeN[whereN];
                }

                // 고객 접수 완료 시간 저장
                timeC[i][0] = time[whereN];
            }

            // 고객 입장시간 재정렬 (접수 완료 시간 -> 창구 번호)
            Arrays.sort(timeC, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[2] - b[2]));

            // 정비 창구 대기시간
            time = new int[M];

            // 고객 번호마다
            for (int i = 0; i < K; i++) {

                // 입장할 창구
                int whereM = -1;

                for (int a = 0; a < M; a++) {
                    // 창구 비면 -> 해당 창구 사용
                    if (time[a] <= timeC[i][0]) {
                        whereM = a;
                        break;
                    }
                }

                if (whereM == -1) { // 빈 창구가 없으면 가장 빨리 비는 창구 사용
                    for (int a = 0; a < M; a++) {
                        if (whereM == -1 || time[a] < time[whereM]) {
                            whereM = a;
                        }
                    }
                }

                // 창구 비면 -> 바로 이용
                if (time[whereM] < timeC[i][0]) {
                    time[whereM] = timeC[i][0] + timeM[whereM];
                // 아직 찼으면 -> 대기 후 시용
                } else {
                    time[whereM] = time[whereM] + timeM[whereM];
                }

                // 접수 창구 A, 정비 창구 B 이용한 고객 번호 누적합
                if (whereM == B && timeC[i][2] == A) {
                    ans += timeC[i][1];
                }
            }

            // 그런 고객이 없다면 -1 출력하기
            if (ans == 0) {
                System.out.println("#" + t + " " + -1);
            } else {
                System.out.println("#" + t + " " + ans);
            }
        }
    }
}