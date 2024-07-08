import java.util.*;
import java.io.*;

//BJ G4 1863 스카이라인 쉬운거
public class BJ_G4_1863{
    private static int ans = 0;
    private static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());//총 N줄 입력
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            Integer.parseInt(st.nextToken()); //x 입력은 필요 없음
            int y = Integer.parseInt(st.nextToken());

            while(stack.size()>0 && stack.peek()>y){
                stack.pop();
                ans++;
            }
            if(stack.size()>0 && stack.peek()==y) continue;
            else if(y!=0) stack.push(y);
        }
        //-----input end-----
        ans += stack.size();
        System.out.println(ans);
    }
}
/*
 * 스카이라인만을 보고 도시에 세워진 건물이 최소 몇개인지 알아내기
 * -------------------
 * 2차원 배열 그리면 메모리 초과 발생
 * 스택 이용
 * 건물 높이가 변할 때마다
 * - 높이가 올라감 -> 새로운 건물을 스택에 추가
 * - 높이가 내려감 
 *      -> 내려간 높이보다 큰 건물은 전부 뺌
 *      -> 내려간 높이와 같은 높이의 건물이면 -> 그 건물의 연장선인것
 *      -> 내려간 높이보다 작은 높이의 건물만 남으면 -> 내려간 높이의 건물은 추가해야
 * 건물을 뺄 때마다 체크해서 ans+1
 * ------------------
 * 건물은 항상 추가
 * 추가하기 전: 해당 높이보다 큰 건물은 빼고 ans++
 * 추가하기 전: stack의 제일 위가 해당 높이와 같음 -> pass
 */