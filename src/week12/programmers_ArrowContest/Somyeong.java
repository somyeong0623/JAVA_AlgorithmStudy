import java.util.*;

class Somyeong {
	int lion[] = new int[11];
	int N;
	int apeach[];
	boolean impossible;
	int maxDiff = 1; // 0으로해서 헤맸음
	int[] answer;
	int totalCnt;

	public int[] solution(int n, int[] info) {
		N = n;

		apeach = info;
		impossible = true; // 라이언이 우승할 방법이 없으면 true;
		int answer2[] = { -1 }; // 라이언이 우승할 방법이 없을 때 리턴할 정답 배열
		answer = new int[11];

		duplComb(0, 0);

		// System.out.println("totalCnt: "+totalCnt);
		if (impossible) // 라이언이 우승할 방법 없으면 -1만들어있는 배열이 답
			return answer2;
		else // 라이언이 우승할 방법 있으면 라이언의 점수배열이 답
			return answer;

	}

	public void duplComb(int cnt, int start) { // 중복조합
		if (cnt == N) {
			// totalCnt++;
			calculate();
			return;
		}

		for (int i = start; i < 11; i++) { // 중복조합 구현 부분
			lion[i]++;
			duplComb(cnt + 1, i);
			lion[i]--;
		}
	}

	public void calculate() { // 라이언과 어피치의 점수 합 계산해서 승부 결정
		int apeachScore = 0;
		int lionScore = 0;
		for (int i = 0; i < 11; i++) {
			if (apeach[i] > 0 && apeach[i] >= lion[i]) { // 각 과녁에서 화살 갯수가 같거나 어피치가 많이 맞혔으면 점수 어피치가 점수 획득 (어피치가 1개이상
															// 맞췄을때만 '같거나'가 성립하므로 해당 조건 필요)
				apeachScore += (10 - i);
			} else if (lion[i] > apeach[i]) { // 라이언은 더 과녁에 더 많이 맞췄을때만 점수 획득
				lionScore += (10 - i);
			}
		}
		if (lionScore - apeachScore > maxDiff) { // 이전 차이보다 클 경우
			maxDiff = lionScore - apeachScore;
			if (impossible)
				impossible = false; // 라이언이 이길 방법이 있다는 것을 체크
			answer = lion.clone();
		} else if (lionScore - apeachScore == maxDiff) { // 이전 점수값 차이와 현재 확인하는 점수값 차이가 같을 경우
			if (impossible)
				impossible = false;

			boolean flag = false;

			// 낮은 점수를 더 많이 맞힌 경우가 정답배열이 된다.
			for (int i = 10; i >= 0; i--) {
				if (lion[i] < answer[i])
					break;

				if (lion[i] > answer[i]) {
					flag = true;
					break;
				}
			}
			if (flag)
				answer = lion.clone(); // 배열 복사해서 정답배열에 저장

		}
	}

}