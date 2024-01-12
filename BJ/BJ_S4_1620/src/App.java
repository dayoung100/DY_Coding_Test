import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//BJ S4 1620 나는야 포켓몬 마스터 이다솜
public class App {
    static HashMap<Integer, String> mapOfKeyInt = new HashMap<>();
    static HashMap<String, Integer> mapOfKeyString = new HashMap<>();
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            mapOfKeyInt.put(n+1, name); //포켓몬 번호는 1부터 시작
            mapOfKeyString.put(name, n+1);
        } //도감 채우기 완료
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            String search = st.nextToken();
            if(checkIsNumber(search)) sb.append(mapOfKeyInt.get(Integer.parseInt(search))+"\n");
            else sb.append(mapOfKeyString.get(search.toString())+"\n");
        }
        System.out.println(sb);
        br.close();
    }

    //숫자인지 체크 with try-catch
    public static boolean checkIsNumber(String keyword){
        try{
            int searchInt = Integer.parseInt(keyword);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
/* 
 * 포켓몬 도감에서 포켓몬 이름 <-> 포켓몬 번호 사이 매치
 * N: 포켓몬의 전체 개수(1~100,000)
 * M: 맞춰야하는 문제 개수(1~100,000)
 * 포켓몬 번호가 1번인 포켓몬부터 N번인 포켓몬까지 입력으로 들어옴
 * 포켓몬 이름은 영어, 첫문자만 대문자, 일부 포켓몬은 마지막 문자만 대문자일수도
 * 포켓몬 이름은 2~20글자
 * 문제로 들어오는 건 글자(이름) 아니면 숫자(번호), 번호면 1~N사이
 * ---------------------------
 * 맵 만들어서 하나씩 조회(O(1))
 */