import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//SWEA D4 1249. [S/W 문제해결 응용] 4일차 - 보급로 
public class SWEA_1249 {
	
	static class Node implements Comparable<Node>{
		int r, c, weight;

		public Node(int r, int c, int weight) {
			super();
			this.r = r;
			this.c = c;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final int INF = Integer.MAX_VALUE;
	static int N, ans;
	static int[][] map, dist;
	static boolean[][] visited;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			ans = INF;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dist = new int[N][N];
			for(int i=0; i<N; i++) Arrays.fill(dist[i], INF);
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = line.charAt(j)-48;
				}
			} //--입력완료
			
			dijk();
			System.out.println("#"+tc+" "+dist[N-1][N-1]);
		}
	}

	private static void dijk() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[0][0] = 0;
		pq.offer(new Node(0, 0, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for(int d=0; d<4; d++) { //뽑은 것의 사방을 탐색 -> dist를 갱신
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
				if(dist[cur.r][cur.c]+map[nr][nc] < dist[nr][nc]) {
					dist[nr][nc] = dist[cur.r][cur.c]+map[nr][nc];
					pq.offer(new Node(nr, nc, dist[nr][nc]));
				}
			}
		}
	}

}