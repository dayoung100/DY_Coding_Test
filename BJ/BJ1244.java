import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ1244 {
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	//스위치 배열
	static int[] sw;

	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken()); //스위치개수
		
		st = new StringTokenizer(bf.readLine());
		sw = new int[n];
		for(int i=0; i<n; i++)
			sw[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(bf.readLine());
		int st_num = Integer.parseInt(st.nextToken()); //학생수
		
		for(int i=0; i<st_num; i++) {
			st = new StringTokenizer(bf.readLine());
			int bg = Integer.parseInt(st.nextToken());
			int sw_num = Integer.parseInt(st.nextToken());
			onoff(bg, sw_num);
			
		}
		
		for(int j=0; j<sw.length; j++) {
			if(j>0 && j%20 == 0) bw.write("\n");
			bw.write(sw[j] + " ");
		}
		
		bw.flush();
		bw.close();
	}

	private static void onoff(int bg, int sw_num) {
		//남학생 - 배수 토글
		if(bg == 1) {
			for (int i = 1; i <= sw.length; i++) {
				if(i%sw_num != 0) continue;
				toggle(i-1);
			}
		}
		//여학생 - 좌우 대칭 구간 토글
		else if(bg == 2) {
			int cnt = 1;
			while(true) {
				if(sw_num-cnt <= 0 || sw_num+cnt > sw.length) break;
				if(sw[sw_num-cnt-1] != sw[sw_num+cnt-1]) break;
				
				toggle(sw_num-cnt-1);
				toggle(sw_num+cnt-1);
				cnt++;	
			}
			toggle(sw_num-1);
		}
	}

	private static void toggle(int index) {
		if(sw[index] == 0) sw[index] = 1;
		else if(sw[index] == 1) sw[index] = 0;
	}
}