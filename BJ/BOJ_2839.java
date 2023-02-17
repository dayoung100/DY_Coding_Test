import java.util.Scanner;

//baekjoon 2839 설탕 배달
public class BOJ_2839 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int res = -1;
		
		int cnt5 = n/5;
		int cnt3 = calcCnt3(n, cnt5);
		
		while(true) {
			int temp = 5*cnt5 + 3*cnt3;
			
			if(temp == n) {
				res = cnt5+cnt3;
				break;
			}
			if(cnt5 == 0) break;
			
			if(temp != n) {
				cnt5--;
				cnt3 = calcCnt3(n, cnt5);
			}
		}
		System.out.println(res);
		
	}

	private static int calcCnt3(int n, int cnt) {
		return ((n - cnt*5) % 3 == 0) ?
				((n - (cnt*5)) / 3) : ((n - (cnt*5)) / 3 + 1);
	}

}
/*
 * 16 -> 1/3 -> 1
 * 16 -> 6/3 -> 2
 * 14 -> 4/3 -> 2
 * 14 -> 9/3 -> 3
 */

/*
 * n킬로그램을 배달해야함, 최대한 적은 봉지로
 * 봉지는 3kg, 5kg
 * 
 * 18kg -> 5*3 + 3*1 -> 4봉지
 * 5를 일단 챙긴다? 
 * >> 16 -> 5 5 5 3 (x) -> 5 5 3 3
 * >> 14 -> 5 5 3 3 (x) -> 5 3 3 3
 * >> 13 -> 5 5 3
 * >> 12 -> 5 5 3 (x) -> 5 3 3 3 (x) -> 3 3 3 3
 * >> 11 -> 5 5 3 (x) -> 5 3 3 (x) -> 3 3 3 3 (x) -> -1
 * 
 * 5의 수와 3의 수를 카운트 계속 함
 * 5를 일단 n/5만큼 챙기고
 * n보다 작으면 계속 3을 챙김
 * -> 완성된 수가 n이 아니면
 * 5를 하나 줄이고
 * n보다 작으면 계속 3을 챙김
 * -> 완성된 수가 n이 아니면...
 * -> 반복
 * -> 5의 수가 0인데 완성된 수가 n이 아니면
 * -> -1
 */