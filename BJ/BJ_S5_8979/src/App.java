import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Country implements Comparable<Country>{
    int no, gold, silver, bronze;
    Country(int no, int gold, int silver, int bronze){
        this.no = no;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    @Override
    public int compareTo(Country o) {
        if(this.gold == o.gold && this.silver == o.silver) return o.bronze - this.bronze;
        else if(this.gold == o.gold) return o.silver - this.silver;
        return o.gold - this.gold;    
    }
    
}

//BJ S4 8979 올림픽
public class App {
    static int N, K;
    static PriorityQueue<Country> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            pq.offer(new Country(no, gold, silver, bronze));
        }//-----입력 완료-----
        int rank = 0; //등수 계산용
        int cnt = 1; //같은 등수 계산용
        Country before = new Country(0, 0, 0, 0); //직전 국가의 메달정보
        while(!pq.isEmpty()){
            Country cur = pq.poll();
            //직전 국가와 메달 정보가 같음 = 같은 등수임
            if(cur.gold == before.gold && cur.silver == before.silver && cur.bronze == before.bronze){
                cnt++;
            }
            else{
                rank += cnt;
                cnt = 1;
            }
            //K국가 찾으면 등수 저장하고 반복 종료
            if(cur.no == K){
                sb.append(rank);
                break;
            }
            before = cur;
        }
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 국가들의 등수 찾기
 * [조건]
 * 1. 금메달 수가 많은 나라
 * 2. 금메달 수가 같으면 은메달 수가 많은 나라
 * 3. 금은메달수가 같으면 동메달 수가 많은 나라
 * 모든 메달 수가 동일하면 같은 등수
 * 한 국가의 등수는 (자신보다 더 잘한 나라 수)+1
 * N: 국가의 수(~1000)
 * K: 등수를 알고 싶은 국가번호(~N)
 * 국가 번호 - 금메달 수 - 은메달 수 - 동메달 수
 * ----------------------------------
 * 클래스 만들어서 위 조건에 따라 comparable 정의
 * pq에 넣고 뽑으면서 등수 계산, 직전 등수의 보유 메달과 비교하며 등수 업뎃
 */