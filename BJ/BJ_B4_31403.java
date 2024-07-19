import java.io.*;
import java.util.*;

public class BJ_B4_31403 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        int A = Integer.parseInt(a);
        st = new StringTokenizer(br.readLine());
        String b = st.nextToken();
        int B = Integer.parseInt(b);
        st = new StringTokenizer(br.readLine());
        String c = st.nextToken();
        int C = Integer.parseInt(c);
        System.out.println(A+B-C);
        System.out.println(Integer.parseInt(a+b)-Integer.parseInt(c));
    }
}
