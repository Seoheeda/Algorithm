class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        // 이진 변환 횟수
        int turns = 0;
        // 제거된 0 개수
        int deletedCnt = 0;
        
        while (true) {
            
            // "1" 됐으면 그만
            if (s.equals("1")) {
                break;
            }
            
            int oneCount = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    oneCount++;
                }
            }
            
            // 제거된 0 개수 세기
            deletedCnt += (s.length() - oneCount);
            
            // 2진 변환 가하기
            s = Integer.toBinaryString(oneCount);
            
            turns++;
        }
        
        answer[0] = turns;
        answer[1] = deletedCnt;
        
        return answer;
    }
}