import java.util.*;

class Solution {
    
    static List<Integer>[] edges;
    
    static int calc(int n) {
        
        // 최단경로 거리 담을 배열
        int[] dists = new int[n + 1];
        
        // 무조건 1부터 시작이니까 0이랑 1은 초기화하기
        dists[0] = 1;
        dists[1] = 1;

        // bfs 돌릴 큐와 방문 배열
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        
        queue.add(1);
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int from = queue.poll();
            
            // 인접 리스트 돌면서
            for (int i = 0; i < edges[from].size(); i++) {
                int to = edges[from].get(i);
                
                // 아직 방문 전이면
                if (!visited[to]) {

                    // from이 유효할 경우에만
                    if (dists[from] > 0) {
                        
                        // to 갱신
                        dists[to] = dists[from] + 1;
                        
                        queue.add(to); 
                        visited[to] = true;
                    }
                }
            }
        }
        
        // 가장 먼 노드가 몇개인지 구하기
        int maxDist = Integer.MIN_VALUE;
        int cnt = 0;
        
        for (int i = 0; i < n + 1; i++) {
            if (dists[i] > maxDist) {
                maxDist = dists[i];
                cnt = 1;
            } else if (dists[i] == maxDist) {
                cnt++;
            }
        }

        return cnt;
    }
    
    public int solution(int n, int[][] edge) {
     
        // 인접 리스트
        edges = new ArrayList[n + 1];
        
        for (int i = 0; i < n + 1; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        
        // 무방향이니까 양쪽 다 넣어주기
        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            
            edges[a].add(b);
            edges[b].add(a);
        }
                
        int answer = calc(n);
                
        return answer;
    }
}