import java.util.*;
import java.io.*;

public class BJ_S3_9375{
    private static int N;
    private static HashMap<String, ArrayList<String>> clothes;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //의상 수
            //맵 초기화
            clothes = new HashMap<>();
            //의상 입력 받기
            for(int n=0; n<N; n++){
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken(); //이름
                String type = st.nextToken(); //종류
                if(!clothes.containsKey(type)) clothes.put(type, new ArrayList<>());
                clothes.get(type).add(name); //종류-이름 쌍 추가
            }//---tc 하나 입력 완료-----
            //경우의 수 계산
            int cnt = 1; //곱할거라 초기값은 1
            for(String type: clothes.keySet()){
                cnt *= clothes.get(type).size()+1; //종류 하나의 경우의 수+1을 곱함
            }
            sb.append(cnt-1);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
/* 
 * N: 가진 의상의 수(0~30)
 * 이하 N줄 의상의 '이름 종류' 꼴로
 * 같은 종류의 의상은 하나만 착용 가능, 이름은 중복x
 * 옷을 하나 이상 입을 때 의상을 입을 수 있는 경우의 수 출력
 * ---------------
 * (각 옷 종류별로 경우의 수)들의 곱 - 1(안입었을 때)
 * 옷들의 경우의 수는 안입었을 때는 0도 포함해야
 * HashMap<ArrayList>
 * 종류 - 이름
 * headgear : hat - turban
 * eyewear : sunglass 
 */