import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//SWEA 1953. [모의 SW 역량테스트] 탈주범 검거
public class SWEA_1953 {
	
	static int N, M, R, C, L, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = { //상하좌우 순
			{0,0,0,0}, {1,1,1,1}, {1, 1, 0, 0}, {0,0,1,1}, {1,0,0,1}, {0,1,0,1}, {0,1,1,0}, {1, 0, 1, 0}
	};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			ans = 0;
			N = sc.nextInt(); //세로
			M = sc.nextInt(); //가로
			R = sc.nextInt(); //시작 R
			C = sc.nextInt(); //시작 C
			L = sc.nextInt(); //흐른 시간
			
			map = new int[N][M];
			visited = new boolean[N][M];
			for(int i=0; i<N; i++) for(int j=0; j<M; j++) map[i][j] = sc.nextInt();
			//--입력완료--
			
			bfs();
			System.out.println("#"+tc+" "+ans);
		}
	}

	private static void bfs() {
		int cnt = 0;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {R, C});
		
		int lvl = 1;
		
		while(!q.isEmpty()) {
			if(lvl > L) break;
			for(int i=0, size = q.size(); i<size; i++) {
				int[] pos = q.poll();
				int r = pos[0];
				int c = pos[1];
				if(visited[r][c]) continue;
				visited[r][c] = true;
				cnt++;
				
				int[] move = dir[map[r][c]];
				int[] up = new int[] {r - move[0], c};
				int[] down = new int[] {r + move[1], c};
				int[] left = new int[] {r, c - move[2]};
				int[] right = new int[] {r, c + move[3]};
				
				
				if(check(r, c, up, "up")) q.offer(up);
				if(check(r, c, down, "down")) q.offer(down);
				if(check(r, c, left, "left")) q.offer(left);
				if(check(r, c, right, "right")) q.offer(right);
			}
			lvl++;
		}
		ans = cnt;
	}

	private static boolean check(int r, int c, int[] vector, String type) {
		//범위 체크
		if(vector[0]<0 || vector[1]<0 || vector[0]>=N || vector[1]>=M) return false;
		//안지나간 칸인가
		if(visited[vector[0]][vector[1]]) return false;
		//파이프가 연결되어 있는가1
		if(map[vector[0]][vector[1]] == 0) return false;
		//파이프가 연결되어 있는가2
		
		switch(type) {
		case "up":
			if(dir[map[vector[0]][vector[1]]][1] != 1) return false;
			break;
		case "down":
			if(dir[map[vector[0]][vector[1]]][0] != 1) return false;
			break;
		case "left":
			if(dir[map[vector[0]][vector[1]]][3] != 1) return false;
			break;
		case "right":
			if(dir[map[vector[0]][vector[1]]][2] != 1) return false;
			break;
		}
		
		return true;
	}

}
/* 시간당 1의 거리 움직임
 * 터널 7종류 -> 상하좌우 4방향
 * 이미 1의 시간이 흐른 뒤에 시작
 * 위치할 수 있는 장소의 개수 -> BFS 탐색
 * 체크해야할 사항
 *  - 범위 안인가
 *  - 아직 안 지나간 칸인가
 *  - 파이프가 연결되어 있는가 <<<<
 */