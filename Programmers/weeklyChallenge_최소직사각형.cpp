#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> sizes) {
    int answer = 0;
    
    int max_x = 0;
    int max_y = 0;
    
    for(int i=0; i<sizes.size();i++){
        int bigger = max(sizes[i][0],sizes[i][1]);
        if(max_x<bigger){
            max_x = bigger;
        } 
    }
    
    for(int j=0;j<sizes.size();j++){
        int smaller = min(sizes[j][0], sizes[j][1]);
        if(max_y<smaller){
            max_y = smaller;
        }
    }
    
    answer = max_x * max_y;
    
    return answer;
}