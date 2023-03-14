import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// baekjoon S4 1018 체스판 다시 칠하기
public class BJ_S4_1018 {
	static int N, M, min;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[] colors = {'B', 'W'};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		min = Integer.MAX_VALUE;
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				map[i][j] = ch[j];
			}
		} //--입력처리완료
		//시작점 고르기
		for(int i=0; i<N-8+1; i++) {
			for(int j=0; j<M-8+1; j++) {
				bfs(i, j, true);
				bfs(i, j, false);
			}
		}
		System.out.println(min);
	}
	private static void bfs(int r, int c, boolean flag) {
		int cnt = 0; //다시칠해야하는 정사각형의 개수
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[8][8];
		q.offer(new int[] {r,c});
		visited[0][0] = true;
		int color = -1;
		if(flag && map[r][c] == 'B') color = 0;
		else if(flag && map[r][c] == 'W') color = 1;
		else if(!flag && map[r][c] == 'B') color = 1;
		else if(!flag && map[r][c] == 'W') color = 0;
		
		while(!q.isEmpty()) {
			for(int i=0, size=q.size(); i<size; i++) {
				int[] cur = q.poll();
				if(map[cur[0]][cur[1]] != colors[color%2]) {
					cnt++;
				}
				for(int d=0; d<4; d++) {
					int nr = cur[0]+dr[d];
					int nc = cur[1]+dc[d];
					if(nr<0 || nc<0 || nr>=N || nc>=M) continue; //전체 map의 범위
					if(nr<r || nc<c || nr>=r+8 || nc>=c+8) continue; //8*8크기 내부여야
					if(visited[nr-r][nc-c]) continue;
					q.offer(new int[] {nr, nc});
					visited[nr-r][nc-c] = true;
				}
			}
			color += 1;
		}
		min = Math.min(min, cnt);
	}

}
/* 8x8로 잘라내 체스판으로 만듬 -> 다시 칠해야하는 칸의 최소 개수
 * 분할 정복? bfs로 하나하나?
 */