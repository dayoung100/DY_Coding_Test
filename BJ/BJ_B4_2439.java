import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ B4 2439 별 찍기 - 2
public class BJ_B4_2439{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        for(int i=N-1; i>=0; i--){
            for(int j=0; j<i; j++) System.out.print(" ");
            for(int k=0; k<N-i; k++) System.out.print("*");
            System.out.println();
        }
    }
}