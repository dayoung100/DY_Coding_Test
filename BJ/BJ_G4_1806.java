import java.util.*;
import java.io.*;

//BJ G4 1806 부분합
public class BJ_G4_1806{
    private static int N;
    private static long S;
    private static int[] input;
    private static long[] totalSum;
    private static int min;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        input = new int[N];
        totalSum = new long[N];
        st = new StringTokenizer(br.readLine());
        int p = -1; //누적합이 S를 넘는 지점
        for(int n=0; n<N; n++) {
            int num = Integer.parseInt(st.nextToken());
            input[n] = num;
            //누적합 계산
            if(n==0) totalSum[n] = num;
            else totalSum[n] = num+totalSum[n-1];
            //p 찾기
            if(totalSum[n]>=S && p==-1) p = n;
        }
        //-----input end-----
        //전부 더해도 S를 만들 수 없는 경우
        if(p==-1) {
            System.out.println(0);
            return;
        }
        //투포인터
        int s = 0;
        int e = p;
        min = e-s+1;
        while(s<=e && e<N){
            //2-1. 부분합과 min 업데이트
            long partSum = totalSum[e] - (s>=1 ? totalSum[s-1] : 0);
            if(partSum >= S && e-s+1 < min) {
                min = e-s+1;
                if(min == 1) {
                    System.out.println(min);
                    return;
                }
            }
            //2-2. 포인터 이동
            if(partSum>S) s+=1;
            else e+=1;
            //2-3. len이 min보다 클 수 없도록 조정
            int nLen = e-s+1;
            if(nLen > min) s = e-min+1;
        }
        System.out.println(min);
    }
}
/* 
 * 연속된 수들의 부분합 -> 그 중 합이 S이상이 되는 것 -> 그 중 가장 짧은 것의 길이
 * 합을 만드는 것이 불가능하면 0 출력
 * 합이 S이상이 되는 걸 만드는게 불가능 = 누적합이 S가 안됨
 * 입력 받으면서 누적합 : O(n) = 10만
 * ------------------
 * 5 1 3 5  10 7  4  9  2  8
 * 5 6 9 14 24 31 35 44 46 54
 * ------------------
 * 5 1 3 4  10 7  4  9  2  8
 * 5 6 9 13 23 30 34 43 45 53
 * ------------------
 * 5 1 3 4  10 2  1  16  2  8
 * 5 6 9 13 23 25 26  
 * -------------------
 * 1. 누적합 구하기
 * 1-1. 누적합이 S가 넘는 지점 p 기록
 * 1-2. 누적합이 S가 넘는 지점이 없으면 0 리턴
 * 2. 투포인터: s=0, e=p / min=e-s+1
 * 2-0. s<=e 이고,
 * 2-1. 부분합과 min 업데이트
 * 2-1-1. s~e까지의 합(partSum)을 구함
 * 2-1-2. partSum이 S보다 크거나 같으면 s~e 사이 거리(len)계산
 * 2-1-3. len이 min보다 작음: min을 len으로 변경
 * 2-1-4. 이때 min이 1이 되면 1 리턴(2-2부터는 len은 항상 1보다 큼)
 * 2-2. 포인터 이동
 * 2-2-1. partSum이 S보다 큼: s를 +1
 * 2-2-2. partSum이 S와 작거나 같음: e를 +1
 * 2-3. len이 min보다 클 수 없도록 조정
 * 2-3-1. 조정된 s와 e로 e-s+1 계산(nLen)
 * 2-3-2. nLen이 min보다 큼: s = e-min+1
 */