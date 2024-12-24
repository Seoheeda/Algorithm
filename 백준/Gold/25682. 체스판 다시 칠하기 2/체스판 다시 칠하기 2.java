import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       
       // 보드 크기
       int N = Integer.parseInt(st.nextToken());
       int M = Integer.parseInt(st.nextToken());
       
       // 만들려는 체스판 크기
       int K = Integer.parseInt(st.nextToken());
       
       // 검은색으로 시작하는 경우
       int[][] startB = new int[N + 1][M + 1];
       // 흰색으로 시작하는 경우
       int[][] startW = new int[N + 1][M + 1];
       
       // 보드 정보
       char[][] board = new char[N + 1][M + 1];
       for (int n = 1; n < N + 1; n++) {
    	   String line = br.readLine();
    	   for (int m = 1; m < M + 1; m++) {
    		   board[n][m] = line.charAt(m - 1);
    		   if (n % 2 == 0 && m % 2 == 0) {
    			  if (board[n][m] == 'B') {
    				startW[n][m] = 1;  
    			  } else {
    				  startB[n][m] = 1;
    			  }
    		   } else if (n % 2 == 0 && m % 2 == 1) {
    			  if (board[n][m] == 'B') {
       				  startB[n][m] = 1;  
       			  } else {
       				  startW[n][m] = 1;
       			  }
    		   } else if (n % 2 == 1 && m % 2 == 0) {
    			  if (board[n][m] == 'B') {
    				  startB[n][m] = 1;  
       			  } else {
       				  startW[n][m] = 1;
       			  }
    		   } else {
    			  if (board[n][m] == 'B') {
          		      startW[n][m] = 1;  
          	      } else {
          	 		  startB[n][m] = 1;
          		  } 
    		   }
    	   }
       }
       
       // 부분합 배열 만들기
       for (int n = 1; n < N + 1; n++) { 
    	   for (int m = 1; m < M + 1; m++) {
    		   startB[n][m] += startB[n][m - 1];
    		   startW[n][m] += startW[n][m - 1];
    	   }
       }
       
	   for (int m = 1; m < M + 1; m++) {
           for (int n = 1; n < N + 1; n++) { 
    		   startB[n][m] += startB[n - 1][m];
    		   startW[n][m] += startW[n - 1][m];
    	   }
       }
	   
	   // 정답 : 최솟값
	   int ans = Integer.MAX_VALUE;
	   
	   // 부분합으로 최솟값 찾기
	   for (int n = 0; n < N - K + 1; n++) {
		   for (int m = 0; m < M - K + 1; m++) {
			   int caseB = startB[n + K][m + K] - startB[n][m + K] - startB[n + K][m] + startB[n][m];
			   int caseW = startW[n + K][m + K] - startW[n][m + K] - startW[n + K][m] + startW[n][m];
			   ans = Integer.min(ans, caseB);
			   ans = Integer.min(ans, caseW);
		   }
	   }
       
       System.out.println(ans); 
    }
}