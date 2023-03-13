import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

//baekjoon S2 1406 에디터
public class BJ_S2_1406 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] str = br.readLine().toCharArray();
		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();
		for(int i=0; i<str.length; i++) left.push(str[i]);
		int orderNum = Integer.parseInt(br.readLine());
		for(int i=0; i<orderNum; i++) {
			char[] order = br.readLine().toCharArray();
			if(order[0] == 'L' && !left.isEmpty()) {
				char ch = left.pop();
				right.push(ch);
			}
			else if(order[0] == 'D' && !right.isEmpty()) {
				char ch = right.pop();
				left.push(ch);
			}
			else if(order[0] == 'B' && !left.isEmpty()) {
				left.pop();
			}
			else if(order[0] == 'P') {
				left.push(order[2]);
			}
		}
		for(char c: left) bw.write(c);
		while(!right.isEmpty()) bw.write(right.pop());
		bw.flush();
		bw.close();
	}

}
/* 커서를 기준으로 좌우를 스택을 이용해 구현
 */