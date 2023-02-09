import java.util.Scanner;

public class BJ2961_2 {
	
	static int N;
	static int[][] data;
	static int ans = 1000000000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //재료의 개수
		data = new int[N][2];
		for (int i = 0; i < N; i++) {
			data[i][0] = sc.nextInt();
			data[i][1] = sc.nextInt();
		}
		
		subset(0, 1, 0);
		System.out.println(ans);
	}

	private static void subset(int cnt, int sSum, int bSum) {
		if(cnt == N) {
			if(sSum == 1 || bSum == 0) return;
			ans = Math.min(Math.abs(sSum - bSum), ans);	
			return;
		}
		//선택함
		subset(cnt+1, sSum*data[cnt][0], bSum+data[cnt][1]);
		//선택안함
		subset(cnt+1, sSum, bSum);
	}

}