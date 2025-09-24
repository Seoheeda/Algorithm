import java.util.*;

class Solution {
    
    static int N;
    static List<Integer>[] info;
    static int[] answer;
    static boolean[] checked;
    
    // 해당 노드에서 홀짝 트리인지 역홀짝 트리인지 보기
    static void check(int node) {
        
        boolean[] visited = new boolean[1000001];
        visited[node] = true;
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(node);
        
        // 해당 트리의 노드들
        List<Integer> nodes = new ArrayList<>();
        nodes.add(node);
        
        checked[node] = true;
        
        // 해당 트리의 노드들 구하기
        while (!queue.isEmpty()) {
            int no = queue.poll();
                                    
            for (int i = 0; i < info[no].size(); i++) {
                int next = info[no].get(i);
                                
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    checked[next] = true;
                    nodes.add(next);
                }
            }    
        } 
                
        // 해당 트리가 홀짝인지 역홀짝인지 판단
        int oddEvenNodeCnt = 0;
        
        for (int i = 0; i < nodes.size(); i++) {
            int no = nodes.get(i);
            
            if ((no % 2 == 0 && info[no].size() % 2 == 0) || (no % 2 == 1 && info[no].size() % 2 == 1)) {
                oddEvenNodeCnt++;
            }
        }
        
        int notOddEvenNodeCnt = nodes.size() - oddEvenNodeCnt;
        
        if (oddEvenNodeCnt == 1) {
            answer[0]++;
        }
        
        if (notOddEvenNodeCnt == 1) {
            answer[1]++;
        }
    }
    
    public int[] solution(int[] nodes, int[][] edges) {
        
        
        N = nodes.length;
        
        // 인접 리스트
        info = new ArrayList[1000001];
        
        for (int i = 0; i < 1000001; i++) {
            info[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            info[a].add(b);
            info[b].add(a);
        }
        
        answer = new int[2];
        checked = new boolean[1000001];
                
        for (int node: nodes) {
            if (!checked[node])
            check(node);
        }
        
        return answer;
    }
}