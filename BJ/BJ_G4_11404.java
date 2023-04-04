import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//baekjoon G4 11404 플로이드
public class BJ_G4_11404 {

	static final int INF = 10000000;
	static int N, M;
	static int[][] dist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dist = new int[N][N];
		for(int i=0; i<N; i++) Arrays.fill(dist[i], INF);
//		for(int i=0; i<N; i++) dist[i][i] = 0;
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			dist[from-1][to-1] = Math.min(cost, dist[from-1][to-1]);
		}//--입력 완료--
		
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				if(i==k) continue;
				for(int j=0; j<N; j++) {
					if(i==j || j==k) continue;
					if(dist[i][j] > dist[i][k]+dist[k][j]) {
						dist[i][j] = dist[i][k]+dist[k][j];
					}
				}
			}
		}
		for(int i=0; i<N; i++) dist[i][i] = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(dist[i][j] >= INF) System.out.print(0+" ");
				else System.out.print(dist[i][j]+" ");
			}
			System.out.println();
		}
	}

}
/* 플로이드 워셜 이용
 */