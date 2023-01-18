import java.util.Arrays;

class Solution {
    public int solution(String s) {
        
        int answer = 0;
        
        char[] s_c = s.toCharArray();
        
        int x_sum = 0;
        int nx_sum = 0;
        int idx = 0;
    
        char x = s_c[0];
        x_sum++;
        idx++;
        
        while(true){
            //더 읽을 글자가 없을 때 종료
            if(idx >= s_c.length-1) break;
        
            //글자보고 계산해주고
            if(s_c[idx]==x) x_sum++;
            else nx_sum++;
            
            //분해되는지 확인
            if(x_sum == nx_sum && x_sum!=0){
                answer++;
                x = s_c[idx+1];
                x_sum = 0;
                nx_sum = 0;
            }
            idx++;
        }
        answer++;
        return answer;
    }
}