package week02.boj_1783;
// 병든 나이트
// 입력 제한이 20억 이므로 N번 이상의 계산으로 생각하면 안된다.
// 1초에 약 1억개를 계산한다고 생각한다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jul {
	static int n,m,result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		if (n == 1) {
			result = 1;
		}else if (n == 2) {
			result = Math.min((m+1)/2,4);
		}else if (n >= 3) {
			if (m < 7) {
				result = Math.min(m, 4);
			}else {
				result = m-2;
			}
		}
		System.out.println(result);
	}
}
