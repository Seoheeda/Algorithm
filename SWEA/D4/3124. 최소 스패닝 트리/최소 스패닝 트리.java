import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 간선 클래스
class Edge implements Comparable<Edge> {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}

public class Solution {

    static List<Edge>[] graph;
    static boolean[] visited;

    public static long prim(int start, int V) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        long sum = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int to = current.to;
            int weight = current.weight;

            if (visited[to]) continue;

            visited[to] = true;
            sum += weight;
            count++;

            if (count == V) {
                break;
            };

            for (Edge edge : graph[to]) {
                if (!visited[edge.to]) {
                    pq.add(edge);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            // 정점 개수
            int V = Integer.parseInt(st.nextToken());
            // 간선 개수
            int E = Integer.parseInt(st.nextToken());

            // 그래프 초기화
            graph = new ArrayList[V + 1];
            visited = new boolean[V + 1];

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            // 간선 정보 입력
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                graph[a].add(new Edge(b, c));
                graph[b].add(new Edge(a, c));
            }

            // 프림 알고리즘 실행 (임의의 시작 정점에서 시작)
            long sum = prim(1, V);

            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }

        bw.append(sb);
        bw.flush();
        bw.close();
    }
}