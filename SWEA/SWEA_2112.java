import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//swea 2112. [모의 SW 역량테스트] 보호 필름
public class SWEA_2112 {
	
	static int D, W, K, ans;
	static int[][] map;
	static int[] visited; //0-방문하지 않음, 1-A를 투입, 2-B를 투입
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			ans = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			visited = new int[D];
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) map[i][j] = Integer.parseInt(st.nextToken());
			} //----------input end--------------
			
			if(checkK(map)) { //처음부터 기준을 통과할 수 있다면 0을 리턴
				System.out.println("#"+t+" "+0);
			} else {
				subset(0, 0); //부분집합 생성
				System.out.println("#"+t+" "+ans);
			}
		}
	}

	//모두 K의 합격 기준을 통과하느냐?
	//동일한 특성의 셀들이 연속적으로 K개 이상 있는가?
	private static boolean checkK(int[][] targetMap) {
		for(int w=0; w<W; w++) {
			int cnt = 1; //연속되는 횟수
			int max = 0; //cnt들 중 최대값
			int before = targetMap[0][w]; //직전 값(비교 기준값)
			for(int d=1; d<D; d++) {
				if(targetMap[d][w] == before) cnt++;
				else {
					max = Math.max(max, cnt);
					cnt = 1;
					before = targetMap[d][w];
				}
			}
			max = Math.max(max, cnt);
			if(max < K) return false;
		}
		return true;
	}

	//부분집합을 생성, visited로 투약종류 구분
	private static void subset(int cnt, int injCnt) {
		if(cnt == D) {
			int[] vCopy = Arrays.copyOf(visited, D);
			inject(vCopy, injCnt); //약품투입
			return;
		}
		//투약횟수가 아무리 많아도 K번임
		//연달아 같은 약을 K번 넣으면 기준 통과하니까
		//ans가 결정났는데 그보다 크면 부분집합 구하는 의미x
		if(injCnt > K || injCnt >= ans) {
			return;
		}
		
		visited[cnt] = 0; //방문하지 않음
		subset(cnt+1, injCnt);
		visited[cnt] = 1; //A를 투입
		subset(cnt+1, injCnt+1);
		visited[cnt] = 2; //B를 투입
		subset(cnt+1, injCnt+1);
	}

	//약품 투입
	private static void inject(int[] visit, int cnt) {
		int[][] copyMap = new int[D][W];
		for(int i=0; i<D; i++) copyMap[i] = Arrays.copyOf(map[i], W);
		for(int i=0; i<D; i++) {
			if(visit[i] == 0) continue;
			Arrays.fill(copyMap[i], visit[i]-1);//A 또는 B를 주입
		}
		if(checkK(copyMap)) ans = Math.min(ans, cnt);
	}
}
/* 옆으로 붙이는게 W, 위로 쌓는게 두께 D
 * 합격 기준 K : 모든 세로줄에 같은 특성이 연달아 K개 이상 존재해야
 * 약품: 가로줄로 투입, 투입 시 A, 또는 B로 그 가로줄을 일괄 변경
 * K를 통과하려면 약품을 최소 몇번 투입해야하는가?(투입 안하고 통과할 수도 있음)
 * --------------------------
 * [메서드]
 * 합격 기준을 통과할 수 있는지 체크하는 메서드
 * 약품을 넣었을 때 A나 B로 변경하는 메서드
 * 부분집합을 만드는 메서드
 * ---------------------------
 * 어디에 약품을 투입하는가 -> 부분집합
 * 부분집합의 원소 개수가 적은 것부터 하나하나 넣어보면서 -> 합격기준 통과하는지 체크?
 * 부분집합으로 투입할 위치 고르면 -> 그 위치에 A와 B중에서 무엇을 넣을지 선택해야
 * ----------------
 */