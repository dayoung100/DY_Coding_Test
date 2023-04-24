import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//baekjoon S5 11651 좌표 정렬하기 2
public class BJ_S5_11651 {
	static class Pos implements Comparable<Pos>{
		int x;
		int y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Pos o) {
			if(this.y == o.y) return Integer.compare(this.x, o.x);
			return Integer.compare(this.y, o.y);
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Pos[] position = new Pos[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			position[i] = new Pos(x, y);
		}
		Arrays.sort(position);
		for(int i=0; i<N; i++) System.out.println(position[i].x + " "+position[i].y);
	}

}