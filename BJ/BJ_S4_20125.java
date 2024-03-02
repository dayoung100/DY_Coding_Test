import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S4_20125 {
    static int N;
    static char[][] board;
    static int flag;
    static int[] heart = new int[2];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new char[N][N];
        flag = 0;
        int cnt = 0;
        int tmpCnt = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            char[] chars = st.nextToken().toCharArray();
            for(int j=0; j<N; j++){
                char tmp = chars[j];
                board[i][j] = tmp;
                if(flag==0 && tmp == '*'){ //head
                    heart[0] = i+1; //머리보다 하나 아래가 심장
                    heart[1] = j; 
                    flag = 1;
                    sb.append((heart[0]+1) + " " + (heart[1]+1) + "\n");
                }
                else if(flag==1 && tmp == '*'){ //left_arm
                    if(i==heart[0] && j==heart[1]){
                        sb.append(cnt+" ");
                        cnt = 0;
                        flag = 2;
                    }
                    else cnt+=1;
                }
                else if(flag==2){ //right_arm
                    if(tmp == '*'){
                        if(i==heart[0] && j==heart[1]) continue;
                        else cnt+=1;
                    }else{
                        sb.append(cnt+" ");
                        cnt = 0;
                        flag = 3;
                    }
                }
                else if(flag==3 && tmp == '*'){ //middle
                    if(j!=heart[1]){
                        sb.append(cnt+" ");
                        cnt = 0;
                        flag = 4;
                    }
                    cnt+=1; //left_leg에 +1
                }
                else if(flag==4 && tmp == '*'){ //leg
                    if(j==heart[1]-1) cnt+=1;
                    else if(j==heart[1]+1) tmpCnt+=1;
                }
            }
        }
        sb.append(cnt+" "+tmpCnt);
        System.out.println(sb);
        br.close();
    }
}
