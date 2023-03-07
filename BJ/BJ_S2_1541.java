import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//baekjoon S2 1541 잃어버린 괄호
public class BJ_S2_1541 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] ch = br.readLine().toCharArray();
		
		int sum = 0; //총합
		int tempNum = 0; //괄호 안의 합
		String tempStr = ""; //입력받은 수
		boolean isOpen = false; //괄호가 열려있는가?
		for(int i=0; i<ch.length; i++) {
			//괄호 안 열렸음
			if(!isOpen) {
				if(Character.isDigit(ch[i])) {
					tempStr += ch[i];
					if(i == ch.length-1) {
						sum += Integer.parseInt(tempStr);
					}
				}
				else if(ch[i] == '+') {
					sum += Integer.parseInt(tempStr);
					tempStr = "";
				}
				else if(ch[i] == '-') { //괄호 열어줌
					sum += Integer.parseInt(tempStr);
					tempStr = "";
					isOpen = true;
				}
			}
			//괄호 열렸음
			else {
				if(Character.isDigit(ch[i])) {
					tempStr += ch[i];
					if(i == ch.length-1) {
						tempNum += Integer.parseInt(tempStr);
						sum -= tempNum;
					}
				}
				else if(ch[i] == '+') {
					tempNum += Integer.parseInt(tempStr); //괄호 안에서 합을 따로 관리
					tempStr = "";
				}
				else if(ch[i] == '-') { //괄호 닫고 다시 열기
					tempNum += Integer.parseInt(tempStr);
					sum -= tempNum; //괄호 안의 합을 빼기
					tempStr = "";
					tempNum = 0;
				}
			}
		}
		
		System.out.println(sum);
	}

}
/* 출력: 괄호를 추가해 나오는 최소값
 * 숫자와 기호를 분리 -> 숫자와 연산자를 리스트에 담음
 * 기본적으로 숫자와 숫자 사이에 연산자가 들어감
 * 괄호의 위치 또한 연산자와 숫자 사이에 들어감
 * 즉 괄호가 들어갈 수 있는 경우의 수 = 연산자 리스트의 사이즈
 * 괄호는 두 개가 한 세트 -> 한 괄호가 들어가면 그 뒤로 닫는 괄호가 들어가야
 * 		-> 순서 상관없음(고정임) -> 괄호 한 개당 2자리를 뽑는 조합
 * 괄호는 여러개 들어갈 수 있음 -> 최대 연산자 리스트의 사이즈 / 2 개
 * 
 * ---------
 * 
 * 숫자의 순서가 고정이므로, 괄호가 마이너스의 앞~다음 마이너스의 앞을 묶어주는 것이
 * 빼는 값을 최대로 해서 최종 값을 최소로 줄일 수 있음
 * -> operator를 돌면서 -가 나오면 괄호 열고, 다시 -가 나오면 닫고 열기
 */