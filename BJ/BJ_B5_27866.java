import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B5_27866 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        String S = st.nextToken();
        st = new StringTokenizer(br.readLine());
        int i = Integer.parseInt(st.nextToken());
        System.out.println(S.charAt(i-1));
    }
}
