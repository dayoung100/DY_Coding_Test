import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BJ S1 2531 회전 초밥
public class BJ_S1_2531 {
    static int N, D, K, C;
    static int[] sushi;
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //총 접시 수
        D = Integer.parseInt(st.nextToken()); //초밥 가짓수
        K = Integer.parseInt(st.nextToken()); //연속해서 먹을 접시 수
        C = Integer.parseInt(st.nextToken()); //쿠폰 번호
        sushi = new int[N];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            sushi[n] = Integer.parseInt(st.nextToken());
        }
        //-----입력 완료-----
        HashSet<Integer> set;
        for(int n=0; n<N; n++){
            set = new HashSet<>();
            for(int k=0; k<K; k++){
                int index = n+k>=N ? n+k-N : n+k;
                set.add(sushi[index]);
            }
            set.add(C);
            answer = Math.max(answer, set.size());
            if(answer == K+1) break;
        }

        sb.append(answer);
        System.out.println(answer);
        br.close();
    }

    
}

/*
 * 먹을 수 있는 초밥 가짓수의 최댓값 구하기
 * N: 총 접시의 수 (2~30,000)
 * d: 초밥의 가짓수 (2~3,000)2에서 d번까지의 초밥이 있다는 것
 * k: 연속에서 먹을 접시의 수 (2~3000 <= N)
 * c: 쿠폰번호(해당하는 초밥 공짜제공) (1~d)
 * --------------------------------------
 * 회전 -> 연결리스트
 * N개의 접시마다 k개까지 체크하며 가짓수 체크 -> Nxk = 90,000,000
 * 가짓수체크->그때그때 set에 넣고->c도 set에-> 개수 카운트
 * 기본적으로 최대 초밥 가짓수는 k+1개 -> 도달하는게 나오면 그대로 return
 */