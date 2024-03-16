import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Team implements Comparable<Team>{
    int teamID; //팀 번호
    int scoreSum; //최종 점수
    int submitCnt; //제출 횟수
    int submitTime; //마지막 제출 시간

    public Team(int teamID){
        this.teamID = teamID;
        this.scoreSum = 0;
        this.submitCnt = 0;
        this.submitTime = 0;
    }

    @Override
    public int compareTo(Team o){
        if(this.scoreSum == o.scoreSum && this.submitCnt == o.submitCnt) return this.submitTime - o.submitTime;
        if(this.scoreSum == o.scoreSum) return this.submitCnt - o.submitCnt;
        return o.scoreSum - this.scoreSum;
    }
}

public class BJ_S2_3758{
    static int[][] score; //팀-문제 점수 저장 배열
    static Team[] teams; //팀 정보 저장 배열
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());//테케 수
        for(int t=0; t<T; t++){
            //첫 입력
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); //팀의 수
            int K = Integer.parseInt(st.nextToken()); //문제의 수
            int myID = Integer.parseInt(st.nextToken()); //내 팀의 수
            int M = Integer.parseInt(st.nextToken()); //로그 수
            
            //초기화
            score = new int[N+1][K+1]; //문제 번호도 1부터 시작
            teams = new Team[N+1]; //팀 아이디는 1부터 시작
            for(int n=0; n<=N; n++) teams[n] = new Team(n);

            //점수들 입력
            for(int m=0; m<M; m++){
                st = new StringTokenizer(br.readLine());
                int teamID = Integer.parseInt(st.nextToken()); //팀 아이디
                int problem = Integer.parseInt(st.nextToken()); //문제 번호
                int pScore = Integer.parseInt(st.nextToken()); //얻은 점수
                score[teamID][problem] = Math.max(score[teamID][problem], pScore); //최대값으로 저장
                teams[teamID].submitCnt += 1;
                teams[teamID].submitTime = m;
            }//-----테케 하나 입력 완료-----

            //합 계산
            for(int n=1; n<=N; n++){
                int sum = 0;
                for(int k=1; k<=K; k++) sum += score[n][k];
                teams[n].scoreSum = sum;
            }

            //등수 계산
            Arrays.sort(teams);
            int cnt = 0;
            for(int n=0; n<N; n++){
                if(teams[n].teamID == myID) break;
                cnt++;
            }
            sb.append(cnt+1+"\n");
        }
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 풀이제출 -> 0~100점 사이의 점수
 * 저장: 팀 ID - 문제 번호 - 점수 (제출되는 순서)
 * 여러번 풀이 제출 가능 -> 그 중 최고 점수가 최종 점수
 * 팀 점수: 각 문제에 대해 받은 점수의 총합
 * 등수: (내 팀보다 높은 점수를 받은 팀의 수)+1
 * 점수가 동일할 때: 풀이 제출 횟수 확인 -> 마지막 제출 순서 확인
 * 순위를 구하기
 * N: 팀의 수 / K: 문제의 수 / T: 내 팀의 ID / M: 로그 엔트리의 개수
 * 팀 ID - 문제 번호 - 획득 점수 (제출 순대로)
 * -----------------------------------
 * 동일한 등수가 여럿 있을 수 없는 구조 -> 등수대로 정렬해서 등수 바로 찾을 수 있음
 * 행이 팀, 열이 문제번호, 칸이 점수가 되도록 2차원 배열
 * 같은 문제에 대해 풀이가 여럿이면 큰 값으로 저장되도록 -> 점수 총합 계산
 * Team 객체 : 점수 총합 / 풀이 횟수 / 마지막 제출(m번 for문돌려서 인덱스 저장)
 */