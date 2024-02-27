import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S2_15663 {
    static int N, M;
    static int[] arr;
    static boolean[] visited;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        visited = new boolean[N];
        for(int n=0; n<N; n++) arr[n] = Integer.parseInt(st.nextToken());
        //-----입력 완료----- 
        Arrays.sort(arr);
        numbers = new int[M];
        solution(0);
        System.out.println(sb);
        br.close();
    }

    public static void solution(int cnt){
        if(cnt == M) {
			for(int i=0; i<cnt; i++) sb.append(numbers[i]+" ");
            sb.append("\n");
			return;
		}
        int before = 0;
		for(int i=0; i<N; i++) {
            if(visited[i]) continue;
            if(before == arr[i]) continue;
			numbers[cnt] = arr[i];
            visited[i] = true;
            before = arr[i];
			solution(cnt+1);
            visited[i] = false;
		}
    }
}
