import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA 2105. [모의 SW 역량테스트] 디저트 카페
public class SWEA_2105 {

	static int N, max; //맵 크기 NxN, 먹을 수 있는 디저트 수의 최대값
	static int[][] map;
	static int[] dessert;
	static int[] dr = {-1, 1, 1, -1};//우상단, 우하단, 좌하단, 좌상단
	static int[] dc = {1, 1, -1, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			max = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			int maxNum = 0;
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int input = Integer.parseInt(st.nextToken());
					map[i][j] = input;
					maxNum = Math.max(maxNum, input); //디저트 최대 숫자
				}
			}//------------input end------------
			for(int i=0; i<N; i++) for(int j=0; j<N; j++) {
				dessert = new int[maxNum+1];
				//처음위치로 돌아와야해서 방문체크, 디저트체크는 하지 않음
				dfs(i, j, new int[] {i, j}, 0, 0);
			}
			if(max == 0) System.out.println("#"+tc+" -1");
			else System.out.println("#"+tc+" "+max);
		}
	}
	
	/**
	 * dfs탐색
	 * @param r 		: row
	 * @param c			: col
	 * @param start		: 시작지점(r,c가 처음으로 돌아오면 종료)
	 * @param turnInfo	: 직전의 회전 정보(0우상단, 1우하단, 2좌하단, 3좌상단)
	 * @param turnCnt	: 회전 횟수(4번째 넘어가면 종료)
	 */
	private static void dfs(int r, int c, int[] start, int turnInfo, int turnCnt) {
		//기저조건(사이클 완성)
		if(r==start[0] && c==start[1] && turnCnt==4) {
			dessert[map[r][c]]++;
			int cnt = 0;
			for(int i=1; i<dessert.length; i++) if(dessert[i] > 0) cnt++;
			max = Math.max(max, cnt);
			dessert[map[r][c]]--;
			return;
		}
		//백트래킹
		if(turnCnt > 4) return;
		
		for(int d=0; d<2; d++) {
			int nr = r+dr[(turnInfo+d)%4];
			int nc = c+dc[(turnInfo+d)%4];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue; //범위바깥
			if(dessert[map[nr][nc]] > 0) continue; //이미 먹은 디저트
			dessert[map[nr][nc]]++;
			if(d==0) dfs(nr, nc, start, turnInfo, turnCnt); //같은 방향
			else dfs(nr, nc, start, (turnInfo+d)%4, turnCnt+1); //다른 방향(회전)
			dessert[map[nr][nc]]--;
		}
	}
}
/* map에 디져트의 종류(번호)가 입력으로 들어옴
 * 대각선으로만 갈 수 있음, 사각형 모양을 그리며 출발한 카페로 돌아와야함
 * 루트 중에 같은 숫자의 디저트를 먹으면 안됨
 * 디저트를 되도록 많이 먹으려할 때, 그  때의 디저트 수를 print
 * 먹을 수 없는 경우 -1을 출력
 * -------------------------
 * dfs탐색
 * 첫 점으로 돌아오면 그 루트 탐색을 종료(완성), 회전횟수가 3을 넘으면 종료(미완성)
 * 루트를 진행하면서 그 지점에 먹은 디저트 리스트를 갖고 돌음 -> 1~100의 배열, 인덱스위치를 조회해 중복 확인
 * 탐색이 종료되고 사이클이 완성되면 디저트 리스트의 크기의 max값을 계산
 * -----------------
1
4
9 8 9 8
4 6 9 4
8 7 7 8
4 5 3 5

1
6
1 8 9 6 3 9
5 3 1 9 8 2
6 9 3 4 1 2
7 1 1 5 1 9
1 9 6 8 7 3
7 6 4 5 5 5
 */