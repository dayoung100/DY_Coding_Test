import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//baekjoon S1 1697 숨바꼭질
public class BJ_S1_1697 {
	
	static int N, K, X, t; //t: time = level
	static int[] dir = {-1, 1, 2};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //수빈이 위치
		K = sc.nextInt(); //동생 위치
		if(N==K) {
			System.out.println(0);
			return;
		}
		bfs();
		System.out.println(t);
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[100000+1];
		q.offer(N);
		visited[N] = true;
		t = 0;
		int next = 0;
		loop:
		while(!q.isEmpty()) {
			for(int i=0, size = q.size(); i<size; i++) {
				int cur = q.poll();
				if(cur == K) break loop;
				
				for(int d=0; d<3; d++) {
					if(d==2) next = cur*dir[d];
					else next = cur + dir[d];
					if(next<0 || next > 100000 || visited[next]) continue;
					q.offer(next);
					visited[next] = true;
				}
			}
			t++;
		}
	}

}
/* 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인가
 * 
 * 수빈이의 이동(1초, 현위치 X)
 *  - X-1
 *  - X+1
 *  - 2X
 * 1초마다 이동, 세 가지 이동을 모두 해보는 BFS
 */