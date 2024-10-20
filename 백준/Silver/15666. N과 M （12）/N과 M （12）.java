import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] nums, arr;
    static StringBuilder sb;
    static TreeSet<List<Integer>> set; 

    static void dfs(int depth, int start) {

        if (depth == M) {
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                tempList.add(arr[i]);
            }
            set.add(tempList);
            return;
        }

        for (int i = start; i < N; i++) {
            arr[depth] = nums[i]; 
            dfs(depth + 1, i);
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums); 

        arr = new int[M];
        
        // TreeSet 사용으로 사전순 및 중복 제거
        set = new TreeSet<>(new Comparator<List<Integer>>() {  
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                for (int i = 0; i < M; i++) {
                    if (!o1.get(i).equals(o2.get(i))) {
                        return o1.get(i) - o2.get(i); 
                    }
                }
                return 0;
            }
        });

        dfs(0, 0);

        for (List<Integer> result : set) {
            StringBuilder sb = new StringBuilder();
            for (int num : result) {
                sb.append(num).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}