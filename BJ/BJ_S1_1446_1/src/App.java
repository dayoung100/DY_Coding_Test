import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BJ S1 1446 지름길_다익스트라x
//지름길 정보
class Way implements Comparable<Way>{
    int start; //시작
    int dest; //도착
    int length; //거리
    //생성자
    public Way(int start, int dest, int length){
        this.start =  start;
        this.dest = dest;
        this.length = length;
    }
    //정렬은 시작 위치가 가까운 순 -> 도착위치가 먼 순 -> 거리가 짧은 순
    @Override
    public int compareTo(Way o) {
        if(this.start == o.start && this.dest == o.dest) return this.length - o.length;
        if(this.start == o.start) return o.dest - this.dest;
        return this.start - o.start;
    }
}

public class App {
    static int D, N;
    static int min;
    static Way[] ways;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        min = D; //지름길 안 쓸때보다 크면 안됨
        //지름길 정보 저장
        ways = new Way[N];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            ways[n] = new Way(start, dest, length);
        } //-----입력 완료-----
        Arrays.sort(ways); //지름길 정보 정렬
        solution(0,0,0);
        sb.append(min);
        System.out.println(sb);
        br.close();
    }

    /** 
     * @param now 현위치
     * @param total 현위치까지의 총 이동거리
     * @param idx 살펴보아야 할 ways의 인덱스(매번 다음 지름길을 빠르게 보기 위함)
     */
    public static void solution(int now, int total, int idx){
        //기저조건
        if(now >= D) {
            min = Math.min(min, total);
            return;
        }
        //백트래킹
        if(total >= min) return;

        int tmpIdx = idx;
        //현위치가 idx번째의 지름길 시작점 이후임 -> 다음 지름길 살펴보기
        while(tmpIdx<N && now > ways[tmpIdx].start) tmpIdx += 1;
        //더이상 지름길이 없는데 아직 도착 안함 -> 도착위치로 바로 이동
        if(tmpIdx >= N) {
            solution(D, total+D-now, tmpIdx);
            return;
        }
        
        //현위치에 지름길이 있음
        if(now == ways[tmpIdx].start) {
            //지름길로 들어감 -> 지름길의 도착지로 이동
            //이때 도착지가 D보다 크면 갈 수 없음
            if(ways[tmpIdx].dest <= D) solution(ways[tmpIdx].dest, total+ways[tmpIdx].length, tmpIdx+1);
            //지름길로 안들어감 -> 다음 분기점 있으면 이동, 없으면 idx만 늘려서 넣기(모든 지름길을 다 봤다는 의미)
            if(tmpIdx < N-1) solution(ways[tmpIdx+1].start, total + ways[tmpIdx+1].start - now, tmpIdx+1);
            else solution(now, total, idx+1);
        }
        //현위치에 지름길이 없음 == tmpIdx번째의 지름길 시작위치가 now보다 큼
        else{
            //다음 분기점으로 이동
            solution(ways[tmpIdx].start, total + ways[tmpIdx].start - now, tmpIdx);
        }
    }
}
/*
 * 총 Dkm 길이의 도로에 지름길 N개
 * N<=12, D<=10,000
 * 지름길 일방통행(역주행x)
 * 지름길 시작위치-도착위치-거리 가 입력으로
 * 0 ------------------ 150 : 길
 * 0 -(10)-50---------- 150 : 지름길 1
 * 운전해야하는 거리의 최솟값 구하기
 * -------------------------------------
 * DFS -> 지름길 정보 배열에 저장해두고 재귀
 * 1. 분기점까지 원래 길로 달리기
 * 2. 지름길로 들어가기
 * 중 하나 수행
 * min 구해두고 그보다 커지면 return해서 백트래킹
 * 재귀 중 들고 다닐 정보: 현위치, 총 이동거리, 인덱스(지름길 정보 조회용)
 * 지름길이 주어질 때 정렬되어있다는 보장 없음
 * 도착 지점이 총거리D보다 크면 이용할 수 없는 지름길(왜 이걸 글로 안써둔거야)
 */