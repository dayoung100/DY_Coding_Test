import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] str = new String[numbers.length];
        for(int i=0;i<str.length;i++){
            str[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(str, new Comparator<String>(){
            public int compare(String str1, String str2){
                return (str2+str1).compareTo(str1+str2);
            }
        });
        
        if(str[0].equals("0")){
            return "0";
        }
        
        for(String s:str){
            answer += s;
        }
        
        return answer;
    }
}