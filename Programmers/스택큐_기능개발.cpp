#include <string>
#include <vector>
#include <queue>
#include <cmath>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    
    queue<int> day;
    for(int i=0; i<progresses.size(); i++){
        int d = ceil((100-progresses[i])/(double)speeds[i]);
        day.push(d);
    }
    
    int cnt = 0;
    int max = day.front();
    while(!day.empty()){
        int d = day.front();
        day.pop();
        if(d > max){
            answer.push_back(cnt);
            cnt = 0;
            max = d;
        }
        cnt++;
    }
    
    answer.push_back(cnt);
    
    return answer;
}