import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B3_2884{
    static int hour, min;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        hour = Integer.parseInt(st.nextToken());
        min = Integer.parseInt(st.nextToken());
        int ansH = hour;
        int ansM = min - 45;
        if(ansM < 0){
            ansH -= 1;
            if(ansH<0) ansH = 24 + ansH;
            ansM = 60 + ansM;
        }
        System.out.println(ansH + " " + ansM);
    }
}