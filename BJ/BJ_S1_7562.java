import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//baekjoon S1 7562 나이트의 이동
public class BJ_S1_7562 {
	
	static int I;
	static boolean[][] visited;
	static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] start = new int[2];
		int[] end = new int[2];
		for(int tc=0; tc<T; tc++) {
			I = Integer.parseInt(br.readLine());
			visited = new boolean[I][I];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int s=0; s<2; s++) start[s] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int e=0; e<2; e++) end[e] = Integer.parseInt(st.nextToken());
			//--입력 완료--
			System.out.println(bfs(start, end));
		}
	}

	private static int bfs(int[] start, int[] end) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;
		int cnt = 0;
		
		loop:
		while(!q.isEmpty()) {
			for(int i=0, size=q.size(); i<size; i++) {
				int[] cur = q.poll();
				if(cur[0]==end[0] && cur[1]==end[1]) break loop;
				
				for(int d=0; d<8; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if(nr<0 || nc<0 || nr>=I || nc>=I) continue;
					if(visited[nr][nc]) continue;
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
			cnt++;			
		}
		return cnt;
	}
}