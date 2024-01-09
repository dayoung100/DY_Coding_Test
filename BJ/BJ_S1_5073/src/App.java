import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int[] tri = new int[] {-1,-1,-1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            tri[0] = Integer.parseInt(st.nextToken());
            tri[1] = Integer.parseInt(st.nextToken());
            tri[2] = Integer.parseInt(st.nextToken());
            //-----입력 완-----
            if(tri[0] == 0 && tri[1] == 0 && tri[2] == 0) break;
            solution();
        }
        System.out.println(sb);
        br.close();
    }

    public static void solution(){
        //가장 긴 변의 길이
        int maxLength = Math.max(tri[0], Math.max(tri[1], tri[2]));
        //삼각형의 조건을 만족하는가
        if(tri[0]+tri[1]+tri[2]-maxLength <= maxLength) sb.append("Invalid\n");
        //세 변의 길이가 모두 같은가
        else if(tri[0] == tri[1] && tri[1] == tri[2]) sb.append("Equilateral\n");
        //두 변의 길이만 같은가
        else if(tri[0] == tri[1] || tri[0] == tri[2] || tri[1] == tri[2]) sb.append("Isosceles\n");
        //그 외(세 변의 길이가 모두 다른가)
        else sb.append("Scalene\n");
    }
}
