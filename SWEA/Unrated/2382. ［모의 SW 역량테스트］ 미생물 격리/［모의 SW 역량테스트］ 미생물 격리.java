import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Misaeng implements Comparable<Misaeng>{
    int x; // x좌표
    int y; // y좌표
    int cnt; // 개체 수
    int way; // 이동 방향

    public Misaeng(int x, int y, int cnt, int way) {
        super();
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.way = way;
    }    

    // x좌표, y좌표, 개체 수 순서로 정렬
    @Override
    public int compareTo(Misaeng o) {
        if (this.x == o.x) {
            if (this.y == o.y ) {
                return Integer.compare(this.cnt, o.cnt);
            }
            return Integer.compare(this.y, o.y);
        }
        return Integer.compare(this.x, o.x);
    }
}

public class Solution {

    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            // 한 변 길이
            int N = Integer.parseInt(st.nextToken());
            // 격리 시간
            int M = Integer.parseInt(st.nextToken());
            // 미생물 개수
            int K = Integer.parseInt(st.nextToken());

            // 미생물 리스트에 각 미생물의 초기 상태 저장
            List<Misaeng> misaeng = new ArrayList<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                Misaeng m = new Misaeng(a, b, c, d);
                misaeng.add(m);
            }

            // 시간만큼 반복
            for (int i = 0; i < M; i++) {
                // 모든 미생물 이동
                for (int j = 0; j < misaeng.size(); j++) {
                    misaeng.get(j).x += dx[misaeng.get(j).way];
                    misaeng.get(j).y += dy[misaeng.get(j).way];
                }

                Collections.sort(misaeng);
                List<Misaeng> misaeng2 = new ArrayList<>();
                
                for (int j = 0; j < misaeng.size(); j++) {

                    // 약품 셀에 도착
                    if (misaeng.get(j).x == 0 || misaeng.get(j).x == N - 1 || misaeng.get(j).y == 0 || misaeng.get(j).y == N - 1) {
                    	
                    	// 개체 수 절반으로 줄임
                        misaeng.get(j).cnt /= 2;
                        // 방향 반대로 바꿈
                        if (misaeng.get(j).way == 1) {
                            misaeng.get(j).way = 2;
                        } else if (misaeng.get(j).way == 2) {
                            misaeng.get(j).way = 1;
                        } else if (misaeng.get(j).way == 3) {
                            misaeng.get(j).way = 4;
                        } else if (misaeng.get(j).way == 4) {
                            misaeng.get(j).way = 3;
                        }
                        
                        // 개체 수가 0보다 큰 미생물만 처리
                        if (misaeng.get(j).cnt > 0) {
                            misaeng2.add(misaeng.get(j));
                        }

                    // 그 외의 공간이라면
                    } else {
                    	
                    	// 현재 미생물 위치
                        int x = misaeng.get(j).x;
                        int y = misaeng.get(j).y;
                        // 동일한 위치의 미생물 개체 수 저장
                        List<Integer> cnts = new ArrayList<>();
                        // 동일한 위치의 미생물 이동 방향 저장
                        List<Integer> ways = new ArrayList<>();

                        // 현재 미생물 위치와 동일한 좌표라면, cnts와 ways에 추가
                        while (j < misaeng.size() && misaeng.get(j).x == x && misaeng.get(j).y == y) {
                            cnts.add(misaeng.get(j).cnt);
                            ways.add(misaeng.get(j).way);
                            j++;
                        }
                        // 마지막으로 처리된 미생물 위치로 back
                        j--;

                        // 동일 위치의 미생물 cnt 합산
                        // 가장 큰 군집의 방향으로 저장
                        int sum = 0;
                        int max = 0;
                        int index = 0;
                        for (int a = 0; a < cnts.size(); a++) {
                            sum += cnts.get(a);
                            if (max < cnts.get(a)) {
                                index = a;
                                max = cnts.get(a);
                            }
                        }
                        Misaeng m = new Misaeng(x, y, sum, ways.get(index));
                        misaeng2.add(m);
                    }
                }

                misaeng = misaeng2;
            }

            int ans = 0;
            for (int i = 0; i < misaeng.size(); i++) {
                ans += misaeng.get(i).cnt;
            }

            System.out.println("#" + t + " " + ans);
        }

    }
}