import java.util.*;

public class PG_1_pccp1 {
    public static void main(String[] args) throws Exception{
        int[] bandage = new int[] {5, 1, 5};
        int health = 30;
        int[][] attacks = new int[][]{{2, 10}, {9,15}, {10,5}, {11,5}};
        Solution.solution(bandage, health, attacks);
    }
}

class Solution {
    public static int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int healTime = 0; //직전 붕대감기 시작 시간
        int nowHp = health; //현재체력
        //공격 들어오는 시간을 기준으로
        for(int i=0; i<attacks.length; i++){
            int attackTime = attacks[i][0]; //공격 시간
            int damage = attacks[i][1]; //피해량
            int continuousTime = attackTime - healTime-1; //연속 성공 시간
            nowHp += continuousTime * bandage[1]; //초당 회복량만큼 회복
            nowHp += (continuousTime/bandage[0])*bandage[2]; //연속 성공 시간만큼 추가 회복
            nowHp = nowHp>health ? health : nowHp; //최대 체력 이상 회복하면 최대체력으로 제한하기
            nowHp -= damage; //대미지
            //체력이 0이하가 되면 사망
            if(nowHp <=0) {
                answer = -1;
                break;
            }
            healTime = attackTime;
        }
        answer = answer!=-1 ? nowHp : -1;
        return answer;
    }
}
/*
[붕대감기]
붕대 감기(t초 동안) -> 1초마다 x만큼 회복
연속성공시간이 t초 -> y만큼 추가 회복
기술이 취소당하거나 기술이 끝나면 다시 사용, 연속 성공시간 0으로 초기화
[몬스터]
공격당하면 기술 취소됨
정해진 피해량만큼 체력 감소
[체력]
최대 체력 넘을 수 없음
0 이하가 되면 사망, 더이상 회복 불가
[인풋]
기술 정보: bandage [시전시간, 1초당 회복량, 추가 회복량]
최대 체력: health
몬스터 공격패턴: attacks[공격 시간, 피해량]
[출력]
끝까지 생존할 수 있는가?
가능 -> 모든 공격 종료 후 남은 체력
불가능 -> -1
[풀이]
구현
*/