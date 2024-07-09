import java.util.*;
import java.io.*;

public class BJ_B4_28453{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++){
            int m = Integer.parseInt(st.nextToken());
            if(m>=300) sb.append(1+" ");
            else if(m>=275) sb.append(2+" ");
            else if(m>=250) sb.append(3+" ");
            else sb.append(4+" ");
        }
        System.out.println(sb.toString());
        br.close();
    }
}