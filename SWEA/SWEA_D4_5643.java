import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//SWEA D4 5643. [Professional] 키 순서
public class SWEA_D4_5643 {
	
	//이중 연결 리스트
	static class Node {
		int value;
		//나보다 큰 애들, 작은 애들
		List<Node> talls, shorts;
		public Node(int value, List<Node> talls, List<Node> shorts) {
			super();
			this.value = value;
			this.talls = talls;
			this.shorts = shorts;
		}
	}
	
	static int N, M;
	static Node[] nodeList;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			nodeList = new Node[N+1];
			
			for(int i=0; i<=N; i++) {
				nodeList[i] = new Node(i, new ArrayList<Node>(), new ArrayList<Node>());
			}
			
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int value = Integer.parseInt(st.nextToken());
				int tall = Integer.parseInt(st.nextToken());
				nodeList[value].talls.add(nodeList[tall]);
				nodeList[tall].shorts.add(nodeList[value]);
			}//----------input end-------------
			
			int ans = 0;
			
			for(int i=1; i<=N; i++) {
				//nodelist의 i번째를 위 아래로 탐색
				//부모의 수와 자식의 수를 누적
				//탐색 자체는 while문으로 q이용
				//방문할 때마다 visited에 체크
				int tallCnt = 0;
				int shortCnt = 0;
				visited = new boolean[N+1];
				Node target = nodeList[i];
				
				Queue<Node> q = new ArrayDeque<>();
				q.offer(target);
				visited[target.value] = true;
				
				//큰 거 체크하기
				while(!q.isEmpty()){
					Node cur = q.poll();
					if(cur.talls == null) continue;
					for(Node next : cur.talls) {
						if(visited[next.value]) continue;
						q.offer(next);
						visited[next.value] = true;
						tallCnt++;
					}
				}
				//작은 거 체크하기
				q.offer(target);
				while(!q.isEmpty()){
					Node cur = q.poll();
					if(cur.shorts == null) continue;
					for(Node next : cur.shorts) {
						if(visited[next.value]) continue;
						q.offer(next);
						visited[next.value] = true;
						shortCnt++;
					}
				}
				if(shortCnt+tallCnt+1 ==  N) ans++;
			}
			System.out.println("#"+t+" "+ans);
		}
	}
}
/* 자신의 키가 몇번째인지 알 수 있는 학생의 수
 * 알 수 있으려면... -> 자신의 앞 뒤로 몇명이 있는지 세고 그 수+1이 전체 인원수와 같아야?
 * 부모자식 관계? 
 * 2 - 4 - 3
 * 		 - 5 - 1
 * 6 - 4 - 3
 * 		 - 5 - 1
 * ----------------
 * tc
1
6
7
1 5
3 4
5 4
4 2
4 6
5 2
2 6

1
6
5
1 2
2 3
4 5
3 4
5 6
 */