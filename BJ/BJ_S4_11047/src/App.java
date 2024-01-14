import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;

//BJ S4 11047 동전 0
public class App {
    static long N, K;
    static long ans = 0;
    static HashMap<Long, ArrayList<Long>> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        boolean flag = false; //K보다 커지는 순간 토글
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            String coin_s = st.nextToken();
            long coin_l = Long.parseLong(coin_s);
            if(flag) continue; //K보다 크면 필요없는 동전 -> skip
            //동전 정보 맵에 넣기(key:자릿수)
            long key = (long)Math.pow(10, coin_s.length()-1);
            if(!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(coin_l);
            //K와 비교
            if(!flag && coin_l > K){
                flag = true;
                solution(key); //필요 개수 연산
            }
        }
        if(ans == 0) solution((long)Math.pow(10, Long.toString(K).length()-1));
        sb.append(ans);
        System.out.println(sb);
        br.close();
    }

    public static void solution(long key){
        long Kcopy = K; //복사본
        while(key > 0 || Kcopy > 0){
            int maxIdx = -1;
            if(map.containsKey(key)) {
                maxIdx = map.get(key).size()-1;
            }
            for(int nowIdx=maxIdx; nowIdx>=0; nowIdx--){
                long value = map.get(key).get(nowIdx);
                if(value > Kcopy) continue;
                long count = (long) Kcopy / value;
                ans += count;
                Kcopy -= value*count;
            }
            key = key/10;
        }
    }
}
/* 
 * 총 N종류의 동전을 갖고 있음
 * 동전의 가격 합을 K로 만들 것
 * 이때 필요한 동전 개수의 최소값은?
 * N, K는 100,000,000이하의 자연수
 * ---------------------------------
 * 1. 그리디: 비싼거부터 쓰기
 * K를 먼저 받으니까 동전들 받으면서 K보다 비싼 동전은 입력받되 쓰지 않음
 * 자릿수를 키로 하는 맵에 동전들 넣기(long-long[] 꼴)
 * 입력 받으면서 K보다 작은 수는 맵에 쌓고,
 * k보다 커지는 순간 연산해서 sb에 넣어두기
 * 그 이후는 그냥 입력받고 아무것도 안하기
 * 연산: K의 자릿수에 해당하는 키 체크 -> 배열의 뒤에서 부터 사용해서 개수 체크
 */