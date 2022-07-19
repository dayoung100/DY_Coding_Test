#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> citations) {
    int answer = 0;
    
    int h=0, p=0;
    sort(citations.begin(), citations.end(), greater<>());
    
    for(h=citations[0]; h>=0; h--){
        if(find(citations.begin(), citations.end(), h) != citations.end()){
            p = find(citations.begin(), citations.end(), h) - citations.begin()+1;
        }
        if(h <= p){
            break;
        }
    }
    
    if(citations.back() > h) h = citations.size();
    
    answer = h;
    
    return answer;
}