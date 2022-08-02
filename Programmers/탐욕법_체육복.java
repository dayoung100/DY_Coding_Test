import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] students = new int[n];
        
        for(int i=0;i<n;i++){
            students[i] = 1;
        }
        
        for(int l:lost){
            students[l-1]--;
        }
        
        for(int r:reserve){
            students[r-1]++;
        }
        
        //빌려주기
        for(int j=0;j<n;j++){
            if(students[j]==0){
                if(j>0 && students[j-1] > 1){
                    students[j-1]--;
                    students[j]++;
                }
                if(j<n-1 && students[j+1] > 1){
                    students[j+1]--;
                    students[j]++;
                }
            }   
        }
        
        //count
        for(int k=0;k<n;k++){
            if(students[k]>0){
                answer++;
            }
        }
        
        return answer;
    }
}