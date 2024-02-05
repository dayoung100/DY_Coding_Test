import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BJ S1 20922 겹치는 건 싫어
public class App {
    static int N, K;
    static int[] list; //수열
    static int ans = 0;
    static int[] count = new int[100001]; //원소는 10만 이하
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) list[n] = Integer.parseInt(st.nextToken());
        //-----입력 완료-----
        int s = 0, e = 0; //start, end
        while(e<N){
            while(e<N && count[list[e]] + 1 <= K){
                count[list[e]]+=1;
                e+=1;
            }
            int length = e - s;
            ans = Math.max(ans, length);
            count[list[s]] -= 1;
            s+=1;
        }
        sb.append(ans);
        System.out.println(sb);
        br.close();
    }
}
/* 
 * N: 수열의 길이(20만 이하)
 * K: 같은 정수가 K개 이하로 포함됨(100이하)
 * 수열의 원소는 10만 이하
 * 최장 연속 부분수열의 길이를 구해라
 * 연속 부분 수열: 그 수열의 원소를 하나 이상 연속하여 선택한 부분 수열
 * ------------------------------------
 * 부분수열.. 투포인터.. 뭐더라...
 */