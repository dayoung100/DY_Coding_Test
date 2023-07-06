import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//baekjoon S5 1676 팩토리얼 0의 개수
public class BJ_S5_1676 {

	static int two = 0;
	static int five = 0;
	static int ans = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i=1; i<=N; i++) factor(i);
		System.out.println(ans);
	}

	private static void factor(int number) {
		while(true) {
			if(number%2 == 0) {
				two+=1;
				number /= 2;
			}
			else if(number%5 == 0) {
				five+=1;
				number /= 5;
			}
			else break;
		}
		int ten = two>five ? five : two;
		ans += ten;
		two -= ten;
		five -= ten;
	}

}
/* 3! = 1*2*3 = 6
 * 10! = 1*2*3*4*5*6*7*8*9*10 = 3,628,800
 *     = 1*3*4*6*7*8*9*100
 * 즉 10으로 몇번이나 나누어 떨어지느냐
 * N까지 반복 -> 10으로 나누어 떨어지는 경우가 있으면 하나씩 오름
 * 10으로 나누어 떨어지는 경우 : 2*5, 10
 * 들어오는 수를 소인수분해 -> 2와 5의 조합이 나오면 빼기->N까지 수행
 */