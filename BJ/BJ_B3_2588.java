import java.io.*;
import java.util.*;

public class BJ_B3_2588 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int one = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int two = Integer.parseInt(st.nextToken());
        //-----input end-----
        System.out.println(one*(two%10));
        System.out.println(one*(two/10%10));
        System.out.println(one*(two/100));
        System.out.println(one*two);
    }
}
