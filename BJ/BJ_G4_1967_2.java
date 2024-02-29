import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//BJ G4 1967 트리의 지름
//풀이2: 전체를 트리가 아니라 그래프로 보고 dfs로 최장거리 찾기
class Node {
    int num, weight;
    public Node(int num, int weight){
        this.num = num;
        this.weight = weight;
    }
}

public class BJ_G4_1967_2 {
    static int N;
    static ArrayList<Node>[] nodes;
    static boolean[] visited;
    static int max = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        nodes = new ArrayList[N+1];
        for(int n=0; n<=N; n++) nodes[n] = new ArrayList<>();
        for(int n=1; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodes[from].add(new Node(to, w)); //양방향
            nodes[to].add(new Node(from, w)); //양방향
        }//-----입력 완료-----
        //모든 정점에 대해 dfs
        for(int n=1; n<=N; n++){
            visited = new boolean[N+1];
            visited[n] = true;
            dfs(n, 0);
        }
        sb.append(max);
        System.out.println(sb);
        br.close();
    }

    public static void dfs(int start, int sum){
        for(Node n : nodes[start]){
            if(visited[n.num]) continue;
            visited[n.num] = true;
            dfs(n.num, sum+n.weight);
        }
        //반복문 끝나거나 안들어감 -> 더이상 갈 자식 노드가 없음
        max = Math.max(max, sum);
    }
}
