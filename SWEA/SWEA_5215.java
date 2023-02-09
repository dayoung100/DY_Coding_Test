import java.util.Scanner;

public class SWEA_5215 {

	static int N, L;
	static int[] score_arr;
	static int[] kcal_arr;
	static int max_sum;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			L = sc.nextInt();
			
			score_arr = new int[N];
			kcal_arr = new int[N];
			max_sum = 0;
			
			for(int i=0; i<N; i++) {
				score_arr[i] = sc.nextInt();
				kcal_arr[i] = sc.nextInt();
			}
			calc(0, 0, 0);
			System.out.println("#"+test_case+" "+max_sum);
		}	
	}
	//재귀 계산
	public static void calc(int idx, int s_sum, int k_sum) {
		if(k_sum > L) return;
		if(idx == N) {
			max_sum = Math.max(s_sum, max_sum);
			return;
		}
		//선택
		calc(idx+1, s_sum+score_arr[idx], k_sum+kcal_arr[idx]);
		//선택x
		calc(idx+1, s_sum, k_sum);
	}
}