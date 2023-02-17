import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//정올 1733: 오목
//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1006&sca=2060
public class JO_1733 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] arr =  new int[19][19];
	static int[] dx = {0, 1, 1, -1}; 
	static int[] dy = {1, 1, 0, 1}; 
	static int[] rx = {0, -1, -1, 1}; 
	static int[] ry = {-1, -1, 0, -1}; 
	static int winColor;
	static int winCol, winRow;
	
	public static void main(String[] args) throws IOException {
		winColor = 0; 
		winRow = 0; 
		winCol = 0; 
		
		for(int i=0; i<19;i++) {
			String[] str = br.readLine().split(" ");
			for(int j = 0; j<19; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		for(int i=0; i<19;i++) {
			for(int j = 0; j<19; j++) {
				if(arr[i][j] == 0) continue;
				for(int k=0; k<4; k++) {
					find(i, j, 1, arr[i][j], k);
					if (winColor != 0) {
						winRow = i;
						winCol = j;
						break;
					}
					
				}
				if (winColor != 0) break;
			}
			if (winColor != 0) break;
		}
		
		if (winColor == 0) System.out.println(0);
		else {
			System.out.println(winColor);
			System.out.println(winRow + 1 + " " + (winCol + 1));
		}
	}
	
	public static void find(int row, int col, int cnt, int color, int dir) {
		if(cnt == 5) {
			int nxR = row + dx[dir];
			int nxC = col + dy[dir];
			if(!(nxR<0||19<=nxR||nxC<0||19<=nxC)) {
				if(arr[nxR][nxC] == color) return;
			}
			winColor = color;
			return;
		}
		if (cnt==1) {
			int nxR = row + rx[dir];
			int nxC = col + ry[dir];
			if(!(nxR<0||19<=nxR||nxC<0||19<=nxC)) {
				
				if(arr[nxR][nxC] == color) return;
			}
		}
		int nxR = row + dx[dir];
		int nxC = col + dy[dir];
		
		if(nxR<0||19<=nxR||nxC<0||19<=nxC) return;
		if(arr[nxR][nxC] != color) return;
		find(nxR, nxC, cnt + 1, color, dir);
	}
}