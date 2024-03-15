import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S2_2607 {
    static int[] origin = new int[26];
    static int ans = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //단어의 개수
        st = new StringTokenizer(br.readLine());
        char[] origin_arr = st.nextToken().toCharArray();
        for(char c: origin_arr) origin[c-'A']++;
        loop:
        for(int n=0; n<N-1; n++){
            st = new StringTokenizer(br.readLine());
            char[] word_arr = st.nextToken().toCharArray();
            int[] word = new int[26];
            for(char c: word_arr) word[c-'A']++;

            //비슷한 단어인지 체크
            //조건1. 원본과 총 알파벳 개수 차이가 +-1이어야
            if(Math.abs(origin_arr.length - word_arr.length) > 1) continue;
            int cnt = 0; //원본과 갯수가 다른 알파벳 수(조건3. cnt가 2개 넘으면 안됨)
            for(int i=0; i<26; i++){
                int tmp = origin[i] - word[i];
                //조건2. 알파벳마다 각 개수의 차 -> 절댓값이 최대 1이어야함
                if(Math.abs(tmp) > 1) continue loop; 
                if(tmp != 0) cnt++;
            }
            ans += cnt>2 ? 0 : 1;
        }
        System.out.println(ans);
    }
}
/* 
 * 비슷하다
 * - 같은 구성을 갖는다
 * (구성문자 종류가 같고, 수가 같다)
 * - 한 단어에서 한문자의 차이가 있다
 * (더하거나 빼거나 변경했을 때 같아진다)
 * ------------------------------------
 * 알파벳과 포함여부 수를 담은 26칸의 배열
 * 조건1. 원본과 총 알파벳 개수 차이가 +-1이어야
 * 조건2. 알파벳마다 각 개수의 차 -> 절댓값이 최대 1이어야함
 * 조건3. 원본과 갯수가 다른 알파벳은 2개까지만 존재할 수 있음(변경했을 때)
 * [DOG]     [GOD]     [GOOD]    [DOLL]      [DOT]      [WOOD]      [G]     [DOGOD]
 * D G O  |  D G O  |  D G O  |  D G L O  |  D G O T  | D G O W  |  D G O | D G O
 * 1 1 1  |  1 1 1  |  1 1 2  |  1 0 2 1  |  1 0 1 1  | 1 0 2 1  |  0 1 0 | 2 1 2
 * -----------------------------------------
 *[DOG-word] 0 0 0     0 0 -1    0 1 -2 0    0 1 0 -1   0 1 -1 -1   1 0 1   -1 0 -1
               O         O           X          O           X          X      X
 */