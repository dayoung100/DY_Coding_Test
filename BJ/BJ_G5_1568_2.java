import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//baekjoon G5 15686 치킨 배달
public class BJ_G5_1568_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, ans;
	static int[][] map;
	static List<int[]> house, chicken;
	static int[][] numbers;
	
	public static void main(String[] args) throws IOException {
		String[] str = br.readLine().split(" ");
		ans = Integer.MAX_VALUE;
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][N];
		numbers = new int[M][2];
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				int input = Integer.parseInt(str[j]);
				map[i][j] = input;
				if(input == 1) house.add(new int[] {i, j});
				else if(input == 2) chicken.add(new int[] {i, j});
			}
		}//--입력완료
		
		comb(0, 0);
		System.out.println(ans);
	}

	private static void comb(int cnt, int start) {
		if(cnt == M) {
			int chiSum = 0;
			for(int h=0; h<house.size(); h++) {
				int minDist = Integer.MAX_VALUE;
				for(int c=0; c<numbers.length; c++) {
					minDist = Math.min(minDist,
							Math.abs(house.get(h)[0] - numbers[c][0])
							+ Math.abs(house.get(h)[1] - numbers[c][1]));
				}
				chiSum += minDist;
			}
			ans = Math.min(ans, chiSum);
			return;
		}
		for(int i=start; i<chicken.size(); i++) {
			numbers[cnt] = chicken.get(i);
			comb(cnt+1, i+1);
		}
	}

}
/* 
도시 NxN
r과 c는 1부터 시작
치킨 거리: 집과 가장 가까운 치킨집 사이의 거리(집을 기준으로)
도시의 치킨거리: 모든 집의 치킨 거리의 합
거리: 두 좌표 사이의 차의 합
0:빈칸 / 1:집 / 2:치킨집
치킨집 중에서 M개를 고름, 도시의 치킨 거리가 가장 작도록 -> comb
출력: 치킨집 최대 M개를 골랐을 때 도시의 치킨 거리의 최솟값
*/