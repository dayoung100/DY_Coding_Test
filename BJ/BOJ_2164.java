import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2164 {

	public static void main(String[] args) {
		Queue<Integer> q = new ArrayDeque<>();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for(int i=1; i<=n; i++) q.offer(i);
		
		int ans;
		
		while(true) {
			if(q.size() == 1) {
				ans = q.poll();
				break;
			}
			q.poll();
			q.offer(q.poll());
		}
		System.out.println(ans);
	}

}