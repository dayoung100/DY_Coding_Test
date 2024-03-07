import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_2577 {
    static int A, B, C;
    static int[] numbers = new int[10]; //0~9까지의 숫자 횟수
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        int mul = A*B*C;
        char[] cArr = String.valueOf(mul).toCharArray();
        for(char c: cArr) numbers[c - '0']++;
        for(int i=0; i<10; i++) sb.append(numbers[i]+"\n");
        System.out.println(sb);
        br.close();
    }   
}
