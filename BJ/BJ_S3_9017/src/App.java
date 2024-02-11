import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Rank implements Comparable<Rank>{
    int team;
    int people;
    int sum;
    int fifth;

    Rank(int team, int people, int sum, int fifth){
        this.team = team;
        this.people = people;
        this.sum = sum;
        this.fifth = fifth;
    }

    @Override
    public int compareTo(Rank o) {
        if(this.sum == o.sum) return this.fifth - o.fifth;
        return this.sum - o.sum;
    }

}

//BJ S3 9017 크로스 컨트리
public class App {
    static int T, N;
    static int[] ranks;
    static int[] peopleNum;
    static HashMap<Integer, Rank> map;
    static PriorityQueue<Rank> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            ranks = new int[N];
            peopleNum = new int[201];
            for(int n=0; n<N; n++){
                int tmp = Integer.parseInt(st.nextToken());
                ranks[n] = tmp;
                peopleNum[tmp]++;
            } //-----tc 입력 완-----
            //6명 인원 체크 후 등수 계산
            map = new HashMap<>();
            int cnt = 0;
            for(int n=0; n<N; n++){
                int tmp = ranks[n];
                if(peopleNum[tmp] < 6) continue;

                if(!map.containsKey(tmp)) map.put(tmp, new Rank(tmp, 0, 0, 0));
                map.get(tmp).people++;
                if(map.get(tmp).people<=4) map.get(tmp).sum += cnt;
                if(map.get(tmp).people==5) map.get(tmp).fifth = cnt;
                cnt++;
            }
            //계산된 등수 정보를 갖고 pq에
            pq = new PriorityQueue<>();
            for(int key:map.keySet()) pq.offer(map.get(key));
            sb.append(pq.poll().team+"\n"); //가장 앞 팀이 답
        }
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 6명의 선수 -> 상위 4명 점수(등수) 합
 * - 여섯명이 참가하지 못하면 제외
 * - 동점의 경우엔 다섯번째 주자가 빨리 들어온 팀이 우승
 * 우승팀 번호 출력(N<1000, M<200)
 * !!!등수 계산 시에 6명 미만인 팀의 등수는 빼고 세야함!!!
 * ------------------------
 * 0. 등수 정보 배열(입력을 순서대로 받은거), 인원수 저장 배열
 * 1. 6명 인원 체크 O(n) -> 인원미달은 빼고 등수 계산이 필요
 * 2. 등수 정보 배열 -> 클래스 만들어서 pq에(이때 등수 계산)
 * 3. pq에서 가장 앞에꺼 뽑기
 * 1 - 1 + 5 + 9 + 10 + 12 + 15
 * 2 - 2 + 7
 * 3 - 3 + 4 + 6 + 11 + 13 + 14
 * 4 - 8
 */