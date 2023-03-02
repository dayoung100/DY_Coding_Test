import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//SWEA 5656. [모의 SW 역량테스트] 벽돌 깨기
public class SWEA_5656 {

	static int N, W, H;
	static int sum, max, cnt;
	static int boomNum;
	static int[][] map, tempmap, boomMap;
	static int[] dr = {-1, 0, 1, 0}; //우, 하, 좌
	static int[] dc = {0, 1, 0, -1};
	//--중복순열용--
	static int[] numbers;
	static ArrayList<int[]> permlist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			max = 0;
			cnt = 0;
			map = new int[H][W];
			permlist = new ArrayList<>();
			for(int h=0; h<H; h++) {
				st = new StringTokenizer(br.readLine());
				for(int w=0; w<W; w++) {
					int block = Integer.parseInt(st.nextToken());
					map[h][w] = block;
					if(block != 0) cnt++;
				}
			}//--입력 완료--
			
			numbers = new int[N];
			
			perm(0); //permlist갱신
			
			for(int[] arr : permlist) {
				tempmap = new int[H][W];
				for(int i=0; i<H; i++) {
					tempmap[i] = Arrays.copyOf(map[i], map[i].length);
				}
				sum = 0;
				
				for(int c=0; c<N; c++) {
					boomMap = new int[H][W];
					boomNum = 0;
					int boomRow = -1; //명중위치
					int boomArea = 0; //폭파 범위
					for(int r=0; r<H; r++) {
						if(tempmap[r][arr[c]] != 0) {
							boomRow = r;
							boomArea = tempmap[r][arr[c]];
							break;
						}
					}
					if(boomRow == -1) continue;
					simul(boomRow, arr[c], boomArea); //boomNum계산
					sum += boomNum;
					boom(); //정해진 위치 터뜨리기
				}
				max = Math.max(max, sum);
			}
			
			System.out.println("#"+tc+" "+(cnt-max));
		}
	}
	
	static void perm(int cnt) {
		if(cnt == N) {
			int[] newPerm = Arrays.copyOf(numbers, numbers.length);
			permlist.add(newPerm);
			return;
		}
		for(int i=0; i<W; i++) {
			numbers[cnt] = i;
			perm(cnt+1);
		}
	}
	
	//낙하 위치를 받아서 깰 수 있는 영역을 계산, 총 깨지는 블럭의 수를 리턴
	//tempmap갱신 해줘야(0&1)
	private static void simul(int row, int col, int area) {
		
		boomMap[row][col] = 1; //자기 위치는 무조건 터짐
		boomNum++;
		
		if(area == 1) return;
		
		for(int a=1; a<area; a++) {
			for(int d=0; d<4; d++) {
				int nr = row+dr[d]*a;
				int nc = col+dc[d]*a;
				if(nr<0 || nc<0 || nr>=H || nc>=W) continue;
				if(boomMap[nr][nc] != 1 && tempmap[nr][nc] != 0) {
					simul(nr, nc, tempmap[nr][nc]);
				}
			}
			
		}
	}

	//터뜨릴 위치를 받아서 터뜨리고 내리기
	private static void boom() {
		for(int i=0; i<H; i++) for(int j=0; j<W; j++) {
			if(boomMap[i][j] == 1) tempmap[i][j] = 0;
		}
		down(); //빈공간 내려주기
	}

	//빈 공간 내려주기
	private static void down() {
		for(int i=0; i<W; i++) {
			int floor = H-1;
			boolean flag = false;
			for(int j=H-1; j>=0; j--) {
				if(tempmap[j][i] == 0) {
					flag = true;
					continue;
				}
				if(flag) {
					int temp = tempmap[j][i];
					tempmap[j][i] = tempmap[floor][i];
					tempmap[floor][i] = temp;
				}
				floor--;
			}
		}
	}
}
/* 
 * n개의 벽돌을 어느 위치에서 어떤 순서로 떨어뜨리느냐 -> 순열
 * -> 그리디로 그때 그때 가장 많이 터뜨릴 수 있는 경우를 찾아 던지기?
 * 
 * 모든 위치에서 예상되는 깨지는 벽돌의 수를 계산
 * -> 그 중 최대값이 되는 위치에서 떨어뜨리기
 * 1. 명중시 범위만큼 제거하는 함수(연쇄폭발을 처리해야함->재귀?)
 * 2. 빈공간이 있으면 내려주는 함수
 */ 
/* testcase
1
3 10 10
0 0 0 0 0 0 0 0 0 0
1 0 1 0 1 0 0 0 0 0
1 0 3 0 1 1 0 0 0 1
1 1 1 0 1 2 0 0 0 9
1 1 4 0 1 1 0 0 1 1
1 1 4 1 1 1 2 1 1 1
1 1 5 1 1 1 1 2 1 1
1 1 6 1 1 1 1 1 2 1
1 1 1 1 1 1 1 1 1 5
1 1 7 1 1 1 1 1 1 1

1
2 9 10
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
1 1 0 0 1 0 0 0 0
1 1 0 1 1 1 0 1 0
1 1 0 1 1 1 0 1 0
1 1 1 1 1 1 1 1 0
1 1 3 1 6 1 1 1 1
1 1 1 1 1 1 1 1 1
 */