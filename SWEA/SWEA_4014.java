import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA 4014. [모의 SW 역량테스트] 활주로 건설
public class SWEA_4014 {
	
	static int N, X; //입력
	static int[][] map;
	static int length, cnt, before; //조건 체크를 위한 공유변수
	static boolean able;
	static boolean[] slide; //경사로 위치 체크(겹치지 않도록)

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}//--------------input end-----------------
			int ans = 0;
			
			//가로줄 방향으로 체크
			for(int r=0; r<N; r++) {
				before = map[r][0]; //비교를 위한 직전의 높이값
				cnt = 1; //cnt:높이가 연속된 횟수
				length = 0; //length:내리막일때 X만큼 둘 수 있는가?체크용
				able = true;
				slide = new boolean[N];
				for(int c=1; c<N; c++) {
					if(!check(r, c, r)) break;
				}
				//한 줄을 다 체크함
				if(able && length == 0) ans++;
			}
			
			//세로줄 방향으로 체크
			for(int c=0; c<N; c++) {
				before = map[0][c]; //비교를 위한 직전의 높이값
				cnt = 1; //cnt:높이가 연속된 횟수,
				length = 0; //length:내리막일때 X만큼 둘 수 있는가?체크용
				able = true;
				slide = new boolean[N];
				for(int r=1; r<N; r++) {
					if(!check(r, c, c)) break;
				}
				//한 줄을 다 체크함
				if(able && length == 0) ans++;
			}
			
			System.out.println("#"+t+" "+ans);
		}
	}

	private static boolean check(int r, int c, int axis) {
		//높이값이 유지됨
		if(map[r][c] == before) cnt++;
		//높이값이 변경됨
		else {
			//내리막을 깔려면 아직 높이가 바뀌면 안됨 -> 설치 불가
			if(length > 0) {
				able = false;
				return false;
			}
			//높이차가 1이 넘음 -> 경사로 설치 불가능 -> 그 줄은 전부 패스
			if(Math.abs(map[r][c]-before) > 1) {
				able = false;
				return false;
			}
			//높이 차가 1임 + 새로운 값이  더 큼 -> 올라가는 방향으로 경사로 설치
			else if(map[r][c] > before) {
				if(cnt < X) {
					able = false;
					return false; //전값이 연속된 횟수가 X보다 작음 -> 설치 불가
				}
				//오르막을 놔야하는데 이미 내리막 경사로가 설치되어있다면
				//즉 직전 X칸 이내에 slide를 놨는지 확인
				if(axis == r) {
					for(int i=c-1; i>c-1-X; i--) {
						if(!slide[i]) continue;
						able = false;
						return false;
					}
				}else { //axis == c
					for(int i=r-1; i>r-1-X; i--) {
						if(!slide[i]) continue;
						able = false;
						return false;
					}
				}
			//높이 차가 1임 + 새로운 값이 더 작음 -> 내려가는 방향으로 경사로 설치
			} else length = X;
			
			//높이 값이 변경됐으니까 갱신
			before = map[r][c];
			cnt = 1;
		}
		if(length > 0) {
			//내려가는 것과 올라가는 경사로가 겹칠 수 있으므로 체크 필요
			if(axis == r) slide[c] = true;
			else slide[r] = true;
			length--;
		}
		return true;
	}
}
/* 활주로는 가로 세로 일직선으로 건설 가능
 * 총 몇 줄을 건설 가능한가?
 * --------------------------
 * 경사로의 높이는 1 고정, 길이는 주어짐(X)
 * 맵은 최대 20x20, 지형의 높이는 1~6사이
 * ---------------------------
 * 경사로 설치가 가능하다 = 높이 차가 1이고, 길이 만큼의 공간이 확보 가능하다 = 높이차가1인 칸이 길이X만큼 유지된다
 * ---------------------------
 * 조건 1. 높이차가 1인가?(무조건 1씩만 나야함)
 * 조건 2. 높이가 변경된 후에 X만큼 그 높이가 유지되는가?(경사로 길이 확보)
 * -> 그냥 행반복, 열반복 돌며 가능여부 탐색? -> 최대 40회
 * ---------------------------
 * 높이가 올라가는 경우도, 내려가는 경우도 있음
 * -> 무조건 그 칸이 연속되는 횟수cnt를 세고, 올라가는 경우는 cnt>X인지, 내려가는 경우는 새로운 cnt>X인지 체크
 * 테케
1
6 2
3 3 3 2 1 1
3 3 3 2 2 1
3 3 3 3 3 2
2 2 3 2 2 2
2 2 3 2 2 2
2 2 2 2 2 2

1
20 3
3 3 3 2 2 2 2 3 3 3 4 4 4 4 4 4 5 5 5 5 
3 3 3 2 2 2 2 3 3 3 4 4 4 4 4 4 5 5 5 5 
5 3 3 2 2 2 2 2 3 3 4 4 4 4 5 5 5 5 5 5 
4 3 3 1 1 1 1 1 2 3 4 4 4 5 5 5 5 5 5 5 
4 2 2 1 1 1 1 1 2 3 4 5 5 5 5 5 5 5 5 5 
4 3 3 2 2 2 2 1 2 3 4 5 5 5 5 5 5 5 5 5 
4 4 4 4 4 3 3 2 3 4 5 5 5 5 5 5 5 5 5 5 
4 3 3 3 3 3 3 3 4 4 4 5 5 5 5 5 5 4 4 4 
4 3 3 3 3 3 3 3 4 4 4 5 5 5 5 5 5 4 4 4 
4 3 3 3 3 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 
3 3 3 3 3 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 
3 3 3 3 3 4 4 4 4 4 4 4 5 5 5 5 5 5 5 5 
3 3 3 3 4 4 4 4 5 5 5 5 5 5 5 5 5 5 5 5 
4 4 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 4 4 4 
4 4 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 4 4 4 
5 5 5 5 5 5 5 5 5 5 5 5 4 4 4 4 4 4 4 4 
5 5 5 5 5 5 5 5 5 5 5 5 3 3 3 3 4 4 4 4 
5 5 5 5 5 5 5 5 5 5 5 5 3 3 2 2 3 3 4 4 
5 5 5 5 5 5 5 5 5 5 5 5 3 3 2 2 3 3 4 4 
5 5 5 5 5 5 5 5 4 4 4 4 3 3 3 3 4 4 4 4 
 */