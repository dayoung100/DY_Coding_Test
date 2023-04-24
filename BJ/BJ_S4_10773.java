import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//baekjoon S4 10773 제로
public class BJ_S4_10773 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		Stack<Integer> s = new Stack<Integer>();
		for(int i=0; i<K; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num != 0) s.push(num);
			else s.pop();
		}
		int sum = 0;
		while(!s.isEmpty()) sum += s.pop();
		System.out.println(sum);
	}
}