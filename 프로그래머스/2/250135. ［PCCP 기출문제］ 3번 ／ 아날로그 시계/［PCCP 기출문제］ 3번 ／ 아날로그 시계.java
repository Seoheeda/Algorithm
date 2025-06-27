class Solution {
    
    // 초로 변환
    static int toSecond(int h, int m, int s){
        
        int time = h * 3600 + m * 60 + s;
        
        return time;
    }
    
    // 시작 시간에 알람이 울리나
    static boolean alramAtStart(int time){
        
        if (time * 59 % 3600 == 0 || time * 719 % 42300 == 0) {
            return true;
        }
        return false;
    }
    
    // 해당 시간까지 겹치는 횟수 계산하기
     static int cal(int time){
         
         // 분침이 한 바퀴 돌 때 3600초가 걸리고, 초침과 59번 겹침
         // 59/3600 초마다 울리는 것
         int secAndMin = time*59/3600; 

         // 시침이 한 바퀴 돌 때 43200초가 걸리고, 초침과 1분에 한번 겹치며, 초침과 719번 겹침
         // 719/42300 초마다 울리는 것
         int secAndHour = time*719/43200; 
         
         int same = 0;
         
         // 분침이랑 시침이 겹쳐 있으면 1번으로 쳐야함 (12:00:00시)
         if (time >= 43200) {
             same = 2;
         } else {
             same = 1;
         }
                                
        return secAndMin + secAndHour - same;
     }
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        
        // 시간, 분을 초로 변환
        int start = toSecond(h1, m1, s1);
        int end = toSecond(h2, m2, s2);
        
        // 끝나는 시간까지 몇번 울리나
        int toEnd = cal(end);
        // 시작 시간까지 몇번 울리나
        int toStart = cal(start);
        // 시작 시간에 울리나
        boolean isAlarmAtStart = alramAtStart(start);
        
        if (isAlarmAtStart) {
            answer = toEnd - toStart + 1;
        } else {
            answer = toEnd - toStart;
        }        															
        
        return answer;
    }
}