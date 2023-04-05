import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//swea D4 1486. 장훈이의 높은 선반
public class SWEA_D4_1486 {

	static int N, B;
	static int diff;
	static int[] employees;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			employees = new int[N];
			visited = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) employees[i] = Integer.parseInt(st.nextToken());
			//------------input end ------------
			diff = Integer.MAX_VALUE;
			subset(0, 0);
			System.out.println("#"+t+" "+diff);
		}
	}

	private static void subset(int cnt, int tempSum) {
		if(cnt == N) {
			if(tempSum >= B) diff = Math.min(diff, tempSum-B);
			return;
		}
		if(diff < Integer.MAX_VALUE && tempSum > B+diff) return;
		visited[cnt] = true;
		subset(cnt+1, tempSum+employees[cnt]);
		visited[cnt] = false;
		subset(cnt+1, tempSum);
	}

}
/* 선반 높이 B / N명의 점원의 키 Hi / 탑의 높이=점원의 키의 합
 * 높이가 B이상인 탑 중에서 차이가 제일 적은
 * ---------------------------
 * 부분 집합->원소들의 합을 구하고 B와의 차이를 계산
 * -> 차이가 최소값보다 커지면 백트래킹
 */