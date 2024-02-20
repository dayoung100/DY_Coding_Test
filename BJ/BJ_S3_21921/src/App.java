import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ S3 21921 블로그
public class App {
    static int N, X;
    static int[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        visit = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) visit[n] = Integer.parseInt(st.nextToken());
        //----- 입력 완료 -----
        int sum = 0; //부분합 계산용
        //0번부터 시작할 때 미리 계산해두기
        for(int x=0; x<X; x++) sum += visit[x];
        int max = sum; //부분합의 최대값
        int cnt = 1; //기간 수 계산용
        for(int left=0, right=X; left<N-X; left++){
            //새로운 구간합 계산
            sum = sum-visit[left]+visit[right];
            if(sum > max){
                max = sum;
                cnt = 1;
            }
            else if(sum == max) cnt++;
            right++;
        }
        if(max == 0) sb.append("SAD");
        else sb.append(max+"\n"+cnt);
        System.out.println(sb);
        br.close();
    }
}
/* 
 * N: 블로그를 한 날 수, 25만 이하
 * X: 체크할 기간, 25만 이하
 * [출력]
 * X일 동안 가장 많이 들어온 방문자수
 * 최대 방문자 수가 0 -> SAD
 * 최대 방문자 수가 0이 아님 -> 최대 방문자 수를 달성한 기간이 몇개인가
 * -------------------------------
 * 부분합
 */