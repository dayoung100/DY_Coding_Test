import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int cnt;
    int length;
    String text;
    
    public Node(int cnt, int length, String text){
        this.cnt = cnt;
        this.length = length;
        this.text = text;
    }

    @Override
    public int compareTo(Node o) {
        if(this.cnt == o.cnt && this.length == o.length) return this.text.compareTo(o.text);
        if(this.cnt == o.cnt) return o.length - this.length;
        return o.cnt - this.cnt;
    }
}

//BJ S3 20920 영단어 암기는 괴로워
public class App {
    static int N, M;
    static HashMap<String, Integer> map = new HashMap<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            String word = st.nextToken();
            if(!map.containsKey(word)) map.put(word, 1);
            else {
                int cnt = map.get(word);
                map.remove(word);
                map.put(word, cnt+1);
            }
        }//-----입력 완료-----
        for(String key : map.keySet()) {
            if(key.length() < M) continue;
            pq.add(new Node(map.get(key), key.length(), key));
        }
        while(!pq.isEmpty()) sb.append(pq.poll().text+"\n");
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 단어장 순서대로 출력하기
 * 1. 자주 나오면 앞
 * 2. 단어 길이가 길면 앞
 * 3. 알파벳 사전순
 * 총 단어는 N개, 그 중에서 길이가 M이상(M은 1~10)인 단어만 등록
 * --------------------------
 * M보다 작으면 애초에 안넣기
 * 1. compareTo만들고 pq 이용하기+map
 * 단어-횟수를 키-값으로 하는 맵
 * 입력 다 받으면 맵 순회하면서 pq에 넣고 출력
 */