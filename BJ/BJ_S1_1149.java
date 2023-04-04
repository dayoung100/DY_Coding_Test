import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//baekjoon S2 1149 RGB거리
public class BJ_S1_1149 {
	static int[][] map, memo;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][3];
		memo = new int[N][3];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				memo[i][j] = minimize(i, j);
			}
		}
		int ans = Integer.MAX_VALUE;
		for(int j=0; j<3; j++) ans = Math.min(ans, memo[N-1][j]);
		System.out.println(ans);
	}
	private static int minimize(int r, int c) {
		if(r==0) {
			return map[r][c];
		}
		int min = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
			if(i==c) continue;
			min = Math.min(min, memo[r-1][i] + map[r][c]);
		}
		return min;
	}
}
/* 
 * 인접한 집과 같은 색이 되지 않도록 -> 비용의 최솟값
 * 1->2->3...순서로 그 칸까지의 최솟값 계산해서 메모이제이션
 * 최솟값 계산하는 법:
 * 가장 작은 값 위주로 가져오면서
 * (빨,파)(빨,초)(파,빨)(파,초)(초,빨)(초,파) : 6가지
 * 직전 라인까지만 비교(직전 라인에는 직전 컬러에 대한 정보도 담고 있으니까)
 */