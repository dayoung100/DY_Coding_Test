import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Person{
    int weight, height;

    Person(int weight, int height){
        this.weight = weight;
        this.height = height;
    }
}

public class App {
    static int N;
    static int[] ans;
    static Person[] people;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ans = new int[N];
        people = new Person[N];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            people[n] = new Person(w, h);
        }//-----입력 완료-----
        for(int i=0; i<N; i++){
            Person target = people[i];
            int cnt = 1;
            for(int j=0; j<N; j++){
                if(i==j) continue;
                if(people[j].weight > target.weight && people[j].height > target.height) cnt++;
            }
            ans[i] = cnt;
        }
        for(int i=0; i<ans.length; i++) sb.append(ans[i]+" ");
        System.out.println(sb);
        br.close();
    }
}
/* 
 * (몸무게, 키) -> 몸무게도 키도 커야 더 큰 것으로 인정
 * 덩치 등수를 순서대로 출력
 * -----------------------
 * N <= 50 -> 이중포문
 */