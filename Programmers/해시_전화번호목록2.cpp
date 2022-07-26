#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

bool solution(vector<string> phone_book) {
    
    unordered_map<string, int> m;
    for(int p=0; p<phone_book.size(); p++) m[phone_book[p]] = 1;
    
    for(int i=0; i<phone_book.size();i++){
        for(int j=0; j<phone_book[i].size()-1; j++){
            string s = phone_book[i].substr(0,j+1);
            if(m[s]){
                return false;
            }
        }
    }
    
    return true;
}