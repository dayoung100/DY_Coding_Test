import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ G5 2138 전구와 스위치
public class BJ_G5_2138 {
    static int N;
    static char[] origin, target, copy;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //전구 수
        st = new StringTokenizer(br.readLine());
        origin = st.nextToken().toCharArray(); //원래 스위치 상태
        copy = new char[N];
        st = new StringTokenizer(br.readLine());
        target = st.nextToken().toCharArray(); //목표 스위치 상태
        //-----입력 완료-----
        int case1 = check(false); //0번째 스위치를 안눌렀을 때
        int case2 = check(true); //0번째 스위치를 눌렀을 때
        if(case1 == -1 && case2 == -1) System.out.println(-1);
        else System.out.println(Math.min(case1==-1?Integer.MAX_VALUE:case1, case2==-1?Integer.MAX_VALUE:case2));         
        br.close();
    }

    /**
     * 가능여부/몇번눌러야하는지 체크
     * @param zeroPush 0번째 스위치를 눌렀는가 아닌가
     * @return -1이면 만들 수 없음, 그 이상은 만들때 필요 횟수
     */
    public static int check(boolean zeroPush){
        for(int n=0; n<N; n++) copy[n] = origin[n]; //원래 스위치 복사
        int cnt = 0; //누른 횟수
        //zeroPush 처리
        if(zeroPush){
            toggle(0);
            cnt++;
        }
        //앞에서부터 순차적으로 스위치 누르기
        for(int n=1; n<N; n++){
            //i-1이 target과 다르면 i를 무조건 눌러야함
            if(copy[n-1] != target[n-1]){
                toggle(n);
                cnt++;
            }
        }
        //타겟과 같아질 수 있는지 체크
        boolean able = true;
        for(int n=0; n<N; n++) {
            if(copy[n] != target[n]) {
                able = false;
                break;
            }
        }
        //결과 반환
        return able ? cnt : -1;
    }

    public static void toggle(int n){
        for(int i=n-1; i<=n+1; i++){
            if(i>=0 && i<N) copy[i] = copy[i]=='1'?'0':'1';
        }
    }
}
/*
 * N: 스위치와 전구의 개수(2~10만)
 * i번 스위치 누름 -> i-1, i, i+1번의 전구가 토글됨
 * 현재 상태 -> 목표 상태로 바꾸기 위해 최소 몇번 눌러야하는가?
 * 만들 수 없으면 -1 출력
 * --------------------------
 * 앞에서부터(=i-1번째부터) target과 똑같이 만들어가기
 * i-1 -> i-1 또는 i를 눌러야 바뀜
 * i-1을 눌렀다 치면 -> i를 눌러야하는지 아닌지 결정할 수 있음
 * (i-1을 눌렀는지 아닌지가 확정된 후에,
 * i-1이 target과 다르면 i를 무조건 눌러야함)
 */