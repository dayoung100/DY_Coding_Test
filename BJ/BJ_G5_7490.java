import java.util.*;
import java.io.*;

public class BJ_G5_7490 {
    private static int N;
    private static int[] numbers;
    private static String[] between;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //input end
            numbers = new int[N];
            between = new String[N-1];
            for(int n=0; n<N; n++) numbers[n] = n+1;
            solution(0); //완탐
            System.out.println();
        }
    }

    static void solution(int index){
        //기저조건
        if(index == N-1){
            //식 만들기
            String str = String.valueOf(numbers[0]);
            for(int i=0; i<N-1; i++){
                str+=between[i];
                str+=String.valueOf(numbers[i+1]);
            }
            //식 계산 결과 확인하기
            int sum = 0;
            int num = 0;
            char sign = '+'; //첫 숫자를 sum에 더할거니까 +로 시작
            for(int i=0; i<str.length(); i++){
                char c = str.charAt(i);
                if(Character.isDigit(c)) num = num*10 + (c-'0');
                //c가 연산자일때와 문자열의 마지막일 떄를 한번에 처리
                if(!Character.isDigit(c) || i == str.length()-1){
                    if(c == ' ') continue; //공백은 패스
                    if(sign == '+') sum += num;
                    else if(sign == '-') sum -= num;
                    sign = c;
                    num = 0;
                }
            }
            if(sum == 0) System.out.println(str);
            return;
        }
        between[index] = " ";
        solution(index+1);
        between[index] = "+";
        solution(index+1);
        between[index] = "-";
        solution(index+1);
    }
}
/* 
 * 1~N까지의 오름차순 수열에
 * +, -, 공백(숫자 이어붙이기) 중 하나를 숫자 사이에 삽입
 * 그 수식의 결과가 0이 되는 모든 수식을 출력
 * 출력 순서는 아스키 순서(공백 -> + -> -)
 */