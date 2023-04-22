import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//baekjoon S4 9012 괄호
public class BJ_S4_9012 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Character> s;
		for(int i=0; i<N; i++) {
			s = new Stack<>();
			char[] input = br.readLine().toCharArray();
			boolean flag = true;
			for(char c : input) {
				if(c == '(') s.push(c);
				else {
					if(s.isEmpty()) {
						System.out.println("NO");
						flag = false;
						break;
					}
					else s.pop();
				}
			}
			if(!flag) continue;
			else if(s.isEmpty()) System.out.println("YES");
			else System.out.println("NO");
		}
	}

}
