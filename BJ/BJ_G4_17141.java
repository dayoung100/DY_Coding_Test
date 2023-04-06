import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//baekjoon G4 17141 연구소 2
public class BJ_G4_17141 {
	
	static int N, M, min, emptyCnt;
	static int[][] map;
	static boolean[] cVisited; //for combination
	static boolean[][] bVisited; //for bfs
	static List<int[]> ablePos = new ArrayList<>();
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
				if(input == 2) ablePos.add(new int[] {i, j});
				if(input == 0 || input == 2) emptyCnt++;
			}
		}//----------input end-----------
		min = Integer.MAX_VALUE;
		cVisited = new boolean[ablePos.size()];
		comb(0, 0);
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}

	private static void comb(int cnt, int start) {
		if(cnt == M) {
			bfs();
			return;
		}
		for(int i=start; i<ablePos.size(); i++) {
			if(cVisited[i]) continue;
			cVisited[i] = true;
			comb(cnt+1, i+1);
			cVisited[i] = false;
		}
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		bVisited = new boolean[N][N];
		int fillCnt = 0;
		for(int i=0; i<cVisited.length; i++) {
			if(cVisited[i]) {
				int r = ablePos.get(i)[0];
				int c = ablePos.get(i)[1];
				q.offer(new int[] {r, c});
				bVisited[r][c] = true;
			}
		}
		int time = 0;
		outer:
		while(!q.isEmpty()) {
			for(int i=0, size=q.size(); i<size; i++) {
				int[] cur = q.poll();
				fillCnt++;
				if(fillCnt == emptyCnt) break outer;
				for(int d=0; d<4; d++) {
					int nr = cur[0]+dr[d];
					int nc = cur[1]+dc[d];
					if(nr<0||nc<0||nr>=N||nc>=N) continue;
					if(bVisited[nr][nc]) continue;
					if(map[nr][nc] == 1) continue;
					q.offer(new int[] {nr, nc});
					bVisited[nr][nc] = true;
				}
			}
			time++;
		}
		if(fillCnt == emptyCnt) min = Math.min(min, time);
	}

}
/* 바이러스를 특정 위치(2)에 M개
 * 2는 바이러스칸 / 1은 벽 / 0은 빈칸
 * 바이러스는 1초에 상하좌우인접한 모든 으로 동시에 복제
 * 모든 빈칸에 퍼뜨리는 최소 시간(불가능하면 -1)
 * ------------------------
 * 2 중에서 M개 선택 -> 조합
 * 최소 시간 -> BFS
 */