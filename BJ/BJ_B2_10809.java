import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_B2_10809{
    static int[] arr = new int[26]; //알파벳 등장 위치
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Arrays.fill(arr, -1); //-1로 초기화
        char[] word = st.nextToken().toCharArray();
        for(int i=0; i<word.length; i++){
            char c = word[i];
            if(arr[c-'a'] == -1) arr[c-'a'] = i;
        }
        for(int n:arr) sb.append(n+" ");
        System.out.println(sb);
    }
}