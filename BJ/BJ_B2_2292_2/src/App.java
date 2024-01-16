import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ B2 2292 벌집
public class App {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        //-----입력 완료-----
        int cnt = 0;
        int cutLine = 1;
        while(true){
            if(N <= cutLine + 6*cnt) break;
            cutLine = cutLine + 6*cnt;
            cnt = cnt+1;
        }
        sb.append(cnt+1);
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 1 - 1
 * 2,3,4,5,6,7 - 2
 * 8,9,10,11,12,13,14,15,16,17,18,19 - 3
 * N <= 1,000,000,000
 * -------------------------
 * 1 -> 1+6 -> 1+6+12 -> 1+6+12+18 -> ...
 * 1+ (6*n까지의 6의 배수의 합)
 * N까지 일단 반복해보기(N번 다 걸리지 않으니까)
 */