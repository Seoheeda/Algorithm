class Solution {
    public int[] solution(int brown, int yellow) {
        
        int[] answer = new int[2];
        
        for (int i = 1; i <= yellow; i++) {
            if (yellow % i == 0) {
                int width = i + 2;
                int height = (yellow / i) + 2;

                
                if (2 * width + (height - 2) * 2 == brown && width >= height) {
                    answer[0] = width;
                    answer[1] = height;
                }
            }
        }
        
        return answer;
    }
}

// 2w + (h - 2) * 2 = brown
// (w - 2) * (h - 2) = yellow