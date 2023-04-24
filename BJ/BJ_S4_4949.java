import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//baekjoon S4 4949 균형잡힌 세상
public class BJ_S4_4949 {

	static Stack<Character> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 받아 배열로 바꿨는데 맨 앞이 .임-> .하나만 들어왔음
		char[] sentence = null;
		while(true) {
			sentence = br.readLine().toCharArray();
			if(sentence[0] == '.') break;
			//한문장씩
			boolean flag = true;
			char pivot = '0';
			stack = new Stack<>();
			
			for (char c : sentence) {
				if(c=='.') break;
				else if(c=='(' || c=='[') stack.push(c);
				else if(c==')') {
					if(stack.isEmpty() || stack.peek() != '(') {
						flag = false;
						break;
					}else stack.pop();
				}
				else if(c==']') {
					if(stack.isEmpty() || stack.peek() != '[') {
						flag = false;
						break;
					} else stack.pop();
				}
			}
			if(!stack.isEmpty()) flag = false;
			if(flag) System.out.println("yes");
			else System.out.println("no");
		}
	}
}