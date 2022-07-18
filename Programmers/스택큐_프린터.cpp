#include <string>
#include <vector>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    
    int idx = location;
    vector<int> print;
    
    while(true){
        //max값 찾기
        int max = 0;
        for(int p: priorities){
            if(p > max) max = p;
        }
        
        int first = priorities.front();
        if(first < max){
            priorities.push_back(first);
            if(idx == 0) idx += priorities.size()-1;
        }
        else{
            print.push_back(first);
            if(idx == 0){
                answer = print.size();
                break;
            }
        }
        priorities.erase(priorities.begin(), priorities.begin()+1);
        idx--;
    }
    
    return answer;
}