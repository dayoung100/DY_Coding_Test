import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BJ G4 4485 녹색 옷 입은 애가 젤다지?
class Node implements Comparable<Node>{
    int i, j, cost;
    public Node(int i, int j, int cost){
        this.i = i;
        this.j = j;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost -  o.cost;
    }
}
public class App {
    static int N;
    static int[][] board, visited;
    static int minCost;
    static int[] di = new int[] {-1,1,0,0};
    static int[] dj = new int[] {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int pNo = 1;
        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0) break;
            minCost = Integer.MAX_VALUE;
            board = new int[N][N];
            visited = new int[N][N]; //0 or 1
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) board[i][j] = Integer.parseInt(st.nextToken());
            }//-----테케 하나의 입력 종료-----
            solution();
            sb.append("Problem "+pNo+": "+minCost+"\n");
            pNo += 1;
        }
        System.out.println(sb);
        br.close();
        
    }

    //dijkstra
    public static void solution(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0,0,board[0][0]));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            //N,N에 도착하면 멈춤
            if(cur.i == N-1 && cur.j == N-1) {
                minCost = cur.cost;
                break;
            }
            if(visited[cur.i][cur.j] == 1) continue; //이미 방문한 노드는 pass
            visited[cur.i][cur.j] = 1; //뽑은 노드 = 가장 가까운 거리로 확정 => 방문체크
            for(int d=0; d<4; d++){
                int ni = cur.i+di[d];
                int nj = cur.j+dj[d];
                if(ni<0 || nj<0 || ni>=N || nj>=N) continue;
                if(visited[ni][nj] == 1) continue;
                pq.offer(new Node(ni, nj, cur.cost+board[ni][nj]));
            }
        }
    }
}
/* 
 * 총 NxN 크기의 동굴, 0,0에서 n-1, n-1까지 이동해야함
 * 잃는 금액을 최소로 할 때 그 금액은?
 * 모든 숫자는 0이상 9이하 한 자리수
 * ---------------------
 * DFS -> 시간초과
 * 다익스트라 with pq -> OK!
 */