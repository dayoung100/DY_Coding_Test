import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//baekjoon S1 5014 스타트링크
public class BJ_S1_5014 {
	
	static int F, S, G, U, D, ans;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		F = sc.nextInt();
		S = sc.nextInt();
		G = sc.nextInt();
		U = sc.nextInt();
		D = sc.nextInt();
		//--입력 완료--
		visited = new boolean[F+1];
		bfs(S);
		if(ans == -1) System.out.println("use the stairs");
		else System.out.println(ans);
	}

	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		boolean flag =  false;
		
		loop:
		while(!q.isEmpty()) {
			for(int i=0, size=q.size(); i<size; i++) {
				int cur = q.poll();
				
				if(cur == G) {
					flag = true;
					break loop;
				}
				
				for(int d=0; d<2; d++) {
					int next;
					
					if(d==0) next = cur+U;
					else next = cur-D;
					
					if(next<1 || next>F) continue;
					if(visited[next]) continue;
					q.offer(next);
					visited[next] = true;
				}
			}
			ans++;
		}
		if(!flag) ans = -1;
	}
}