import java.util.Scanner;

public class BJ2798_2 {
	
	static int N, M;
	static int[] numbers, inputs;
	static int max = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		numbers = new int[3];
		inputs = new int[N];
		for(int i=0; i<N; i++) inputs[i] = sc.nextInt();
		comb(0, 0);
		System.out.println(max);
	}

	private static void comb(int cnt, int start) {
		if(cnt == 3) {
			int temp = 0;
			for(int i=0; i<3; i++) temp += numbers[i];
			if(temp <= M) max = Math.max(temp, max);
			return;
		}
		for(int i=start; i<N; i++) {
			numbers[cnt] = inputs[i];
			comb(cnt+1, i+1);
		}
	}

}