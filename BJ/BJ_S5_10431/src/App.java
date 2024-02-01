import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//BJ S5 10431 줄세우기
public class App {
    static int[] kids;
    static ArrayList<Integer> sortedKids;
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int P = Integer.parseInt(st.nextToken());
        for(int p=1; p<=P; p++){
            kids = new int[20];
            sortedKids = new ArrayList<>();
            cnt = 0;
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int firstKid = Integer.parseInt(st.nextToken());
            sortedKids.add(firstKid);
            for(int i=0; i<19; i++){
                int kid = Integer.parseInt(st.nextToken());
                //맨 뒤의 값과 비교 -> kid가 더 크면 맨뒤에 넣기
                if(sortedKids.get(sortedKids.size()-1) <= kid) sortedKids.add(kid);
                else{
                    int idx = 0;
                    //kid가 더 작으면 자기 자리 찾기
                    for(int k=sortedKids.size()-1; k>=0; k--){
                        int tmpKid = sortedKids.get(k); //비교할 애
                        if(tmpKid <= kid) {
                            idx = k+1;
                            break;
                        }
                        cnt++; //비교할 애가 더 큼 -> 걸음수 up
                    }
                    sortedKids.add(idx, kid);//자기 자리에 넣기
                }
            }
            sb.append(T+" "+cnt+"\n");
        }
        System.out.println(sb);
        br.close();
        
    }
}
/* 
 * P: 테케 수, 한 테케당 테케 번호 T, 20명의 아이 키
 * [조건]
 * - 앞에서부터 줄의 맨 뒤로 서기
 * - 앞에 자기보다 큰 사람 있으면 그중 가장 앞으로 이동 + 뒤 학생들은 뒤로 이동
 * 총 몇번 물러서는가(물러난 걸음 수의 총합)?
 * ----------------------------------------
 * arraylist -> 삽입 반복
 * 마지막 값과 비교 -> 자기가 더 크면 그대로
 *   -> 자기가 더 작으면 반복 -> 자기보다 작은 애가 나올때까지
 *   -> 반복하면서 걸음 수 올리기 -> 총 걸음수에 더함
 */