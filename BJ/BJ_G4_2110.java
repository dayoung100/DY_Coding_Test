import java.util.*;
import java.io.*;

// BJ G4 2110 공유기 설치
public class BJ_G4_2110 {
    private static int N, C;
    private static long[] home;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        home = new long[N];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            home[n] = Long.parseLong(st.nextToken());
        }//----- input end -----
        Arrays.sort(home);
        long start = 1;
        //upper bound가 key를 초과하는 값 찾는 것
        //key를 찾으려면 key보다 1크게 범위를 잡고 탐색해야 
        long end = home[N-1] - home[0] + 1;
        while(start<end){
            long mid = (end+start)/2;
            int cnt = countC(mid); //거리가 mid일 때 설치 가능한 최대 공유기 수
            //설치 가능 개수가 c보다 작음 -> 거리 줄이기
            if(cnt<C) end = mid; //mid포함->최종값이 end에 위치할 것
            //설치 가능 개수가 c보다 크거나 같음 -> 거리 늘리기
            else start = mid+1; //mid 포함 x
        }
        System.out.println(end-1); //초과 값이라 -1해줌
    }

    static int countC(long distance){
        int cnt = 1; //설치 가능 공유기 개수
        int installedIndex = 0; //직전에 설치한 위치
        for(int n=1; n<N; n++){
            //설치 불가능
            if(home[n] - home[installedIndex] < distance) continue;
            //설치 가능
            cnt++;
            installedIndex = n;
        }

        return cnt;
    }
}
/*
 * N: 집의 개수(~20만)
 * C: 공유기 개수(~N)
 * C개의 공유기를 N개의 집에 적당히 설치해서
 * 가장 인접한 두 공유기 사이의 거리를 최대로
 * 1 2 3 4 5 6 7 8 9 10 11...
 * * * _ * _ _ _ * * __ __...
 * v * _ v _ _ _ * v __ __...
 * --------------------------
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
 * _ * _ _ _ * _ _ * __  * __ __ __  *  * __ __ __ __ __ __  *
 * _ v _ _ _ * _ _ v __  * __ __ __  v  * __ __ __ __ __ __  v 4개
 * 거리가 5일 때 최대 4개 설치 가능
 * _ v _ _ _ v _ _ * __  v __ __ __  v  * __ __ __ __ __ __  v 5개
 * 거리가 4일 때 최대 5개 설치 가능
 * _ v _ _ _ v _ _ v __  * __ __ __  v  * __ __ __ __ __ __  v 5개
 * 거리가 3일 때 최대 5개 설치 가능
 * _ v _ _ _ v _ _ v __  v __ __ __  v  * __ __ __ __ __ __  v 6개
 * 거리가 2일 떄 최대 6개 설치 가능
 * 거리가 1일 때 최대 7개 설치 가능
 * 이때, 공유기가 5개면 최대 거리가 얼마? -> 거리 4
 * -----------------------------
 * 거리 기준 upper bound -> 최소 1, 최대 첫 집과 마지막 집 사이의 거리
 * 거리가 mid일 때 최대 설치 가능 개수 카운트
 * - 설치 가능 개수가 c보다 큼 -> 거리 늘리기(end mid로)
 * - 설치 가능 개수가 c보다 작음 -> 거리 줄이기(start를 mid로)
 * - 설치 가능 개수가 c임 -> max에 저장 후 거리 늘리기
 */