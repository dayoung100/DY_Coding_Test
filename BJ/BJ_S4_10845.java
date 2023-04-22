import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

//baekjoon S4 10845 큐
public class BJ_S4_10845 {

	static int N;
	static Queue<Integer> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		int tail = 0; //가장 앞에 있는 수
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			switch (order) {
			case "push":
				int num = Integer.parseInt(st.nextToken());
				q.offer(num);
				tail = num;
				break;
			case "pop":
				if(q.isEmpty()) System.out.println(-1);
				else System.out.println(q.poll());
				break;
			case "size":
				System.out.println(q.size());
				break;
			case "empty":
				if(q.isEmpty()) System.out.println(1);
				else System.out.println(0);
				break;
			case "front":
				if(q.isEmpty()) System.out.println(-1);
				else System.out.println(q.peek());
				break;
			case "back":
				if(q.isEmpty()) System.out.println(-1);
				else System.out.println(tail);
				break;
			}
		}
	}

}
