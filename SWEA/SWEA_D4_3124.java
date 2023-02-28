import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//SWEA D4 3124 최소 스패닝 트리
public class SWEA_D4_3124 {
	
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
	
	static int V, E;
	static Edge[] edgelist;
	static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edgelist = new Edge[E];
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				edgelist[i] 
						= new Edge(Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()));
			} //--입력완료--
			
			Arrays.sort(edgelist);
			make_set();
			
			long ans=0, cnt=0;
			for(Edge e : edgelist) {
				if(union(e.from, e.to)) {
					ans += e.weight;
					if(++cnt == V-1) break;
				}
			}
			
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void make_set() {
		parents = new int[V+1];
		for(int i=1; i<=V; i++) parents[i] = i;
	}
	
	static int find_set(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find_set(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find_set(a);
		int bRoot = find_set(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
}