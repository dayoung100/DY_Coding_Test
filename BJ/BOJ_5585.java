import java.util.Scanner;

//baekjoon 5585 거스름돈
public class BOJ_5585 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cost = 1000 - sc.nextInt();
		int[] coin = {500, 100, 50, 10, 5, 1}; //잔돈
		int sum = 0;
		
		for(int i=0; i<coin.length; i++) {
			if(cost == 0) break;
			int cnt = cost/coin[i];
			cost -= cnt*coin[i];
			sum += cnt;
		}
		
		System.out.println(sum);
	}

}
/*
 * 500 / 100 / 50 / 10 / 5 / 1
 * 1000 - 380 = 620
 * 620 = 500+100+10+10 => 4
 * 999 => 500+100*4+50+10*4+5+1*4 => 15
 */