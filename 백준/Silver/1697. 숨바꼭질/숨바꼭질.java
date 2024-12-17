import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] visited = new int[100001]; // 방문 여부 및 시간 기록 배열
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken()); // 수빈이 위치
        k = Integer.parseInt(st.nextToken()); // 동생 위치
        
        System.out.println(bfs(n));
    }
    
    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start); // 시작 위치 큐에 삽입
        visited[start] = 1; // 시작 위치 방문 처리 (1부터 시작)
        
        while (!q.isEmpty()) {
            int current = q.poll();
            
            if (current == k) {
                return visited[current] - 1; // 시작 위치를 1로 설정했으므로 결과에 -1 필요
            }
            
            // 이동할 수 있는 3가지 경우
            int[] nextPositions = {current - 1, current + 1, current * 2};
            
            for (int next : nextPositions) {
                if (next >= 0 && next <= 100000 && visited[next] == 0) {
                    visited[next] = visited[current] + 1; // 시간 증가
                    q.add(next);
                }
            }
        }
        return -1; // 도달할 수 없는 경우 (문제 조건상 발생하지 않음)
    }
}