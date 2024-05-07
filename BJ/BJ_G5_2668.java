import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

//BJ_G5_2668 숫자고르기
public class BJ_G5_2668 {
    static int N;
    static int[][] arr;
    static HashSet<Integer> ableSet = new HashSet<>();
    static HashSet<Integer> disableSet = new HashSet<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = i+1;
            arr[i][1] = Integer.parseInt(st.nextToken());;
        }//-----input end-----
        for(int n=0; n<N; n++){
            //첫 시작이 ableSet에 있는 수면 패스
            if(ableSet.contains(arr[n][0])) continue;
            HashSet<Integer> tempSet = new HashSet<>();
            boolean able = false;
            int firstNum = n+1;
            tempSet.add(arr[n][0]); //인덱스의 값(=사이클의 시작) 넣고 시작
            //n번째를 시작으로 사이클 만들기
            while(true){
                int secondNum = arr[firstNum-1][1];
                //사이클의 시작과 같으면 완성
                if(secondNum == arr[n][0]) {
                    able = true;
                    break; 
                }
                //사이클의 시작과 다른데
                //이미 나온 숫자 -> 사이클 불가능
                //disableSet에 있음 -> 사이클 불가능
                if(tempSet.contains(secondNum) || disableSet.contains(secondNum)){
                    able = false;
                    break;
                }
                //사이클의 시작과 다른데 위에 해당없음 -> 사이클 계속 돌기
                tempSet.add(secondNum);
                firstNum = secondNum;
            }

            //사이클 가능 -> tempSet의 숫자를 ableSet에
            if(able){
                for(int num : tempSet) ableSet.add(num);
            }
            //사이클 불가능 -> 인덱스 n에 있는 숫자를 disableSet에
            else disableSet.add(n+1);
        }
        //ableSet을 배열로 만들고 정렬
        ArrayList<Integer> list = new ArrayList<>();
        for(int ableNum : ableSet) list.add(ableNum);
        Collections.sort(list);
        System.out.println(list.size());
        for(int item : list) sb.append(item+"\n");
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 첫줄: 1~N까지의 수가 차례로
 * 둘째줄: 1~N사이의 수가 랜덤하게
 * 집합1: 첫줄에서 뽑은 몇개
 * 집합2: 첫줄에서 뽑은 수의 아래에 있는 수
 * 이때 집합1과 집합2가 일치하되, 크기가 최대인 집합 구하기
 * ----------------------------------
 * 첫줄-둘째줄-첫줄-둘째줄... 형태의 사이클이 완성되어야함
 * 전체 체크 -> 사이클 완성되면 set에 담기
 * 가능한 숫자 set : 완성된 사이클에 들어간 모든 수 -> 첫 시작이 이 안에 있는 수면 체크필요x(어차피 순환)
 * 불가능 숫자 set : 불가능한 사이클의 시작점 -> 사이클 만들다가 이걸 만나면 그건 불가능한 사이클
 */