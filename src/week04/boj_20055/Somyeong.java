package week04.boj_20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Somyeong {

	static class Belt {
		int a; // 내구도
		boolean robot; // 칸위에 로봇이 올려져 있는지의 여부

		public Belt(int a,boolean robot) {
			this.a=a;
			this.robot=robot;
		}

		@Override
		public String toString() {
			return "Belt [a=" + a + ", robot=" + robot + "]";
		}
		
		
	}

	static int n, k;
	static int cnt;// belt[i]=0인 갯수
	static Belt[] belts;

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		belts = new Belt[2*n+1];
		
		st=new StringTokenizer(br.readLine());
		for(int i=1; i<=2*n; i++) {
			int x= Integer.parseInt(st.nextToken()); //내구도 
			belts[i]=new Belt(x,false);
		}
		int answer=0; //단계 
		while(true) {
			answer++; 
			//1.벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다. (끝에부터 옮겨야함)
//			Belt temp = belts[2*n];
			Belt temp =new Belt(belts[2*n].a,belts[2*n].robot);
			for(int i=2*n; i>=2; i--) {
				belts[i].a=belts[i-1].a;
				belts[i].robot=belts[i-1].robot;
				if(i>=n) //n번째 칸부터는 벨트 아래쪽이라서 로봇이 존재할수없다. 
					belts[i].robot=false;
			}
			belts[1].a=temp.a;
			belts[1].robot=temp.robot; // 이단계에서는 1번째칸에 로봇이 있을수 없음.
			
			//2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			//로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
			
			if( belts[n-1].robot==true && belts[n].a>0){// n-1번째칸에 로봇이 잇으면 로봇이 아래로 떨어짐 
				belts[n].a--;
				belts[n-1].robot=false; //앞으로 한칸이동했으니 false
			}
			for(int i=n-2; i>=1; i--) {
				if(belts[i].robot==true && belts[i+1].robot==false && belts[i+1].a>0) {
					belts[i].robot=false;
					belts[i+1].robot=true;
					belts[i+1].a--;
				}
			}
			//3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if(belts[1].a>0) {
				belts[1].robot=true;
				belts[1].a--;
			}
			
			//4.내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			if(cnt()>=k) {
				break;
			}
		}
		System.out.println(answer);
	}
	static int cnt() {
		int zero=0;
		for(int i=1; i<=2*n; i++) {
			if(belts[i].a==0)
				zero++;
		}
		return zero;
	}
}
