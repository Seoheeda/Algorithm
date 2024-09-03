import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution {
	
	static int N, M, L;
	static int cnt;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> queue;
	static int x, y;
	
	public static void bfs(int depth) {
			
		int size = queue.size();
		
		// 큐가 비었거나, L초 이상 지났다면 return
		if (size == 0 || depth > L) {
			return;
		}
		
		for (int i = 0; i < size; i++) {
			
			// 큐에서 하나 꺼내기
			int[] q = queue.poll();
			x = q[0];
			y = q[1];
			
			// 방문한 칸 증가
			cnt++;
			
			// 1번 구조물의 경우
			if (map[x][y] == 1) {
				// 위로 이동
				if (x - 1 >= 0 && visited[x - 1][y] == false) {
					if (map[x - 1][y] == 1 || map[x - 1][y] == 2 || map[x - 1][y] == 5 || map[x - 1][y] == 6) {
					queue.add(new int[] {x - 1, y});
					visited[x - 1][y] = true;
					}
				}
				// 아래로 이동
				if (x + 1 < N && visited[x + 1][y] == false) {
					if (map[x + 1][y] == 1 || map[x + 1][y] == 2 || map[x + 1][y] == 4 || map[x + 1][y] == 7) {
						queue.add(new int[] {x + 1, y});
						visited[x + 1][y] = true;
					}
				}
				// 왼쪽으로 이동
				if (y - 1 >= 0 && visited[x][y - 1] == false) {
					if (map[x][y - 1] == 1 || map[x][y - 1] == 3 || map[x][y - 1] == 4 || map[x][y - 1] == 5) {
						queue.add(new int[] {x, y - 1});
						visited[x][y - 1] = true;
					}
				}
				// 오른쪽으로 이동
				if (y + 1 < M && visited[x][y + 1] == false) {
					if (map[x][y + 1] == 1 || map[x][y + 1] == 3 || map[x][y + 1] == 6 || map[x][y + 1] == 7) {
						queue.add(new int[] {x, y + 1});	
						visited[x][y + 1] = true;
					}
				}
			// 2번 구조물의 경우
			} else if (map[x][y] == 2) {
				// 위로 이동
				if (x - 1 >= 0 && visited[x - 1][y] == false) {
					if (map[x - 1][y] == 1 || map[x - 1][y] == 2 || map[x - 1][y] == 5 || map[x - 1][y] == 6) {
						queue.add(new int[] {x - 1, y});
						visited[x - 1][y] = true;
					}	
				}
				// 아래로 이동
				if (x + 1 < N && visited[x + 1][y] == false) {
					if (map[x + 1][y] == 1 || map[x + 1][y] == 2 || map[x + 1][y] == 4 || map[x + 1][y] == 7) {
						queue.add(new int[] {x + 1, y});
						visited[x + 1][y] = true;
					}
				}
			// 3번 구조물의 경우
			} else if (map[x][y] == 3) {
				// 왼쪽으로 이동
				if (y - 1 >= 0 && visited[x][y - 1] == false) {
					if (map[x][y - 1] == 1 || map[x][y - 1] == 3 || map[x][y - 1] == 4 || map[x][y - 1] == 5) {
						queue.add(new int[] {x, y - 1});
						visited[x][y - 1] = true;
					}
				}
				// 오른쪽으로 이동
				if (y + 1 < M && visited[x][y + 1] == false) {
					if (map[x][y + 1] == 1 || map[x][y + 1] == 3 || map[x][y + 1] == 6 || map[x][y + 1] == 7) {
						queue.add(new int[] {x, y + 1});
						visited[x][y + 1] = true;
					}
				}
			// 4번 구조물의 경우
			} else if (map[x][y] == 4) {
				// 위로 이동
				if (x - 1 >= 0 && visited[x - 1][y] == false) {
					if (map[x - 1][y] == 1 || map[x - 1][y] == 2 || map[x - 1][y] == 5 || map[x - 1][y] == 6) {
						queue.add(new int[] {x - 1, y});
						visited[x - 1][y] = true;
					}
				}
				// 오른쪽으로 이동
				if (y + 1 < M && visited[x][y + 1] == false) {
					if (map[x][y + 1] == 1 || map[x][y + 1] == 3 || map[x][y + 1] == 6 || map[x][y + 1] == 7) {
						queue.add(new int[] {x, y + 1});	
						visited[x][y + 1] = true;
					}
				}
			// 5번 구조물의 경우
			} else if (map[x][y] == 5) {
				// 아래로 이동
				if (x + 1 < N && visited[x + 1][y] == false) {
					if (map[x + 1][y] == 1 || map[x + 1][y] == 2 || map[x + 1][y] == 4 || map[x + 1][y] == 7) {
						queue.add(new int[] {x + 1, y});
						visited[x + 1][y] = true;
					}
				}
				// 오른쪽으로 이동
				if (y + 1 < M && visited[x][y + 1] == false) {
					if (map[x][y + 1] == 1 || map[x][y + 1] == 3 || map[x][y + 1] == 6 || map[x][y + 1] == 7) {
						queue.add(new int[] {x, y + 1});		
						visited[x][y + 1] = true;
					}
				}
			// 6번 구조물의 경우
			} else if (map[x][y] == 6) {
				// 아래로 이동
				if (x + 1 < N && visited[x + 1][y] == false) {
					if (map[x + 1][y] == 1 || map[x + 1][y] == 2 || map[x + 1][y] == 4 || map[x + 1][y] == 7) {
						queue.add(new int[] {x + 1, y});
						visited[x + 1][y] = true;
					}
				}
				// 왼쪽으로 이동
				if (y - 1 >= 0 && visited[x][y - 1] == false) {
					if (map[x][y - 1] == 1 || map[x][y - 1] == 3 || map[x][y - 1] == 4 || map[x][y - 1] == 5) {
						queue.add(new int[] {x, y - 1});
						visited[x][y - 1] = true;
					}
				}
			// 7번 구조물의 경우
			} else if (map[x][y] == 7) {
				// 위로 이동
				if (x - 1 >= 0 && visited[x - 1][y] == false) {
					if (map[x - 1][y] == 1 || map[x - 1][y] == 2 || map[x - 1][y] == 5 || map[x - 1][y] == 6) {
						queue.add(new int[] {x - 1, y});
						visited[x - 1][y] = true;
					}
				}
				// 왼쪽으로 이동
				if (y - 1 >= 0 && visited[x][y - 1] == false) {
					if (map[x][y - 1] == 1 || map[x][y - 1] == 3 || map[x][y - 1] == 4 || map[x][y - 1] == 5) {
						queue.add(new int[] {x, y - 1});
						visited[x][y - 1] = true;
					}
				}
			}	
		}
		
		bfs(depth + 1);
	}
   
	
    public static void main(String[] args) throws Exception {
 
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       int T = Integer.parseInt(br.readLine());
       
       for (int t = 1; t <= T; t++) {
    	   StringTokenizer st = new StringTokenizer(br.readLine());
    	   
    	   // 지하 터널 지도 크기
    	   N = Integer.parseInt(st.nextToken());
    	   M = Integer.parseInt(st.nextToken());
    	   // 맨홀 뚜껑 위치
    	   int R = Integer.parseInt(st.nextToken());
    	   int C = Integer.parseInt(st.nextToken());
    	   // 시간
    	   L = Integer.parseInt(st.nextToken());
    	   
    	   // 지도 채우기
    	   map = new int[N][M];
    	   for (int i = 0; i < N; i++) {
    		   st = new StringTokenizer(br.readLine());
    		   for (int j = 0; j < M; j++) {
    			   map[i][j] = Integer.parseInt(st.nextToken());
    		   }
    	   }
    	   
    	   // 시작점 방문처리
    	   visited = new boolean[N][M];
    	   visited[R][C] = true;
    	   // 지점 개수 초기화
    	   cnt = 0;
    	   
    	   // 큐에 시작점 넣기
    	   queue = new ArrayDeque<>();
    	   queue.add(new int[] {R, C});
   
    	   x = R;
    	   y = C;
    	   
    	   bfs(1);
    	   
    	   System.out.println("#" + t + " " + cnt);
    	   
       }
    }
}