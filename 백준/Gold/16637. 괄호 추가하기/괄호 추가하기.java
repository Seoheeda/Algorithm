import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] nums;
    static char[] calcs;
    static int max = Integer.MIN_VALUE;

    // 숫자, 연산자, 숫자 -> 주어지면 계산하는 함수
    public static int calc(int a, int b, char yeon) {
    	if (yeon == '+') {
    		return a + b;
    	} else if (yeon == '-') {
    		return a - b;
    	} else {
    		return a * b;
    	}
    }

    // 괄호를 넣을 수 있는 모든 경우의 수를 탐색하는 dfs 함수
    public static void dfs(int depth, int ans) {
    	
    	// 범위 벗어나면
    	// 최댓값 구하고 종료
        if (depth >= N / 2) {
            max = Math.max(max, ans);
            return;
        }

        // 괄호 없이 연산
        int result = calc(ans, nums[depth + 1], calcs[depth]);
        dfs(depth + 1, result);

        // 괄호 있이 연산
        if (depth + 1 < N / 2) {
            result = calc(nums[depth + 1], nums[depth + 2], calcs[depth + 1]);
            dfs(depth + 2, calc(ans, result, calcs[depth]));
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 수식 길이
        N = Integer.parseInt(br.readLine());

        // 숫자만 저장
        nums = new int[N / 2 + 1];
        // 연산자만 저장
        calcs = new char[N / 2];

        String sick = br.readLine();
    	int n = 0;
    	int c = 0;
    	
    	for (int i = 0; i < N; i++) {
    		if (sick.charAt(i) == '+' || sick.charAt(i) == '-' || sick.charAt(i) == '*' ) {
    			calcs[c] = sick.charAt(i);
    			c++;
    		} else {
    			nums[n] = sick.charAt(i) - '0';
    			n++;
    		}
    	}

        dfs(0, nums[0]);
        System.out.println(max);
    }
}