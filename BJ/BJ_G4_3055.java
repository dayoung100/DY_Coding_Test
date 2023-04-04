import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//baekjoon G4 3055 탈출
public class BJ_G4_3055 {

	static int R, C;
	static int[][] map;
	static int[] start, dest, pos;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] visited;
	static boolean able;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			char[] inputs = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				char input = inputs[j];
				if(input == 'D') {
					dest = new int[] {i, j};
					map[i][j] = -100;
				} else if(input == 'S') {
					start = new int[] {i, j};
					map[i][j] = -100;
				} else if(input=='.') map[i][j] = 0;
				else if(input=='*') map[i][j] = 1;
				else if(input=='X') map[i][j] = -1;
			}
		}//-----------input end--------------
		pos = new int[2];
		visited = new boolean[R][C];
		int ans = bfs();
		if(able) System.out.println(ans);
		else System.out.println("KAKTUS");
	}

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {start[0], start[1]});
		visited[start[0]][start[1]] = true;
		int time = 0;
		
		loop:
		while(!q.isEmpty()) {
			mapUpdate(time);
			for(int i=0, size=q.size(); i<size; i++) {
				pos = q.poll();
				
				if(pos[0] == dest[0] && pos[1] == dest[1]) {
					able = true;
					break loop;
				}
				
				for(int d=0; d<4; d++) {
					int nr = pos[0] + dr[d];
					int nc = pos[1] + dc[d];
					if(nr<0 || nc<0 || nr>=R || nc>=C) continue; //범위
					if(visited[nr][nc]) continue; //방문했음
					if(map[nr][nc] == -1) continue; // 벽임
					if(map[nr][nc] == time+2) continue; //물 또는 물이 들어올 예정
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
			time++;
		}
		return time;
	}

	private static void mapUpdate(int time) {
		for(int i=0; i<R; i++) for(int j=0; j<C; j++) {
			if(map[i][j] == time+1) {
				map[i][j]++;
				for(int d=0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(nr<0 || nc<0 || nr>=R || nc>=C) continue;
					if(map[nr][nc] == 0) map[nr][nc] = time+2;
				}
			}
		}
	}
}
