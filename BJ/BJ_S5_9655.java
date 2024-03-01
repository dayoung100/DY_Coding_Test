import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S5_9655{
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        //----------- 입력 완료 -------------
        String turn = "SK"; //처음은 상근이 턴
        while(N>0){
            if(N>3) N -= 3;
            else N -= 1;
            turn = turn=="SK"?"CY":"SK";
        } //내 턴이 돌아왔을 때 돌이 없으면 패배
        sb.append(turn=="SK"?"CY":"SK"); //승자는 한 번 더 뒤집어서 출력해야
        System.out.println(sb);
        br.close();
    }
}
/*
 * 그리디
 */