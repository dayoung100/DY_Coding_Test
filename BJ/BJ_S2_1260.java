import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

//baekjoon S2 1260 DFS와 BFS
public class BJ_S2_1260 {
	
	static int N, M, V;
	static boolean[] visited;
	static ArrayList<Integer>[] nodes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		V = Integer.parseInt(str[2]);
		visited = new boolean[N+1];
		nodes = new ArrayList[N+1];
		for(int i=0; i<=N; i++) nodes[i] = new ArrayList<Integer>(); 
		int from, to;
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			from = Integer.parseInt(str[0]);
			to = Integer.parseInt(str[1]);
			nodes[from].add(to);
			nodes[to].add(from);
		}
		for(int i=0; i<=N; i++) Collections.sort(nodes[i]);
		
		dfs(V);
		System.out.println();
		bfs();
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		visited = new boolean[N+1];
		q.offer(V);
		visited[V] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			System.out.print(cur + " ");
			
			for(int i: nodes[cur]) {
				if(visited[i]) continue;
				q.offer(i);
				visited[i] = true;
			}
		}
	}

	private static void dfs(int start) {
		visited[start] = true;
		System.out.print(start+" ");
		for(int i : nodes[start]) if(!visited[i]) dfs(i);
	}
}
