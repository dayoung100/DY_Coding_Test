import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//baekjoon G3 17135 캐슬 디펜스
public class BJ_G3_17135_2 {

	static int N, M, D, ans, totalEnemy;
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		map = new int[N][M];
		for(int i=0; i<N; i++) for(int j=0; j<M; j++) {
			int input = sc.nextInt();
			map[i][j] = input;
			if(input == 1) totalEnemy++;
		}
		
		//comb
		for(int i=0; i<M-2; i++) {
			for(int j=i+1; j<M-1; j++) {
				for(int k=j+1; k<M; k++) {
					int[] archerPos = {i, j, k};
					
					ans = Math.max(ans, playGame(archerPos));
				}
			}
		}
		System.out.println(ans);
	}

	private static int playGame(int[] archerPos) {
		int[][] copyMap = new int[N][M];
		for(int p=0; p<N; p++) copyMap[p] = Arrays.copyOf(map[p], M);
		
		int killNum = 0;
		int enemyNum = totalEnemy;
		while(enemyNum > 0) {
			
			List<int[]> targets = new ArrayList<>();
			for(int a=0; a<3; a++) { //궁수번호
				int[] targetPos = findTarget(copyMap, archerPos[a]);
				if(targetPos == null) continue;
				
				boolean contains = false;
				for(int[] pos:targets) {
					if(pos[0] == targetPos[0] && pos[1] == targetPos[1]) {
						contains = true;
						continue;
					}
				}
				if(!contains) targets.add(targetPos);
			}
			killNum += targets.size();
			enemyNum -= targets.size();
			for(int[] pos : targets) copyMap[pos[0]][pos[1]] = 0;
				
			//down
			for(int i=N-1; i>=0; i--) for(int j=0; j<M; j++) {
				if(i == N-1 && copyMap[i][j] == 1) enemyNum--;
				
				if(i == 0) copyMap[i][j] = 0;
				else copyMap[i][j] = copyMap[i-1][j];
			}
		}
		return killNum;
	}


	private static int[] findTarget(int[][] copyMap, int archer) {
		int minDist = Integer.MAX_VALUE;
		int[] pos = {-1, -1};
		for(int i=0; i<N; i++) for(int j=0; j<M; j++) {
			if(copyMap[i][j] == 0) continue;
			
			int dist = (N - i) + Math.abs(archer - j);
			if(dist > D || dist > minDist) continue;
			else if(dist < minDist) {
				minDist = dist;
				pos[0] = i;
				pos[1] = j;
			}
			else if(pos[1] > j) {
				pos[0] = i;
				pos[1] = j;
			}
		}
		if(pos[0] == -1 && pos[1] == -1) return null;
		return pos;
	}
}
/* NxM 크기의 격자판(0은 빈칸 1은 적)
 * D: 궁수의 사거리
 * N+1번째 행에는 성이 있음
 * 궁수: 3명
 * 공격: 모든 궁수는 동시에 공격,
 *      거리가 D이하인 적 중에서 가장 가까운, 가장 왼쪽에 있는 적)
 * 이동: 아래로 한칸 이동, 성에 닿으면 삭제
 * 
 * 궁수의 위치에 따라 제거할 수 있는 적의 수 달라짐 -> 조합
 * 출력: 제거할 수 있는 적의 최대 수
 */