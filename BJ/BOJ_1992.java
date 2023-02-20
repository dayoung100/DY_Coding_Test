import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//baekjoon 1992 쿼드트리
public class BOJ_1992 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				board[i][j] = str.charAt(j)-'0';
			}
		}
		zip(0, 0, N);
		System.out.println(sb);
	}

	private static void zip(int r, int c, int size) {
		int sum = 0;
		for (int i = r, rEnd = r+size; i < rEnd; i++) {
			for (int j = c, cEnd = c+size; j < cEnd; j++) {
				sum += board[i][j];
			}
		}
		
		if(sum == size*size) { //black
			sb.append(1);
			return;
		}else if(sum == 0) { //white
			sb.append(0);
			return;
		}else { //mix
			sb.append("(");
			int half = size/2;//4분할 
			zip(r, c, half);
			zip(r, c+half, half);
			zip(r+half, c, half);
			zip(r+half, c+half, half);
		}
		sb.append(")");
	}
}