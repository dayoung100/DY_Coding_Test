import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16926 {
	
	static int[][] tb, tb2;
	static int N, M, R;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		tb = new int[N][M];
		tb2 = new int[N][M];
		for(int x=0; x<N; x++) {
			for(int y=0; y<M; y++) {
				tb[x][y] = sc.nextInt();
			}
		}
		
		//내부 루프
		int loop = (Math.min(N, M))/2;
		
		for(int i=0; i<loop; i++) {
			int len = R % (((N-i*2)+(M-i*2))*2 - 4);
			//회전시켜주는 메서드
			//tb의 값을 직접 변경
			//인자: 회전횟수 len, 몇번째 네모인지 i
			turn(len, i);
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(tb2[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void turn(int length, int lnum) {
		Queue<Integer> q = new LinkedList<>();
		for(int i=lnum; i<M-lnum-1; i++) q.offer(tb[lnum][i]);
		for(int i=lnum; i<N-lnum-1; i++) q.offer(tb[i][M-lnum-1]);
		for(int i=M-lnum-1; i>lnum; i--) q.offer(tb[N-lnum-1][i]);
		for(int i=N-lnum-1; i>lnum; i--) q.offer(tb[i][lnum]);
		
		for(int i=0; i<length; i++) q.offer(q.poll());
		
		for(int i=lnum; i<M-lnum-1; i++) tb2[lnum][i] = q.poll();
		for(int i=lnum; i<N-lnum-1; i++) tb2[i][M-lnum-1] = q.poll();
		for(int i=M-lnum-1; i>lnum; i--) tb2[N-lnum-1][i] = q.poll();
		for(int i=N-lnum-1; i>lnum; i--) tb2[i][lnum] = q.poll();
	}

}