import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//baekjoon S1 2667 단지번호붙이기
public class BJ_S1_2667 {
	
	static int N, cnt;
	static int[][] map;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static List<Integer> houseNum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		houseNum = new ArrayList<>();
		houseNum.add(0);
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<N; j++) {
				map[i][j] = ch[j] - '0';
			}
		}//--입력완료--
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					cnt++;
					map[i][j] = 0;
					houseNum.add(1);
					dfs(i, j);
				}
			}
		}
		Collections.sort(houseNum);
		System.out.println(cnt);
		for(int n : houseNum) if(n!=0) System.out.println(n);
	}

	private static void dfs(int r, int c) {
		int nr, nc;
		for(int d=0; d<4;d++) {
			nr = r+dr[d];
			nc = c+dc[d];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			if(map[nr][nc] == 1) {
				map[nr][nc] = 0;
				houseNum.set(cnt, houseNum.get(cnt)+1);
				dfs(nr, nc);
			}
		}
	}
}
