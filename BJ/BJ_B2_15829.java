import java.util.Scanner;

//baekjoon B2 15829 Hashing
public class BJ_B2_15829 {
	
	static final long M = 1234567891;
	static final int r = 31;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L = Integer.parseInt(sc.nextLine()); //길이
		char[] str = sc.nextLine().toCharArray(); //문자열
		//calc
		long res = 0;
		long pow = 1;
		for(int i=0; i<L; i++) {
			//(str[i] - '0' - 48) * Math.pow(r, i)
			//-> 이렇게 다 더해준 뒤 마지막에 모듈러
			//->분배법칙 1 : ((str[i] - '0' - 48) * Math.pow(r, i)) % M
			//->븐배법칙 2 : ((str[i] - '0' - 48) * pow) % M ; pow=pow*31%M
			res += ((str[i] - '0' - 48) * pow) % M;
			pow = (pow * 31) % M;
		}
		System.out.println(res%M);
	}

}
/* a=1, b=2, ... z=26
 * 알파벳을 숫자로 바꾼 뒤
 * -> 수열의 각 i번쨰 항마다 31의 i승을 곱해줌
 * -> 모두 더함
 * -> 그리고 M=1234567891으로 나눔
 * 
 * L = 50 -> aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 * 1*1 + 1*31 + 1*31^2 + ...
 */
