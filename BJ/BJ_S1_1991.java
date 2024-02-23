import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

//BJ S1 1991 트리 순회
class Node{
    String root, left, right;
    Node(String root, String left, String right){
        this.root = root;
        this.left = left;
        this.right = right;
    }
}

public class BJ_S1_1991 {
    static int N;
    static HashMap<String, Node> nodes = new HashMap<>();
    static String[] ans1, ans2, ans3;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            String root = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();
            nodes.put(root, new Node(root, left, right));
        }//-----입력 완료-----
        solution1();
        solution2();
        solution3();
        for(int i=0; i<N; i++) sb.append(ans1[i]);
        sb.append("\n");
        for(int i=0; i<N; i++) sb.append(ans2[i]);
        sb.append("\n");
        for(int i=0; i<N; i++) sb.append(ans3[i]);
        System.out.println(sb);
        br.close();
    }

    //전위순회
    public static void solution1(){
        //초기화
        ans1 = new String[N];
        Stack<Node> stack = new Stack<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        for(String key:nodes.keySet()) visited.put(key, false);
        //전위 순회
        int cnt = 0;
        Node cur = nodes.get("A"); //초기 노드 A
        stack.push(cur);
        while(cnt<N || stack.size() > 0){
            cur = stack.peek();
            //1)루트
            if(!visited.get(cur.root)){
                ans1[cnt] = cur.root;
                cnt++;
                visited.replace(cur.root, true);
            }
            String left = cur.left;
            String right = cur.right;
            //2)왼
            if(!left.equals(".") && !visited.get(left)) stack.push(nodes.get(left));
            //3)오
            else if(!right.equals(".") && !visited.get(right)) stack.push(nodes.get(right));
            //왼 오 처리 끝나면 볼 일 없는 노드
            else stack.pop();
        }
    }

    //중위 순회
    public static void solution2(){
        ans2 = new String[N];
        Stack<Node> stack = new Stack<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        for(String key:nodes.keySet()) visited.put(key, false);
        //전위 순회
        int cnt = 0;
        Node cur = nodes.get("A"); //초기 노드 A
        stack.push(cur);
        while(cnt<N || stack.size() > 0){
            cur = stack.peek();
            String left = cur.left;
            String right = cur.right;
            //1)왼
            if(!left.equals(".") && !visited.get(left)) stack.push(nodes.get(left));
            //2)루트
            else if(!visited.get(cur.root)){
                ans2[cnt] = cur.root;
                cnt++;
                visited.replace(cur.root, true);
            }
            //3)오
            else if(!right.equals(".") && !visited.get(right)) stack.push(nodes.get(right));
            //왼 오 처리 끝나면 볼 일 없는 노드
            else stack.pop();
        }
    }

    //중위 순회
    public static void solution3(){
        ans3 = new String[N];
        Stack<Node> stack = new Stack<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        for(String key:nodes.keySet()) visited.put(key, false);
        //전위 순회
        int cnt = 0;
        Node cur = nodes.get("A"); //초기 노드 A
        stack.push(cur);
        while(cnt<N || stack.size() > 0){
            cur = stack.peek();
            String left = cur.left;
            String right = cur.right;
            //1)왼
            if(!left.equals(".") && !visited.get(left)) stack.push(nodes.get(left));
            //2)오
            else if(!right.equals(".") && !visited.get(right)) stack.push(nodes.get(right));
            //3)루트
            else if(!visited.get(cur.root)){
                ans3[cnt] = cur.root;
                cnt++;
                visited.replace(cur.root, true);
            }
            //처리 끝나면 볼 일 없는 노드
            else stack.pop();
        }
    }
}
/* 전위: 루트-왼-오
 * 중위: 왼-루트-오
 * 후위: 왼-오-루트
 * 항상 A가 루트 노드
 * ------------------
 * 노드 클래스 정의 -> 루트, 왼, 오
 * 방문체크/트리구조를 위한 스택/출력 순서 담을 배열
 */
