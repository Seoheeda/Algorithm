import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int robotCnt = routes.length;

        // 각 로봇의 현재 위치와 이동 경로 저장
        int[][] curPos = new int[robotCnt][2];
        // 각 로봇의 현재 목표 인덱스
        int[] pathIdx = new int[robotCnt]; 
        // 도착 여부
        boolean[] isFinished = new boolean[robotCnt];
        // 도착한 개수
        int finishedCnt = 0;

        // 초기 위치 설정
        for (int i = 0; i < robotCnt; i++) {
            int startIdx = routes[i][0] - 1;
            curPos[i][0] = points[startIdx][0];
            curPos[i][1] = points[startIdx][1];
            pathIdx[i] = 1; 
        }

        // 0초 충돌 체크
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < robotCnt; i++) {
            String key = curPos[i][0] + " " + curPos[i][1];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        for (int cnt : map.values()) {
            if (cnt > 1) {
                answer++;
            }
        }

        while (finishedCnt < robotCnt) {
            map = new HashMap<>();

            for (int i = 0; i < robotCnt; i++) {
                if (isFinished[i]) {
                    continue;
                }

                int[] cur = curPos[i];
                int curIdx = routes[i][pathIdx[i] - 1] - 1;
                int nextIdx = routes[i][pathIdx[i]] - 1;
                int[] target = points[nextIdx];

                // 이동
                if (cur[0] != target[0]) {
                    if (cur[0] < target[0]) {
                        cur[0]++;
                    } else {
                        cur[0]--;
                    }
                } else if (cur[1] != target[1]) {
                    if (cur[1] < target[1]) {
                        cur[1]++;
                    } else {
                        cur[1]--;
                    }
                }

                // 위치 갱신
                curPos[i] = cur;

                // 도착했으면 다음 경유지로
                if (cur[0] == target[0] && cur[1] == target[1]) {
                    pathIdx[i]++;
                    if (pathIdx[i] == routes[i].length) {
                        isFinished[i] = true;
                        finishedCnt++;
                    }
                }

                String key = cur[0] + " " + cur[1];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            for (int cnt : map.values()) {
                if (cnt > 1) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
