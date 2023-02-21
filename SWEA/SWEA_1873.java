import java.util.Scanner;

//SWEA 1873. 상호의 배틀필드
public class SWEA_1873 {

	static char[] elem = {'.', '*', '#', '-', '^', 'v', '<', '>'};
	static char[][] map;
	static char[] order;
	static int H, W, tankR, tankC;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			H = sc.nextInt();
			W = sc.nextInt();
			map = new char[H][W];
			tankR = -1;
			tankC = -1; 
			sc.nextLine();
			
			for(int i=0; i<H; i++) {
				String str = sc.nextLine();
				for(int j=0; j<W; j++) {
					char temp = str.charAt(j);
					map[i][j] = temp;
					//맵 전체에서 전차의 위치를 찾아 저장, 관리
					for(int k=4; k<8; k++) {
						if(temp == elem[k]) {
							tankR = i;
							tankC = j;
						}
					}
				}
			}
			
			int n = sc.nextInt();
			order = new char[n];
			sc.nextLine();
			String str2 = sc.nextLine();
			for(int i=0; i<n; i++) order[i] = str2.charAt(i);
			//-----입력 완료-----
			
			for(int i=0; i<n; i++) {
				switch(order[i]) {
				case 'U': move(0); break;
				case 'D': move(1); break;
				case 'L': move(2); break;
				case 'R': move(3); break;
				case 'S': shoot(); break;
				}
			}
			System.out.print("#"+tc+" ");
			printMap();
		}
	}

	private static void printMap() {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void shoot() {
		int head_dir = Integer.MAX_VALUE;
		switch(map[tankR][tankC]) {
		case '^': head_dir = 0; break;
		case 'v': head_dir = 1; break;
		case '<': head_dir = 2; break;
		case '>': head_dir = 3; break;
		}
		
		int nr = tankR + dir[head_dir][0];
		int nc = tankC + dir[head_dir][1];
		
		while(nr >= 0 && nr < H && nc >= 0 && nc < W) {
			if(map[nr][nc] == '#') break;
			if(map[nr][nc] == '*') {
				map[nr][nc] = '.';
				break;
			}
			nr += dir[head_dir][0];
			nc += dir[head_dir][1];
		}
	}

	private static void move(int orderNum) {
		char tank_head = ' ';
		
		switch(orderNum) {
		case 0: //up
			tank_head = '^';
			break;
		case 1: //down
			tank_head = 'v'; 
			break;
		case 2: //left
			tank_head = '<'; 
			break;
		case 3: //right
			tank_head = '>'; 
			break;
		}
		
		map[tankR][tankC] = tank_head;
		
		int nr = tankR + dir[orderNum][0];
		int nc = tankC + dir[orderNum][1];
		
		if(nr < 0 || nr >= H
				|| nc < 0 || nc >= W) return;
		
		if(map[nr][nc] == '.') {
			map[tankR][tankC] = '.';
			map[nr][nc] = tank_head;
			tankR = nr;
			tankC = nc;
		}
	}

}