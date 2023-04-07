import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//swea 1949. [모의 SW 역량테스트] 등산로 조성
public class SWEA_1949 {
	
	static int N, K, top, topCnt, max;
	static int[][] map;
	static int[][] tops;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			top = 0;
			topCnt = 0;
			max = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int input = Integer.parseInt(st.nextToken());
					map[i][j] = input;
					if(input > top) {
						top = input;
						topCnt = 1;
					}
					else if(input == top) topCnt++;
				}
			}//---------input end--------------
			tops = new int[topCnt][2];
			int idx = 0;
			for(int i=0; i<N; i++) for(int j=0; j<N; j++) {
				if(map[i][j]==top) {
					tops[idx][0] = i;
					tops[idx][1] = j;
					idx++;
				}
			}
			
			for(int i=0; i<topCnt; i++) {
				boolean[][] visited = new boolean[N][N];
				visited[tops[i][0]][tops[i][1]] = true;
				dfs(tops[i][0], tops[i][1], map[tops[i][0]][tops[i][1]], false, 1, visited);
			}
			System.out.println("#"+tc+" "+max);
		}
	}
	/**
	 * dfs
	 * @param r			: row
	 * @param c			: col
	 * @param nowHeight	: 그 칸의 높이
	 * @param flag		: 공사여부
	 * @param length	: 등산로 길이
	 */
	private static void dfs(int r, int c, int nowHeight, boolean flag, int length, boolean[][] visited) {
		max = Math.max(max, length);
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			if(visited[nr][nc]) continue;
			int nextHeight = map[nr][nc];
			
			if(nowHeight > nextHeight) {
				boolean[][] newVisited = new boolean[N][N];
				for(int i=0; i<N; i++) newVisited[i] = Arrays.copyOf(visited[i], N);
				newVisited[nr][nc] = true;
				dfs(nr, nc, nextHeight, flag, length+1, newVisited);
			}
			if(!flag) {
				for(int k=1; k<=K; k++) {
					int tempNextHeight = nextHeight - k; //K깎음
					if(tempNextHeight < 0) break;
					if(nowHeight > tempNextHeight) {
						boolean[][] newVisited = new boolean[N][N];
						for(int i=0; i<N; i++) newVisited[i] = Arrays.copyOf(visited[i], N);
						newVisited[nr][nc] = true;
						dfs(nr, nc, tempNextHeight, true, length+1, newVisited);
						break;
					}
				}
			}
		}
	}
}
/* NxN의 맵, 가장 긴 등산로
 * 가장 높은 곳에서 시작
 * 등산로는 높은 지형->낮은 지형으로, 대각선 불가
 * 딱 한 곳만 K만큼 깎을 수 있음
 * >>>필요한 경우 깎아서 높이를 0으로 만들수도 있음<<<
 * >>>가장 높은 봉우리도 깎을 수 있음<<<
 * -----------------------
 * 가장 높은 곳 찾아서 저장->각 위치에서 완탐(DFS)
 * 1. 사방 탐색
 *  1-1. 그 칸이 map범위 안이라면
 *  	1-1-1. flag가 꺼져 있다면
 *  		->. 한 칸을 최대 K만큼 깎거나, 깎지 않거나(깎으면 flag를 on)
 *  	1-1-2. 그 칸의 map값이 현재값보다 작은가
 * 		->dfs재귀
 * 기저조건: 더이상 갈 수가 없음(더이상 작은 값이 주위에 없음)
 * dfs파라미터: r, c, nowHeight, flag, length
 * nowHeight-현재 칸이 공사해서 온 칸이면 map값과 달라지니까 파라미터로 관리 필요
 * -----------------------
 * K: 최대 K만큼 깎을 수 있다...
 * 작게 깎을 수록 길게 갈 수 있지 않나? -> K와 상관없이 1만 깎도록?NOOO
 * -> 테케2에서 2를 2만큼 깎아서 0으로 만들어야 함 -> K는 1만 깎으면 안됨
 */