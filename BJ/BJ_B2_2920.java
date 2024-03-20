import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_2920 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        boolean asc = true;
        boolean desc = true;
        for(int i=1; i<=8; i++){
            int note = Integer.parseInt(st.nextToken());
            if(note != i) asc = false;
            if(note != 9-i) desc = false;
        }

        String ans = "";
        if(!asc && !desc) ans = "mixed";
        else if(asc) ans = "ascending";
        else ans = "descending";

        System.out.println(ans);
    }
}
