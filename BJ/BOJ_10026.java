import java.util.Arrays;
import java.util.Scanner;

//baekjoon 10026 적록색약
public class BOJ_10026 {
	
	static int N, ans1, ans2;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		map = new char[N][];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) map[i] = sc.nextLine().toCharArray();
		//--입력완료
		
		//색약x
		for(int i=0; i<N; i++) for(int j=0; j<N; j++) {
			if(!visited[i][j]) {
				visited[i][j] = true;
				ans1++;
				dfs(i, j, map[i][j], 0);
			}
		}
		//갱신
		for(int i=0; i<N; i++) Arrays.fill(visited[i], false);
		
		//색약
		for(int i=0; i<N; i++) for(int j=0; j<N; j++) {
			if(!visited[i][j]) {
				visited[i][j] = true;
				ans2++;
				dfs(i, j, map[i][j], 1);
			}
		}
		System.out.println(ans1+" "+ans2);
	}

	private static void dfs(int r, int c, int color, int flag) {
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			
			if(color == 'B' || flag == 0) {
				if(map[nr][nc] == color && !visited[nr][nc]) {
					visited[nr][nc] = true;
					dfs(nr, nc, map[nr][nc], flag);
				}
			}
			else {
				if(map[nr][nc] == 'R' || map[nr][nc] == 'G') {
					if(!visited[nr][nc]) {
						visited[nr][nc] = true;
						dfs(nr, nc, map[nr][nc], flag);
					}
				}
			}
			
		}
	}

}