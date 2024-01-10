import java.io.BufferedReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

//BJ G5 13549 숨바꼭질 3
public class App{
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2){
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0]-o2[0];
        }
    }); //(시간, 위치)
    static boolean[] visited = new boolean[100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //-----------입력 완료------------
        solution();
        System.out.println(sb);
        br.close();
    }
    
    public static void solution(){
        pq.offer(new int[] {0, N});//초기 수빈이의 위치
        visited[N] = true; //방문체크
        
        outer:
        while(!pq.isEmpty()){
            int[] cur = pq.poll(); //현재 시간, 위치
            //동생의 위치에 도달했는가? -> 종료
            if(cur[1] == M) {
                sb.append(cur[0]);
                break outer;
            }
            
            if(cur[1]*2 <= 100000 && !visited[cur[1]*2]){
                pq.offer(new int[] {cur[0], cur[1]*2});
                visited[cur[1]*2] = true;
            }
            if(cur[1]-1 >= 0 && !visited[cur[1]-1]){
                pq.offer(new int[] {cur[0]+1, cur[1]-1});
                visited[cur[1]-1] = true;
            }
            if(cur[1]+1 <= 100000 && !visited[cur[1]+1]){
                pq.offer(new int[] {cur[0]+1, cur[1]+1});
                visited[cur[1]+1] = true;
            }
        }
    }
}
/* 
 * 수빈: 점 N에 위치 / 동생: 점 K에 위치
 * 수빈이가 동생을 찾을 수 있는 가장 빠른 시간(bfs)
 * 수빈이가 할 수 있는 행동
 * - 걷기1: 1초 후 X에서 X-1로 이동
 * - 걷기2: 1초 후 X에서 X+1로 이동
 * - 순간이동: 0초후 X에서 2X로 이동
 * -------------------
 * 최단거리 -> bfs -> 큐
 * 0초와 1초 차이가 있음 -> (시간, 위치) 형태로 우선순위 큐에
 */