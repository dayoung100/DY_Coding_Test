import java.util.Scanner;

//baekjoon B1 11050 이항 계수 1
public class BJ_B1_11050 {

	static int N, K;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		int ans = 0;
		ans = fac(N) / (fac(K) * fac(N-K));
		System.out.println(ans);
	}

	private static int fac(int n) {
		int cnt = 1;
		for(int i=2; i<=n; i++) {
			cnt *= i;
		}
		return cnt;
	}
}
