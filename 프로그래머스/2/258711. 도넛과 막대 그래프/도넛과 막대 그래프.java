import java.util.*;

class Solution {
    
    static int[] answer;
    
    static void dfs(int node, List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        
        while (true) {
            // 끝나면 막대
            if (graph.get(node).size() == 0) {
                answer[2]++;
                return;
            }
            
            // 2개 이상으로 갈라지면 8자
            if (graph.get(node).size() >= 2) {
                answer[3]++;
                return;
            }
            
            // 다시 돌아오면 도넛
            if (visited[node]) {
                answer[1]++;
                return;
            }
            
            visited[node] = true;
            node = graph.get(node).get(0);
        }
    }
    
    public int[] solution(int[][] edges) {
        answer = new int[4];
        
        // 가장 숫자 큰 노드
        int maxNode = 0;
        for (int i = 0; i < edges.length; i++) {
            maxNode = Integer.max(maxNode, edges[i][0]);
            maxNode = Integer.max(maxNode, edges[i][1]);
        }
        
        // 들어오는 간선 수
        int[] inNode = new int[maxNode + 1];
        // 나가는 간선 수
        int[] outNode = new int[maxNode + 1];

        // 그래프의 인접 리스트
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= maxNode; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            int out = edge[0];
            int in = edge[1];
            outNode[out]++;
            inNode[in]++;
            graph.get(out).add(in);
        }
        
        // 생성된 노드
        int start = 0;
        for (int i = 0; i <= maxNode; i++) {
            if (inNode[i] == 0 && outNode[i] >= 2) {
                start = i;
                break;
            }
        }
        
        answer[0] = start;
        
        for (int node : graph.get(start)) {
            dfs(node, graph);
        }
        
        return answer;
    }
}