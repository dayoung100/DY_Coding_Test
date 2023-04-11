import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//swea 5658. [모의 SW 역량테스트] 보물상자 비밀번호
public class SWEA_5658 {
	
	static int N, K;
	static char[] binary;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			binary = br.readLine().toCharArray();
			//-------------input end--------------
			int slice = N / 4; //한변의 길이
			List<Integer> list = new ArrayList<>();
			Queue<Character> q = new ArrayDeque<>();
			for(int i=0; i<binary.length; i++) q.offer(binary[i]);
			int cnt = 0; //회전 횟수 slice번
			while(cnt++ < slice) {
				for(int i=0; i<4; i++) { //변은 4개
					char[] temp = new char[slice];
					for(int j=0; j<slice; j++) { //한 변에 slice자리의 16진수
						temp[j] = q.poll();
						q.offer(temp[j]);
					}
					int decimal = BiToDeci(temp, slice);
					if(!list.contains(decimal)) list.add(decimal);
				}
				q.offer(q.poll()); //회전
			}
			Collections.sort(list, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2-o1;
				}
				
			});
			System.out.println("#"+tc+" "+list.get(K-1));
		}
	}

	private static int BiToDeci(char[] temp, int slice) {
		int deci = 0;
		int cnt = 0;
		for(int i=slice-1; i>=0; i--) {
			int tempD = 0;
			switch (temp[i]) {
			case 'A':
				tempD = 10;
				break;
			case 'B':
				tempD = 11;
				break;
			case 'C':
				tempD = 12;
				break;
			case 'D':
				tempD = 13;
				break;
			case 'E':
				tempD = 14;
				break;
			case 'F':
				tempD = 15;
				break;
			default:
				tempD = temp[i] - '0';
				break;
			}
			deci += tempD * (int)Math.pow(16, cnt);
			cnt++;
		}
		return deci;
	}

}
/* N: 입력되는 숫자의 개수 / K: 찾아야할 수는 K번째로 큰 수
 * 시계방향 순으로 16진수를 형성, 한 변에 모인 숫자가 하나의 수
 * 회전해가며 만들어지는 모든 숫자들 중 K번째로 큰 수를 10진수로 변환해 출력
 * 이때, 중복은 카운트하지 않도록 주의
 * ------------------------
 */