import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_G3_17135_캐슬디펜스 {
    static class Enemy{
        int r, c;

        public Enemy(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
        
    }
    
    static int N, M, D, ans;
    static int[][] map;
    static List<Enemy> elist;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        elist = new ArrayList<>();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input;
                if(input == 1) elist.add(new Enemy(i,j));
                
            }
        }

        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                for (int k = j + 1; k < M; k++) {
                    // 궁수가 쏠 사람을 정한다.
                    List<Enemy> copyList = new ArrayList<>();
                    for(Enemy e : elist) copyList.add(new Enemy(e.r, e.c));
                    killEnemy(copyList, new int[] {i,j,k});
                }
            }
        }
        System.out.println(ans);
    }

    private static void killEnemy(List<Enemy> copyList, int[] archer) {
        int cnt = 0;
        while(!copyList.isEmpty()) { //game
            Set<Enemy> killset = new HashSet<>();
            for(int i=0; i<archer.length; i++) {
                Enemy target = find(archer[i], copyList);
                if(target != null) killset.add(target);
            }
            cnt += killset.size();
            copyList.removeAll(killset);
            downEnemy(copyList);
        }
        ans = Math.max(cnt, ans);
        
    }

    private static void downEnemy(List<Enemy> copyList) {
        for(int i=0; i<copyList.size(); i++) {
            copyList.get(i).r += 1;
            if(copyList.get(i).r == N) {
                copyList.remove(i);
                i--;
            }
        }
    }

    private static Enemy find(int archerIdx, List<Enemy> copyList) {
        int minD = 1<<30, minC = 51;
        Enemy target = null;
        
        for(Enemy e: copyList) {
            int dist = N - e.r + Math.abs(e.c - archerIdx);
            if(dist > D || dist > minD) continue;
            
            if(dist < minD) {
                minD = dist;
                minC = e.c;
                target = e; 
            }
            if(minC > e.c) {
                minC = e.c;
                target = e;
            }
        }
        
        return target;
    }
}