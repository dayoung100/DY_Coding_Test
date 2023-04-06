import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

//SWEA 2382. [모의 SW 역량테스트] 미생물 격리
public class SWEA_2382 {
	//미생물 클래스
	static class Micro implements Comparable<Micro>{
		int r, c, dir;
		long num;

		public Micro(int r, int c, long num, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
			this.dir = dir; //상: 1, 하: 2, 좌: 3, 우: 4
		}

		@Override
		public int compareTo(Micro o) {
			if(this.r == o.r && this.c == o.c) return (int) (this.num - o.num);
			if(this.r == o.r) return this.c - o.c;
			return this.r - o.r;
		}
	}
	
	static int N, M, K; //N셀의개수 M격리시간 K군집의개수
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<Micro> mList; //미생물 리스트
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			mList = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				mList.add(new Micro(r, c, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}//-----------입력완료--------------
			
			int time = 0;
			while(time++ < M) {
				//M시간 동안 시뮬레이션
				//a. 이동
				for(int i=0; i<mList.size(); i++) move(i); // map과 list의 위치 갱신
				//a-1. 약품위치인가? -> 수 줄이고 방향 반대로
				checkBorder();
				Collections.sort(mList);
				//a-2. 합쳐졌는가? -> 수 올리고 방향 설정, 사라지는 군집 리스트에서 처리
				checkOverlap();
			}
			int remainM = 0;
			for(Micro m : mList) remainM += m.num;
			System.out.println("#"+tc+" "+remainM);
		}
	}

	//합쳐졌는지 체크
	private static void checkOverlap() {
		//직전값과 현재값이 겹쳐지는 지점을 찾음
		//찾았다면 중복군집들 모아두는 컬렉션에 add
		//그 안에서 가장 큰 수와 미생물 수의 합을 구하고
		//새로운 군집 객체에 방향과 수를 설정
		//removeAll로 중복군집 컬렉션과 겹치는 건 삭제
		//새로운 군집객체를 기존 리스트에 추가
		Micro before = new Micro(N+1, N+1, 0, 0);
		Micro now = null;
		HashSet<Micro> overlapSet = null; //중복군집들 모아두는 용
		List<HashSet<Micro>> setList = new ArrayList<>(); //위 set을 관리하는 리스트
		List<Micro> newMList = new ArrayList<>(); //추가할 군집들을 관리하는 리스트
		long sum = 0; //해당 위치의 미생물 수 모두 합하기
		for(int i=0; i<mList.size(); i++) {
			now = mList.get(i);
			//중복아님
			if(now.r != before.r || now.c != before.c) {
				//중복 카운트 중이던게 끝나면
				if(overlapSet != null) {
					Micro newM = new Micro(before.r, before.c, sum, before.dir);
					setList.add(overlapSet);
					overlapSet = null;
					newMList.add(newM);
				}
			}
			//중복임
			else {
				if(overlapSet == null) { //중복된 첫 군집
					overlapSet = new HashSet<>();
					sum = before.num;
				}
				overlapSet.add(before);
				overlapSet.add(now);
				sum += now.num;
			}
			before = now;
		}
		//set입력해주다가 끝났으면
		if(overlapSet != null) {
			Micro newM = new Micro(before.r, before.c, sum, before.dir);
			setList.add(overlapSet);
			overlapSet = null;
			newMList.add(newM);
		}
		for(HashSet<Micro> set : setList) mList.removeAll(set);
		mList.addAll(newMList);
	}

	//가장 자리 체크
	private static void checkBorder() {
		List<Integer> removeList = new ArrayList<>();
		for(int i=0; i<mList.size(); i++) {
			Micro m = mList.get(i);
			if(m.r == 0 || m.c == 0 || m.r == N-1 || m.c == N-1) { //가장자리
				//수를 절반으로 줄이기
				m.num = m.num / 2; //integer니까 소수점 이하는 버림됨
				if(m.num == 0) { //미생물 수가 0이되면 군집 사라짐
					removeList.add(i);
					continue;
				}
				//이동방향 반대로 돌리기
				switch (m.dir) {
				case 1: //상
					m.dir = 2;
					break;
				case 2: //하
					m.dir = 1;
					break;
				case 3: //좌
					m.dir = 4;
					break;
				case 4: //우
					m.dir = 3;
					break;
				}
				mList.set(i, m);
			}
		}
		mList.removeAll(removeList);
	}

	//군집 하나당 이동
	private static void move(int index) {
		Micro m = mList.get(index);
		m.r += dr[m.dir-1];
		m.c += dc[m.dir-1];
		mList.set(index, m);
	}
	
	//디버깅용
	static void printMList() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				List<Long> getNum = new ArrayList<>();
				List<Integer> getDir = new ArrayList<>();
				for(int k = 0; k < mList.size(); k++) {
					if(mList.get(k).r == i && mList.get(k).c == j) {
						getNum.add(mList.get(k).num);
						getDir.add(mList.get(k).dir);
					}
				}
				if(getNum.size() > 0) {
					for(int p=0; p<getNum.size();p++) System.out.print(getNum.get(p)+"("+getDir.get(p)+")");
					getNum.clear();
					getDir.clear();
				}
				else System.out.print("ㅁ");	
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
/* 군집의 위치, 미생물 수, 이동 방향
 * 1시간 마다 이동
 * 약품이 칠해진 곳에 도착하면 : 수 = 원래 수 /2(integer) , 이동방향 반대로
 * 두 개 이상의 군집이 한 셀에 모이면 : 합쳐짐(군집 내 모든 미생물 수의 합), 수가 가장 많은 군집의 이동 방향대로 변경
 * M시간 후의 남아있는 미생물 수의 총합
 * ------------------------
 * 겹치는 걸 어떻게 확인할거냐? 
 * -> 2차원 배열인 map에 list의 인덱스를 관리 ->리스트 삭제 시 유동적으로 매칭이 불가능
 * -> 군집 관리 리스트를 항상 정렬(r기준) -> 직전값과 비교하며 같은 위치의 것이 있는지 탐색
 * --------------------------
 * 
 */