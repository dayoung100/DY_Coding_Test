import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class App {
    static int N, L, R;
    static int[][] map;
    static int[][] markedMap;
    static int[][] visited;
    static int[] average;
    static ArrayDeque<int[]> q;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }//-----입력 완료-----
        int ans = 0;
        while(true){
            //0.초기화
            visited = new int[N][N];
            markedMap = new int[N][N];
            average = new int[N*N+1];
            int cnt = 0; //인구이동 횟수(종료체크용)
            //1.국경선 열기
            int no = 1; //연합 구분용
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(visited[i][j] == 1) continue; 
                    cnt += bfs(i, j, no);
                    no++;
                }
            }
            //1-1. 종료 조건 체크
            if(cnt == 0) break;
            //2. 평균으로 세팅
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    map[i][j] = average[markedMap[i][j]];
                }
            }
            //ans증가
            ans++;
        }
        sb.append(ans);
        System.out.println(sb);
        br.close();
    }

    public static int bfs(int i, int j, int no){
        //초기화
        int flag = 0;
        q = new ArrayDeque<>();
        //bfs
        q.offer(new int[]{i, j});
        visited[i][j]=1;
        int peopleNum = 0;
        int sum = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            markedMap[cur[0]][cur[1]] = no;
            peopleNum += 1;
            sum += map[cur[0]][cur[1]];
            //사방 탐색
            for(int d=0; d<4; d++){
                int nx = cur[0]+dx[d];
                int ny = cur[1]+dy[d];
                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                if(visited[nx][ny]==1) continue;
                //차이 계산
                int diff = Math.abs(map[nx][ny] - map[cur[0]][cur[1]]);
                if(diff < L || diff > R) continue;
                q.offer(new int[] {nx, ny});
                visited[nx][ny] = 1;
                flag++; //q.offer가 발생->이동횟수up
            }
        }
        average[no] = sum/peopleNum;
        return flag;
    }
}
/* 
 * 인구수 저장한 2차원 배열 A
 * [인구 이동]
 * - 인구수의 차이가 L이상 R이하면 국경선 오픈
 * - 국경선 다 열면 연합 구성
 * - 연합 인원을 평균(소수점버림)내서 조절하고 국경선 닫음
 * 인구이동이 며칠동안 발생하는가?
 * --------------------------
 * 일단 2차원 배열에 저장
 * 국경선 열기: BFS + visited / 구역-평균 저장용 배열(최대N^2)
 * -> 그 칸에 1,2,3등 구역 넘버기입 -> 인원수의 합, 칸수의 합 계산 -> 평균(n^2)
 * -> 1에 평균, 2에 평균... 채워넣기(n^2)
 * -> 반복횟수 카운트 -> 답
 * 매 과정마다 n^2을 두번씩...? 50*50=2500
 * -> 최대 2000일-> 2500*2000 -> 5,000,000
 */