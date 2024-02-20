import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ S5 4659 비밀번호 발음하기
public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            String pw = st.nextToken();
            //end 체크
            if(pw.equals("end")) break;
            boolean check1 = false; //1. 모음 하나를 반드시 포함
            boolean check2 = true; //2. 모음 3개 또는 자음 3개가 연속으로 오면 안됨
            int cnt = 0; 
            int beforeStatus = 0; //-1은 자음 1은 모음
            boolean check3 = true; //3. 같은 글자가 연속으로 두번 안됨, but ee와oo는 허용
            char before='0';
            for(int i=0; i<pw.length(); i++){
                char cur = pw.charAt(i);
                //모음일 때 -> 조건 1,2
                if(cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o' || cur == 'u') {
                    check1 = true;
                    if(beforeStatus == 1) cnt++; //전값이 모음이었으면 cnt 업
                    else { //전 값이 모음이 아니었으면 모음으로 다시 카운트
                        beforeStatus = 1; 
                        cnt = 1;
                    }
                }
                //자음일 때 -> 조건2
                else{
                    if(beforeStatus == -1) cnt++; //전값이 자음이었으면 cnt 업
                    else { //전 값이 자음이 아니었으면 자음으로 다시 카운트
                        beforeStatus = -1; 
                        cnt = 1;
                    }
                }
                if(cnt == 3) check2 = false;
                if(cur == before && cur != 'e' && cur != 'o') check3 = false;
                before = cur;
            }
            if(check1 && check2 && check3) sb.append("<"+pw+"> is acceptable. \n");
            else sb.append("<"+pw+"> is not acceptable. \n");
        }
        System.out.println(sb);
        br.close();
    }
}
/* 
 * acceptable / not acceptable 둘 중 하나
 * 1. 모음 하나를 반드시 포함
 * 2. 모음 3개 또는 자음 3개가 연속으로 오면 안됨
 * 3. 같은 글자가 연속으로 두번 안됨, but ee와oo는 허용
 * 입력이 end가 나올때까지 반복
 * 패스워드 1~20글자 문자열
 * --------------------------------------
 *  
 */