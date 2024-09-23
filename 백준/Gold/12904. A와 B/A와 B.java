import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static String T;
	static boolean can;
	
	static StringBuilder putA(StringBuilder sb) {
		
		sb.deleteCharAt(sb.length() - 1);
		return sb;
	}
	
	static StringBuilder putB(StringBuilder sb) {
		
		sb.deleteCharAt(sb.length() - 1);
		sb.reverse();
		return sb;
	}	

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder S = new StringBuilder(br.readLine());
		StringBuilder T = new StringBuilder(br.readLine());
		
		while (S.length() < T.length()) {
			
			if (T.charAt(T.length() - 1) == 'A') {
				T = putA(T);
			} else {
				T = putB(T);
			}
			
		}
		
		if (T.toString().equals(S.toString())) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

	}

}