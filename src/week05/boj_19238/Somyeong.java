package week05.boj_19238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

//19238. 스타트 택시 
public class Somyeong {
	
	static class Info implements Comparable<Info> {
		int pr, pc; //person_r, person_c
		int ar,ac;//arrive_r, arrive_c
		int dist;
		public Info(int pr, int pc,int ar, int ac) {
			this.pr=pr;
			this.pc=pc;
			this.ar=ar;
			this.ac=ac;
			this.dist=0; // 처음 생성시 거리는 0으로 초기화 
		}
		
		//거리순으로 오름차순정렬, 행 오름차순 정렬, 열 오름차순 정렬 
		@Override
		public int compareTo(Info i) {
			if(this.dist==i.dist) {
				if(this.pr==i.pr) {
					return this.pc-i.pc;
				}
				return this.pr-i.pr;
			}
			return this.dist-i.dist;
		}
		
	}
	static class Point{
		int r, c;
		public Point(int r,int c) {
			this.r=r;
			this.c=c;
		}
	}
	static int N,M,oil;
	static int tr,tc;//택시의 좌표 
	static int arr[][];
	static ArrayList<Info> personList;
	static int dr[]= {-1,1,0,0};
	static int dc[]= {0,0,-1,1};
	static int dist[][];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		oil=Integer.parseInt(st.nextToken());
	
		
		arr=new int[N][N];
		dist=new int[N][N];
		personList=new ArrayList<Info>();
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		st=new StringTokenizer(br.readLine());
		tr=Integer.parseInt(st.nextToken());
		tc=Integer.parseInt(st.nextToken());
		tr--; tc--; //인덱스 0부터라서 
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int person_r=Integer.parseInt(st.nextToken());
			int person_c=Integer.parseInt(st.nextToken());
			int arrive_r=Integer.parseInt(st.nextToken());
			int arrive_c=Integer.parseInt(st.nextToken());
			//M개의 손님정보 personList에 담기 
			personList.add(new Info(person_r-1,person_c-1,arrive_r-1,arrive_c-1));//인덱스 0부터라서 
		}
		
		for(int i=0; i<M; i++) { //사람수 만큼 반복
			initDist(); // dist[][] 배열 -1로 초기화 
			bfs(tr,tc); //택시의 위치 (r,c)를 기준으로 각 손님의 거리까지의 최단경로 구하기
			
			//제일 가까운 손님 구하기
			for(int k=0; k<personList.size(); k++) {
				Info cur = personList.get(k);
				cur.dist=dist[cur.pr][cur.pc];
			} 
			Collections.sort(personList); //거리 오름차순, 행 오름차순, 열 오름차순 정렬 
			
			Info target = personList.get(0); //우선순위 가장높은 손님 
			if(target.dist==-1) { // 우선순위 1번손님이 갈 수 없는 위치에 있다면 -1출력하고 종료 
				System.out.println(-1);
				return;
			}
			
			oil=oil-target.dist;
			
			initDist();
			bfs(target.pr,target.pc); // 타겟손님위치 기준으로 bfs 실행 ( 타겟손님 도착위치 까지의 거리 구하기 위해)
			oil-=dist[target.ar][target.ac]; // 도착하는데 걸린 만큼 빼주기 
			
			//택시위치 -> 타겟손님 위치 -> 타겟손님 도착위치 순서로 갔고, 이때  연료가 0미만이 될 경우 -1출력하고 끝내기 
			if(oil<0) { //여기서 oil<=0 으로해서 틀렸음 연료가 0 미만일때부터 연료 바닥난걸로 취급하는 듯 
				System.out.println(-1);
				return;
			}
			oil+=dist[target.ar][target.ac]*2;
			personList.remove(0); // 타겟손님 제거 
			tr=target.ar; //택시좌표를 타겟손님의 도착좌표로 갱신 
			tc=target.ac;
			
		}
		System.out.println(oil);
	}
	public static void bfs(int r, int c) { //(r,c)기준으로 dist[][] 구하는 bfs
		dist[r][c]=0;
		Queue<Point> q= new ArrayDeque<Point>();
		q.offer(new Point(r,c));
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for(int d=0; d<4; d++) {
				int nr=cur.r+dr[d];
				int nc=cur.c+dc[d];
				if(nr>=0 && nc>=0 && nr<N && nc<N&& arr[nr][nc]==0&&dist[nr][nc]==-1) {
					dist[nr][nc]=dist[cur.r][cur.c]+1;
					q.offer(new Point(nr,nc));
				}
			}
		}
	}
	
	public static void initDist() { //dist[][] 배열 -1로 초기화 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				dist[i][j]=-1;
			}
		}
	}
}
