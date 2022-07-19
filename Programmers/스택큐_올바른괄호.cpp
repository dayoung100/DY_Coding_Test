#include<string>
#include <iostream>
#include <stack>

using namespace std;

bool solution(string s)
{
    bool answer = true;

    stack<int> st;
    for(int i=0; i<s.length(); i++){
        if(s[i] == '(') st.push(1);
        else{
            if(st.size() == 0) return false;
            st.pop();
        }
    }
    if(st.size() > 0) return false;

    return true;
}