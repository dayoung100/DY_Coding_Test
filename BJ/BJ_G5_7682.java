import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_7682{
    static char[][] boardMap;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            String board = st.nextToken();
            if(board.equals("end")) break;
            char[] boardStat = board.toCharArray();
            boardMap = new char[3][3]; //3x3형태
            int Xnum = 0, Onum = 0;
            for(int i=0; i<9; i++){
                boardMap[i/3][i%3] = boardStat[i];
                if(boardStat[i] == 'X') Xnum++;
                else if(boardStat[i] == 'O') Onum++;
            }
            //꽉찼음AND개수충족
            if(Xnum+Onum == 9 && Xnum-Onum == 1){
                //꽉차면 O 이길 수 없음
                if(check('O')) sb.append("invalid\n");
                //둘다 빙고 안됨
                else sb.append("valid\n");
            }
            //꽉 안찼음
            else{
                //동시에 빙고 안됨
                if(check('X') && check('O')) sb.append("invalid\n");
                //X가 이기면 하나 많음
                else if(check('X') && Xnum-Onum==1) sb.append("valid\n");
                //O가 이기면 개수 같음
                else if(check('O') && Xnum==Onum) sb.append("valid\n");
                else sb.append("invalid\n"); //이긴사람이 없는데 게임 종료(다안채워짐)
            }
        }
        System.out.println(sb);
        br.close();
    }

    static boolean check(char ch){
        //가로 빙고
        for(int i=0; i<3; i++){
            if(boardMap[i][0] != ch) continue;
            int cnt = 1;
            for(int j=1; j<3; j++){
                if(boardMap[i][j] == ch) cnt++;
            }
            if(cnt == 3) return true;
        }
        //세로 빙고
        for(int j=0; j<3; j++){
            if(boardMap[0][j] != ch) continue;
            int cnt = 1;
            for(int i=1; i<3; i++){
                if(boardMap[i][j] == ch) cnt++;
            }
            if(cnt == 3) return true;
        }
        //대각선 빙고
        if(boardMap[0][0]==ch && boardMap[1][1]==ch && boardMap[2][2]==ch) return true;
        if(boardMap[0][2]==ch && boardMap[1][1]==ch && boardMap[2][0]==ch) return true;
        
        return false;
    }
}
/* 
 * 게임판: 3x3 격자판
 * 첫번째가 X, 두번째가 O. 놓는 위치에 제한 없음
 * 종료 조건1. 한 사람의 말이 가로, 세로, 대각선 방향으로 3칸 성공
 * 종료 조건2. 게임판이 가득 참
 * 그 상태가 게임에서 발생할 수 있는 최종 상태인지?
 * 9개의 문자: 왼쪽 위부터 나열, end 입력시 종료
 * --------------------------
 * 가능한 경우
 * - 개수: X와 O가 한개 차이나는데 X가 하나 많거나 같거나임
 * - 한줄이 찼는지 파악하기 OR 꽉 차서 끝난건지
 */