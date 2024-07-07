import java.util.*;
import java.io.*;

public class BJ_S1_1522 {
    private static String str;
    private static int min=Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        str = st.nextToken();
        
        int aCnt = 0, bCnt = 0;
        //a의 개수 세기
        for(int i=0; i<str.length(); i++) if(str.charAt(i) == 'a') aCnt++;

        //포인터
        int start = 0, end = aCnt-1;
        //b의 개수 세기
        for(int i=start; i<=end; i++) if(str.charAt(i) == 'b') bCnt++;
        min = Math.min(min, bCnt);
        
        //슬라이딩 윈도우
        for(start=0; start<str.length(); start++){
            end = (++end)%str.length(); 
            //start는 그대로, end만 포인터 갱신 후 b 개수 계산
            if(str.charAt(start) == 'b') bCnt--;
            if(str.charAt(end) == 'b') bCnt++;
            min = Math.min(min, bCnt);
        }

        System.out.println(min);
    }

    
}
/* a를 모두 연속으로 만드는데, 문자열이 원형이다
 * 슬라이딩 윈도우 + 투포인터
 * a를 모은다 => 윈도우 크기를 a의 개수만큼
 * 슬라이싱한 윈도우 안에 b의 개수가 제일 적은 경우 찾기(그 b만큼 옮기면 됨)
 * abababababababa
 * aba]bababab[ababa
 */