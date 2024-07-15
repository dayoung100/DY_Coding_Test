import java.util.*;
import java.io.*;

//BJ S2 2805 나무 자르기
public class BJ_S2_2805 {
    private static int N, M;
    private static int[] tree;
    private static int maxH = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++){
            tree[n] = Integer.parseInt(st.nextToken());
            maxH = Math.max(maxH, tree[n]);
        }//-----input end-----
        int start = 0;
        int end = maxH;
        int mid = 0;
        int ans = -1;
        while(start<=end){
            mid = (start+end)/2;
            //mid값일 때 얻는 나무의 양 계산
            long sum = 0;
            for(int n=0; n<N; n++){
                if(tree[n] > mid) sum += tree[n] - mid;
            }
            //나무의 양 보고 start와 end 조정
            if(sum == M) {
                ans = mid;
                break;
            }
            if(sum < M){
                end = mid-1;
            }else{
                start = mid+1;
                ans = mid;
            }
        }
        System.out.println(ans);
    }
}
/* 
 * 높이 H -> H 위의 부분이 잘림
 * 집으로 가져갈 나무 길이 M
 * 적어도 M 미터의 나무를 집에 가져가기 위한 높이의 최댓값
 * ------------------------
 * 1~나무 최대 높이 사이의 이분탐색
 * -> 로그N의 시간
 * 5 20
 * 4 42 40 26 47
 * s=0 / e=47 / mid = 23 -> 19+17+3+24 = 63
 * s=24 / e=47 / mid = 36 -> 6+4+11 = 21
 * s=37 / e=47 / mid = 42 -> 5 = 5
 * s=37 / e=4 /  
 */