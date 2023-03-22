import java.util.Scanner;

//baekjoon B2 2292 벌집
public class BJ_B2_2292 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		if(N==1) {
			System.out.println(1);
			return;
		}
		double group = (double)(N-1) / 6;
		long i = 1;
		while(group > 0) {
			group -= (i++);
		}
		System.out.println(i);
	}

}
/* 6배수로 증가함
 * 1: 1개의 방
 * 2~7(6개): 2개의 방
 * 8~19(12개): 3개의 방
 * =>나누기 6을 함: 6씩 몇묶음인지
 * =>묶음을 1, 2, 3...개씩 지워나감
 * =>다 지워졌을 때가 몇번째였는지 -> 그 수 +1=answer
 */