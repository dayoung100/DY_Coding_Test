import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BJ G5 5972 택배 배송
class Edge implements Comparable<Edge>{
    int from, to, weight;
    public Edge(int from, int to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    @Override
    public int compareTo(Edge e){
        return Integer.compare(this.weight, e.weight);
    }
}

public class BJ_G5_5972 {
    static int N, M;
    static ArrayList<Edge>[] list;
    static int[] dist;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        for(int n=0; n<N+1; n++) list[n] = new ArrayList<Edge>();
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Edge(a, b, c)); //양방향
            list[b].add(new Edge(b, a, c)); //양방향
        }//----- 입력 완료 -----
        dijkstra();
        sb.append(dist[N]);
        System.out.println(sb);
        br.close();
    }

    public static void dijkstra(){
        boolean[] visited = new boolean[N+1];
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 1, 0)); //0번->1번인 가상 엣지

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(visited[cur.to]) continue; //방문했던 노드면 패스
            visited[cur.to] = true; //가중치 가장 낮은 노드를 방문

            //방문한 노드의 연결노드 살펴보기
            for(int i=0; i<list[cur.to].size(); i++){
                Edge next = list[cur.to].get(i); //연결노드
                if(visited[next.to]) continue;
                if(dist[next.to] > next.weight+dist[next.from]){
                    dist[next.to] = next.weight+dist[next.from];
                    pq.offer(new Edge(next.from, next.to, dist[next.to]));
                }
            }
        }
    }
}
/* 
 * 소에게 최소한의 여물을 주고 지나가기
 * N : 헛간의 수(1~50,000)-정점
 * M : 길의 수(1~50,000)-엣지
 * C : 소 마릿수(1~1000)-가중치
 * 정점A와 정점B가 하나 이상의 간선을 가질 수 있음
 * 정점1에서 정점N까지 이동
 * [출력] 최소 비용
 * ---------------------
 * 다익스트라
 */