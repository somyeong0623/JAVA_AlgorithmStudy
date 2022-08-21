package week02.boj_2078;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2078. 무한 이진트리 
/* 
 * (1,1)부터 재귀로 (a,b)에 도달할때까지의 모든경우를 찾으려 했는데 종료조건을 생각해낼수가 없었다. (메모리 초과남)
 * 노드의 왼쪽, 오른쪽 수 중 , 왼쪽이 크면 부모의 왼쪽 자식이고, 오른쪽이 크면 부모의 오른쪽 자식이다. 
 * 위의 사실을 이용해 입력받은 (a,b)로 부터 (1,1)까지 거꾸로 가면서 왼쪽,오른쪽으로 이동한 횟수를 찾았다. 
 */
public class Somyeong {
	static int a, b;
	

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		int left=0, right=0; //전역으로 했더니 시간초과 났음 .
		while (!(a == 1 && b == 1)) {
			if(a<b) {
				b-=a;
				right++;
			}else if(a>b){
				a-=b;
				left++;
			}
		}
		sb.append(left).append(" ").append(right);
		System.out.println(sb);

	}
}
