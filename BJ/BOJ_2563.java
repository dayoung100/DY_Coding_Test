import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_2563 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[][] paper = new int[n][2];
		
		for(int i=0; i<n; i++) {
			paper[i][0] = sc.nextInt();
			paper[i][1] = sc.nextInt();
		}
		
		int[][] board = new int[100][100];
		
		int sum = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				for(int k=0; k<n; k++) {
					if(paper[k][0] <= i && i < paper[k][0]+10
							&& paper[k][1] <= j && j < paper[k][1]+10) {
						sum++;
						board[i][j] = 1;
						break;
					}
				}
			}
		}
		System.out.println(sum);
	}
}