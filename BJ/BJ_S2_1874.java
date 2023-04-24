import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//beakjoon S2 1874 스택 수열
public class BJ_S2_1874 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] target = new int[N];
		for(int i=0; i<N; i++) target[i] = Integer.parseInt(br.readLine());
		//-------input end------
		int num = 0;
		int idx = 0;
		Stack<Integer> s = new Stack<>();
		StringBuilder sb = new StringBuilder();
		while(true) {
			if(++num == N+1) break;
			
			s.push(num);
			sb.append("+\n");
			
			while(!s.isEmpty() && s.peek() == target[idx]) {
				s.pop();
				sb.append("-\n");
				idx++;
			}
		}
		if(s.isEmpty()) System.out.println(sb.toString());
		else System.out.println("NO");
	}

}