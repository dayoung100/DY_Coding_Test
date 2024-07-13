import java.util.*;
import java.io.*;

//BJ B2 23027 1번부터 문제의 상태가…?
public class BJ_B2_23027{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String S = st.nextToken();
        
        if(S.contains("A")){
            String[] alpha = {"B", "C", "D", "F"};
            for(String s : alpha) S = S.replace(s, "A");
        }else if(S.contains("B")){
            String[] alpha = {"C", "D", "F"};
            for(String s : alpha) S = S.replace(s, "B");
        }else if(S.contains("C")){
            String[] alpha = {"D", "F"};
            for(String s : alpha) S = S.replace(s, "C");
        }else{
            S = S.replaceAll(".", "A");
        }

        System.out.println(S);        
    }
}