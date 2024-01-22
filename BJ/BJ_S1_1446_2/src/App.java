import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//BJ S1 1446 지름길_다익스트라o
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
    static ArrayList<Way> ways = new ArrayList<>();
    static int[] distance; //다익스트라용 최단거리 배열
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        //지름길 정보 저장
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            if(dest > D) continue;
            if(dest - start <= length) continue;
            ways.add(new Way(start, dest, length));
        } //-----입력 완료-----
        Collections.sort(ways);
        sb.append(solution());
        System.out.println(sb);
        br.close();
    }

    //다익스트라
    public static int solution(){
        distance = new int[D+1]; //모든 지점에 대해서 최단거리
        Arrays.fill(distance, Integer.MAX_VALUE); //최대값으로 초기화
        distance[0] = 0;
        int idx = 0; //지름길 ways의 인덱스
        int now = 0; //현재 위치
        //도착할때까지 계속 체크
        while(now<D){
            //아직 봐야할 지름길이 있음
            if(idx < ways.size()){
                Way shortcut = ways.get(idx);
                //현위치에 지름길이 있음
                if(now == shortcut.start){
                    //지름길을 통했을 때와 현재까지 계산한 최단거리 비교, 갱신
                    distance[shortcut.dest] = Math.min(distance[now]+shortcut.length, distance[shortcut.dest]);
                    idx += 1; //다음 지름길 보기
                }
                //현위치에 지름길 없음
                else{
                    //그냥 갔을 때와 현재까지 계산한 최단거리 비교, 갱신
                    distance[now+1] = Math.min(distance[now+1], distance[now]+1);
                    now += 1;
                }
            }
            //모든 지름길을 다 봤지만 아직 도착 못함
            else{
                distance[now+1] = Math.min(distance[now+1], distance[now]+1);
                now += 1;
            }
        }
        return distance[D];
    }
}
