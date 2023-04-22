import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//baekjoon S4 10866 덱
public class BJ_S4_10866 {
	static int N;
	static Deque<Integer> d = new ArrayDeque<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			switch (order) {
			case "push_front":
				d.offerFirst(Integer.parseInt(st.nextToken()));
				break;
			case "push_back":
				d.offerLast(Integer.parseInt(st.nextToken()));
				break;
			case "pop_front":
				if(d.isEmpty()) System.out.println(-1);
				else System.out.println(d.pollFirst());
				break;
			case "pop_back":
				if(d.isEmpty()) System.out.println(-1);
				else System.out.println(d.pollLast());
				break;
			case "size":
				System.out.println(d.size());
				break;
			case "empty":
				if(d.isEmpty()) System.out.println(1);
				else System.out.println(0);
				break;
			case "front":
				if(d.isEmpty()) System.out.println(-1);
				else System.out.println(d.peekFirst());
				break;
			case "back":
				if(d.isEmpty()) System.out.println(-1);
				else System.out.println(d.peekLast());
				break;
			}
		}
	}

}
