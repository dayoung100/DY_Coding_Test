import java.util.Scanner;

//baekjoon S2 1012 유기농 배추
public class BJ_S2_1012 {
	
	static int M, N, K, ans;
	static int[][] map;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=0; tc<T; tc++) {
			ans = 0;
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][M];
			for(int i=0; i<K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				map[y][x] = 1; //배추가 있음
			}
			
			for(int r=0; r<N; r++) for(int c=0; c<M; c++) {
				if(map[r][c] == 1) {
					ans++;
					map[r][c] = 0;
					dfs(r, c);
				}
			}
			System.out.println(ans);
		}
	}

	private static void dfs(int r, int c) {
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
			if(map[nr][nc] == 0) continue;
			map[nr][nc] = 0;
			dfs(nr, nc);
		}
	}

}
