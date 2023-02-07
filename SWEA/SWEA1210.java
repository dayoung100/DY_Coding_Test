import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA1210 {
	
	static int X;
	static int Y;
	static int[][] board;

	public static void main(String[] args) throws FileNotFoundException {
		
		int T =10;
		Scanner sc = new Scanner(System.in);
		for (int t = 1; t <= T; t++) {
			
			int num = sc.nextInt();
			int ans = -1;
			board = new int[100][100];
			
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			//find start point in last line(x,y)
			X = 99;
			Y = -1;
			for(int k=0; k<100; k++) {
				if(board[99][k] == 2) {
					Y = k;
					break;
				}
			}
			//System.out.println("Y: "+Y);
			//from start point
			//move left, right, up
			//return end point index in first line
			move(X, Y);
			
			System.out.println("#"+t+" "+Y);
			
		}

	}

	private static void move(int x, int y) {
		//현위치에서 움직일 수 있는 방향을 서치
		//해당 방향으로 이동
		if(y != 0 && board[x][y-1] == 1) {
			Y = y-1;
			board[x][y] = 0;
			move(x, y-1);
		}
		else if(y != 99 && board[x][y+1] == 1) {
			Y = y+1;
			board[x][y] = 0;
			move(x, y+1);
		}
		else if(x != 0 && board[x-1][y] == 1) move(x-1, y);
		else return;
	}

}