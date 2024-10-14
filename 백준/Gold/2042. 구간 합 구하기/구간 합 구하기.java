import java.io.*;
import java.util.*;

public class Main {
	
	static long[] arr, tree;
	
	static long init(int start, int end, int node) {
		
		// 트리의 리프 노드 초기화
		if (start == end) {
			return tree[node] = arr[start];
		}
		
		int mid = (start + end) / 2;
		
		// 왼쪽 자식 노드 + 오른쪽 자식 노드
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	
	// start, end : 노드의 구간
	// node : 현재 트리에서의 노드 번호
	// left, right : 구간합 구하고자 하는 범위
	static long find(int start, int end, int node, int left, int right) {
		
		// 범위 밖에 있다면
		if (left > end || right < start) {
			return 0;
		}
		
		// 범위 안에 있다면
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		// 그렇지 않다면, 두 부분으로 나누어 합 구하기
		int mid = (start + end) / 2;
		return find(start, mid, node * 2, left, right) + find(mid + 1, end, node * 2 + 1, left, right);
	}
	
	// start, end : 시작 인덱스, 끝 인덱스
	// index : 수정하고자 하는 노드
	// value : 수정할 값
	static void update(int start, int end, int node, int index, long value) {
		
		// 범위 밖에 있는 경우
		if (index < start || index > end) {
			return;
		}
		
		// 범위 안에 있으면 내려가며 다른 원소도 갱신
		tree[node] += value;
		if (start == end) {
			return;
		}
		
		int mid = (start + end) / 2;
		update(start, mid, node * 2, index, value);
		update(mid + 1, end, node * 2 + 1, index, value);
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 수의 개수
		int N = Integer.parseInt(st.nextToken());
		// 수 변경 횟수
		int M = Integer.parseInt(st.nextToken());
		// 구간 합 구하는 횟수
		int K = Integer.parseInt(st.nextToken());
		
		arr = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		// 세그먼트 트리
		tree = new long[N * 4];
		
		init(1, N, 1);
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if (a == 1) {
				long value = c - arr[b];
				arr[b] = c;
				update(1, N, 1, b, value);
			} else if (a == 2) {
				System.out.println(find(1, N, 1, b, (int) c));
			}
		}	
	}
}