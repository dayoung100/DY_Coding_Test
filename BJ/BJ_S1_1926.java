import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//baekjoon S1 1926 그림
public class BJ_S1_1926 {
	
	static int N, M, cnt, max;
	static int[][] map;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//--입력 받기 완료--
		for(int i=0; i<N; i++) for(int j=0; j<M; j++) {
			if(map[i][j] == 1) {
				cnt++;
				max = Math.max(max, bfs(i, j));
			}
		}
		System.out.println(cnt);
		System.out.println(max);
	}

	private static int bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		map[r][c] = 0;
		int sum = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			sum++;
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
				if(map[nr][nc] == 1) {
					q.offer(new int[] {nr, nc});
					map[nr][nc] = 0;
				}
			}
		}
		return sum;
	}
}
