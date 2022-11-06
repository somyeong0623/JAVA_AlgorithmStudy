//1806. 부분합
/*
N (10 ≤ N < 100,000)
S (0 < S ≤ 100,000,000)

*/
#include <iostream>
using namespace std;

int n,s;
int answer=100000;
int arr[100005];
int temp;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cin>>n>>s;
    for(int i=0;i<n; i++){
        cin>>arr[i];
    }
    int en=0;
    int sum=arr[0];
    for(int st=0; st<n; st++){

       while(st<=en){
        //    cout<<"sum :" <<sum<<"\n";
            if(sum>=s){
                if(st==en)
                    temp=1;
                else
                    temp=en-st+1;


                if(answer>temp){
                    answer=temp;
                    // cout<<"st: "<<st<<", en: "<<en<<"\n";
                }

                break;
            }

            if(en<n-1)
                en++;
            else
                break;

           sum+=arr[en];
           
       }
       sum=sum-arr[st];

    }
    if(answer==100000)
        answer=0;
    cout<<answer<<"\n";


}
