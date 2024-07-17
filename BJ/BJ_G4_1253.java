import java.util.*;
import java.io.*;

//BJ G4 1253 좋다
public class BJ_G4_1253 {
    private static int[] input;
    private static HashMap<Integer, HashSet<Integer>> map = new HashMap<>(); //값-인덱스 꼴
    private static int cnt = 0; //good인 수
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        input = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) {
            int a = Integer.parseInt(st.nextToken());
            input[n] = a;
            //map에 기록
            if(!map.containsKey(a)) map.put(a, new HashSet<>());
            map.get(a).add(n);
        }
        //-----input end-----
        //두 수의 합으로 특정 수를 만들 수 있는지 체크
        for(int n=0; n<N; n++){
            loop:
            for(int m=0; m<N; m++){
                if(n==m) continue; //자기 자신과는 합할 수 없음
                int gap = input[n] - input[m]; //두 수의 차가 존재하는지
                if(map.containsKey(gap)){
                    HashSet<Integer> set = map.get(gap);
                    //gap은 두 수와 같은 수일 수 없음(=n번째수, m번째수, gap이 각각 다른 수여야)
                    //gap의 인덱스set이 n과 m만으로 구성되면 안됨
                    if(set.size() == 1 && (set.contains(n) || set.contains(m))) continue;
                    if(set.size() == 2 && set.contains(n) && set.contains(m)) continue;
                    cnt++;
                    break loop; //good임을 확인하면 넘어감
                }
            }
        }
        System.out.println(cnt);
    }
}
/*
 * N개의 수(1~2000)
 * 어떤 수가 다른 수 2개의 합으로 나타낼 수 있으면 '좋은 수'
 * N개의 수가 주어지면 그 중에서 좋은 수의 개수는?
 * 수의 위치가 다르면 값이 같아도 다른 수
 * ---------------------
 * 2000*2000 = 4,000,000 n^2연산이면 OK
 * A+B=C일 때 B를 이용해 C를 만들기 위해선 C-B인 A가 있어야
 * -> 각 숫자들의 존재 여부를 기록해두고 조회 : O(1)
 * 이때 위치가 다르면 다른 수니까 인덱스도 같이 기록 -> Map
 * -> 수를 key로, value는 그 수의 인덱스들
 * value : 인덱스 존재 여부만 파악 -> set으로해서 조회는 O(1)
 * ------------------------
8
1000000000 -400000000 600000000 -400000000 300000000 700000000 800000000 -800000000
=> 300000000 600000000 1000000000 -800000000

5
-1 0 1 2 3
=> 0 1 2 3
 */