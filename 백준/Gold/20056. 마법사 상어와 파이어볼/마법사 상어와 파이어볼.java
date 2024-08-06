import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	
	// 파이어볼 객체
    static class Fireball {
        int r, c, m, s, d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N: 격자 크기, M: 파이어볼 개수, K: 이동 횟수
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int M = Integer.parseInt(st1.nextToken());
        int K = Integer.parseInt(st1.nextToken());

        // 방향
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        // 격자에 파이어볼 정보 저장
        ArrayList<Fireball>[][] fireballs = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fireballs[i][j] = new ArrayList<>();
            }
        }

        // 초기 파이어볼 입력
        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st2.nextToken()) - 1;
            int c = Integer.parseInt(st2.nextToken()) - 1;
            int m = Integer.parseInt(st2.nextToken());
            int s = Integer.parseInt(st2.nextToken());
            int d = Integer.parseInt(st2.nextToken());

            // m: 질량, s: 속력, d: 방향
            fireballs[r][c].add(new Fireball(r, c, m, s, d));
        }

        // 총 K번 명령
        for (int k = 0; k < K; k++) {

            ArrayList<Fireball>[][] newFireballs = new ArrayList[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newFireballs[i][j] = new ArrayList<>();
                }
            }

            // 1. 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (Fireball ball : fireballs[i][j]) {
                        int r = ball.r;
                        int c = ball.c;
                        int m = ball.m;
                        int s = ball.s;
                        int d = ball.d;

                        int newR = (r + dx[d] * s % N + N) % N;
                        int newC = (c + dy[d] * s % N + N) % N;

                        newFireballs[newR][newC].add(new Fireball(newR, newC, m, s, d));
                    }
                }
            }

            // 2. 파이어볼 합치고 나누기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (newFireballs[i][j].size() > 1) {
                    	
                    	// 총 질량 합
                        int totalM = 0;
                        // 총 속력 합
                        int totalS = 0;
                        
                        // 모두 홀수인가
                        boolean allOdd = true;
                        // 모두 짝수인가
                        boolean allEven = true;
                        int count = newFireballs[i][j].size();

                        // 총 질량, 속력 계산 - 짝수 홀수 파악
                        for (Fireball ball : newFireballs[i][j]) {
                            totalM += ball.m;
                            totalS += ball.s;
                            if (ball.d % 2 == 0) allOdd = false;
                            else allEven = false;
                        }

                        int newM = totalM / 5;
                        int newS = totalS / count;
                        
                        // 파이어볼 지우고
                        newFireballs[i][j].clear();

                        // 새로운 파이어볼 저장
                        if (newM > 0) {
                            int[] newDirections = (allOdd || allEven) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};
                            for (int d : newDirections) {
                                newFireballs[i][j].add(new Fireball(i, j, newM, newS, d));
                            }
                        }
                    }
                }
            }

            // 갱신된 파이어볼로
            fireballs = newFireballs;
        }

        // 최종 남은 모든 파이어볼 질량의 합 계산
        int totalMass = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Fireball ball : fireballs[i][j]) {
                    totalMass += ball.m;
                }
            }
        }

        System.out.println(totalMass);
    }
}
