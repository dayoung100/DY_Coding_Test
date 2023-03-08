import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

//baekjoon G4 12851 숨바꼭질2
public class BJ_S1_12851 {
	
	static int N, K, X, t, cnt; //t: time = level
	static int[] dir = {-1, 1, 2};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //수빈이 위치
		K = sc.nextInt(); //동생 위치
		if(N==K) {
			System.out.println(0);
			System.out.println(1);
			return;
		}
		bfs();
		System.out.println(t-1);
		System.out.println(cnt);
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		int[] visited = new int[100000+1];
		Arrays.fill(visited, Integer.MAX_VALUE);
		q.offer(N);
		visited[N] = t;
		int next = 0;
		int minTime = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			if(minTime < t) break;
			for(int i=0, size = q.size(); i<size; i++) {
				int cur = q.poll();
				if(cur == K) {
					minTime = t;
					cnt++;
				}
				
				for(int d=0; d<3; d++) {
					if(d==2) next = cur*dir[d];
					else next = cur + dir[d];
					if(next<0 || next > 100000 || visited[next] < t) continue;
					q.offer(next);
					visited[next] = Math.min(visited[next], t);
				}
			}
			t++;
		}
	}

}
/* 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인가
 * + 가장 빠른 시간으로 찾는 방법이 몇가지 인지
 * 
 * 수빈이의 이동(1초, 현위치 X)
 *  - X-1
 *  - X+1
 *  - 2X
 * 1초마다 이동, 세 가지 이동을 모두 해보는 BFS
 * visited를 미리 체크하면 도착점이 한번 들어가면 더는 들어가지 않음 -> 늦게 체크해볼까
 * 
 * 목표가 10이라고 했을 때, 갈 수 있는 직전 노드는...
 * 9(+1), 11(-1), 5(*2)
 */