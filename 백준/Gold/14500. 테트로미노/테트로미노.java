import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st1 = new StringTokenizer(br.readLine());
    	
    	// 종이의 세로 크기 N, 가로 크기 M
    	int N = Integer.parseInt(st1.nextToken());
    	int M = Integer.parseInt(st1.nextToken());
    	
    	// 종이에 초기 정보 입력
    	int[][] paper = new int[N][M];
    	for (int i = 0; i < N; i++) {
    		StringTokenizer st2 = new StringTokenizer(br.readLine());
    		for (int j = 0; j < M; j++) {
    			paper[i][j] = Integer.parseInt(st2.nextToken());
    		}
    	}
    	
    	List<int[][]> shapes = new ArrayList<int[][]>();

        // mino1 
        shapes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}});
        shapes.add(new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}});
        
        // mino2
        shapes.add(new int[][]{{0, 0}, {1, 0}, {0, 1}, {1, 1}});
        
        // mino3
        shapes.add(new int[][]{{0, 0}, {1, 0}, {2, 0}, {2, 1}});
        shapes.add(new int[][]{{0, 1}, {1, 1}, {2, 1}, {2, 0}});
        shapes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}});
        shapes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 2}});
        shapes.add(new int[][]{{0, 0}, {0, 1}, {1, 0}, {2, 0}});
        shapes.add(new int[][]{{0, 0}, {0, 1}, {1, 1}, {2, 1}});
        shapes.add(new int[][]{{0, 0}, {1, 0}, {1, 1}, {1, 2}});
        shapes.add(new int[][]{{0, 2}, {1, 0}, {1, 1}, {1, 2}});

        // mino4
        shapes.add(new int[][]{{0, 0}, {0, 1}, {1, 1}, {1, 2}});
        shapes.add(new int[][]{{1, 0}, {0, 1}, {1, 1}, {0, 2}});
        shapes.add(new int[][]{{0, 0}, {1, 0}, {1, 1}, {2, 1}});
        shapes.add(new int[][]{{1, 0}, {2, 0}, {0, 1}, {1, 1}});
        
        // mino5 
        shapes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 1}});
        shapes.add(new int[][]{{0, 1}, {1, 1}, {2, 1}, {1, 0}});
        shapes.add(new int[][]{{1, 0}, {1, 1}, {1, 2}, {0, 1}});
        shapes.add(new int[][]{{0, 0}, {1, 0}, {2, 0}, {1, 1}});
    	
        int max = 0;
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) { // 여기도 N이 아니라 M
                for (int[][] shape : shapes) {
                	boolean ok = true;
                    int temp = 0;
                    for (int i = 0; i < 4; i++) {
                        int nr = r + shape[i][0];
                        int nc = c + shape[i][1];
                        if (nr >= N || nr < 0 || nc >= M || nc < 0) {
                            ok = false;
                        } else {
                            temp += paper[nr][nc];
                        }
                    }
                    if (ok && max < temp) {
                        max = temp;
                    }
                }
            }
        }
        
        System.out.println(max);
    }
}
