package week13.programmers_NewId;

#include <string>
#include <vector>
#include <iostream>

using namespace std;

string solution(string new_id) {
    string answer = "";
    int s_size=new_id.size();
    
     //1단계: 대문자를 소문자로
    for(int i=0; i<s_size; i++){
        char cur=new_id[i];
       
        if(cur>='A' && cur<='Z')
            new_id[i]+=32;
       
    }
     cout<<"1: "<<new_id<<endl;
    
    //2단계: 특수문자 제거
    for(int i=0; i<s_size; i++){
        char cur=new_id[i]; 
        
        if((cur<'a' || cur>'z') && (cur<'0' || cur>'9') &&cur!='-' && cur!='_' && cur!='.'){
            new_id.erase(i,1); //해당인덱스부터 1개 삭제
            s_size--; //size 갱신
            i--;// 하나 제거했으니 인덱스 i 한칸 앞으로 당기기.
        }
    }
    cout<<"2: "<<new_id<<endl;
    
    //3단계: 연속. 제거
    s_size=new_id.size();
    for(int i=0; i<s_size-1;i++){
        if(new_id[i]=='.'){
            if(new_id[i+1]=='.'){
                new_id.erase(i,1);
                s_size--;
                i--;
            }
        }
    }
    cout<<"3: "<<new_id<<endl;
    
    //4단계: 처음이나 끝에위치한 .제거
    while(new_id[0]=='.'){
        new_id.erase(0,1);
    }
    while(new_id[new_id.size()-1]=='.'){
        new_id.erase(new_id.size()-1,1);
    }
     cout<<"4: "<<new_id<<endl;
    
    //5단계: 빈 문자열이면 'a'대입
    if(new_id.size()==0)
        new_id+='a';
    cout<<"5: "<<new_id<<endl;
    
    //6단계: 16자 이상이면 앞의 15개남기고 제거 + 마지막 문자가 .이면 제거
    if(new_id.size()>15)
        new_id.erase(15,new_id.size()-15);
    while(new_id[new_id.size()-1]=='.'){
        new_id.erase(new_id.size()-1,1);
    }
    cout<<"6: "<<new_id<<endl;
    
    //7단계:  길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙이기
    if(new_id.size()<=2){
        char temp=new_id[new_id.size()-1];
        while(new_id.size()<=2){
            new_id+=temp;
        }
    }
     cout<<"7: "<<new_id<<endl;
    
    answer=new_id;
    return answer;
}