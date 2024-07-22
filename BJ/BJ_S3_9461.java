import java.util.*;
import java.io.*;

//BJ S3 9461 파도반 수열
public class BJ_S3_9461{
    private static long[] perm = new long[101];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<=3; i++) perm[i] = 1;
        for(int i=4; i<=100; i++) perm[i] = perm[i-3]+perm[i-2];
        int T = Integer.parseInt(st.nextToken());
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            sb.append(perm[num]+"\n");
        }
        System.out.println(sb.toString());
    }
}
/* 
 * P(1):  1
 * P(2):  1 = P(1)
 * P(3):  1 = P(2)
 * P(4):  2 = P(1)+P(3)
 * P(5):  2 = P(4)
 * P(6):  3 = P(1)+P(5)
 * P(7):  4 = P(2)+P(6)
 * P(8):  5 = P(3)+P(7)
 * P(9):  7 = P(4)+P(8)
 * P(10): 9 = P(5)+P(9)
 * P(11): 12 = P(6)+P(10)
 * P(12) = P(7)+P(11) = 4+12 = 16
 * P(13) = P(8)+P(12) = 5+16 = 21
 * P(14) = P(9)+P(13) = 7+21 = 28
 * P(15) = P(10)+P(14) = 9+28 = 37
 * P(16) = P(11)+P(15) = 12+37 = 49
 * =>P(N) = P(N-5)+P(N-1)
 * ------------------
 * 1 1 1 2 2 3 4 5 7 9 12 16 21 28 37 49 65 86
 * P(1)+P(2) = P(4)
 * P(N) = P(N-3)+P(N-2)
 */