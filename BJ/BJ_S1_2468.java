import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//baekjoon S1 2468 안전 영역
public class BJ_S1_2468 {
	
	static int N, maxHeight, cnt, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for(int i=0; i<N; i++) for(int j=0; j<N; j++) {
			map[i][j] = sc.nextInt();
			maxHeight = Math.max(maxHeight, map[i][j]);
		}//--입력 완료--
		for(int h=0; h<=maxHeight; h++) {
			visited = new boolean[N][N];
			cnt = 0;
			for(int r=0; r<N; r++) for(int c=0; c<N; c++) {
				if(map[r][c] > h && !visited[r][c]) {
					cnt++;
					bfs(r, c, h);
				}
			}
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
	}

	private static void bfs(int r, int c, int height) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int d=0; d<4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nr<0|| nc<0 || nr>=N || nc>=N) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] <= height) continue;
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}
}