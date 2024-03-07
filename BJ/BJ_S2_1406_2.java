import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_S2_1406_2 {
    static Stack<Character> left = new Stack<>();
    static Stack<Character> right = new Stack<>();
    static int M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        char[] cArr = st.nextToken().toCharArray();
        for(char c:cArr) left.push(c);
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            if(order.equals("L")){
                if(left.size() == 0) continue;
                right.push(left.pop());
            }else if(order.equals("D")){
                if(right.size() == 0) continue;
                left.push(right.pop());
            }else if(order.equals("B")){
                if(left.size()==0) continue;
                left.pop();
            }else if(order.equals("P")){
                String addText = st.nextToken();
                left.push(addText.charAt(0));
            }
        }//----입력 완료----
        while(!left.empty()) {
            char c = left.pop();
            sb.append(c);
        }
        sb.reverse();
        while(!right.empty()) sb.append(right.pop());
        System.out.println(sb);
        br.close();
    }
}
