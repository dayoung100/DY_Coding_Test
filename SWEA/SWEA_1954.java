import java.util.Arrays;
import java.util.Scanner;

public class SWEA_1954 {
	static int[][] arr;
	static int[] dirX = {0, 1, 0, -1};
	static int[] dirY = {1, 0, -1, 0};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++)
		{
			int num = sc.nextInt();
			arr = new int[num][num];
			System.out.println("#"+tc);
			snail(1, num, num, 0, 0, 0);
			
			for(int m=0; m<num; m++) {
				for(int n=0; n<num; n++) {
					System.out.print(arr[m][n]+" ");
				}
				System.out.println();
			}
		}                                                                                                          
	}
	
	private static void snail(int startNum, int num, int cnt, int x, int y, int idx) {
		if(startNum>num*num) return;
		for(int i=0; i<cnt; i++) {
			arr[x+dirX[idx%4]*i][y+dirY[idx%4]*i] = startNum+i;
		}
		
		int next_idx = idx+1;
		int next_x = x+dirX[idx%4]*(cnt-1) + dirX[next_idx%4];
		int next_y = y+dirY[idx%4]*(cnt-1) + dirY[next_idx%4];
		int next_cnt = num - (int)(Math.ceil((double)next_idx / 2));
		snail(startNum+cnt, num, next_cnt, next_x, next_y, next_idx);
		
	}
}