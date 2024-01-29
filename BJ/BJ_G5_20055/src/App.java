import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BJ G5 20055 컨베이어 벨트 위의 로봇
class Block {
    int durability; //내구도
    boolean isRobot; //로봇 있느냐 없느냐
    public Block (int durability, boolean isRobot){
        this.durability = durability;
        this.isRobot = isRobot;
    }
}

public class App {
    static int N, K;
    static Block[] belt; //내구도 배열
    static int zeroBlock = 0; //내구도가 0이 된 칸 수
    static int upPos, downPos; //올리는 위치, 내리는 위치
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        upPos = 1; downPos = N;
        belt = new Block[2*N+1];
        for(int n=1; n<=2*N; n++) belt[n] = new Block(Integer.parseInt(st.nextToken()), false);
        //----- 입력 완료 -----
        int step = 1; //단계
        while(true){
            turn(); //1. 벨트가 한 칸 회전
            move(); //2. 회전 방향으로 이동할 수 있다면 한 칸 이동
            up(); //올리는 위치에 로봇 올리기
            if(zeroBlock >= K) break; //4. 내구도가 0인 칸이 K개 이상이면 종료
            step += 1;
        }
        sb.append(step);
        System.out.println(step);
        br.close();
    }

    public static void turn(){
        //올리는 위치, 내리는 위치 조정
        upPos = upPos==1 ? 2*N : upPos-1;
        downPos = downPos==1 ? 2*N : downPos-1;
        down();
    }

    public static void move(){
        //내리는 위치~올리는 위치 사이에 로봇이 위치
        //가장 먼저 올라간 로봇 => 내리는 위치에 가까운 로봇
        //i가 하나 흐를 때마다 로봇 인덱스를 뒤로
        int idx = downPos; //시작은 내리는 위치
        for(int i=1; i<N; i++){
            int nextPos = idx; //로봇이 향할 위치
            idx = idx == 1 ? 2*N : idx-1; //현재 로봇 위치(다음 반복에서 로봇이 향할 위치가 됨)
            if(!belt[idx].isRobot) continue; //로봇이 없는 칸은 패스
            if(belt[nextPos].isRobot) continue; //다음칸에 로봇 있으면 패스
            if(belt[nextPos].durability == 0) continue; //다음칸이 내구도 없으면 패스
            belt[nextPos].isRobot = true;
            belt[idx].isRobot = false;
            belt[nextPos].durability -= 1;
            if(belt[nextPos].durability == 0) zeroBlock += 1; //내구도 0인 블럭 수 증가
            down(); //내릴 애 있는지 체크 후 내리기
        }
    }

    public static void up(){
        if(belt[upPos].durability > 0){
            belt[upPos].isRobot = true;
            belt[upPos].durability -= 1; //내구도 깎기
            if(belt[upPos].durability == 0) zeroBlock += 1; //내구도 0인 블럭 수 증가
        }
    }

    //내리는 위치에 도달하면 즉시 내림
    public static void down(){
        if(belt[downPos].isRobot) belt[downPos].isRobot = false;
    }
}
/* 
 * N: 컨베이어 벨트 길이 / 2N: 벨트 길이이자 칸 / K: 내구도 종료조건
 * 1번칸: 올리는 위치 / N번칸: 내리는 위치
 * 내구도: Ai, 로봇을 올리거나 로봇이 이동하면 1만큼 감소
 * [발생]
 * 1. 벨트가 한 칸 회전(로봇과 함께)
 * 2. 먼저 올라간 로봇부터 회전 방향으로 이동할 수 있다면 한 칸 이동, 없다면 그대로
 * (이동 조건: 이동하려는 칸에 로봇이 없음, 내구도가 1이상 있음)
 * 3. 올리는 위치에 로봇 올리기(내구도가 0이 아니라면)
 * 4. 내구도가 0인 칸이 K개 이상이면 종료, 아니면 1로 돌아감
 * [출력] 종료시 몇번째 단계가 진행중이었는가
 * ---------------------------------
 * 구현...
 */