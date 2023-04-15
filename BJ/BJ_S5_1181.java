import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

//baekjoon S5 1181 단어 정렬
public class BJ_S5_1181 {

	static int N;
	static String[] words;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		words = new String[N];
		for(int i=0; i<N; i++) words[i] = br.readLine();
		//---------------input end---------------
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() - o2.length() == 0) return o1.compareTo(o2);
				return o1.length() - o2.length();
			}
		});
		String temp = "";
		for(int i=0; i<N; i++) {
			String now = words[i];
			if(!now.equals(temp)) System.out.println(words[i]);
			temp = now;
		}
	}

}
/* N개의 단어 정렬
 * - 길이가 짧음 / 같은 길이면 사전순
 * - 중복 제거 필요
 */