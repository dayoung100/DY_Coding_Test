import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//baekjoon S1 2583 영역 구하기
public class BJ_S1_2583 {
	
	static int M, N, K, cnt;
	static int[][] map;
	static List<Integer> area = new ArrayList<>();
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		int[] p1 = new int[2];
		int[] p2 = new int[2];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			p1[1] = Integer.parseInt(st.nextToken());
			p1[0] = M - Integer.parseInt(st.nextToken());
			p2[1] = Integer.parseInt(st.nextToken());
			p2[0] = M - Integer.parseInt(st.nextToken());
			for(int r=p2[0]; r<p1[0]; r++) {
				for(int c=p1[1]; c<p2[1]; c++) {
					map[r][c] = 1;
				}
			}
		}//--입력완료--
		for(int i=0; i<M; i++) for(int j=0; j<N; j++) {
			if(map[i][j] == 0) {
				cnt++;
				bfs(i, j);
			}
		}
		System.out.println(cnt);
		Collections.sort(area);
		for(int i=0; i<area.size(); i++) System.out.print(area.get(i)+" ");
	}

	private static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {i, j});
		map[i][j] = 1;
		int sum = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			sum++;
			for(int d=0; d<4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nr<0 || nc<0 || nr>=M || nc>=N) continue;
				if(map[nr][nc] == 1) continue;
				q.offer(new int[] {nr, nc});
				map[nr][nc] = 1;
			}
		}
		area.add(sum);
	}
}