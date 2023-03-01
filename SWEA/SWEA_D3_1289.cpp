#include <iostream>
#include <string>

using namespace std;

string target, now;
int ans;

bool check(int idx){
    return now[idx] == target[idx];
}

void modi_memory(int idx){
    char ch = '0';
    if(now[idx] == '0') ch = '1';
    for(int i=idx; i<target.length(); i++){
        now[i] = ch;
    }
}

int main(){
    int T;
    cin >> T;

    for(int tc=1; tc<=T; tc++){
        cin >> target; //변경 전 메모리상태
        now = "";
        for(int i=0; i<target.length(); i++) now.push_back('0');
        ans = 0;
        int index = 0;
        
        for(int i=0; i<target.size(); i++){
            if(check(i)) continue;
            else{
                modi_memory(i);
                ans++;
            }
        }
        cout << "#" << tc << " " << ans << endl;
    }
    return 0;
}