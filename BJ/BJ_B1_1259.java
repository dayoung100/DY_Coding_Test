import java.util.Scanner;
import java.util.Stack;

//baekjoon B1 1259 팰린드롬수
public class BJ_B1_1259 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			char[] input = sc.nextLine().toCharArray();
			if(input[0] == '0') break;
			
			Stack<Character> s = new Stack<>();
			int length = input.length;
			for(int i=0; i<length/2; i++) {
				s.push(input[i]);
			}
			int mid = length/2;
			if(length%2 == 1) mid+=1;
			int flag = 0;
			for(int i=mid; i<length; i++) {
				int cur = s.pop();
				if(cur != input[i]) {
					flag++;
					break;
				}
			}
			if(flag == 0) System.out.println("yes");
			else System.out.println("no");
		}
		
	}
}
