import java.util.Scanner;

//baekjoon B1 2869 달팽이는 올라가고 싶다
public class BJ_B1_2869 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long A = sc.nextLong();
		long B = sc.nextLong();
		long V = sc.nextLong();
		
		long day = 1;
		long remain = V - A;
		long step = A-B;
		day += Math.ceil((double)remain/step);
		
		System.out.println(day);
	}

}
