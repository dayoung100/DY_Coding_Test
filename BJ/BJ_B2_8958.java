import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_8958 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            char[] arr = st.nextToken().toCharArray();
            int cnt = 0;
            int sum = 0;
            for(char c : arr){
                if(c == 'O') cnt+=1;
                else cnt = 0;
                sum += cnt;
            }
            sb.append(sum+"\n");
        }
        System.out.println(sb);
        br.close();
    }
}
