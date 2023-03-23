import java.util.Scanner;

//baekjoon B1 2609 최대공약수와 최소공배수
public class BJ_B1_2609 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		diff(N, M);
		multi(N, M);
	}

	private static void multi(int a, int b) {
		//최소공배수
		int maxN = Math.max(a, b);
		int mul = maxN;
		int cnt = 1;
		while(true) {
			if(mul%a == 0 && mul%b == 0) {
				System.out.println(mul);
				return;
			}
			mul = maxN*(cnt++);
		}
	}

	private static void diff(int a, int b) {
		//최대공약수
		int minN = Math.min(a, b);
		for(int i=minN; i>=1; i--) {
			if(a%i == 0 && b%i == 0) {
				System.out.println(i);
				return;
			}
		}
	}
}
