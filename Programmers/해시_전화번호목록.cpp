#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool solution(vector<string> phone_book) {
    bool answer = true;
    
    sort(phone_book.begin(), phone_book.end());
    
    for(int i=0; i<phone_book.size()-1; i++){
        string s = phone_book[i];
        string p = "";
        for(int j=0; j<s.length(); j++){
            p += phone_book[i+1][j];
        }
        if(s == p){
            answer = false;
            break;
        }
    }
    
    return answer;
}