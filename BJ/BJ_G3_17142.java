import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//baekjoon G3 17142 연구소 3
public class BJ_G3_17142 {
	
	static int N, M, min, zeroCnt;
	static int[][] map;
	static int[] numbers;
	static List<int[]> ablePos = new ArrayList<>();
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

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
				if(input == 0) zeroCnt++;
			}
		}//------------input end----------
		if(zeroCnt == 0) {
			System.out.println(0);
			return;
		}
		numbers = new int[ablePos.size()];
		min = Integer.MAX_VALUE;
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
			numbers[cnt] = i;
			comb(cnt+1, i+1);
		}
	}

	private static void bfs() {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque<>();
		for(int i=0; i<M; i++) {
			q.offer(ablePos.get(numbers[i]));
			visited[ablePos.get(numbers[i])[0]][ablePos.get(numbers[i])[1]] = true;
		}
		int time = 0;
		int fillCnt = 0;
		boolean flag = false;
		while(!q.isEmpty()) {
			for(int i=0, size=q.size(); i<size; i++) {
				int[] cur = q.poll();
				for(int d=0; d<4; d++) {
					int nr = cur[0]+dr[d];
					int nc = cur[1]+dc[d];
					if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
					if(map[nr][nc] == 1) continue;
					if(visited[nr][nc]) continue;
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					if(map[nr][nc]==0) fillCnt++;
				}
			}
			time++;
			if(fillCnt == zeroCnt) {
				flag = true;
				break;
			}
		}
		if(flag) min = Math.min(min, time);
	}

}