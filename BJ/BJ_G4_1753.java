import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//baekjoon G4 1753 최단경로
public class BJ_G4_1753 {
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int V, E, K;
	static ArrayList<Edge>[] edgeList;
	static int[] dist;
	static boolean[] visited;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		edgeList = new ArrayList[V+1];
		dist = new int[V+1];
		visited = new boolean[V+1];
		Arrays.fill(dist, INF);
		for(int i=0; i<=V; i++) edgeList[i] = new ArrayList<>();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[from].add(new Edge(from, to, weight));
			
		}//--입력 완료--
		
		dijk();
		for(int i=1; i<=V; i++) {
			if(dist[i] == INF) bw.write("INF"+"\n");
			else bw.write(dist[i]+"\n");
		}
		bw.flush();
		bw.close();
	}

	private static void dijk() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[0] = 0;
		dist[K] = 0;
		pq.offer(new Edge(0,K,0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(visited[cur.to]) continue;
			visited[cur.to] = true;
			
			for(Edge e: edgeList[cur.to]) {
				if(e.weight+dist[cur.to] < dist[e.to]) {
					dist[e.to] = e.weight+dist[cur.to];
					pq.offer(new Edge(e.from, e.to, dist[e.to]));
				}
			}
		}
	}
}