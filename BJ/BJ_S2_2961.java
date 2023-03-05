import java.util.Scanner;

//baekjoon S2 2961 도영이가 만든 맛있는 음식
public class BJ_S2_2961 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //재료의 개수
		int[][] data = new int[N][2];
		for (int i = 0; i < N; i++) {
			data[i][0] = sc.nextInt();
			data[i][1] = sc.nextInt();
		}
		int ans = 1000000000;
		for(int i=1; i<(1<<N); i++) {
			int sSum = 1, bSum = 0;
			for(int j=0; j<N; j++) {
				if((i & (1<<j)) == 0) continue;
				sSum *= data[j][0];
				bSum += data[j][1];
			}
			ans = Math.min(Math.abs(sSum - bSum), ans);
		}
		System.out.println(ans);
	}

}