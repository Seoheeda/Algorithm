import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
	// dist : 해당 좌표까지 거리
    int x, y, dist;

    public Point(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    @Override
    public int compareTo(Point o) {
        if (this.dist == o.dist) {
            if (this.x == o.x) {
            	// 3) y좌표 긱준으로 오름차순
                return Integer.compare(this.y, o.y);
            }
            
            // 2) x좌표 기준으로 오름차순
            return Integer.compare(this.x, o.x);
        }
        // 1) 거리 기준으로 오름차순
        return Integer.compare(this.dist, o.dist);
    }
}

public class Main {

    static int[][] arr;
    static int N;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int r, c, size, eaten;
    static int time;

    public static Point bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        // 방문 좌표 기록
        boolean[][] visited = new boolean[N][N];
        // 이동 가능한 경로 추가
        queue.add(new Point(x, y, 0));
        visited[x][y] = true;

        // 먹을 수 있는 물고기 담는 리스트
        List<Point> fishes = new ArrayList<>();

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // 범위 내에 있고, 방문 전이라면 방문하기
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    visited[nx][ny] = true;

                    // 아기상어 크기 이하라면 이동 가능
                    if (arr[nx][ny] <= size) {
                        queue.add(new Point(nx, ny, p.dist + 1));
                        // 먹을 수 있다면 리스트에 추가
                        if (arr[nx][ny] > 0 && arr[nx][ny] < size) {
                            fishes.add(new Point(nx, ny, p.dist + 1));
                        }
                    }
                }
            }
        }

        // 리스트가 비어있다면 --> 먹을 수 있는 물고기 없음
        if (fishes.isEmpty()) {
            return null;
        }

        // 리스트에 물고기가 있다면 --> 정렬 후 가장 우선순위 높은 물고기 반환
        Collections.sort(fishes);
        return fishes.get(0);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 공간 크기
        N = Integer.parseInt(br.readLine());
        // 공간
        arr = new int[N][N];
        // 아기상어 크기
        size = 2;
        // 현재 크기에서 먹은 물고기 수
        eaten = 0;
        // 물고기 먹는데 걸린 시간
        time = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                	// 아기상어 위치
                    r = i;
                    c = j;
                    arr[i][j] = 0; // 아기 상어 위치를 0으로 초기화
                }
            }
        }

        while (true) {
            Point target = bfs(r, c);
            
            // 더 이상 먹을 수 있는 물고기가 없으면 종료
            if (target == null) {
                break;
            }

            // 물고기를 먹었다면
            // 시간 누적
            time += target.dist;
            // 아기상어 위치 업데이트
            r = target.x;
            c = target.y;
            arr[r][c] = 0;
            // 먹은 물고기 수 증가
            eaten++;

            // 먹은 물고기 수가 아기상어 사이즈와 같아지면 업데이트
            if (eaten == size) {
                size++;
                eaten = 0;
            }
        }

        System.out.println(time);
    }
}