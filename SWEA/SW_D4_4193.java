import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//swea D4 4193. 수영대회 결승전
public class SW_D4_4193 {
	
	static int N, time;
	static int[] start, end, now;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			map = new int[N][N];
			start = new int[2];
			end = new int[2];
			for(int i=0; i<N; i++) for(int j=0; j<N; j++) map[i][j] = sc.nextInt();
			start[0] = sc.nextInt();
			start[1] = sc.nextInt();
			end[0] = sc.nextInt();
			end[1] = sc.nextInt();
			//-- 입력 완료--
			time = 0;
			if(!bfs()) time = -1;
			System.out.println("#"+t+" "+time);
		}
	}

	private static boolean bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {start[0], start[1]});
		visited = new boolean[N][N];
		visited[start[0]][start[1]] = true;
		boolean able = true;
		
		loop:
		while(!q.isEmpty() && able) {
			int cnt = 0;
			for(int i=0, size=q.size(); i<size; i++) {
				int[] cur = q.poll();
				
				if(cur[0] == end[0] && cur[1] == end[1]) break loop;
				if(map[cur[0]][cur[1]] == 2 && time%3 != 0) {
					q.offer(cur);
					cnt++;
					continue;
				}
				
				for(int d=0; d<4; d++) {
					int nr = cur[0]+dr[d];
					int nc = cur[1]+dc[d];
					if(nr<0||nc<0||nr>=N||nc>=N) continue;
					if(visited[nr][nc]) continue;
					if(map[nr][nc] == 1) continue;
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					cnt++;
				}
			}
			//한 레벨이 다 지나도록 넣을 수 있는 게 없음
			if(cnt == 0) {
				able = false;
				break;
			}
			time++;
		}
		return able;
	}
}
/* 0-갈 수 있음 / 1-못감 / 2-소용돌이(주기가 2초)
 * 2는 시간이 지날 수록 2->1->0->2->1... 을 반복
 * 소용돌이 턴과 움직이는 쪽 중에서 더 시간을 절약하는 쪽으로 움직여야
 * -> 직접 while로 시뮬레이션 해보지 않고도 최단 시간을 구할 수 있지 않나?
 * 소용돌이 계산: 3t마다 건널 수 있음
 * -> 건널 수 있으면 건너고(사방 체크 후 큐에)
 * -> 건널 수 없으면 제자리에(자기 자신을 다시 큐에)
 * + 갈 수 있는지 없는지도 체크해야함 -> 남은 모든 방향에 1이 있어서 아무것도 큐에 들어갈 수 없으면 on
 */
/* tc
1
5
0 0 0 0 0
0 0 0 1 0
0 0 0 1 0
2 2 1 1 0
0 0 0 0 0
4 0
2 0
 */