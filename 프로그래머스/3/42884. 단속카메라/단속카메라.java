import java.util.*;

// 경로
class Route implements Comparable<Route> {
    int start;
    int end;
    
    public Route(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public int compareTo(Route o) {
        return Integer.compare(this.end, o.end);
    }
}

class Solution {
    public int solution(int[][] routes) {
        
        int answer = 0;
        
        // 차 대수
        int N = routes.length;
        
        // 경로 담을 우선순위 큐 (시작점이 작은 순으로 정렬)
        PriorityQueue<Route> queue = new PriorityQueue<>();
        
        for (int i = 0; i < N; i++) {
            queue.add(new Route(routes[i][0], routes[i][1]));
        }
        
        // 카메라 위치
        int camera = Integer.MIN_VALUE;
        
        
        for (int i = 0; i < N; i++) {
            Route route = queue.poll();
            
            if (camera < route.start) {
                camera = route.end;
                answer++;
            }
        }
                
        return answer;
    }
}
