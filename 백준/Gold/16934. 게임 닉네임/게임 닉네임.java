import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 가입한 유저의 수
		int N = Integer.parseInt(br.readLine());
		
		// 닉네임과 별칭 저장
		HashMap<String, String> starName = new HashMap<String, String>();
		// 닉네임 개수 저장
		HashMap<String, Integer> nicknameCnt = new HashMap<String, Integer>();
		
		for (int i = 0; i < N; i++) {
			String nickname = br.readLine();
			
			// 해당 닉네임 개수 업데이트
			if (nicknameCnt.containsKey(nickname)) {
				nicknameCnt.replace(nickname, nicknameCnt.get(nickname) + 1);
			} else {
				nicknameCnt.put(nickname, 1);
			}
			
			// 별칭
			String star = "";
			// 별칭 존재 여부
			boolean okay = false;
			for (int j = 0; j < nickname.length(); j++) {
				star += nickname.charAt(j);
				
				if (!starName.containsKey(star)) {
					starName.put(star, star);
					System.out.println(star);
					okay = true;
					
					for (int k = j + 1; k < nickname.length(); k++) {
						star += nickname.charAt(k);
						starName.put(star, star);
					}
					break;
				}
			}
			
			// 별칭 없으면 닉네임 뒤에 개수 붙이기
			if (!okay) {
				if (nicknameCnt.get(nickname) == 1) {
					System.out.println(nickname);
				} else {
					System.out.println(nickname + nicknameCnt.get(nickname));
				}
			}
		}
	}
}