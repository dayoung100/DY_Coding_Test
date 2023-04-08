import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

//swea 2383. [모의 SW 역량테스트] 점심 식사시간
public class SWEA_2383 {

	static int N, min; //맵 크기
	static int[][] map, stairs, ptos; //맵, 계단 관리 배열, 사람 별 계단 관리 배열
	static List<int[]> people; //사람 관리 list
	static List<Integer> s0, s1; //각각의 계단 인원 관리용

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			stairs = new int[2][3];
			people = new ArrayList<>();
			int sIdx=0;
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int input = Integer.parseInt(st.nextToken());
					map[i][j] = input;
					if(input==1) people.add(new int[] {i,j});
					else if(input!=0) {
						stairs[sIdx][0] = i;
						stairs[sIdx][1] = j;
						stairs[sIdx][2] = input;
						sIdx++;
					}
				}
			}//----------input end-----------
			ptos = new int[people.size()][2];
			min = Integer.MAX_VALUE;
			subset(0);
			System.out.println("#"+tc+" "+min);
		}
	}

	private static void subset(int cnt) {
		if(cnt == people.size()) {
			int[][] copyPtos = new int[people.size()][2];
			for(int i=0; i<ptos.length; i++) copyPtos[i][0] = ptos[i][0];
			//ptos에 사람별로 정해진 계단까지의 거리 계산해 저장
			for(int i=0; i<copyPtos.length; i++) {
				if(copyPtos[i][0] == 0) copyPtos[i][1] = calcDist(people.get(i), stairs[0]);
				else copyPtos[i][1] = calcDist(people.get(i), stairs[1]);
			}
			simul(copyPtos);
			return;
		}
		ptos[cnt][0] = 0; //첫번째 계단
		subset(cnt+1);
		ptos[cnt][0] = 1; //두번째 계단
		subset(cnt+1);
	}
	
	//계단 내려가는 시뮬레이션(min값 갱신도)
	private static void simul(int[][] copyPtos) {
		//ptos를 시간별로 정렬
		Arrays.sort(copyPtos, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
		});
		//길이 최대 3으로 관리할 계단들의 리스트
		s0 = new ArrayList<>();
		s1 = new ArrayList<>();
		int time0 = 0, time1 = 0;
		for(int i=0; i<copyPtos.length; i++) {
			int sNum = copyPtos[i][0];
			int time = sNum==0 ? time0 : time1;
			List<Integer> targetS = sNum==0 ? s0 : s1;
			if(time < copyPtos[i][1]+1) {
				int diff = copyPtos[i][1]+1 - time;
				time =  copyPtos[i][1]+1; //i가 계단을 내려갈 시간이 되도록
				updateS(sNum, diff);//시간이 추가로 흘렀으므로 계단상태 갱신
			}
			if(targetS.size() < 3) targetS.add(0);
			else {
				int diff = stairs[sNum][2] - targetS.get(0);
				time += diff;
				updateS(sNum, diff);
				targetS.add(0);
			}
			if(sNum==0) time0 = time;
			else time1 = time;
		}
		time0 += s0.size() > 0 ? stairs[0][2] - s0.get(s0.size()-1) : 0;
		time1 += s1.size() > 0 ? stairs[1][2] - s1.get(s1.size()-1) : 0;
		min = Math.min(min, Math.max(time0, time1));
	}
	
	/**
	 * 시간이 흐름에 따라 특정 계단 리스트를 갱신
	 * @param sNum : 계단 리스트의 인덱스
	 * @param diff : 흐른 시간
	 */
	private static void updateS(int sNum, int diff) {
		List<Integer> sList = sNum==0 ? s0 : s1;
		for(int i=0; i<sList.size(); i++) {
			sList.set(i, sList.get(i)+diff);
			if(sList.get(i) >= stairs[sNum][2]) {
				sList.remove(i);
				i--;
			}
		}
	}

	//계단과의 거리 계산
	private static int calcDist(int[] person, int[] stair) {
		return Math.abs(person[0] - stair[0]) + Math.abs(person[1] - stair[1]);
	}
}
/* 총 시간 = 계단 입구까지 이동 시간 + 계단 내려가는 시간
 * 계단 입구까지 이동 시간 = r좌표의 차 + c 좌표의 차
 * 계단 내려가는 시간 = 내려가기 시작만 하면 K
 * !!! 계단 위에는 동시에 최대 3명까지만 있을 수 있음!!!
 * 계단의 입구는 반드시 2개
 * 최소 시간을 출력
 * ----------------------------------
 * 어느 사람이 어느 계단으로 향할 것인가(이분) -> 부분집합(재귀)
 * -> 사람 별 계단이 결정 되면 그 계단 입구까지 걸리는 시간을 계산(단순계산)
 * -> 사람 별 계단과 도착 시간이 결정되면 -> 도착하는 순서대로 계단에 올림
 * 계단에 올라가는 상황을 컨트롤할 메서드 필요: simul()
 * -------------------------------
 * map 필요
 * 사람 저장할 배열 : 2차원(r,c)
 * 계단 배열 : (r, c, k)를 갖고 있는 배열 -> 크기 2x3
 * 사람 별 계단 관리 배열 : 2차원, 인덱스로 사람정보와 조인, 
 * 	- [0]: 계단의 종류. 부분집합을 뽑을 때 계단배열의 인덱스를 여기다가 넣음
 * 	- [1]: 계단까지의 소요시간.
 * -----------------------------------
1
5
0 0 1 1 0
0 0 1 0 2
0 0 0 1 0
0 1 1 0 0
1 1 3 1 0
 */