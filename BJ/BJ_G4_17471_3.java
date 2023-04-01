import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

//baekjoon G4 17471 게리맨더링
public class BJ_G4_17471_3 {
	
	static int N, ans;
	static int[] popul;
	static boolean[] visited;
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		ans = Integer.MAX_VALUE;
		popul = new int[N];
		visited = new boolean[N];
		map = new int[N][];
		for(int i=0; i<N; i++) popul[i] = sc.nextInt();
		for(int i=0; i<N; i++) {
			int conArea = sc.nextInt();
			map[i] = new int[conArea];
			for(int j=0; j<conArea; j++) map[i][j] = sc.nextInt()-1;
		}//--입력완료--
		subset(0);
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}

	private static void subset(int cnt) {
		if(cnt == N) {
			boolean[] copySubset = Arrays.copyOf(visited, N);
			if(bfs(copySubset, true) && bfs(copySubset, false)) {
				ans = Math.min(ans, calcDiff(copySubset));
			}
			return;
		}
		visited[cnt] = true;
		subset(cnt+1);
		visited[cnt] = false;
		subset(cnt+1);
	}

	private static int calcDiff(boolean[] copySubset) {
		int p1 = 0, p2 = 0;
		for(int i=0; i<N; i++) {
			if(copySubset[i] == true) p1+=popul[i];
			else p2+=popul[i];
		}
		return Math.abs(p1 - p2);
	}

	private static boolean bfs(boolean[] copySubset, boolean b) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] qVisited = new boolean[N];
		int originNum = 0;
		for(int i=0; i<N; i++) if(copySubset[i] == b) originNum++; 
		if(originNum == 0) return false;
		
		for(int i=0; i<N; i++) if(copySubset[i] == b) {
			q.offer(i);
			qVisited[i] = true;
			break;
		}
		int compareNum = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=0; i<map[cur].length; i++) {
				int next = map[cur][i];
				if(copySubset[next] != b) continue;
				if(qVisited[next]) continue;
				q.offer(next);
				qVisited[next] = true;
				compareNum++;
			}
		}
		if(compareNum == originNum) return true;
		return false;
	}
}
/* 일단 부분집합을 구함
 * 두개로 나누니까 그 부분집합이 선거구A인것, 나머지가 선거구B
 * 만든 뒤에 연결 정보 갖고 bfs탐색, A, B 모두에 대해서
 * 탐색 영역 수를 세서 부분집합의 크기와 비교
 * 다르면 불가능한 것
 * 가능한 경우의 수가 나오면 인구의 차이를 갱신
 */