import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//baekjoon G4 2636 치즈
public class BJ_G4_2636 {

	static int R, C, t, remain, area;
	static int[][] map;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 세로의 길이
		C = Integer.parseInt(st.nextToken()); // 가로의 길이
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) area++; // 1이면 늘려서 초기 치즈의 수를 파악
				map[i][j] = input;
			}
		} // --입력 처리 완료--

		checkAirBfs(0, 0); // 바깥쪽을 돌면서 공기칸은 -1로 표시 -> 공기랑 구멍을 구분

		// area: 그 시간에 남아있는 영역의 수
		// remain:그 시간에 삭제처리 되고 남은 영역의 수
		remain = area;
		while (true) {
			if (remain <= 0) break;// 남은 치즈의 영역이 0이 되면 끝

			// 구멍이었다가 공기에 접하게 된 경우
			checkHoleToAir();

			area = remain;
			int deleteNum = 0;
			// 공기에 닿은 치즈 표시, 그 수를 카운트
			for (int i = 0; i < R; i++) for (int j = 0; j < C; j++) {
				int target = t + 1;
				if (map[i][j] == target) deleteNum += checkDeleteBfs(i, j, target);
			}
			// 삭제하기로 한 칸 일괄 삭제(-1로 변경)
			for (int i = 0; i < R; i++) for (int j = 0; j < C; j++) {
				if (map[i][j] == 'c') map[i][j] = -1;
			}
			t++;
			remain -= deleteNum;
		}
		System.out.println(t);
		System.out.println(area);
	}

	// 삭제할 부분을 bfs로 체크
	private static int checkDeleteBfs(int i, int j, int target) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { i, j });
		map[i][j] = map[i][j] + 1; // 방문체크
		int cnt = 0;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			boolean flag = false;
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
				if (map[nr][nc] == -1) {
					flag = true;
					continue;
				} else if (map[nr][nc] == target) {
					q.offer(new int[] { nr, nc });
					map[nr][nc] = map[nr][nc] + 1; // 방문체크
				}
			}
			if (flag) { // 공기랑 접해있으면
				map[cur[0]][cur[1]] = 'c';
				cnt++;
			}
		}
		return cnt;
	}

	// 구멍이었다가 공기에 접하게 된 경우
	private static void checkHoleToAir() {
		for (int i = 0; i < R; i++) for (int j = 0; j < C; j++) {
			if (map[i][j] == 0) {
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (nr < 0 || nc < 0 || nr >= R || nc >= C)
						continue;
					if (map[nr][nc] == -1) {
						map[i][j] = -1;
						checkAirBfs(i, j);
					}
				}
			}
		}
	}

	// 공기와 닿아있는 부분을 bfs로 체크
	private static void checkAirBfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { r, c });
		map[r][c] = -1; // 방문체크 겸

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (nr < 0 || nc < 0 || nr >= R || nc >= C)
					continue;
				if (map[nr][nc] == 0) {
					q.offer(new int[] { nr, nc });
					map[nr][nc] = -1; // 방문체크 겸
				}
			}
		}
	}
}

/*
 * dfs나 bfs로 바깥쪽을 돌면서 외곽을 -1로 체크 다시 돌면서 1을 만나면 사방 탐색 -> -1과 인접해있으면 체크 -> 1시간 뒤에 지워지도록 
 * -> 지우기로 한 영역 삭제(-1로 변경) 
 * -> 이때 남아있는 영역의 수를 저장(remain = remain-지운 개수)
 * 
 * ~ 한 시간(t)이 흐름 ~
 * 
 * 다시 돌면서 1을 만나면 사방 탐색 -> -1과 인접해있으면 체크 -> 1시간 뒤에 지워지도록 
 * -> 지우기로 한 영역 삭제(-1로 변경)
 * -> 이때 남아있는 영역의 수를 저장(remain = remain-지운 개수)
 * 
 * ~ 두 시간이 흐름 ~ 
 * ... 
 * 
 * remain을 계산 -> 0이 됨: 전부 지워짐 
 * 전부 지워졌을 때: t를 프린트하고, 그 전의remain(area)을 프린트
 * (area는 매 시간마다 remain으로 덮어쓰기)
 */