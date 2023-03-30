import java.util.Scanner;

//baekjoon S2 10971 외판원 순회 2
public class BJ_S2_10971 {

	static int N, min;
	static int[][] weight;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		min = Integer.MAX_VALUE;
		N = sc.nextInt();
		weight = new int[N][N];
		for(int i=0; i<N; i++) for(int j=0; j<N; j++) weight[i][j] = sc.nextInt();
		
		for(int i=0; i<N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			dfs(i, i, 0, 0);
		}
		System.out.println(min);
	}
	
	//city:현재 방문한 도시 / start:방문 시작지점 / sum:가중치의 합 / cnt:밤문횟수
	private static void dfs(int city, int start, int sum, int cnt) {
		if(cnt == N-1) {
			if(weight[city][start] == 0) return;
			int finalSum = sum+weight[city][start];
			min = Math.min(min, finalSum);
			return;
		}
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			if(weight[city][i] == 0) continue;
			int expect = sum+weight[city][i];
			if(min < expect) continue;
			visited[i] = true;
			dfs(i, start, expect, cnt+1);
			visited[i] = false;
		}
	}
}