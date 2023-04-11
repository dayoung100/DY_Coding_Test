import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA 1952. [모의 SW 역량테스트] 수영장
public class SWEA_1952 {

	static int ans;
	static int[] cost, plan; //이용권 가격들, 이용 계획
	static int[] month; //각 달의 이용권 세팅
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost = new int[4];
			plan = new int[12];
			for (int i = 0; i < 4; i++) cost[i]=Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) plan[i]=Integer.parseInt(st.nextToken());
			//----------input end-----------
			month = new int[12];
			ans = Integer.MAX_VALUE;
			calc(0);
			ans = Math.min(ans, cost[3]);
			System.out.println("#"+tc+" "+ans);
		}
	}

	private static void calc(int cnt) {
		if(cnt >= 12) {
			int sum = 0;
			for(int i=0; i<12; i++) {
				if(month[i] == 1) sum += cost[0] * plan[i];
				else if(month[i] == 2) sum += cost[1];
				else if(month[i] == 3) sum += cost[2];
				//나머지 -> month[i] == 0인 경우 -> 3달 이용으로 계산할 것이 없는 달
			}
			ans = Math.min(ans, sum);
			return;
		}
		month[cnt] = 1; //1일
		calc(cnt+1);
		month[cnt] = 2; //1달
		calc(cnt+1);
		month[cnt] = 3; //3달
		calc(cnt+3);
		month[cnt] = 0; //초기화
	}

}
/* 1일, 1달, 3달, 1년 이용권
 * 가장 적은 비용 출력
 * -------------------
1
10 40 100 300
0 0 2 9 1 5 0 0 0 0 0 0

1
10 100 50 300
0 0 0 0 0 0 0 0 6 2 7 8
 */