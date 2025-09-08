import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        
        // 근무태도, 동료평가
        int[] target = scores[0];
        // 점수 합산
        int targetSum = target[0] + target[1];
        
        // 정렬 : 근무태도 내림차순, 동료평가 오름차순
        Arrays.sort(scores, (a, b) -> {
                   if (a[0] != b[0]) {
                       return b[0] - a[0];
                   }
                   return a[1] - b[1];
        });
        
        // 완호 탈락 여부
        for (int i = 0; i < scores.length; i++) {
            int[] score = scores[i];
            if (score[0] > target[0] && score[1] > target[1]) {
                return -1;
            }
        }
        
        // 기본 1등
        int ranking = 1;
        // 지금까지 중 동료평가 최대점수
        int maxPeerScore = Integer.MIN_VALUE;
        
        for (int i = 0; i < scores.length; i++) {
            int[] score = scores[i];
            
            int attitude = score[0];
            int peer = score[1];
            
            // 앞사람의 동료평가 최대값보다 작으면 스킵
            if (peer < maxPeerScore) {
                continue;
            }
            
            // 합이 완호보다 크면 완호 순위 낮아짐
            if (attitude + peer > targetSum) {
                ranking++;
            }
            
            // 최대 동료평가 갱신
            if (peer > maxPeerScore) {
                maxPeerScore = peer;
            }
        }
        
        return ranking;
    }
}