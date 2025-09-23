class Solution {
    
    // 00:00:00 형태를 숫자 (초 단위)로 변환
    static int converToSec(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int min = Integer.parseInt(times[1]);
        int sec = Integer.parseInt(times[2]);
        
        return hour * 3600 + min * 60 + sec;
    }
    
    // 숫자 (초 단위)를 00:00:00 형태로 변환
    static String converToStringTime(int time) {
        
        int hour = time / 3600;
        int min = (time % 3600) / 60;
        int sec = time % 60;
        
        return String.format("%02d:%02d:%02d", hour, min, sec);
        
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        
        // 정답 = 광고 재생 시작시간
        int answerSec = 0;
        
        // 동영상 재생시간 초로
        int playSec = converToSec(play_time);
        // 광고 재생시간 초로
        int advSec = converToSec(adv_time);
        
        // 각 초별 재생중인 시청자 수
        int[] cnts = new int[playSec + 1];
        
        // 각 시청자별로 cnts 갱신
        for (String log : logs) {
            String[] logTime = log.split("-");
            
            int logStartSec = converToSec(logTime[0]);
            int logEndSec = converToSec(logTime[1]);
            
            for (int i = logStartSec; i < logEndSec; i++) {
                cnts[i]++;
            }
        }
        
        // 최대 누적 재생시간
        long maxTime = 0;
        
        // 00:00:00이 시작일 때로 초기화
        for (int i = 0; i < advSec; i++) {
            maxTime += cnts[i];
        }
        
        long tempTime = maxTime;
        
        for (int i = advSec; i < playSec; i++) {
            tempTime += cnts[i];
            tempTime -= cnts[i - advSec];
            
            if (tempTime > maxTime) {
                answerSec = i - advSec + 1;
                maxTime = tempTime;
            }
        }
        
        return converToStringTime(answerSec);
    }
}