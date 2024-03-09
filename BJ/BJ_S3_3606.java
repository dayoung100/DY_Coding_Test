import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_S3_3606 {
    static int N, K;
    static ArrayList<Integer>[] nodeList;
    static boolean[] visited;
    static int sum = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //컴퓨터의 수
        nodeList = new ArrayList[N+1]; //컴퓨터 인접 정보(1~N번까지)
        visited = new boolean[N+1]; //방문체크용
        for(int n=0; n<=N; n++) nodeList[n] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); //컴퓨터 인접 간선 수
        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodeList[from].add(to);
            nodeList[to].add(from);
        }//-----입력 완료-----
        visited[1] = true; //1번 컴에서 시작
        dfs(1);
        System.out.println(sum);
        br.close();
    }
    private static void dfs(int node){
        for(int nextNode : nodeList[node]){
            if(visited[nextNode]) continue;
            visited[nextNode] = true;
            sum+=1;
            dfs(nextNode);
        }
    }
}
