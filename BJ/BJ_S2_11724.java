import java.util.*;
import java.io.*;

//BJ S2 11724 연결 요소의 개수
public class BJ_S2_11724 {
    private static int N, M;
    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;
    private static int cnt = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //정점
        M = Integer.parseInt(st.nextToken()); //간선
        graph = new ArrayList[N+1];
        for(int n=0; n<=N; n++) graph[n] = new ArrayList<Integer>();
        visited = new boolean[N+1];
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }//-----input end-----
        for(int n=1; n<=N; n++){
            if(visited[n]) continue;
            //이전에 방문한 적 없음 -> 연결요소의 시작이 됨
            cnt++;
            //그 노드를 시작으로 연결된 지점 방문
            dfs(n);
        }
        System.out.println(cnt);
    }

    static void dfs(int cur){
        visited[cur] = true;
        for(int num:graph[cur]){
            if(visited[num]) continue;
            dfs(num);
        }
        return;
    }
}
/* 
 * 연결요소: 간선 전부 연결 후 그래프가 총 몇개의 묶음으로 구성되느냐
 * 그래프 -> ArrayList<int>[] 형태
 * 방문체크하면서 연결된 거 쭉 탐색
 */