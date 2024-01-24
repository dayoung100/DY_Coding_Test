import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

//BJ B1 1157 단어 공부
public class App {
    static char[] chars;
    static HashMap<Character, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        chars = st.nextToken().toCharArray();
        for(int i=0; i<chars.length; i++){
            char upper = Character.toUpperCase(chars[i]);
            if(!map.containsKey(upper)) map.put(upper, 0);
            map.put(upper, map.get(upper)+1);
        }//-----입력 완료-----
        ArrayList<Character> keySet = new ArrayList<>(map.keySet());
        keySet.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return map.get(o2) - map.get(o1);
            }
        });
        if(map.size() > 1 && map.get(keySet.get(0)).equals(map.get(keySet.get(1)))) sb.append("?");
        else sb.append(keySet.get(0));
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 가장 많이 사용된 알파벳 알아내기
 * 대문자로 출력/여러개일 경우는 ? 출력
 * -----------------------
 * 한글자씩 쪼개서 맵에 넣고 키는 대문자, 값은 숫자
 */