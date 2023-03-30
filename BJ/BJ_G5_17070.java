import java.util.Scanner;

//baekjoon G5 17070 파이프 옮기기 1
public class BJ_G5_17070 {

	static int N, ans;
	static int[][] map;
	static boolean[][] visited;
	static int dir[][] = { { 0, 1 }, { 1, 0 }, { 1, 1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();
		visited[0][0] = true;
		visited[0][1] = true;
		dfs(0, 1, 0);
		System.out.println(ans);
	}

	private static void dfs(int r, int c, int now) {
		if (r == N - 1 && c == N - 1) {
			ans++;
			return;
		}
		// 현 파이프 상태에 따라 이동 방법이 다름
		// 현 파이프 상태를 갱신-0:가로 1:세로 2:대각선
		switch (now) {
		case 0: // 가로
			// now에 따라 칸이 비어있는지 체크
			// i : 오른쪽, 아래, 또는 오른쪽 아래 대각선 방향
			for (int i = 0; i < 3; i++) {
				if (i == 1)
					continue;
				if (checkEmpty(r, c, i)) {
					int nr = r + dir[i][0];
					int nc = c + dir[i][1];
					visited[nr][nc] = true;
					dfs(nr, nc, i);
					visited[nr][nc] = false;
				}
			}
			break;
		case 1: // 세로
			for (int i = 0; i < 3; i++) {
				if (i == 0)
					continue;
				if (checkEmpty(r, c, i)) {
					int nr = r + dir[i][0];
					int nc = c + dir[i][1];
					visited[nr][nc] = true;
					dfs(nr, nc, i);
					visited[nr][nc] = false;
				}
			}
			break;
		case 2: // 대각선
			for (int i = 0; i < 3; i++) {
				if (checkEmpty(r, c, i)) {
					int nr = r + dir[i][0];
					int nc = c + dir[i][1];
					visited[nr][nc] = true;
					dfs(nr, nc, i);
					visited[nr][nc] = false;
				}
			}
			break;

		}
	}

	private static boolean checkEmpty(int r, int c, int i) {
		int nr = r + dir[i][0];
		int nc = c + dir[i][1];
		
		if(nr<0 || nc<0 || nr>=N || nc>=N) return false;
		if(map[nr][nc] == 1) return false;
		if(visited[nr][nc]) return false;
		
		if(i == 2) {
			if(!checkEmpty(r, c, 0) || !checkEmpty(r, c, 1)) return false;
		}
		
		return true;
	}

}
/*
 * DFS N,N으로 보내는 방법의 개수
 */