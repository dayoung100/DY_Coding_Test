import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//baekjoon B1 2775 부녀회장이 될테야
public class BJ_B1_2775_2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t=0; t<tc; t++) {			
			int K = Integer.parseInt(br.readLine());
			int N = Integer.parseInt(br.readLine());
			int[][] apart = new int[K+1][N+1];
			for(int i=0; i<=N; i++) apart[0][i] = i;
			for(int i=1; i<=K; i++) {
				for(int j=1; j<=N; j++) {
					int sum = 0;
					for(int k=1; k<=j; k++) {
						sum += apart[i-1][k];
					}
					apart[i][j] = sum;
				}
			}
			System.out.println(apart[K][N]);
		}
		
	}	
}
/* a층의 b호 : a-1층의 1호부터 b호까지의 합만큼 사람이 있어야
 * k층의 n호에는 몇명이 살고 있는가?
 * 아파트: 0층부터 시작, 1호부터 시작, 0층의 i호에는 i명이 산다
 * -------------------
 * 0층-1호:1명-2호:2명-3호:3명-4호:4명-... 1,2,3,4,5..
 * 1층-1호:1명-2호:3명-3호:6명-4호:10명-...1,3,6,10,15...
 * 2층-1호:1명-2호:4명-3호:10명-4호:20명-...1,4,10,20,35...
 * 3층-1호:1명-2호:5명-3호:15명-4호:35명-...1,5,15,35,70...
 * +1+1+1+1 / +2+3+4+5 / +3+6+10+15 / +4+10+20+35
 * k층의n호인원수=F(k, n)
 * =F(k-1,1)+F(k-1,2)+...+F(k-1,n)
 * 
 */