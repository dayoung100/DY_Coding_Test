import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//baekjoon S3 1966 프린터 큐
public class BJ_S3_1966 {
	
	static class Docu implements Comparable<Docu>{
		int pos; //원래 위치
		int pri; //중요도
		public Docu(int pos, int pri) {
			super();
			this.pos = pos;
			this.pri = pri;
		}
		@Override
		public int compareTo(Docu o) {
			return o.pri-this.pri;
		}
	}
	
	static Docu[] prios;
	static int N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t=0; t<tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			Docu[] documents = new Docu[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) documents[i] = new Docu(i, Integer.parseInt(st.nextToken()));
			//----------input end---------------
			prios = Arrays.copyOf(documents, N);
			Arrays.sort(prios); //우선순위에 따른 정렬 -> 큐에 있는 가장 높은 우선순위 확인용
			System.out.println(simul(documents)+1);
		}
	}

	private static int simul(Docu[] documents) {
		Queue<Docu> q = new ArrayDeque<>();
		for (Docu docu : documents) q.offer(docu);
		int idx = 0; //documents와 비교용 인덱스=빼는 순서
		while(!q.isEmpty()){
			Docu cur = q.poll();
			//우선순위 작으면 뒤로 넣기
			if(cur.pri < prios[idx].pri) q.offer(cur);
			//우선순위 같은데(클 리는 없음)
			else {
				if(cur.pos == M) return idx; //찾는 그녀석임 -> 몇번쨰인지 출력
				else idx++; //찾는 그녀석은 아님->걔는 뽑고 나머지도 계속 뽑아봐야
			}
		}
		return N;
	}

}