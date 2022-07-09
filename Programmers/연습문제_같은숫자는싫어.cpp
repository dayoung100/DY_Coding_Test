#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    vector<int> answer;

    int n = -1;
    
    for (int a : arr){
        if(n != a){
            n = a;
            answer.push_back(n);
        }
    }

    return answer;
}