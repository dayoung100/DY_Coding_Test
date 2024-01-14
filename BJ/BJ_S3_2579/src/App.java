import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ S3 2579 계단 오르기
public class App {
    static int N;
    static int maxScore = 0;
    static int[] stairs;
    static int[][] partScore; //연속여부, 계단 N개
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        stairs = new int[N+1];
        partScore = new int[2][N+1];
        for(int n=1; n<=N; n++){
            st = new StringTokenizer(br.readLine());
            stairs[n] = Integer.parseInt(st.nextToken()); //1부터 시작
        }//-----입력 완료-----
        dfs(0, 0, 0);
        sb.append(maxScore);
        System.out.println(sb);
        br.close();
    }

    public static void dfs(int score, int idx, int count){
        //기저조건
        if(idx == N) {
            maxScore = Math.max(maxScore, score+stairs[N]);
            return;
        }

        //처음 제외하고는 비용 비교 작업 진행
        if(count!=0){
            //더 점수를 많이 갖고 갈 수 있는 방법이 있었으면 탐색 멈춤
            if(partScore[count-1][idx] > score+stairs[idx]) return;
            //크거나 같다면 값 갱신
            partScore[count-1][idx] = score+stairs[idx];
        }

        if(count < 2) dfs(score+stairs[idx], idx+1, count+1); //한칸 오르기
        if(N-idx > 1) dfs(score+stairs[idx], idx+2, 1); //두칸 오르기
    }
}
/* [행동]
 * - 한계단만 오르기
 * - 두번째 계단으로 오르기
 * [조건]
 * 연속 3개의 계단 밟으면 안됨(시작점 제외)
 * 마지막 계단은 꼭 밟아야
 * 계단 300개 이하, 점수 계단당 1만점 이하
 * [목표]
 * 점수의 최댓값 구하기
 * ------------------------
 * dfs + dp
 * dp할 때 연속한 횟수도 나누어 따져야함(서로 다른 케이스니까)
 */