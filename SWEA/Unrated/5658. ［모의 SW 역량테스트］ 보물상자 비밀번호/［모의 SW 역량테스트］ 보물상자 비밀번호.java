import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;
  
public class Solution {
	
	// 16진수 변환
	static int change(char a) {
		
		int b = 0;
		
		switch(a) {	
		case 'A' : b = 10;
			break;
		case 'B' : b = 11;
			break;
		case 'C' : b = 12;
			break;
		case 'D' : b = 13;
			break;
		case 'E' : b = 14;
			break;
		case 'F' : b = 15;
			break;
		}
		
		return b;
	}
      
    public static void main(String[] args) throws Exception {
   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          
        int T = Integer.parseInt(br.readLine());
          
        for (int t = 1; t <= T; t++) {
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	// 숫자 개수
        	int N = Integer.parseInt(st.nextToken());
        	// 크기 순서
        	int K = Integer.parseInt(st.nextToken());
        	
        	// 16진수 숫자들
        	Queue<Character> nums = new ArrayDeque<>();
        	String num = br.readLine();
        	
        	for (int i = 0; i < N; i++) {
        		nums.add(num.charAt(i));
        	}
        	
        	// 턴 횟수
        	int turn = N / 4;
        	
        	// 중복 없이 숫자들
        	HashSet<String> set = new HashSet<>();
        	
        	// 숫자 돌리면서 set에 넣기
        	for (int i = 0; i < turn; i++) {
        		for (int a = 0; a < 4; a++) {
        			String n = "";
        			for (int b = 0; b < turn; b++) {
        				char temp = nums.poll();
        				n += temp;
        				nums.add(temp);
        			}
        			set.add(n);
        		}
        		nums.add(nums.poll());
        	}
        	
        	// 정렬을 위해 arr로 이동
        	String[] numList = new String[set.size()];
        	int index = 0;
        	for (String s : set) {
        		numList[index] = s;
        		index++;
        	}
        	       	
        	// 오름차순 정렬
        	Arrays.sort(numList);
        	
        	// K번째 수
        	String target = numList[numList.length - K];
        	
        	// 16진수 변환
        	int ans = 0;
        	for (int i = 0; i < turn; i++) {
        		char temp = target.charAt(i);
        		if (temp - '0' <= 9) {
        			ans += (Math.pow(16, turn - i - 1) * (temp - '0'));
        		} else {
        			ans += (Math.pow(16, turn - i - 1) * (change(temp)));
        		}
        	}
        	
        	System.out.println("#" + t + " " + ans);
        	
        	
        }
    }
}