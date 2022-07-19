import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int p=0; p<commands.length; p++){
            int i = commands[p][0];
            int j = commands[p][1];
            int k = commands[p][2];
            int[] cut = new int[j-i+1]; //자른 배열

            for(int q=i-1;q<j;q++){
                cut[q-i+1] = array[q];   
            }

            Arrays.sort(cut);
            answer[p] = cut[k-1];
        }
        
        
        return answer;
    }
}