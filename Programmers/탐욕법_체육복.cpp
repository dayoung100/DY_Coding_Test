#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
    int answer = 0;
    
    vector<int> student;
    for(int i=0; i<n; i++) student.push_back(1);
    
    for(int l=0; l<lost.size(); l++){
        student[lost[l]-1]--;
    }
    
    for(int r=0; r<reserve.size(); r++){
        student[reserve[r]-1]++;
    }
    
    for(int j=0; j<n; j++){
        //체육복이 없음
        if(student[j] == 0){
            if(student[j-1] > 1 && j-1>=0){
                student[j-1]--;
                student[j]++;
            }
            else if(student[j+1] > 1 && j+1 < n){
                student[j+1]--;
                student[j]++;
            }
        }
    }
    
    answer = n - count(student.begin(), student.end(), 0);
    
    return answer;
}