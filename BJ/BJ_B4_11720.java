import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B4_11720{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        char[] arr = st.nextToken().toCharArray();
        int sum = 0;
        for(int n=0; n<N; n++){
            int temp = arr[n] - '0';
            sum += temp;
        }
        System.out.println(sum);
    }
}