class Solution {
    public long solution(int[] weights) {

        long answer = 0;

        // 무게 개수
        int[] weightCnts = new int[1001];  

        for (int weight : weights) {
            weightCnts[weight]++;
        }

        for (int weight = 100; weight <= 1000; weight++) {
            int weightCnt = weightCnts[weight];

            if (weightCnt > 0) {
                // 같은 무게 짝꿍
                if (weightCnt >= 2) {
                    answer += (long) weightCnt * (weightCnt - 1) / 2;
                }

                // 비율 조합: 2:3, 1:2, 3:4
                int[][] ratios = {
                    {2, 3}, {1, 2}, {3, 4}
                };

                for (int[] ratio : ratios) {
                    int a = ratio[0];
                    int b = ratio[1];
                    int pair = weight * b / a;

                    if ((weight * b) % a == 0 && pair <= 1000 && weightCnts[pair] > 0 && weight < pair)  {
                        answer += (long) weightCnt * weightCnts[pair];
                    }
                }
            }
            
        }

        return answer;
    }
}
