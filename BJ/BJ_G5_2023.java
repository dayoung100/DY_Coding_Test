import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

//baekjoon G5 2023 신기한 소수
public class BJ_G5_2023 {
	
	static int N;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		for(int i = 2 * (int) Math.pow(10, N-1); i<4 * (int) Math.pow(10, N-1); i++) {
			isMagicNum(i, N-1);
		}
		
		for(int i = 5 * (int) Math.pow(10, N-1); i<6 * (int) Math.pow(10, N-1); i++) {
			isMagicNum(i, N-1);
		}
		
		for(int i = 7 * (int) Math.pow(10, N-1); i<8 * (int) Math.pow(10, N-1); i++) {
			isMagicNum(i, N-1);
		}
		
		bw.flush();
		bw.close();
	}
	
	//신기한 소수인지 체크해서 맞으면 프린트하기
	private static void isMagicNum(int num, int idx) throws IOException {
		if(idx < 0) {
			bw.write(String.valueOf(num));
			bw.write("\n");
			return;
		}
		if(isPrimeNum(num / (int)Math.pow(10, idx)) == false) return;
		isMagicNum(num, idx-1);
	}
	
	//소수인지 체크
	private static boolean isPrimeNum(int num) {
		if(num == 0 || num == 1) return false;
		for(int i=2; i<=Math.sqrt(num); i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
}