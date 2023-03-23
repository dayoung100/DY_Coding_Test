import java.util.Scanner;

//baekjoon B1 2775 부녀회장이 될테야
public class BJ_B1_2775 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=0; tc<T; tc++) {
			int k = sc.nextInt();
			int n = sc.nextInt();
			if(n == 1) {
				System.out.println(1);
				continue;
			}
			System.out.println(calc(k, n));
		}
	}

	private static int calc(int floor, int room) {
		if(floor == 0) return room;
		
		int sum = 0;
		for(int i=1; i<=room; i++) {
			sum+=calc(floor-1, i);
		}
		return sum;
	}

}
/*  
 * 3층: 1 5 15 35 70
 * 2층: 1 4 10 20 35
 * 1층: 1 3  6 10 15
 * 0층: 1 2  3  4  5
 */
