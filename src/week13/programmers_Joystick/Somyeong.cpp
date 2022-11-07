#include <string>
 
using namespace std;
 
int solution(string name) {
    int answer = 0, i = 0;
    //A로 구성된 화면
    string temp(name.length(), 'A');
    while (true) {
        //바꾸고 있는 화면에 반영하고
        temp[i] = name[i];
        //둘중에 적은걸로 결과에 추가하기
        name[i] - 'A' > 'Z' + 1 - name[i] ? answer += 'Z' + 1 - name[i] : answer += name[i] - 'A';
        //바꾼후 문자열이 동일하다면 계산 종료
        if (temp == name)    break;
        //왼쪽으로 갈지 오른쪽으로 갈지 계산하기 
        for (int move = 1; move < name.length(); move++) {
            //오른쪽 이동이 빠르다면 오른쪽으로 이동하고 이동횟수 반영
            if (name[(i + move) % name.length()] != temp[(i + move) % name.length()]) {
                i = (i + move) % name.length();
                answer += move;
                break;
            }
            //왼쪽으로 이동이 빠르다면 왼쪽으로 이동하고 이동횟수 반영
            else if (name[(i + name.length() - move) % name.length()] 
                != temp[(i + name.length() - move) % name.length()]) {
                i = (i + name.length() - move) % name.length();
                answer += move;
                break;
            }
        }
    }
    return answer;
}
