import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2178 {
	
	static int N, M;
	static int min = Integer.MAX_VALUE;
	static int[][] board;
	static boolean[][] visited;
	static int[] dirR = {-1,1,0,0};
	static int[] dirC = {0,0,-1,1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		board = new int[N][M];
		visited = new boolean[N][M];
		sc.nextLine();
		for(int i=0; i<N; i++) {
			char[] line = sc.nextLine().toCharArray();
			for(int j=0; j<M; j++) {
				int num = line[j]-'0';
				board[i][j] = num;
			}
		}
		bfs();
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0,0});
		visited[0][0] = true;
		
		int[] current;
		int lvl = 0;
		loop:
		while(!q.isEmpty()) {
			lvl++;
			for(int i=0, size = q.size(); i<size; i++) {
				current = q.poll();
				
				if(current[0] == N-1 && current[1] == M-1) break loop;
				
				for(int d=0; d<4; d++) {
					int x = current[0]+dirR[d];
					int y = current[1]+dirC[d];
					
					if(x>=0 && x<N && y>=0 && y<M) {
						if(visited[x][y] == false && board[x][y] == 1) {
							visited[x][y] = true;
							q.offer(new int[] {x, y});
						}
					}
				}
			}
		}
		System.out.println(lvl);
	}

}