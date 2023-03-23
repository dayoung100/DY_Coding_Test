import java.util.Scanner;

//baekjoon B2 2231 분해합
public class BJ_B2_2231 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextInt();
		for(long i=1; i<=N; i++) {
			long subsum = i;
			long num = i;
			//int cnt = 1;
			while(num>0) {
				long remain = num % 10;
				subsum += remain;
				num /= 10;
			}
			if(subsum == N) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
	}

}