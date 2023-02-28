import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

//SWEA D4 1238. [S/W 문제해결 기본] 10일차 - Contact
public class SWEA_D4_1238 {
	
	static int L, S, ans; //길이, 시작점, 답
	static ArrayList<Integer>[] callgraph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tc=1; tc<=10; tc++) {
			String[] str = br.readLine().split(" ");
			L = Integer.parseInt(str[0]);
			S = Integer.parseInt(str[1]);
			String[] str2 = br.readLine().split(" ");
			callgraph = new ArrayList[101];
			for(int i=1; i<=100; i++) callgraph[i] = new ArrayList<Integer>();
			for(int i=0; i<L; i+=2) {
				int from = Integer.parseInt(str2[i]);
				int to = Integer.parseInt(str2[i+1]);
				callgraph[from].add(to);
			}//--입력 완료--
			
			bfs();
			System.out.println("#"+tc+" "+ans);
		}
	
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		q.offer(S);
		visited[S] = true;
		
		int current = 0;
		while(!q.isEmpty()) {
			int max = 0;
			for(int i=0, size = q.size(); i<size; i++) {
				current = q.poll();
				
				max = Math.max(max, current);
				
				for(int n : callgraph[current]) {
					if(visited[n]) continue;
					q.offer(n);
					visited[n] = true;
				}
			}
			ans = max;
		}
	}
}