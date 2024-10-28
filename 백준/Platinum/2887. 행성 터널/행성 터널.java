import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 간선 클래스
class Line implements Comparable<Line> {
	int end;
	int weight;
	
	public Line(int end, int weight) {
		super();
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Line o) {
		return Integer.compare(this.weight, o.weight);
	}		
}
  
public class Main {
	
	static int N;
	static PriorityQueue<Line> queue;
	static int[][] planets;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 행성 개수
		N = Integer.parseInt(br.readLine());
		
		planets = new int[N][4];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			planets[i][0] = Integer.parseInt(st.nextToken());
			planets[i][1] = Integer.parseInt(st.nextToken());
			planets[i][2] = Integer.parseInt(st.nextToken());
			planets[i][3] = i;
		}
		
		// 인접 리스트
		List<Line>[] arr = (List<Line>[]) new ArrayList[N];

		for (int i = 0; i < N; i++) {
		    arr[i] = new ArrayList<>(); 
		}
		
		// 인접 리스트 구성
		for (int d = 0; d < 3; d++) {
			int D = d;
            Arrays.sort(planets, (a, b) -> Integer.compare(a[D], b[D]));
            for (int i = 0; i < N - 1; i++) {
            	int a = Math.abs(planets[i][0] - planets[i + 1][0]);
            	int b = Math.abs(planets[i][1] - planets[i + 1][1]);
            	int c = Math.abs(planets[i][2] - planets[i + 1][2]);
            	int w = Integer.min(a, b);
            	w = Integer.min(w, c);
            	int s = planets[i][3];
            	int e = planets[i + 1][3];
            	arr[s].add(new Line(e, w));
            	arr[e].add(new Line(s, w));
            }
		}
		
		// 프림 알고리즘을 위한 우선순위 큐
		queue = new PriorityQueue<>();
		
		// 방문 여부 배열
		visited = new boolean[N];
		
		// 최종 정답
		int ans = 0;
		
		// 첫번째 행성 넣기
		visited[0] = true;
		
		for (int i = 0; i < arr[0].size(); i++) {
			queue.add(new Line(arr[0].get(i).end, arr[0].get(i).weight));
		}
		
		// 프림 알고리즘
		while (!queue.isEmpty()) {
			Line line = queue.poll();
			int e = line.end;
			int w = line.weight;
			
			if (visited[e]) {
				continue;
			}
			
			visited[e] = true;
			ans += w;
			
			for (int i = 0; i < arr[e].size(); i++) {
				if (!visited[arr[e].get(i).end]) {
					queue.add(arr[e].get(i));
				}
			}			
		}
		System.out.println(ans);
	}
}