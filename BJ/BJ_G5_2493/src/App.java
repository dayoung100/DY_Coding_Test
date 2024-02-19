import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//BJ G5 2494 탑
public class App {
    static int N;
    static int[][] tower;
    static int[] ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        tower = new int[N][2];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) tower[n] = new int[] {Integer.parseInt(st.nextToken()), n}; //(높이, 인덱스)
        //-----입력 완료-----
        ans = new int[N];
        Stack<int[]> stack = new Stack<>();
        stack.push(tower[N-1]); //가장 끝의 탑 넣기
        for(int n=N-2; n>=0; n--){
            int[] cur = tower[n];
            //스택에 있는 값보다 새로 넣을 값이 작거나 같을 때
            //수신할 수 없음 -> 스택에 넣기
            //스택에 있는 값보다 새로 넣을 값이 큼
            //수신할 수 있음 -> 처리 후 스택에 넣기
            if(cur[0] > stack.peek()[0]){
                while(stack.size() > 0){
                    int[] top = stack.peek();
                    if(top[0] >= cur[0]) break;
                    int[] tmp = stack.pop();
                    ans[tmp[1]] = cur[1]+1;
                }
            }
            stack.push(cur);
        }
        for(int i=0; i<N; i++) sb.append(ans[i]+" ");
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 레이저: 왼 <- 오 방향으로 쏨, 땅과 수평 높이
 * 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는가?
 * 어느탑 => 탑의 인덱스+1를 출력
 * ----------------------------
 * 이중 포문 -> n*n... -> x
 * ---------------------------
 * 스택 -> 역순으로
 */