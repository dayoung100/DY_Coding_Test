import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

//baekjoon S2 18111 마인크래프트
public class BJ_S2_18111 {
	
	static class Status{
		int topHeight, topNum;
		int bottomHeight, bottomNum;
		int[][] statusMap;
		int time;
		
		public Status(int topHeight, int topNum, int bottomHeight, int bottomNum,
				int[][] statusMap, int time) {
			super();
			this.topHeight = topHeight;
			this.topNum = topNum;
			this.bottomHeight = bottomHeight;
			this.bottomNum = bottomNum;
			this.statusMap = statusMap;
			this.time = time;
		}
	}
	
	static int N, M, B;
	static int[][] map;
	static int ansTime, ansHeight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		N = Integer.parseInt(arr[0]);
		M = Integer.parseInt(arr[1]);
		B = Integer.parseInt(arr[2]);
		map = new int[N][M];
		int topH = -1, bottomH = 257;
		for(int i=0; i<N; i++) {
			String[] ar = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(ar[j]);
				if(topH < map[i][j]) topH = map[i][j];
				if(bottomH > map[i][j]) bottomH = map[i][j];
			}
		}
		//---map setting done---
		//topNum, bottomNum setting
		int topNum = setTopBottomNum(map, topH);
		int bottmNum = setTopBottomNum(map, bottomH);
		Status status = new Status(topH, topH, bottomH, bottomH, map, 0);
		bfs(status);
		System.out.println(ansTime+" "+ansHeight);
	}

	//map을 주면 맵에서 해당 높이를 가진 칸의 수를 반환
	private static int setTopBottomNum(int[][] map, int height) {
		
		return 0;
	}

	private static void bfs(Status status) {
		Queue<Status> q = new ArrayDeque<>();
		q.offer(status);
		
		while(!q.isEmpty()) {
			Status cur = q.poll();
			if(cur.topHeight == cur.bottomHeight) {
				ansTime = cur.time;
				ansHeight = cur.bottomHeight;
				break;
			}
			//답에 해당하는 경우 중에서도 가장 높은 높이를 찾아야함
			//답이 아닐경우 다음 액션을 큐에 넣기
			
			
		}
	}

}
/* 세로N, 가로M 크기의 땅, 왼쪽위가 0,0
 * 행동1. 그 땅의 블록을 제거해 인벤토리에 넣기(2초)
 * 행동2. 인벤토리에서 블록을 꺼내 그 땅 위에 쌓기(1초)
 * 인벤토리에 이미 B개의 블록이 있음
 * 땅의 높이를 모두 동일하게 만들기 위해 걸리는 최소 시간과 그 때의 높이(가장높은)
 * ---------------------
 * 각 상황의 맵의 상태, 가장 높은 땅의 높이, 가장 높은 땅의 수, 가장 낮은 땅의 높이, 가장 낮은 땅의 수, 걸린 시간
 * 이걸 노드로 해서 큐에 저장(bfs)
 * 가장 높은 땅의 높이와 가장 낮은 땅의 높이가 같아지면 시간을 저장,
 * 그 시간보다 오래 걸리는 노드는 패스
 */