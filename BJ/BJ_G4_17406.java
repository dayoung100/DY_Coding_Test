import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//baekjoon G4 17406 배열 돌리기 4
public class BJ_G4_17406 {
	
	static int N, M, K, R, C, S, ans;
	static int[][] map, order;
	static int[] oNumbers; 
	static boolean[] oVisited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		map = new int[N+1][M+1];
		order = new int[K][3];
		oNumbers = new int[K];
		oVisited = new boolean[K];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) order[i][j] = Integer.parseInt(st.nextToken());
		}//--입력 완료--
		
		perm(0);
		System.out.println(ans);
	}

	private static void perm(int cnt) {
		if(cnt == K) {
			int[] oCopy = Arrays.copyOf(oNumbers, K);
			turn(oCopy);
			return;
		}
		for(int i=0; i<K; i++) {
			if(oVisited[i]) continue;
			oVisited[i] = true;
			oNumbers[cnt] = i;
			perm(cnt+1);
			oVisited[i] = false;
		}
	}

	private static void turn(int[] oCopy) {
		int[][] copyMap = new int[N+1][];
		for(int i=1; i<=N; i++) copyMap[i] = Arrays.copyOf(map[i], M+1);
		for(int i=0; i<K; i++) {
			int r = order[oCopy[i]][0];
			int c = order[oCopy[i]][1];
			int s = order[oCopy[i]][2];
			//TODO: copymap을 회전시키기
			for(int a=1; a<=s; a++) {
				Queue<Integer> q = new ArrayDeque<>();
				for(int b=c+a; b>=c-a+1; b--) q.offer(copyMap[r-a][b]);
				for(int b=r-a; b<=r+a-1; b++) q.offer(copyMap[b][c-a]);
				for(int b=c-a; b<=c+a-1; b++) q.offer(copyMap[r+a][b]);
				for(int b=r+a; b>=r-a+1; b--) q.offer(copyMap[b][c+a]);
				
				q.offer(q.poll());
				
				for(int b=c+a; b>=c-a+1; b--) copyMap[r-a][b] = q.poll();
				for(int b=r-a; b<=r+a-1; b++) copyMap[b][c-a] = q.poll();
				for(int b=c-a; b<=c+a-1; b++) copyMap[r+a][b] = q.poll();
				for(int b=r+a; b>=r-a+1; b--) copyMap[b][c+a] = q.poll();
			}
		}
		calcMin(copyMap);
	}

	private static void calcMin(int[][] copyMap) {
		for(int i=1; i<=N; i++) {
			int sum = 0;
			for(int j=1; j<=M; j++) sum += copyMap[i][j];
			ans = Math.min(ans, sum);
		}
	}

}
/* 회전 연산의 순서 -> 순열
 * 배열 돌리는 메서드
 * 배열 A의 값 계산하는 메서드
 */