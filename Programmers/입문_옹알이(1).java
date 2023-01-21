class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        String[] able = {"aya", "ye", "woo", "ma"};
        
        for(int b=0; b<babbling.length; b++){
            for(int i=0; i<4; i++){
                babbling[b] = babbling[b].replace(able[i],"0");
            }
            babbling[b] = babbling[b].replace("0","");
            if(babbling[b].length() == 0) answer++;
        }
        return answer;
    }
}