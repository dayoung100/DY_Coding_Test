import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_15686 {

	static int N, M, ans;
	static int[][] map;
	static int[][] surv;
	static LinkedList<Integer> hr = new LinkedList<>();
	static LinkedList<Integer> hc = new LinkedList<>();
	static LinkedList<Integer> cr = new LinkedList<>();
	static LinkedList<Integer> cc = new LinkedList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ans = Integer.MAX_VALUE;
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		surv = new int[M][2];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int input = sc.nextInt();
				if(input == 1) {
					hr.add(i);
					hc.add(j);
				}
				if(input == 2) {
					cr.add(i);
					cc.add(j);
				}
				map[i][j] = input;
			}
		}
		
		//전체 치킨집에서 m개를 고르는 조합
		comb(0,0);
		System.out.println(ans);
	}


	private static void comb(int cnt, int start) {
		if(cnt == M) {
			ans = Math.min(ans, calc(surv));
			return;
		}
		for(int i=start; i<cr.size(); i++) {
			surv[cnt][0] = cr.get(i);
			surv[cnt][1] = cc.get(i);
			comb(cnt+1, i+1);
		}
	}
	
	//도시의 치킨 거리 계산
	private static int calc(int[][] clist) {
		int sum = 0;
		for(int i=0; i<hr.size(); i++) {
			int distance = Integer.MAX_VALUE;
			for(int l=0; l<clist.length; l++) {
				distance = Math.min(distance,
						Math.abs(hr.get(i) - clist[l][0])
						+ Math.abs(hc.get(i) - clist[l][1]));
			}
			sum += distance;
		}
		
		return sum;
	}
}