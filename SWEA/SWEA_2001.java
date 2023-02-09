import java.util.Scanner;

public class SWEA_2001 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();
			int ans = 0;
			
			int[][] area = new int[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					area[i][j] = sc.nextInt();
				}
			}
			
			for(int r=0; r<N-M+1; r++) {
				for(int c=0; c<N-M+1; c++) {
					int temp_ans = 0;
					for(int mx=0; mx<M; mx++) {
						for(int my=0; my<M; my++) {
							temp_ans += area[r+mx][c+my];
						}
					}
					ans = Math.max(temp_ans, ans);
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
	}

}