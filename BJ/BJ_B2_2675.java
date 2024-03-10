import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_2675 {
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //테케수
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            char[] sArr = st.nextToken().toCharArray();
            for(char c: sArr){
                for(int r=0; r<R; r++) sb.append(c);
            } 
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
