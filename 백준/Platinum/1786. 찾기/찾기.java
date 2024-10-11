import java.io.*;
import java.util.*;
 
public class Main {
	
	static ArrayList<Integer> ans = new ArrayList<>();
	
	static int[] findLps(String P) {
		
		int len = 0;
		int i = 1;
		int[] lps = new int[P.length()];
		while (i < P.length()) {
			if (P.charAt(i) == P.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else if (len == 0) {
				i++;
			} else {
				len = lps[len - 1];
			}
		}
		return lps;
	}
	
	static void kmp(String T, String P) {
		int i = 0;
		int j = 0;
		int[] lps;
		lps = findLps(P);
		while (i < T.length() ) {
			if (T.charAt(i) == P.charAt(j)) {
				i++;
				j++;
				if (j == P.length()) {
					ans.add(i - j + 1);
					j = lps[j - 1];
				}
			} else if (j == 0) {
				i++;
			} else {
				j = lps[j - 1];
			}
		}
	}
     
    public static void main(String[] args) throws Exception{
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        String T = br.readLine();
        String P = br.readLine();
                
        kmp(T, P);
        
        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        for (int i = 0; i < ans.size(); i++) {
        	sb.append(ans.get(i)).append(" ");
        }
        
        System.out.println(sb.toString());
        
    }
}