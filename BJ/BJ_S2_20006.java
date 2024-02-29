import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

//BJ S2 20006 랭킹전 대기열
class Player {
    int lvl;
    String name;
    public Player(int lvl, String name){
        this.lvl = lvl;
        this.name = name;
    }
}

class Room{
    int max; //M
    int baseLvl; //기준 레벨
    String state; //Started!/Waiting!
    ArrayList<Player> playerList;
    public Room(int max, int baseLvl, Player p){
        this.max = max;
        this.baseLvl = baseLvl;
        this.playerList = new ArrayList<>();
        this.playerList.add(p);
        this.state = max == 1 ? "Started!": "Waiting!";
    }
}

public class BJ_S2_20006 {
    static int P, M;
    static ArrayList<Room> list = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int p=0; p<P; p++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();
            //매칭 가능한 방이 있는지 확인
            boolean entered = false;
            for(int i=0; i<list.size(); i++){
                //시작한 방에는 못들어감
                if(list.get(i).state == "Started!") continue;
                //가능한 방 있음 -> 입장
                if(l<=list.get(i).baseLvl+10 && l>=list.get(i).baseLvl-10){
                    list.get(i).playerList.add(new Player(l, n)); //인원추가
                    entered = true;
                    //최대 인원에 도달? -> 게임 시작
                    if(list.get(i).playerList.size() == M) list.get(i).state = "Started!";
                    break;
                }
            }
            //가능한 방 없음 -> 생성
            if(!entered) list.add(new Room(M, l, new Player(l, n)));
        }
        //출력 준비
        for(int i=0; i<list.size(); i++){
            sb.append(list.get(i).state+"\n"); //상태
            //정렬
            Collections.sort(list.get(i).playerList, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2){
                    return p1.name.compareTo(p2.name);
                }
            });
            //플레이어
            for(int j=0; j<list.get(i).playerList.size(); j++){
                Player p = list.get(i).playerList.get(j); 
                sb.append(p.lvl+" "+p.name+"\n");
            }
        }
        System.out.println(sb);
        br.close();
    }  
}
/* 
 * [매칭]
 * 1. 입장 신청 
 * 1-1. 매칭가능한 방 없음 
 *      -> 방생성 -> 플레이어기준 -10~+10 입장 가능
 * 1-2. 매칭가능한 방 있음
 *      -> 먼저생성된 방으로 입장 -> 정원찰때까지 대기 -> 모두 차면 시작
 * P: 플레이어 수(1~300)
 * N: 플레이어 닉네임(중복x, 16자 이하)
 * l: 플레이어 레벨(1~500)
 * M: 방 한개의 정원
 * [출력] 최종 만들어진 방 상태와 입장 플레이어
 * ----------------------------
 * 시뮬 -> player, room이란 클래스, room의 리스트 
 * - room은 player의 리스트를 가짐
 * - player는 닉네임 순으로 정렬
 * - 방은 생성된 순 -> 리스트에 그냥 넣은 순서로
 */