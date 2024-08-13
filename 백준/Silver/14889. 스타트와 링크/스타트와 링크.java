import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] force;
    static int[] arr;
    static int min = 100000000;
    static boolean[] visited;

    private static void dfs(int depth, int first) {
        if (depth == N / 2) {
            int[] starts = new int[N / 2];
            int[] links = new int[N / 2];

            int s = 0;
            int l = 0;
            int a = 0;
            
            int[] tempArr = Arrays.copyOf(arr, N/2);
            Arrays.sort(tempArr);
            
            for (int i = 0; i < N; i++) {
                if (a < N / 2 && i == tempArr[a]) {
                    starts[s] = i;
                    a++;
                    s++;
                } 
                
                else {
                    links[l] = i;
                    l++;
                }
            }

            int start = 0;
            int link = 0;

            for (int i = 0; i < N / 2; i++) {
                for (int j = i + 1; j < N / 2; j++) {
                    start += force[starts[i]][starts[j]];
                    start += force[starts[j]][starts[i]];
                    link += force[links[i]][links[j]];
                    link += force[links[j]][links[i]];

                }
            }
            

            if (Math.abs(start - link) < min) {
                min = Math.abs(start - link);
            }

            return;
        }

        for (int i = first; i < N; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                arr[depth] = i;
                dfs(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사람 수 N
        N = Integer.parseInt(br.readLine());

        // 능력치 저장하는 배열
        force = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                force[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        arr = new int[N / 2];
        visited = new boolean[N];

        dfs(0, 0);
        
        System.out.println(min);

    }

}
