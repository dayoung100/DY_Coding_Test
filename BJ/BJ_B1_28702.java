import java.util.*;
import java.io.*;

public class BJ_B1_28702 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] outputs = new String[3]; //출력값들
        int num = Integer.MAX_VALUE; //i의 값
        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            outputs[i] = st.nextToken(); //문자열
            if(num != Integer.MAX_VALUE) {
                num++;
                continue;
            }
            try{
                num = Integer.parseInt(outputs[i]);
            }catch(NumberFormatException e){
                continue;
            }
        }
        num++; //다음에 올 문자열이기 때문에 +1
        if(num%3==0 && num%5==0){
            System.out.println("FizzBuzz");
        }else if(num%3==0) {
            System.out.println("Fizz");
        }else if(num%5==0){
            System.out.println("Buzz");
        }else System.out.println(num);
    }
}
/*
 * 3배수 5배수 -> 3개의 입력이면 숫자는 무조건 하나 포함될 수 밖에 없음
 * 그 숫자로 다음 i값이 뭘지 알아내기
 * 숫자인지 아닌지 체크 -> try-catch 이용
 */