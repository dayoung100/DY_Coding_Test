#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    
    for(auto c: commands){
        vector<int> v;
        for(int i=c[0]-1; i<c[1]; i++){
            v.push_back(array[i]);
        }
        sort(v.begin(), v.end());
        answer.push_back(v[c[2]-1]);
    }
    
    return answer;
}