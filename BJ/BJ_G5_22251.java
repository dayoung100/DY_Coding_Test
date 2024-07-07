import java.util.*;
import java.io.*;

//BJ G5 22251 빌런 호석
public class BJ_G5_22251 {
    private static int N, K, P, X, ans;
    private static int[] LED = {
        Integer.parseInt("1110111",2), //0
        Integer.parseInt("0010010",2), //1
        Integer.parseInt("1011101",2), //2
        Integer.parseInt("1011011",2), //3
        Integer.parseInt("0111010",2), //4
        Integer.parseInt("1101011",2), //5
        Integer.parseInt("1101111",2), //6
        Integer.parseInt("1010010",2), //7
        Integer.parseInt("1111111",2), //8
        Integer.parseInt("1111011",2), //9
    };
    private static int[][] numToAnotherNum;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        //----- input end -----
        //1. 각 숫자가 다른 숫자가 되기 위해 필요한 반전 횟수 구하기
        numToAnotherNum = new int[10][10];
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                //XOR연산 후 1의 개수 세기
                numToAnotherNum[i][j] = Integer.bitCount(LED[i]^LED[j]);
            }
        }
        //2. 1부터 N까지 돌면서 확인하기
        Outer:
        for(int i=1; i<=N; i++){
            int now = X;
            int target = i;
            int cnt = 0;
            for(int j=0; j<K; j++){
                cnt += numToAnotherNum[now%10][target%10];
                if(cnt>P) continue Outer;
                now /= 10;
                target /= 10;
            }
            if(cnt>0 && cnt<=P) ans++;
        }
        System.out.println(ans);
        br.close();
    }
}
/* 
 * N: 건물 최대 층 수
 * K: 디스플레이에 보이는 숫자의 자릿수
 * P: 최대 반전시킬 LED
 * X: 실제로 엘베가 있는 층
 * 빌런: 1~P개를 반전 시켜 올바른 수를 만들 것
 * 이때 빌런이 반전시켜 만들 경우의 수 계산하기
 * ----------------------------
 * 1. 미리 각 숫자들이 다른 숫자가 되기 위해 필요한 반전 횟수 구해두기
 * 2. 1부터 N까지 돌면서 X가 특정 수가 되는데 필요한 반전 횟수 구하기
 * -> 1의 자리수부터 하나씩 앞으로 가면서 확인
 * -> 501을 0501로 변환하는 등의 작업 필요x
 * ----------------------------
 * X의 각 숫자들이 다른 숫자가 되기 위해 필요한 반전 횟수
 * => 각 LED 위치에 따라 2진수 -> XOR연산으로 달라진 부분 찾으면 됨
 * 1 : 0010010
 * 2 : 1011101
 * XOR 1001111 => 5회 반전 필요(Integer.bitCount())
 * => 이 반전횟수를 계산해서 2차원 배열에 넣기
 */