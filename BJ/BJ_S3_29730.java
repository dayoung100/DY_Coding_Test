import java.util.*;
import java.io.*;

//BJ S3 29730 임스의 데일리 인증 스터디
public class BJ_S3_29730{
    private static int N;
    private static ArrayList<String> works = new ArrayList<>();
    private static ArrayList<String> bjLink = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            String string = br.readLine();
            if(string.startsWith("boj.kr/")) {
                String s = string.split("/")[1];
                int num = -1;
                try{
                    num = Integer.parseInt(s);
                }catch(Exception e){}
                if(num>=1 && num<=30000){
                    bjLink.add(string);
                    continue;
                }   
            }
            works.add(string);
        }//-----input end-----
        Collections.sort(works, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                if(s1.length() == s2.length()) return s1.compareTo(s2);
                return s1.length() - s2.length();
            }
        });
        Collections.sort(bjLink, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                int num1 = Integer.parseInt(s1.split("/")[1]);
                int num2 = Integer.parseInt(s2.split("/")[1]);
                return num1 - num2;
            }
        });
        for(String s:works) System.out.println(s);
        for(String s:bjLink) System.out.println(s);
    }
}