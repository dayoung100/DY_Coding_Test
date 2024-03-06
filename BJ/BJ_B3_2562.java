import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B3_2562 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int max = 0;
        int idx = -1;
        for(int i=0; i<9; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num > max) {
                max = num;
                idx = i+1;
            }
        }
        System.out.println(max);
        System.out.println(idx);
    }
}
