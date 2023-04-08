import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//beakjoon G1 2042 구간 합 구하기
public class BJ_G1_2042 {

	static int N, M, K;	
	static long[] numbers;
	static long[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		numbers = new long[N+1];
		tree = new long[N+1];
		for(int i=1; i<=N; i++) {
			numbers[i] = Long.parseLong(br.readLine());
			update(i, numbers[i]);
		}
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if(a == 1) {
				long diff = c - numbers[b];
				numbers[b] = c;
				update(b, diff);
			}
			else {
				int end = (int) c;
				System.out.println(sum(end) - sum(b-1));
			}
		}
	}

	private static long sum(int index) {
		long sum = 0;
		while(index > 0) {
			sum += tree[index];
			index -= (index&-index);
		}
		return sum;
	}

	private static void update(int index, long diff) {
		while(index<N+1) {
			tree[index] += diff;
			index += (index&-index);
		}
	}

}
/* 펜윅 트리... 인덱스를 2진수로 바꾸어 비트 연산
 * 최하위 비트는 i & -i
 * 부분합: 그 위치에서의 값에, 필요한 위치의 값을 찾아가면서 합하기
 *  -> 그 위치는 첫 인덱스에서 최하위 비트를 '빼면서' 찾을 수 있음
 * 특정 위치 갱신: 그 위치에서의 값을 변경하고, 필요한 위치의 값을 찾아가면서 같은 값만큼 더하거나 빼주기
 *  -> 그 위치는 첫 인덱스에서 최하위 비트를 '더하면서' 찾을 수 있음
 *  ----------------------
 *  그럼 2진수 배열은....? -> update를 반복
 *  
 */