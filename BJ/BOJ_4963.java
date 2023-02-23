import java.util.Scanner;

//baekjoon 4963 섬의 개수
public class BOJ_4963 {
	
	static int W, H, ans;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[][] map;
	static boolean[][] selected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] endKey = {0,0};
		int[] wh;
		while(true) {
			W = sc.nextInt();
			H = sc.nextInt();
			if(W == endKey[0] && H == endKey[1]) break;
			
			ans = 0;
			map = new int[H][W];
			selected = new boolean[H][W];
			for(int i=0; i<H; i++) for(int j=0; j<W; j++) map[i][j] = sc.nextInt();
			//--지도 정보 하나 입력 완
			
			for(int i=0; i<H; i++) for(int j=0; j<W; j++) {
				if(map[i][j] == 1  && !selected[i][j]) {
					selected[i][j] = true;
					ans++;
					search(i, j);
				}
			}
			System.out.println(ans);
		}
	}

	//dfs?
	private static void search(int r, int c) {
		for(int i=0; i<8; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(nr<0 || nr>=H || nc<0 || nc>=W) continue;
			if(map[nr][nc] == 1 && !selected[nr][nc]) {
				selected[nr][nc] = true;
				search(nr, nc);
			}
		}
	}
}