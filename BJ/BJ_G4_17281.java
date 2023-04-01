import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//baekjoon G4 17281 ⚾
public class BJ_G4_17281 {

	static int N, max;
	static int[][] scoreMap;
	static int[] numbers = new int[9];
	static boolean[] visited = new boolean[9];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		scoreMap = new int[N][9];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) scoreMap[i][j] = Integer.parseInt(st.nextToken());
		} //--입력 완료--
		perm(0);
		System.out.println(max);
	}

	private static void perm(int cnt) {
		if(cnt == 9) {
			int[] turnFix = Arrays.copyOf(numbers, 9);
			max = Math.max(max, game(turnFix));
			return;
		}
		if(cnt == 3) { //1(0)번 타자를 위한 고정 자리
			numbers[cnt] = 0; //1번선수 넣음
			visited[0] = true;
			perm(cnt+1);
		} else {
			for(int i=1; i<9; i++) {
				if(visited[i]) continue;
				numbers[cnt] = i; //선수 번호 넣기
				visited[i] = true;
				perm(cnt+1);
				visited[i] = false;
			}
		}
	}

	//전달받은 순서로 N이닝까지 게임 진행해보기
	private static int game(int[] turnFix) {
		int sum = 0;
		int playerIdx = 0;
		for(int g=0; g<N; g++) {
			int outNum = 0;
			int[] Ru = new int[3]; //1루, 2루, 3루
			while(true) {
				if(outNum == 3) break;
				int player = turnFix[playerIdx];
				switch (scoreMap[g][player]) {
				case 1: //안타
					if(Ru[2] != 0) sum++;
					Ru[2] = Ru[1];
					Ru[1] = Ru[0];
					Ru[0] = player+1;
					break;
				case 2: //2루타
					if(Ru[2] != 0) sum++;
					if(Ru[1] != 0) sum++;
					Ru[2] = Ru[0];
					Ru[1] = player+1;
					Ru[0] = 0;
					break;
				case 3: //3루타
					if(Ru[2] != 0) sum++;
					if(Ru[1] != 0) sum++;
					if(Ru[0] != 0) sum++;
					Ru[2] = player+1;
					Ru[1] = 0;
					Ru[0] = 0;
					break;
				case 4: //홈런
					if(Ru[2] != 0) sum++;
					if(Ru[1] != 0) sum++;
					if(Ru[0] != 0) sum++;
					sum++;
					Arrays.fill(Ru, 0);
					break;

				default: //아웃
					outNum++;
					break;
				}
				playerIdx++;
				if(playerIdx == 9) playerIdx = 0;
			}
		}
		return sum;
	}

}
/* 팀은 9명, 총 N(2~50)이닝동안 게임을 진행, 한 이닝에 3아웃 발생시 종료
 * 타순은 1~9순서로 반복, 이닝이 변경되어도 순서를 유지
 * 0.아웃 / 1.안타 / 2.2루타 / 3.3루타 / 4.홈런
 * 1번 선수가 4번 타자로 고정: _ _ _ 1 _ _ _ _ _
 * 인풋은 각 이닝 별로 1~9번 선수의 결과
 * 가장 많은 득점 구하기
 * 
 * -> 순서를 구하기 -> 순열
 * 
 * Ru [1, 2, 3] 선수 4의 차례
 * 1루타 : [1, 2, 3] -> [4, 1, 2] sum++
 * 2루타 : [1, 2, 3] -> [0, 4, 1] sum += 2
 * 3루타 : [1, 2, 3] -> [0, 0, 4] sum += 3
 * 
 * 순열
 * 1 2 3 0 4 5 6 7 8
 * 2 3 4 1 5 6 7 8 9
 * ---------------------------
 * 3 2 1 4 0 4 3 2 1
 * 2 3 4 1 1 2 3 4 0
 * 1이닝 
 * 2.3루타([0,0,2]) -> 3.2루타([0,3,0], 1점) -> 4.1루타([4, 0, 3], 1점)
 * -> 1.홈런([0,0,0], 5점) -> 5.아웃([0,0,0], 5점 1아웃)
 * -> 6.홈런([0,0,0], 6점1아웃) -> 7.3루타([0,0,7], 6점1아웃)
 * -> 8.2루타([0,7,0], 7점 1아웃) -> 9.1루타([9,0,8], 7점 1아웃)
 * -> 2.3루타([0,0,2], 9점 1아웃) -> 3.2루타([0,3,0], 10점 1아웃)
 * -> 4.1루타([4,0,3], 10점 1아웃) -> 1.홈런([0,0,0], 13점1아웃)
 * -> 5.아웃([0,0,0], 13점 2아웃) -> 6.홈런([0,0,0], 14점 1아웃)
 * -> ...21점 3아웃
 */