// Project1.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <bitset>
using namespace std;
bitset<33554432> memory;

int main()
{
	// 코테 기본 설정
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int num;
	while (cin >> num) {
		if (!memory[num]) {
			memory[num] = 1;
			cout << num << " ";
		}
	}

	return 0;
}
