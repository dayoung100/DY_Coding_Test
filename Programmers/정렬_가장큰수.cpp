#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool sorting(int a, int b){
    string a_s = to_string(a);
    string b_s = to_string(b);
    int sum1 = stoi(a_s+b_s);
    int sum2 = stoi(b_s+a_s);
    if(sum1 > sum2) return true;
    else return false;
}

string solution(vector<int> numbers) {
    string answer = "";
    
    sort(numbers.begin(), numbers.end(), sorting);
    
    for(int n: numbers){
        string n_s = to_string(n);
        answer += n_s;
    }
    answer = numbers.front() == 0 ? "0" : answer;
    return answer;
}