import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {

            // 원본 암호문
            int N = Integer.parseInt(br.readLine());

            List<Integer> original = new LinkedList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                original.add(Integer.parseInt(st.nextToken()));
            }

            // 명령어
            int M = Integer.parseInt(br.readLine());

            String command;

            st = new StringTokenizer(br.readLine());
           
            for (int m = 0; m < M; m++) {
                command = st.nextToken();
                if (command.equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) {
                        original.add(x + i, Integer.parseInt(st.nextToken()));
                    }
                } else if (command.equals("A")) {
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) {
                        original.add(Integer.parseInt(st.nextToken()));
                    }
                } else if (command.equals("D")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) {
                        original.remove(x + i);
                    }
                }
            }

            // 결과 출력
            sb.append('#').append(t).append(' ');
            for (int i = 0; i < 10; i++) {
                sb.append(original.get(i)).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}