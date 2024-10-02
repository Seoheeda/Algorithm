import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static void find(String key) {
		
		// 가능한 키 중 가장 짧은 키 출력
    	for (int i = 1; i <= key.length(); i++) {
    		if (key.length() % i == 0) {
    			String tempKey = key.substring(0, i);
    			boolean flag = true;
    			
    			for (int j = 0; j < key.length(); j++) {
    				if (key.charAt(j) != tempKey.charAt(j % i)) {
    					flag = false;
    					break;
    				}
    			}
    			if (flag) {
        			System.out.println(tempKey);
        			return;
    			}
    		}
    	}
	}
	
    public static void main(String[] args) throws Exception {
    
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    	String origin = br.readLine();
    	String sec = br.readLine();
    	
    	int[] original = new int[origin.length()];
    	int[] secrete = new int[sec.length()];
    	
    	for (int i = 0; i < origin.length(); i++) {
    		original[i] = origin.charAt(i) - 64;
    	}
    	
    	for (int i = 0; i < sec.length(); i++) {
    		secrete[i] = sec.charAt(i) - 64;
    	}
    	
    	// 평문과 암호문을 이용하여 키의 반복 문자열 만들기
    	String key = "";
    	for (int i = 0; i < origin.length(); i++) {
    		char a = (char) ((char) secrete[i] - original[i] + 64);
    		if (a<= 64) {
    			a += 26;
    		}
    		key += a;
    	}
    	
    	find(key);
    	
    }
}