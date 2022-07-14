#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(string s) {
    int answer = 0;
    
    vector<string> num_list = {"zero","one","two","three","four","five","six","seven","eight","nine"};
    string st_num = "";
    int num = 0;
    
    for(int i = 0; i < s.length(); i++){
        if(isdigit(s[i]) == 0) {
            st_num += s[i];
            
            auto it = find(num_list.begin(), num_list.end(), st_num);
            if(it != num_list.end()){
                num = it - num_list.begin();
            }
            else continue;
        }
        else{
            num = s[i]-48;
        }
        
        if(answer == 0) answer += num;
        else answer = answer*10 + num;
        
        st_num = "";
    }
    
    return answer;
}