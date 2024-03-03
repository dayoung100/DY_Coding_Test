import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_1152 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        while(st.hasMoreTokens()) {
            st.nextToken();
            cnt+=1;
        }
        sb.append(cnt);
        System.out.println(sb);
        br.close();
    }
}