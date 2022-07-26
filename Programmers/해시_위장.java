import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        HashMap<String, Integer> mp = new HashMap<String, Integer>();
        for(int i=0;i<clothes.length;i++){
            mp.put(clothes[i][1], mp.getOrDefault(clothes[i][1], 0)+1);
        }
        String[] keysets = mp.keySet().toArray(new String[mp.size()]);
        int mul=1;
        for(String k:keysets){
            mul = mul*(mp.get(k)+1);
        }

        answer = mul - 1;
        return answer;
    }
}