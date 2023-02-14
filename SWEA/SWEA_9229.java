import java.util.Scanner;

public class SWEA_9229 {
	
	static int N, M;
	static int R = 2;
	static int[] numbers, weight;
	static int ans;

	public static void main(String[] args) {
		//조합 - 무게 계산
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=0; tc<T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			ans = -1;
			weight = new int[N];
			numbers = new int[R];
			for(int i=0; i<N; i++) weight[i] = sc.nextInt();
			
			comb(0,0,0);
			
			System.out.println("#"+(tc+1)+" "+ans);
		}
	}

	private static void comb(int cnt, int start, int sum) {
		if(sum > M) return;
		if(cnt == 2) {
			ans = Math.max(ans, sum);
			return;
		}
		for(int i=start; i<N; i++) {
			numbers[cnt] = weight[i];
			comb(cnt+1, i+1, sum+weight[i]);
		}
	}

}