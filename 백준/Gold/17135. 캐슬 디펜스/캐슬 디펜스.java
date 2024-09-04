import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 적 클래스
class Enemy implements Comparable<Enemy>{
	int x;
	int y;
	int dist;
	
	public Enemy(int x, int y, int dist) {
		super();
		this.x = x;
		this.y = y;
		this.dist = dist;
	}

	@Override
	public int compareTo(Enemy o) {
		if (this.dist == o.dist) {
			if (this.y == o.y) {
				return Integer.compare(this.x, o.x);
			}
			return Integer.compare(this.y, o.y);
		}
		return Integer.compare(this.dist, o.dist);
	}	
}


public class Main {
	
	static int[][] area;
	static int N, M, D, enemyCnt;
	static int[] arr;
	static boolean[] visited;
	static int max;
	
	static void dfs(int depth, int start) {
		
		// 궁수 배치한 후, 공격 메소드 실행
		if (depth == 3) {
			int temp = attack(arr);
			max = Integer.max(max, temp);
			
			return;
		}
		
		for (int i = start; i < M; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				arr[depth] = i;
				dfs(depth + 1, i + 1);
				visited[i] = false;
			}
		}
	}
	
	
	// 공격 메소드
	static int attack(int[] goong) {
		
		// 배열 복사
		int[][] tempArea = new int[N + 1][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tempArea[i][j] = area[i][j];
			}
		}
		
		// 남은 적 개수 초기화
		int enemyCnt2 = enemyCnt;
		
		// 공격으로 죽인 적 수 초기화
		int die = 0;
		
		// 남은 적이 없을 때까지
		while (enemyCnt2 > 0) {
			
			// 공격당할 예정인 적
			List<Enemy> will = new ArrayList<Enemy>();
						
			for (int i = 0; i < 3; i++) {
				// 궁수 위치
				int x = N;
				int y = goong[i];
				
				// 적 담을 PQ
				PriorityQueue<Enemy> queue = new PriorityQueue<Enemy>();
				
				// 적들 정렬
				for (int a = 0; a < N; a++) {
					for (int b = 0; b < M; b++) {
						if (tempArea[a][b] == 1) {
							if (Math.abs(x - a) + Math.abs(y - b) <= D) {
								queue.add(new Enemy(a, b, Math.abs(x - a) + Math.abs(y - b)));
							}
						}
					}
				}
				
				// 공격할 적만 담기
				if (queue.size() > 0) {
					Enemy e = queue.peek();
					will.add(new Enemy(e.x, e.y, e.dist));
				}
				
			}
			
			// 공격하기
			for (Enemy e : will) {
				if (tempArea[e.x][e.y] == 1) {
					tempArea[e.x][e.y] = 0;
					// 남은 적 감소
					enemyCnt2--;
					// 죽인 적 증가
					die++;
				}	
			}
			
			// 한칸 아래로 이동하기
			for (int a = N - 1; a >= 0; a--) {
				for (int b = 0; b < M; b++) {
					if (tempArea[a][b] == 1) {
						if (a == N - 1) {
							tempArea[a][b] = 0;
							enemyCnt2--;
						} else {
							tempArea[a + 1][b] = 1;
							tempArea[a][b] = 0;
						}
					}
				}
			}
		}
		
		return die;
	}

    public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st =  new StringTokenizer(br.readLine());
        // 격자판 크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 궁수의 공격 거리 제한
        D = Integer.parseInt(st.nextToken());
         
        area = new int[N + 1][M];
        // 적의 수
        enemyCnt = 0;
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < M; j++) {
        		area[i][j] = Integer.parseInt(st.nextToken());
        		if (area[i][j] == 1) {
        			enemyCnt++;
        		}
        	}
        }
        
        // 조합 형성 위함
        visited = new boolean[M];
        arr = new int[3];
        
        // 정답
        max = 0;
        
        dfs(0, 0);
        
        System.out.println(max);
    }
}