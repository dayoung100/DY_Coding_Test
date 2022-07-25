#include <string>
#include <vector>
#include <map>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    
    map<string, int> m;
    for(string p:participant){
        if(m.count(p) == 0){
            m.insert(pair<string, int>(p, 1));
        }
        else m[p] += 1;
    }
    
    for(string c:completion){
        m[c] -= 1;
    }
    
    for(auto iter=m.begin();iter!=m.end();iter++){
        if(iter->second > 0){
            answer = iter->first;
            break;
        }
    }
    
    return answer;
}