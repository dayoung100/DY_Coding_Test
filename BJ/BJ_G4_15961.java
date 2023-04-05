import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

//baekjoon G4 15961 회전 초밥
public class BJ_G4_15961 {
	
	static int N, D, K, C, max;
	static Queue<Integer> q = new ArrayDeque<>(); //먹을 초밥들, K개를 넘지 않음
	static boolean findAns; //답을 찾았는가 -> 초밥 종류 수가 k+1개가 되었을 때
	static int[] front, back; //앞에서 K-1, 뒤에서 K-1개를 저장하는 배열
	static int[] sNum; //select에 들어가있는 초밥의 개수를 종류별로 관리
	static HashSet<Integer> set = new HashSet<>(); //종류의 개수 확인 용

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sNum = new int[D+1];
		front = new int[K-1];
		back = new int[K-1];
		int idx = 0;
		for(int i=0; i<N; i++) { //접시 수 N 만큼 입력 받기
			int sushi = Integer.parseInt(br.readLine());
			//순환하니까 맨 앞, 맨 뒤 K개는 저장해두고 나중에 계산
			if(i<K-1) front[i] = sushi;
			if(i>N-K) back[idx++] = sushi;
			if(findAns) continue; //이미 k+1개의 종류를 먹을 수 있다면 pass
			
			if(q.size() < K) { //큐가 꽉 안 찼음
				add(sushi);
			} else { //큐가 꽉 찼음 -> 빼는 작업, 가짓수 계산 필요
				add(sushi);
				remove();
				calcCnt();
			}
			if(max == K+1) {
				findAns = true;
				System.out.println(K+1);
				return;
			}
		}//--------input end-------
		//루프가 가능하니까 그 부분도 처리해줘야
		//다시 체크하기 위한 초기화
		q = new ArrayDeque<>();
		sNum = new int[D+1];
		set = new HashSet<>();
		
		for(int j=0; j<K-1; j++) add(back[j]);
		add(front[0]); //큐 채우기
		calcCnt();
		
		for(int i=1; i<K-1; i++) {
			add(front[i]);
			remove();
			calcCnt();
		}
		
		System.out.println(max);
	}

	private static void remove() {
		int pop = q.poll();
		sNum[pop]--;
		if(sNum[pop] == 0) set.remove(pop);
	}

	private static void add(int sushi) {
		q.offer(sushi);
		if(sNum[sushi] == 0) set.add(sushi);
		sNum[sushi]++;
	}

	private static void calcCnt() {
		int cnt = set.size();
		if(sNum[C] == 0) cnt += 1;
		max = Math.max(max, cnt);
	}
}
/* 1. k개의 접시를 연속해서 먹는다
 * 2. 쿠폰의 초밥은 확정적으로 먹는다
 * => 최대한 다양한 종류의 초밥을 먹을 때, 먹을 수 있는 초밥의 가짓수
 * -------------------------------
 * 순환이 존재
 * 어디에서 출발해서 k개를 먹느냐 -> 접시의 수 N개의 경우의 수
 * N<=3백만 -> 배열x... 입력 받으면서 카운트
 * 크기가 k인 1차원 배열이나.. 큐 -> 입력 받으면서 순서대로 큐에 넣고 빼며 먹는 접시 갱신
 * -> 그때마다 초밥 종류 수 카운트, max값으로 업데이트
 * ** 쿠폰이랑 겹치는 메뉴인지 확인 -> 안겹치면 cnt+1
 * 초밥종류수는 최대 k+1 -> k+1이 나오면 연산은 종료, 입력만 받고  k+1을 리턴
 * ------------------------------
 * 종류가 겹치는 것을 어떻게 확인?
 * set? -> set.size = cnt
 * 		-> 넣을때는 그냥 넣는데 큐에서 뺄 때는.. -> 별도의 리스트로 종류별 개수만 관리?
 */