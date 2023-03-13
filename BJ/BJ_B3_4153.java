import java.util.Scanner;

//baekjoon B3 4153 직각삼각형
public class BJ_B3_4153 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			if(a == 0 && b == 0 && c == 0) break;
			int max = 0;
			int[] line = {a, b, c};
			int maxIdx = -1;
			for(int i=0; i<3; i++) {
				if(line[i] > max) {
					max = line[i];
					maxIdx = i;
				}
			}
			int longest = line[maxIdx]*line[maxIdx];
			int others = 0;
			for(int i=0; i<3; i++) {
				if(i == maxIdx) continue;
				others += line[i]*line[i];
			}
			if(longest == others) System.out.println("right");
			else System.out.println("wrong");
		}
	}

}
