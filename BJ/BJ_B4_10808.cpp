#include <bits/stdc++.h>
using namespace std;

//baekjoon B4 10808 알파벳 개수
int main(){
    string s;
    cin >> s;
    int alpha[26] = {0};

    for(char c : s) alpha[c - 97]++;

    for(int i : alpha) cout << i << " ";
}