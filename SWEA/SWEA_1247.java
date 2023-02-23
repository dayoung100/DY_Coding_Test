import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//swea 1247. [S/W 문제해결 응용] 3일차 - 최적 경로
public class SWEA_1247 {
	
	static int N, min; //고객의 수, 최단거리
	static int[] start, end, numbers; //회사, 집 위치, 순열만들때 쓸 임시배열
	static boolean[] selected;
	static int[][] points; //경유지들 위치
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			min = Integer.MAX_VALUE;
			points = new int[N][];
			numbers = new int[N];
			selected = new boolean[N];
			
			String[] str = br.readLine().split(" ");
			
			start = new int[] {Integer.parseInt(str[0]), Integer.parseInt(str[1])};
			end = new int[] {Integer.parseInt(str[2]), Integer.parseInt(str[3])};
			
			int cnt = 0;
			for(int i=4; i<str.length; i++) {
				if(i%2 == 0) {
					points[cnt] = new int[2]; 
					points[cnt][0] = Integer.parseInt(str[i]);
				}else{
					points[cnt++][1] = Integer.parseInt(str[i]);
				}
			}
			//입력 완료
			
			perm(0, 0, 0);
			System.out.println("#"+tc+" "+min);
		}
	}

	private static void perm(int cnt, int lIdx, int dist) {
		if(cnt == N) {
			int tdist = dist + calc(lIdx, 0, cnt);
			min = Math.min(min, tdist);
			return;
		}
		for(int i=0; i<N; i++) {
			if(selected[i]) continue;
			if(dist > min) continue;
			selected[i] = true;
			numbers[cnt] = i; //points의 index
			int tdist = dist + calc(lIdx, i, cnt);
			perm(cnt+1, i, tdist);
			selected[i] = false;
		}
	}

	private static int calc(int lIdx, int nIdx, int cnt) {
		int lx = points[lIdx][0];
		int ly = points[lIdx][1];
		int nx = points[nIdx][0];
		int ny = points[nIdx][1];
		
		if(cnt == 0) { // start -> 첫방문
			lx = start[0];
			ly = start[1];
		}
		if(cnt == N) { // start -> 첫방문
			nx = end[0];
			ny = end[1];
		}
		
		return Math.abs(lx - nx) + Math.abs(ly - ny);
	}
}